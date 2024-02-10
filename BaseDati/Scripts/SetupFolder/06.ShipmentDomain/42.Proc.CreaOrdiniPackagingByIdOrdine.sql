/*
    Nome procedure: CreaOrdiniPackagingByIdOrdine
    Descrizione: crea gli ordini di packaging per l'ordine IdOrdine ricevuto in input
*/
create or replace procedure UNINADEV.CreaOrdiniPackagingByIdOrdine(pIdOrdine in integer) is

    cursor cAnalisiOrdine(curIdOrdine ORDINECLIENTE.id%type) is
        select
            oc.id as idOrdine,
            nvl(oc.IDINDIRIZZOSPEDIZIONE, oc.IDINDIRIZZOFATTURAZIONE) as idIndirizzoSpedizione,
            D.Id as IdDettaglio, D.IDFILIALERIFERIMENTO, D.IDMAGAZZINORIFERIMENTO,
            c2.id as idProdotto,
            c2.PERICOLOSITA,
            D.QUANTITA as QuantitaOrdinata,
            M2.Id as idMerceStoccata,
            M2.QUANTITAREALE as quantitaAMagazzino,
            M2.QUANTITAPRENOTATA as quantitaPrenotata,
            c2.PESO as Peso_Kg,
            sum(C2.PESO) over (partition by oc.ID, D.IDMAGAZZINORIFERIMENTO) as PesoTotaleMagazzino
        from
            ORDINECLIENTE oc
            join UNINADEV.DETTAGLIOORDINE D on oc.ID = D.IDORDINE
            join UNINADEV.CATALOGOPRODOTTI C2 on C2.ID = D.IDPRODOTTO
            join UNINADEV.MERCESTOCCATA M2 on M2.IDMAGAZZINO = D.IDMAGAZZINORIFERIMENTO and M2.IDPRODOTTO = C2.ID
        where
            oc.ID = curIdOrdine
        order by D.IDFILIALERIFERIMENTO, D.IDMAGAZZINORIFERIMENTO;

    rec cAnalisiOrdine%rowtype;

    idPackaging integer;
    vIdSpedizione spedizione.id%type;
    recPackagingDetails PackagingDetails%rowtype;
    currIdMagazzino integer;
    currPeso integer;
    currPropostaPacco integer;
    statoOrdine OrdineCliente.Stato%type;
    cntPackaging integer;
begin
    -- verifico che l'ordine sia in stato Completato

    select Stato into statoOrdine from OrdineCliente where Id = pIdOrdine;

    if statoOrdine <> 'Completato' then
        raise_application_error(-20001, 'L''ordine non è in stato Completato');
    end if;

    -- verifico che non esistano già ordini di packaging per l'ordine

    select count(*) into cntPackaging
        from
            DETTAGLIOORDINE do
                join PACKAGINGDETAILS pd on do.Id = pd.IdDettaglioOrdine
                join ORDINEDILAVOROPACKAGING olp on pd.IdOrdineDiLavoroPackaging = olp.Id
        where
            do.IdOrdine = pIdOrdine;

    if cntPackaging > 0 then
        raise_application_error(-20002, 'Esistono già ordini di packaging per l''ordine ' || pIdOrdine);
    end if;

    -- mi ricavo per ogni dettaglio ordine le info necessarie per creare l'ordine di lavoro di packaging

    open cAnalisiOrdine(pIdOrdine);
    currIdMagazzino := null;
    currPeso := 0;
    currPropostaPacco := 1;

    loop
        fetch cAnalisiOrdine into rec;
        exit when cAnalisiOrdine%notfound;

        currPeso := currPeso + rec.Peso_Kg;
        if currIdMagazzino is null or currIdMagazzino <> rec.IDMAGAZZINORIFERIMENTO then

            begin
                select Id into vIdSpedizione
                from Spedizione where IdOrdineCliente = pIdOrdine;
            exception
                when no_data_found then
                    raise_application_error(-20003, 'Non esiste una spedizione per l''ordine ' || pIdOrdine);
            end;

            insert into OrdineDiLavoroPackaging (IdFiliale, IdMagazzino, IdSpedizione, IdIndirizzoSpedizione)
            values (rec.IDFILIALERIFERIMENTO, rec.IDMAGAZZINORIFERIMENTO, vIdSpedizione, rec.idIndirizzoSpedizione) returning id into idPackaging;

            currIdMagazzino := rec.IDMAGAZZINORIFERIMENTO;
            currPropostaPacco := currPropostaPacco + 1;
        else
            if currPeso > 3 then -- oltre 3kg serve un nuovo pacco, al di sotto di 3kg si può continuare a imballare nello stesso pacco
                currPropostaPacco := currPropostaPacco + 1;
                currPeso := 0;
            end if;
        end if;

        recPackagingDetails := null;
        recPackagingDetails.IdOrdineDiLavoroPackaging := idPackaging;
        recPackagingDetails.IdDettaglioOrdine := rec.IdDettaglio;
        recPackagingDetails.IdMerceStoccataRiferiemento := rec.idMerceStoccata;
        recPackagingDetails.Pericolosita := rec.Pericolosita;
        recPackagingDetails.CodicePropostaPacco := currPropostaPacco;

        insert into PackagingDetails values recPackagingDetails;

        dbms_output.put_line('Aggiorno le quantità prenotate in magazzino');

        update MerceStoccata
        set QuantitaPrenotata = QuantitaPrenotata + rec.QuantitaOrdinata
        where Id = rec.idMerceStoccata;

    end loop;

    close cAnalisiOrdine;

    commit;

    dbms_output.PUT_LINE('Ordini di packaging creati con successo per l''ordine ' || pIdOrdine);
exception when others then
    rollback;
    raise_application_error(-20004, 'Errore durante la creazione degli ordini di packaging per l''ordine ' || pIdOrdine || ': ' || sqlerrm);
end CreaOrdiniPackagingByIdOrdine;