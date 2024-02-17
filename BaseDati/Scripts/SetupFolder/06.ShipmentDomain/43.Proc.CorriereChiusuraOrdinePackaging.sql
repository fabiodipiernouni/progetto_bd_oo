/*
    Nome: CorriereChiusuraOrdinePackaging
    Descrizione: la procedura rappresenta la chiusura di un ordine di lavoro di tipo 'Packaging' da parte del corriere.
    Prende in input l'Id dell'ordine di lavoro e l'Id dell'operatore corriere che ha chiuso l'ordine di lavoro.
*/
create or replace procedure CorriereChiusuraOrdinePackaging(
    pIdOrdineDiLavoro in integer,
    pNoteAggiuntive in varchar2 default null
) as
begin
    update OrdineDiLavoroPackaging set
        DataFineLavorazione = sysdate,
        noteAggiuntiveOperatore = nvl(pNoteAggiuntive, noteAggiuntiveOperatore)
    where Id = pIdOrdineDiLavoro;
end;
