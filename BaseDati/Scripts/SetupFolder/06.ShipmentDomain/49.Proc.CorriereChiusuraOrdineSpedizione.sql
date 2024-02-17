/*
    Nome: CorriereChiusuraOrdineSpedizione
    Descrizione: la procedura rappresenta la chiusura di un ordine di lavoro di tipo 'Spedizione' da parte del corriere.
    Prende in input l'Id dell'ordine di lavoro e l'Id dell'operatore corriere che ha chiuso l'ordine di lavoro.
*/
create or replace procedure CorriereChiusuraOrdineSpedizione(
    pIdOrdineDiLavoro in integer,
    pNoteAggiuntive in varchar2 default null
) as
    vIdFiliale spedizione.id%type;
    vIdOrdineCliente ordinecliente.id%type;
    vIdSpedizione spedizione.id%type;
    vCount integer;
begin
    update OrdineDiLavoroSpedizione set
        DataFineLavorazione = sysdate,
        noteAggiuntiveOperatore = nvl(pNoteAggiuntive, noteAggiuntiveOperatore)
    where Id = pIdOrdineDiLavoro;

    select
        ols.IDFILIALE, s.id, s.IDORDINECLIENTE into vIdFiliale, vIdSpedizione, vIdOrdineCliente
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

        -- verifico se sono l'ultimo
        select count(*) into vCount from STATOORDINECLIENTEFILIALE where IDORDINECLIENTE = vidordinecliente and stato <> 'Consegnato';
        dbms_output.put_line('DEBUG - vCount STATOORDINECLIENTEFILIALE con stato non Consegnato: ' || vCount);

        if vCount = 0 then
            dbms_output.put_line('DEBUG - Aggiorno stato spedizione ' || vIdSpedizione || ' a LavorataSpedizione.');

            update SPEDIZIONE set
                stato = 'LavorataSpedizione', TRACKINGSTATUS = 'Consegnata'
            where ID = vIdSpedizione;
        end if;
    end if;
end;