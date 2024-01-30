/*
    Nome: WorkerCompletaDettaglioOrdine
    Descrizione:
        la seguente procedura si preoccupa di completare i dettagli ordine in base alla giacenza in magazzino e alla filiale di riferimento
        Il trigger scatta quando il back-end conferma l'ordine.
 */
create or replace trigger WorkerCompletaDettaglioOrdine
    before update of Stato on OrdineCliente
    for each row
    when ( new.Stato = 'Confermato' )
declare
    vIdFiliale integer;
    vIdMagazzino integer;

    vFlagDistribuita char(1) := 'N'; -- Y se occorre gestire la completezza del dettaglio in più magazzini, N altrimenti
    vQuantitaSum integer;
    vSetCompletato char(1) := 'Y';
begin
    vQuantitaSum := 0;
    vIdFiliale := null;

    for dettaglio in (
        select
            dett.IDPRODOTTO, dett.id as IdDettaglio, dett.quantita as quantitaOrdinata
        from
            DettaglioOrdine dett
        where
            dett.IdOrdine = :new.Id and dett.FlagCompletato = 'N')
    loop
        begin
            select
                m.IDFILIALE, m.id as IdMagazzino
            into
                vIdFiliale, vIdMagazzino
            from
                MERCESTOCCATA ms
                join MAGAZZINO m on ms.IDMAGAZZINO = m.ID
            where
                ms.idprodotto = dettaglio.idprodotto
                and (ms.quantitareale - ms.quantitaprenotata) >= dettaglio.quantitaOrdinata
                and (ms.quantitareale - ms.quantitaprenotata) = (
                    select min(quantitareale - quantitaprenotata)
                    from mercestoccata
                    where idprodotto = dettaglio.idprodotto and (quantitareale - quantitaprenotata) >= dettaglio.quantitaordinata
                    )
                and rownum < 2; -- potrebbero esserci più magazzini con la stessa quantità minima disponibile
        exception when no_data_found then
            vFlagDistribuita := 'Y'; -- un singolo magazzino con la quantità necessaria non esiste, forse è distribuita in più magazzini
        end;

        if vFlagDistribuita = 'N' then
            -- inserisce in LocationDettaglioOrdine la filiale e il magazzino di riferimento dal quale prelevare la merce
            insert into LocationDettaglioOrdine (IdDettaglioOrdine, IdFilialeRiferimento, IdMagazzinoRiferimento)
            values (dettaglio.IdDettaglio, vIdFiliale, vIdMagazzino);

            -- inserisce in StatoOrdineClienteFiliale lo stato dell'ordine per la filiale di riferimento
            insert into StatoOrdineClienteFiliale (IdOrdineCliente, IdFiliale, Stato) values (:new.Id, vIdFiliale, 'Completato');

            -- aggiorna la quantità prenotata in magazzino
            update MERCESTOCCATA set QUANTITAPRENOTATA = QUANTITAPRENOTATA + dettaglio.quantitaOrdinata where ID = vIdMagazzino and IDPRODOTTO = dettaglio.IDPRODOTTO;

            /* aggiorna il dettaglio ordine con il flag che indica che la quantità richiesta è disponibile.
               Tale flag viene utilizzato per la gestione di casistiche di errore dove possono pervenire prodotti ordinati
               senza disponibilità a magazzino. Tali errori a volte sono generati o dal sistema Back-end o da errato aggiornamento
               delle disponibilità a magazzino da parte dell'operatore.
             */
            update DettaglioOrdine set FlagQuantitaDisponibile = 'Y', FLAGCOMPLETATO = 'Y' where Id = dettaglio.IdDettaglio;
        else
            /*
                Gestione di quantità disponibile in più magazzini:
                mi ricavo il totale delle quantità disponibili nei vari magazzini, verifico se c'è copertura della richiesta.
             */
            select sum(quantitareale - quantitaprenotata) into vQuantitaSum
            from MERCESTOCCATA
            where IDPRODOTTO = dettaglio.IDPRODOTTO;

            if nvl(vQuantitaSum, 0) >= dettaglio.quantitaOrdinata then
                -- la quantità richiesta è disponibile in più magazzini
                vQuantitaSum := 0;

                -- Ricavo i magazzini che hanno il prodotto richiesto
                for dep in (
                    select
                        ms.id, m.idfiliale, ms.idmagazzino, (ms.quantitareale - ms.quantitaprenotata) as quantitaDisponibile
                    from
                        MERCESTOCCATA ms
                        join MAGAZZINO m on ms.IDMAGAZZINO = m.ID
                    where
                        ms.IDPRODOTTO = dettaglio.IDPRODOTTO
                        and (ms.quantitareale - ms.quantitaprenotata) > 0
                    order by m.idfiliale, (ms.quantitareale - ms.quantitaprenotata))
                loop
                    vQuantitaSum := vQuantitaSum + dep.quantitaDisponibile;

                    if dettaglio.quantitaOrdinata >= vQuantitaSum then
                        --diamo fondo a tutto le scorte di quel prodotto in quel magazzino

                        -- inserisce in LocationDettaglioOrdine la filiale e il magazzino di riferimento dal quale prelevare la merce
                        insert into LocationDettaglioOrdine (IdDettaglioOrdine, IdFilialeRiferimento, IdMagazzinoRiferimento)
                        values (dettaglio.IdDettaglio, dep.IDFILIALE, dep.IDMAGAZZINO);

                        -- aggiorno la quantitaprenotata con quella reale
                        update MERCESTOCCATA set QUANTITAPRENOTATA = QUANTITAREALE where ID = dep.ID;
                    else
                        vQuantitaSum := vQuantitaSum - dep.quantitaDisponibile;

                        insert into LocationDettaglioOrdine (IdDettaglioOrdine, IdFilialeRiferimento, IdMagazzinoRiferimento)
                        values (dettaglio.IdDettaglio, dep.IDFILIALE, dep.IDMAGAZZINO);

                        update MERCESTOCCATA set QUANTITAPRENOTATA = QUANTITAPRENOTATA + (dettaglio.quantitaOrdinata - vQuantitaSum) where ID = dep.ID;
                    end if;
                end loop;

                update DettaglioOrdine set FlagQuantitaDisponibile = 'Y', FLAGCOMPLETATO = 'Y' where Id = dettaglio.IdDettaglio;
            else
                update DettaglioOrdine set FlagQuantitaDisponibile = 'N' where Id = dettaglio.IdDettaglio;
                vSetCompletato := 'N';
            end if;

            vFlagDistribuita := 'N';
        end if;
    end loop;

    if vSetCompletato = 'Y' then
        :new.Stato := 'Completato';
    end if;
end;