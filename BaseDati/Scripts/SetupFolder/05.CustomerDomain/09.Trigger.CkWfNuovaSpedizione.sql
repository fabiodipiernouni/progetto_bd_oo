/*
    Nome: CkWfNuovaSpedizione
    Descrizione: creazione trigger di aggiornamento ordineclient a seguito di inserimento di una nuova spedizione
 */
create or replace trigger CkWfNuovaSpedizione
    after insert on Spedizione
    for each row
declare
    statoOrdineCliente OrdineCliente.Stato%type;

begin
    select Stato into statoOrdineCliente
    from OrdineCliente
    where Id = :new.IdOrdineCliente;

    if statoOrdineCliente = 'Completato' then
        update OrdineCliente
        set Stato = 'InLavorazione', DataInizioLavorazione = sysdate
        where Id = :new.IdOrdineCliente;
    end if;

    -- aggiorno le quantità prenotate in magazzino
end;
