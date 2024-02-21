/*
    Nome: CorriereChiusuraOrdineSpedizione
    Descrizione:
        la procedura rappresenta la chiusura di un ordine di lavoro di tipo 'Spedizione' da parte del corriere.
        Prende in input l'Id dell'ordine di lavoro e l'Id dell'operatore corriere che ha chiuso l'ordine di lavoro.
    ***NOTA***: modificato dopo la consegna a BD

*/
create or replace procedure CorriereChiusuraOrdineSpedizione(
    pIdOrdineDiLavoro in integer,
    pNoteAggiuntive in varchar2 default null
) as
    vIdFiliale spedizione.id%type;
    vIdOrdineCliente ordinecliente.id%type;
    vIdSpedizione spedizione.id%type;
    vIdMezzo ORDINEDILAVOROSPEDIZIONE.IDMEZZODITRASPORTO%type;
    vCount integer;
begin
    -- aggiorno l'ordine di lavoro con la data di fine lavorazione e le note aggiuntive
    update OrdineDiLavoroSpedizione set
        DataFineLavorazione = sysdate,
        noteAggiuntiveOperatore = nvl(pNoteAggiuntive, noteAggiuntiveOperatore)
    where Id = pIdOrdineDiLavoro;

    -- aggiorno lo stato dell'ordine cliente filiale se non ci sono altri ordini di lavoro aperti
    select
        ols.IDFILIALE, ols.IDMEZZODITRASPORTO, s.id, s.IDORDINECLIENTE into vIdFiliale, vIdMezzo, vIdSpedizione, vIdOrdineCliente
    from
        ORDINEDILAVOROSPEDIZIONE ols
        join SPEDIZIONE s on s.id = ols.IDSPEDIZIONE
    where
        ols.ID = pIdOrdineDiLavoro;

    select count(*) into vCount from ORDINEDILAVOROSPEDIZIONE where IDFILIALE = vIdFiliale and DataFineLavorazione is null;
    dbms_output.put_line('DEBUG - vCount ordinedilavorospedizione con datafinelavorazione a null per filiale ' || vIdFiliale ||  ': ' || vCount);

    if vCount = 0 then
        update STATOORDINECLIENTEFILIALE set
            stato = 'Consegnato'
        where IDORDINECLIENTE = vidordinecliente and IDFILIALE = vIdFiliale;

        -- aggiorno la data di fine utilizzo del mezzo di trasporto
        update IMPEGNOMEZZO set DATAFINE = sysdate where IDMEZZO = vIdMezzo;

        -- verifico se sono l'ultima filiale a consegnare l'ordine cliente per aggiornare lo stato della spedizione
        select count(*) into vCount from STATOORDINECLIENTEFILIALE where IDORDINECLIENTE = vidordinecliente and stato <> 'Consegnato';
        dbms_output.put_line('DEBUG - vCount STATOORDINECLIENTEFILIALE con stato non Consegnato: ' || vCount);

        if vCount = 0 then
            dbms_output.put_line('DEBUG - Aggiorno stato spedizione ' || vIdSpedizione || ' a LavorataSpedizione.');
            -- chiudo la spedizione
            update SPEDIZIONE set
                stato = 'LavorataSpedizione', DATAFINELAVORAZIONE = sysdate, TRACKINGSTATUS = 'Consegnata'
            where ID = vIdSpedizione;
        end if;
    end if;
end;