/*
    Nome: CkWfPresaInCaricoSpedizione
    Descrizione:
        quando l'ordine di spedizione viene preso in carico e comincia,
        occorre aggiornare lo stato dell'ordine cliente per la filiale in Spedito
 */
create or replace trigger CkWfPresaInCaricoSpedizione
after update of datainiziolavorazione on ordinedilavorospedizione
for each row
when (new.datainiziolavorazione is not null and new.DATAFINELAVORAZIONE is null)
declare
  vIdOrdineCliente OrdineCliente.id%type;
begin
    select
        s.idOrdineCliente into vIdOrdineCliente
    from
        spedizione s
    where s.id = :new.idspedizione;

    update statoordineclientefiliale set stato = 'Spedito' where idordinecliente = vIdOrdineCliente and idfiliale = :new.idfiliale;
end;
