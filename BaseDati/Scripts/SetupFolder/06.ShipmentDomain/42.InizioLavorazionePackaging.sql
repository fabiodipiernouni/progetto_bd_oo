/*
    Nome: InizioLavorazionePackaging
    Descrizione: Procedura che permette di impostare la data di inizio lavorazione di un ordine di lavoro di Packaging
 */
create or replace procedure InizioLavorazionePackaging
(
  pIdOrdineLavoroPackaging in number,
  pDataInizioLavorazione in date
)
as
    vDataInizioLavorazione date;
begin
    if pDataInizioLavorazione is null then
        vDataInizioLavorazione := sysdate;
    else
        vDataInizioLavorazione := pDataInizioLavorazione;
    end if;

    update OrdineDiLavoroPackaging
    set DATAINIZIOLAVORAZIONE = vDataInizioLavorazione
    where Id = pIdOrdineLavoroPackaging;
end InizioLavorazionePackaging;
