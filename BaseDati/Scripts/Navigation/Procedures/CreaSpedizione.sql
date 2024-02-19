/*
    Nome: CreaSpedizione
    Descrizione:
        crea una nuova spedizione per un ordine completato.
        Se lo stato dell'ordine non è 'Completato' viene sollevata un'eccezione.
        Se la creazione della spedizione va a buon fine, viene restituito l'Id della spedizione.
 */
create or replace procedure CreaSpedizione(pIdOrdine in number, pIdOperatore in integer, pIdSpedizione out integer)
as
    vCnt number;
    statoOrdine OrdineCliente.Stato%type;
begin
    begin
        select stato into statoOrdine from OrdineCliente where Id = pIdOrdine;
    exception when no_data_found then
        statoOrdine := null;
    end;

    if statoOrdine is not null and statoOrdine <> 'Completato' then
        RAISE_APPLICATION_ERROR(-20001, 'Lo stato dell''ordine non permette la creazione della spedizione.');
    end if;

    -- verifico se in dettaglioordine ci sono prodotti non disponibili, vincolo VI.36
    select count(*) into vCnt
    from DettaglioOrdine
    where IdOrdine = pIdOrdine and FLAGQUANTITADISPONIBILE = 'N';

    if vCnt > 0 then
        RAISE_APPLICATION_ERROR(-20002, 'Impossibile creare la spedizione, alcuni prodotti non sono disponibili.');
    end if;

    -- se esiste già torna l'id già creato altrimenti l'inserisce
    begin
        select id into pIdSpedizione
        from spedizione
        where idordinecliente = pIdOrdine;
    exception when no_data_found then
        insert into Spedizione (IdOrdineCliente, IdUtenteOrganizzatore, TrackingNumber) values (pIdOrdine, pIdOperatore, 'TRK' || pIdOrdine) returning Id into pIdSpedizione;
        commit;
    end;
end;