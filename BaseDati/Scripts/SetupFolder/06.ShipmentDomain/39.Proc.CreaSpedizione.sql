/*
    Nome: CreaSpedizione
    Descrizione:
        crea una nuova spedizione per un ordine completato.
        Se lo stato dell'ordine non è 'Completato' viene sollevata un'eccezione.
        Se la creazione della spedizione va a buon fine, viene restituito l'Id della spedizione.
 */
create or replace procedure CreaSpedizione(pIdOrdine in number, pIdOperatore in integer, pIdSpedizione out integer)
as

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