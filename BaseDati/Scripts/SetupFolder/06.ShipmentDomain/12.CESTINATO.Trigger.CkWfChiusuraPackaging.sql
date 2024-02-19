/*
    Nome: CkWfChiusuraPackaging
    Descrizione:
        quando l'ordine di packaging viene chiuso,
        occorre aggiornare lo stato dell'ordine cliente per la filiale in ''
 */

 drop trigger CkWfChiusuraPackaging;

create or replace trigger CkWfChiusuraPackaging
    after update of datafinelavorazione on OrdineDiLavoroPackaging
    for each row
    when (new.datafinelavorazione is not null)
declare
    vIdOrdineCliente OrdineCliente.id%type;
begin
    select
        s.idOrdineCliente into vIdOrdineCliente
    from
        ordinedilavoropackaging olp
        join spedizione s on olp.idspedizione = s.id
    where olp.id = :new.id;

    update statoordineclientefiliale set stato = '' where idordinecliente = vIdOrdineCliente and idfiliale = :new.idfiliale;
end;
