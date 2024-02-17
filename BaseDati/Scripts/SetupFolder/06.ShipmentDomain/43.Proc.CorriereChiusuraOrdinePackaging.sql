/*
    Nome: CorriereChiusuraOrdinePackaging
    Descrizione: la procedura rappresenta la chiusura di un ordine di lavoro di tipo 'Packaging' da parte del corriere.
    Prende in input l'Id dell'ordine di lavoro e l'Id dell'operatore corriere che ha chiuso l'ordine di lavoro.
*/
create or replace procedure CorriereChiusuraOrdinePackaging(
    pIdOrdineDiLavoro in integer,
    pNoteAggiuntive in varchar2 default null
) as
    vIdSpedizione spedizione.id%type;
    vCnt integer;
begin
    select IdSpedizione into vIdSpedizione
    from OrdineDiLavoroPackaging
    where Id = pIdOrdineDiLavoro;

    update OrdineDiLavoroPackaging set
        DataFineLavorazione = sysdate,
        noteAggiuntiveOperatore = nvl(pNoteAggiuntive, noteAggiuntiveOperatore)
    where Id = pIdOrdineDiLavoro;

    select count(*) into vCnt
    from OrdineDiLavoroPackaging
    where IdSpedizione = vIdSpedizione and DataFineLavorazione is null;

    -- se questo è l'ultimo ordine di packaging che viene chiuso allora
    if vCnt = 0 then
        update Spedizione set
            TrackingStatus = 'InPartenza'
        where Id = vIdSpedizione;
    end if;
end;
