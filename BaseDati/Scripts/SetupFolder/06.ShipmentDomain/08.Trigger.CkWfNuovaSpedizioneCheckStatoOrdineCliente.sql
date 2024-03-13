/*
    Nome: CkWfNuovaSpedizioneCheckStatoOrdineCliente
    scopo: risoluzione vincolo VI.35 e VI.36 (fare riferimento al documento di analisi)
    Descrizione:
        creazione trigger di verifica stato ordinecliente a seguito di inserimento di una nuova spedizione.
        Lo stato dell'ordine deve essere 'Completato'.
 */
create or replace trigger CkWfNuovaSpedizioneCheckStatoOrdineCliente
    before insert on Spedizione
    for each row
declare
    statoOrdineCliente OrdineCliente.Stato%type;
    vCnt number;
begin
    select Stato into statoOrdineCliente
    from OrdineCliente
    where Id = :new.IdOrdineCliente;

    if statoOrdineCliente <> 'Completato' then
        raise_application_error(-20001, 'Lo stato dell''ordine cliente deve essere ''Completato''');
    end if;

    -- verifico se in dettaglioordine ci sono prodotti non disponibili, vincolo VI.36
    select count(*) into vCnt
    from DettaglioOrdine
    where IdOrdine = :new.IdOrdineCliente and FLAGQUANTITADISPONIBILE = 'N';

    if vCnt > 0 then
        RAISE_APPLICATION_ERROR(-20002, 'Impossibile creare la spedizione, alcuni prodotti non sono disponibili.');
    end if;
end;
