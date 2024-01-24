/*
 *  Trigger: CkSpedizioneDataInizioDataOrdineCliente
 *  scopo: risoluzione vincolo VI.26 (fare riferimento al documento di analisi)
 *  descrizione:
 *      Se Spedizione.DataInizioLavorazione è valorizzato allora deve essere maggiore di OrdineCliente.DataOrdine
 */

create or replace trigger CkSpedizioneDataInizioDataOrdineCliente
before insert or update on Spedizione
for each row
when (new.DataInizioLavorazione is not null)
declare
    dataOrdine date;
begin
    select DataOrdine into dataOrdine
    from OrdineCliente
    where OrdineCliente.Id = :new.IdOrdineCliente;

    if (:new.DataInizioLavorazione < dataOrdine) then
        raise_application_error(-20001, 'Errore: la data di inizio lavorazione deve essere maggiore della data di ordine del cliente.');
    end if;

end;