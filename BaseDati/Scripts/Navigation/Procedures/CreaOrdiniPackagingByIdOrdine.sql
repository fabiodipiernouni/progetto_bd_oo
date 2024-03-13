/*
    Nome procedure: CreaOrdiniPackagingByIdOrdine
    Descrizione: crea gli ordini di packaging per l'ordine IdOrdine ricevuto in input
*/
create or replace procedure CreaOrdiniPackagingByIdOrdine(pIdOrdine in integer, pIdFiliale integer) is

    cursor cAnalisiOrdine(curIdOrdine ORDINECLIENTE.id%type) is
        select
            oc.id as idOrdine,
            nvl(oc.IDINDIRIZZOSPEDIZIONE, oc.IDINDIRIZZOFATTURAZIONE) as idIndirizzoSpedizione,
            D.Id as IdDettaglio,  M.IDFILIALE as IDFILIALERIFERIMENTO, DM.IDMAGAZZINO as IDMAGAZZINORIFERIMENTO,
            c2.id as idProdotto,
            c2.PERICOLOSITA,
            D.QUANTITA as QuantitaOrdinata,
            M2.Id as idMerceStoccata,
            M2.QUANTITAREALE as quantitaAMagazzino,
            M2.QUANTITAPRENOTATA as quantitaPrenotata,
            c2.PESO as Peso_Kg,
            sum(C2.PESO) over (partition by oc.ID, M.ID) as PesoTotaleMagazzino
        from
            ORDINECLIENTE oc
            join DETTAGLIOORDINE D on oc.ID = D.IDORDINE
            join DETTAGLIOORDINEMAGAZZINO DM on D.ID = DM.IDDETTAGLIOORDINE
            join MAGAZZINO M on DM.IDMAGAZZINO = M.ID
            join CATALOGOPRODOTTI C2 on C2.ID = D.IDPRODOTTO
            join MERCESTOCCATA M2 on M2.IDMAGAZZINO = DM.IDMAGAZZINO and M2.IDPRODOTTO = C2.ID
        where
            oc.ID = curIdOrdine and M.IDFILIALE = pIdFiliale
        order by M.IDFILIALE, M.ID;

    rec cAnalisiOrdine%rowtype;

    idPackaging integer;
    vIdSpedizione spedizione.id%type;
    recPackagingDetails PackagingDetails%rowtype;
    currIdMagazzino integer;
    currPeso integer;
    currPropostaPacco integer;
    statoOrdine OrdineCliente.Stato%type;
    cntPackaging integer;
    vNoteAggiuntiveOperatore varchar2(512);
    vNoteAggiuntiveCliente varchar2(512);
begin
    vNoteAggiuntiveCliente := null;
    vNoteAggiuntiveOperatore := null;

    -- verifico che l'ordine sia in stato Completato

    select Stato into statoOrdine from OrdineCliente where Id = pIdOrdine;

    if statoOrdine <> 'InLavorazione' then -- Se è in lavorazione vuol dire che la spedizione è stata creata o c'è un problema di consistenza e workflow
        raise_application_error(-20001, 'L''ordine non è in stato Completato');
    end if;

    -- verifico che non esistano già ordini di packaging per l'ordine

    select count(*) into cntPackaging
        from
            DETTAGLIOORDINE do
                join PACKAGINGDETAILS pd on do.Id = pd.IdDettaglioOrdine
                join ORDINEDILAVOROPACKAGING olp on pd.IdOrdineDiLavoroPackaging = olp.Id
        where
            do.IdOrdine = pIdOrdine and olp.IDFILIALE = pIdFiliale;

    if cntPackaging > 0 then
        raise_application_error(-20002, 'Esistono già ordini di packaging per l''ordine ' || pIdOrdine);
    end if;

    begin
        select Id into vIdSpedizione
        from Spedizione where IdOrdineCliente = pIdOrdine;
    exception
        when no_data_found then
            raise_application_error(-20003, 'Non esiste una spedizione per l''ordine ' || pIdOrdine);
    end;

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

            if(rec.PERICOLOSITA != 'Nessuna') then
                vNoteAggiuntiveCliente := 'Attenzione: il pacco contiene merce pericolosa (' || rec.PERICOLOSITA || ')';
                vNoteAggiuntiveOperatore := 'Attenzione: il pacco contiene merce pericolosa (' || rec.PERICOLOSITA || '). Utilizzare con cautela e utilizzare confezioni protettive.';
            else
                vNoteAggiuntiveOperatore := null;
                vNoteAggiuntiveCliente := null;
            end if;

            insert into OrdineDiLavoroPackaging (IdFiliale, IdMagazzino, IdSpedizione, IdIndirizzoSpedizione, NoteAggiuntiveOperatore, NoteAggiuntiveCliente)
            values (rec.IDFILIALERIFERIMENTO, rec.IDMAGAZZINORIFERIMENTO, vIdSpedizione, rec.idIndirizzoSpedizione, vNoteAggiuntiveOperatore, vNoteAggiuntiveCliente) returning id into idPackaging;

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
        recPackagingDetails.IdMerceStoccataRiferimento := rec.idMerceStoccata;
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