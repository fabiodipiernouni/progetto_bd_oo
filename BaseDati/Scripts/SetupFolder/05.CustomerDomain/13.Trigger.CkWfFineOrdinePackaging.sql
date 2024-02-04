/*
    Nome: CkWfFineOrdinePackaging
    Descrizione:
        Controlla se tutti gli ordini di packaging sono stati completati.
        Se tutti gli ordini di packaging sono terminati (DataFineLavorazione valorizzata),
        allora aggiorna lo stato della spedizione a 'LavorataPackeging'.
 */
create or replace trigger CkWfFineOrdinePackaging
    after update of DataFineLavorazione on OrdineDiLavoroPackaging
    for each row
    when ( old.DataFineLavorazione is null and new.DataFineLavorazione is not null )
declare
    vCount number;
begin

    select count(*) into vCount
    from OrdineDiLavoroPackaging
    where IdSpedizione = :new.IdSpedizione
    and DataFineLavorazione is null;

    if vCount = 0 then
        update Spedizione
        set Stato = 'LavorataPackaging'
        where Id = :new.IdSpedizione;
    end if;
end;
