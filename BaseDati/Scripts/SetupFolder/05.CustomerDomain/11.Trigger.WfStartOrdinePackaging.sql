/*
    Nome: WfStartOrdinePackaging
    Descrizione:
        a seguito di aggiornamento data inizio lavorazione, verifica se lo stato in
        statoOrdineClienteFiliale è 'Completato' e nel caso aggiorna lo stato a 'Packaging'.
 */
create or replace trigger WfStartOrdinePackaging
    after update of DataInizioLavorazione on OrdineDiLavoroPackaging
    for each row
    when ( old.DataInizioLavorazione is null and new.DataInizioLavorazione is not null )
declare
    vIdStatoOrdineClienteFiliale StatoOrdineClienteFiliale.Id%type;
    vStatoOrdineClienteFiliale StatoOrdineClienteFiliale.Stato%type;
begin

    select Id, Stato into vIdStatoOrdineClienteFiliale, vStatoOrdineClienteFiliale
    from
        StatoOrdineClienteFiliale socf,
        spedizione s
    where
        s.Id = :new.IdSpedizione
        and socf.IdFiliale = :new.IdFiliale
        and socf.IdOrdineCliente = s.IdOrdineCliente;

    if vStatoOrdineClienteFiliale = 'Completato' then
        update StatoOrdineClienteFiliale
        set Stato = 'Packaging'
        where Id = vIdStatoOrdineClienteFiliale;
    end if;
end;