/*
     Nome: GeneraPacchiByIdOrdinePackaging
     Descrizione:
        procedura che genera i pacchi per un ordine di lavoro di tipo 'Packaging', secondo i raggruppamenti scelti
        tramite il campo CodicePropostaPacco di PackagingDetails
 */
create or replace procedure GeneraPacchiByIdOrdinePackaging(IdOrdinePackaging in integer)
as
    vCurrCodicePropostaPacco PackagingDetails.CodicePropostaPacco%type;

    cursor cPackagingDetails(pIdOrdine integer) is
        select
            pd.Pericolosita,
            pd.CodicePropostaPacco,
            o.IdMagazzino,
            o.IdIndirizzoSpedizione,
            o.IdSpedizione,
            do.id as IdDettaglioOrdine,
            do.Quantita as quantita_ordinata,
            c2.PESO as peso_prodotto
        from
            ORDINEDILAVOROPACKAGING o
            join PackagingDetails pd on pd.IDORDINEDILAVOROPACKAGING = o.ID
            join DETTAGLIOORDINE do on pd.IdDettaglioOrdine = do.Id
            join CATALOGOPRODOTTI C2 on C2.ID = do.IDPRODOTTO
            where o.Id = pIdOrdine;

    ordinePackaging cPackagingDetails%rowtype;

    recPacco pacco%rowtype;
    vPeso pacco.peso%type;
    vIdPacco pacco.id%type;
    vCnt integer;
begin
    vCurrCodicePropostaPacco := null;
    recPacco := null;
    vPeso := 0;

    for dettaglio in cPackagingDetails(IdOrdinePackaging) loop

            if vCurrCodicePropostaPacco is null or dettaglio.CodicePropostaPacco <> vCurrCodicePropostaPacco then

                if recPacco.Id is not null then
                    update Pacco set Peso = vPeso where Id = vIdPacco;
                end if;

                vPeso := 0;

                recPacco := null;
                recPacco.IdMagazzino := ordinePackaging.IdMagazzino;
                recPacco.IdIndirizzoDestinazione := ordinePackaging.IdIndirizzoSpedizione;
                recPacco.IdOrdineLavoroOrigine := IdOrdinePackaging;
                recPacco.IdSpedizione := ordinePackaging.IdSpedizione;
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

    commit;
exception
    when others then
        rollback;
        dbms_output.put_line('Errore in GeneraPacchiByIdOrdinePackaging: ' || sqlerrm);
end;