/*
    Nome: WfCompletaDettaglioOrdine
    Descrizione:
        la seguente procedura si preoccupa di completare i dettagli ordine in base alla giacenza in magazzino e alla filiale di riferimento
        Il trigger scatta quando il back-end conferma l'ordine.
*/

create or replace trigger WfCompletaDettaglioOrdine
    before update of Stato on OrdineCliente
    for each row
    when ( new.Stato = 'Confermato' )
declare
    vIdFiliale integer;
    vIdMagazzino integer;

    vFlagDistribuita char(1) := 'N'; -- Y se occorre gestire la completezza del dettaglio in più magazzini, N altrimenti
    vQuantitaSum integer;
    vSetCompletato char(1) := 'Y';
    fStatoOrdineInserito integer;
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
            -- cerca un magazzino con la quantità necessaria avente la quantità disponibile minima
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
            vFlagDistribuita := 'Y'; -- un singolo magazzino con la quantità necessaria non esiste, forse la quantità richiesta è distribuita in più magazzini
        end;

        if vFlagDistribuita = 'N' then
            -- aggiorna la quantità prenotata in magazzino
            update MERCESTOCCATA set QUANTITAPRENOTATA = QUANTITAPRENOTATA + dettaglio.quantitaOrdinata where ID = vIdMagazzino and IDPRODOTTO = dettaglio.IDPRODOTTO;

            insert into DettaglioOrdineMagazzino (IdDettaglioOrdine, IdMagazzino) values (dettaglio.IdDettaglio, vIdMagazzino);

            /* aggiorna il dettaglio ordine con il flag che indica che la quantità richiesta è disponibile.
               Tale flag viene utilizzato per la gestione di casistiche di errore dove possono pervenire prodotti ordinati
               senza disponibilità a magazzino. Tali errori a volte sono generati o dal sistema Back-end o da errato aggiornamento
               delle disponibilità a magazzino da parte dell'operatore.
             */
            update DettaglioOrdine set FlagQuantitaDisponibile = 'Y', FLAGCOMPLETATO = 'Y'
                                     --, IdFilialeRiferimento = vIdFiliale, IdMagazzinoRiferimento = vIdMagazzino
            where Id = dettaglio.IdDettaglio;

            begin
                insert into StatoOrdineClienteFiliale (IdOrdineCliente, IdFiliale, Stato) values (:new.Id, vIdFiliale, 'Completato');
            exception when dup_val_on_index then -- elenco named system exceptions: https://www.techonthenet.com/oracle/exceptions/named_system.php
                -- la filiale è già stata aggiunta
                null;
            end;
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
                        and (ms.quantitareale - ms.quantitaprenotata) > 0 -- escludo quantità completamente prenotate
                    order by (ms.quantitareale - ms.quantitaprenotata))
                loop -- ciclo per ogni magazzino che ha il prodotto richiesto
                    --tengo conto della quantità dei magazzini in modo da fermarmi in caso di copertura della richiesta
                    vQuantitaSum := vQuantitaSum + dep.quantitaDisponibile;

                    begin
                        insert into StatoOrdineClienteFiliale (IdOrdineCliente, IdFiliale, Stato) values (:new.Id, vIdFiliale, 'Completato');
                    exception when dup_val_on_index then -- elenco named system exceptions: https://www.techonthenet.com/oracle/exceptions/named_system.php
                        -- la filiale è già stata aggiunta
                        null;
                    end;
                    if vQuantitaSum <= dettaglio.quantitaOrdinata then
                        -- diamo fondo a tutto le scorte di quel prodotto in quel magazzino
                        -- aggiorno la quantitaprenotata con quella reale
                        update MERCESTOCCATA set QUANTITAPRENOTATA = QUANTITAREALE where ID = dep.ID;
                    else
                        -- diamo fondo a parte delle scorte di quel prodotto in quel magazzino
                        update MERCESTOCCATA set QUANTITAPRENOTATA = QUANTITAPRENOTATA + dettaglio.quantitaOrdinata where ID = dep.ID;
                    end if;

                    -- inserisco il dettaglio ordine magazzino
                    insert into DettaglioOrdineMagazzino (IdDettaglioOrdine, IdMagazzino) values (dettaglio.IdDettaglio, dep.idmagazzino);

                    -- esco dal ciclo se ho coperto la richiesta, se la quantità è uguale sicuramente ho gestito tutto,
                    -- se è maggiore sicuramente ho gestito il parziale necessario
                    exit when vQuantitaSum >= dettaglio.quantitaOrdinata;
                end loop;

                update DettaglioOrdine set FlagQuantitaDisponibile = 'Y', FLAGCOMPLETATO = 'Y' where Id = dettaglio.IdDettaglio;

                begin
                    insert into StatoOrdineClienteFiliale (IdOrdineCliente, IdFiliale, Stato) values (:new.Id, vIdFiliale, 'Completato');
                exception when dup_val_on_index then -- elenco named system exceptions: https://www.techonthenet.com/oracle/exceptions/named_system.php
                    -- la filiale è già stata aggiunta
                    null;
                end;
            else
                update DettaglioOrdine set FlagQuantitaDisponibile = 'N' where Id = dettaglio.IdDettaglio;
                vSetCompletato := 'N';
            end if;

            vFlagDistribuita := 'N';
        end if;
    end loop; -- fine ciclo dettagli ordine non completati

    if vSetCompletato = 'Y' then
        :new.Stato := 'Completato';
    end if;
end;