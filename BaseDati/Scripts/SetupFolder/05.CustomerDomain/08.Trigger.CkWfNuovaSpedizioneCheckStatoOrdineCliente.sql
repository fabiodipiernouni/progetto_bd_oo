/*
    Nome: CkWfNuovaSpedizioneCheckStatoOrdineCliente
    Descrizione:
        creazione trigger di verifica stato ordinecliente a seguito di inserimento di una nuova spedizione.
        Lo stato dell'ordine deve essere 'Completato'.
 */
create or replace trigger CkWfNuovaSpedizioneCheckStatoOrdineCliente
    before insert on Spedizione
    for each row
declare
    statoOrdineCliente OrdineCliente.Stato%type;
begin
    select Stato into statoOrdineCliente
    from OrdineCliente
    where Id = :new.IdOrdineCliente;

    if statoOrdineCliente <> 'Completato' then
        raise_application_error(-20001, 'Lo stato dell''ordine cliente deve essere ''Completato''');
    end if;
end;
