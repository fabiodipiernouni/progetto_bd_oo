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
    -- QUANDO HO VALORIZZATO LA DATA DI INIZIO LAVORAZIONE
declare
    vIdOrdineCliente StatoOrdineClienteFiliale.IDORDINECLIENTE%type;
    vStatoOrdineClienteFiliale StatoOrdineClienteFiliale.Stato%type;
begin
    select socf.IdOrdineCliente, socf.Stato into vIdOrdineCliente, vStatoOrdineClienteFiliale
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
        where IdOrdineCliente = vIdOrdineCliente and IdFiliale = :new.IdFiliale;
    end if;
end;