/*
     Nome: GeneraPacchiByIdOrdinePackaging
     Descrizione:
        procedura che genera i pacchi per un ordine di lavoro di tipo 'Packaging', secondo i raggruppamenti scelti
        tramite il campo CodicePropostaPacco di PackagingDetails
 */
create or replace procedure GeneraPacchiByIdOrdinePackaging(
    pIdOrdinePackaging in integer,
    pIdMagazzino in integer,
    pIdIndirizzoSpedizione in integer,
    pIdSpedizione in integer)
is
    vCurrCodicePropostaPacco PackagingDetails.CodicePropostaPacco%type;

    cursor cPackagingDetails(pIdOrdine integer) is
        select
            --pd.Pericolosita,
            pd.CodicePropostaPacco,
            do.id as IdDettaglioOrdine,
            do.Quantita as quantita_ordinata,
            c2.PESO as peso_prodotto
        from
            PackagingDetails pd
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
                recPacco.IdMagazzino := pIdMagazzino;
                recPacco.IdIndirizzoDestinazione := pIdIndirizzoSpedizione;
                recPacco.IdOrdineLavoroOrigine := pIdOrdinePackaging;
                recPacco.IdSpedizione := pIdSpedizione;
                recPacco.Peso := 0; --sarà aggiornato in seguito

                loop
                    recPacco.CodicePacco := dbms_random.string('X',20);
                    select count(*) into vCnt from Pacco where CodicePacco = recPacco.CodicePacco;

                    exit when vCnt = 0;
                end loop;

                insert into Pacco values recPacco returning Id into vIdPacco;
                vCurrCodicePropostaPacco := dettaglio.CodicePropostaPacco;
            end if;

            -- aggiungo il dettaglio ordine al pacco
            insert into PaccoDettaglioOrdine (IdPacco, IdDettaglioOrdine) values (vIdPacco, dettaglio.IdDettaglioOrdine);

            vPeso := vPeso + (dettaglio.quantita_ordinata * dettaglio.peso_prodotto);
    end loop;

    --aggiorno l'ultimo record pacco elaborato
    update Pacco set Peso = vPeso where Id = vIdPacco;
exception
    when others then
        dbms_output.put_line('Errore in GeneraPacchiByIdOrdinePackaging: ' || sqlerrm);
end;