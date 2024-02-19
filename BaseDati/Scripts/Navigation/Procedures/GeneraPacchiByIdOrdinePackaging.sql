/*
     Nome: GeneraPacchiByIdOrdinePackaging
     Descrizione:
        procedura che genera i pacchi per un ordine di lavoro di tipo 'Packaging', secondo i raggruppamenti scelti
        tramite il campo CodicePropostaPacco di PackagingDetails
 */
create or replace procedure GeneraPacchiByIdOrdinePackaging(pIdOrdinePackaging in integer)
is
    vCurrCodicePropostaPacco PackagingDetails.CodicePropostaPacco%type;

    cursor cPackagingDetails(pIdOrdine integer) is
        select
            --pd.Pericolosita,
            olp.IDMAGAZZINO,
            olp.IDSPEDIZIONE,
            olp.IDINDIRIZZOSPEDIZIONE,
            pd.CodicePropostaPacco,
            do.id as IdDettaglioOrdine,
            do.Quantita as quantita_ordinata,
            c2.PESO as pesoUnitario
        from
            ORDINEDILAVOROPACKAGING olp
            join PackagingDetails pd on olp.ID = pd.IDORDINEDILAVOROPACKAGING
            join DETTAGLIOORDINE do on pd.IdDettaglioOrdine = do.Id
            join CATALOGOPRODOTTI C2 on C2.ID = do.IDPRODOTTO
            where pd.IDORDINEDILAVOROPACKAGING = pIdOrdine;

    recPacco pacco%rowtype;
    vPeso pacco.peso%type;
    vIdPacco pacco.id%type;
    vCnt integer;
begin
    vCurrCodicePropostaPacco := null;
    recPacco := null;
    vPeso := 0;
    vIdPacco := null;

    for dettaglio in cPackagingDetails(pIdOrdinePackaging) loop

            if vCurrCodicePropostaPacco is null or dettaglio.CodicePropostaPacco <> vCurrCodicePropostaPacco then

                if vIdPacco is not null then
                    update Pacco set Peso = vPeso where Id = vIdPacco;
                end if;

                vPeso := 0;

                recPacco := null;
                recPacco.IdMagazzino := dettaglio.IDMAGAZZINO;
                recPacco.IdIndirizzoDestinazione := dettaglio.IDINDIRIZZOSPEDIZIONE;
                recPacco.IdOrdineLavoroOrigine := pIdOrdinePackaging;
                recPacco.IdSpedizione := dettaglio.IDSPEDIZIONE;
                recPacco.Peso := 0; --sarà aggiornato in seguito

                loop
                    recPacco.CodicePacco := dbms_random.string('X',20);
                    select count(*) into vCnt from Pacco where CodicePacco = recPacco.CodicePacco;

                    exit when vCnt = 0;
                end loop;

                insert into Pacco values recPacco returning Id into vIdPacco;
                recPacco.ID := vIdPacco;
                vCurrCodicePropostaPacco := dettaglio.CodicePropostaPacco;
                dbms_output.put_line('DEBUG - nuovo pacco con codice ' || recPacco.CodicePacco || ' e id ' || vIdPacco);
            end if;

            -- aggiungo il dettaglio ordine al pacco
            insert into PaccoDettaglioOrdine (IdPacco, IdDettaglioOrdine) values (vIdPacco, dettaglio.IdDettaglioOrdine);

            dbms_output.put_line('DEBUG - vPeso prima dell''aggiornamento = ' || to_char(vPeso, 'FM90.99'));
            vPeso := vPeso + (dettaglio.quantita_ordinata * dettaglio.pesoUnitario);
            dbms_output.put_line('DEBUG - aggiorno vPeso = ' || to_char(vPeso, 'FM90.99') || ' (quantita_ordinata = ' || dettaglio.quantita_ordinata || ' * pesoUnitario = ' || to_char(dettaglio.pesoUnitario, 'FM90.99') || ') = ' || to_char(dettaglio.quantita_ordinata * dettaglio.pesoUnitario, 'FM90.99'));
    end loop;

    --aggiorno l'ultimo record pacco elaborato
    update Pacco set Peso = vPeso where Id = vIdPacco;
    dbms_output.put_line('DEBUG - aggiorno pacco con id ' || vIdPacco || ' con vPeso = ' || to_char(vPeso, 'FM90.99'));

    if recPacco.ID is not null then
        -- check per verificare se sono l'ultimo pacco da generare
        vCnt := PacchiTuttiGenerati(recPacco.IDSPEDIZIONE);
        dbms_output.put_line('DEBUG - vCnt = ' || vCnt);
        if vCnt = 1 then
            dbms_output.put_line('DEBUG - aggiorno lo stato dell''ordine di lavoro a ''LavorataPackaging''');
            --se lo sono, aggiorno lo stato dell'ordine di lavoro a 'LavorataPackaging'
            update Spedizione
            set Stato = 'LavorataPackaging'
            where Id = recPacco.IDSPEDIZIONE;
        end if;
    else
        dbms_output.put_line('DEBUG - Errore in GeneraPacchiByIdOrdinePackaging: nessun pacco generato');
    end if;
exception
    when others then
        dbms_output.put_line('Errore in GeneraPacchiByIdOrdinePackaging: ' || sqlerrm);
end;