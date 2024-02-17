/*
    Nome: WfChiusuraOrdinePackaging
    Descrizione:
        il trigger scatta quando viene valorizzata la data fine elaborazione di un ordine di lavoro di tipo 'Packaging'.
        Si occupa di creari i pacchi seguendo le indicazioni dell'ordine di lavoro di tipo 'Packaging'
        invocando la procedura GeneraPacchiByIdOrdinePackaging(pIdOrdinePackaging integer)
 */

drop trigger WfChiusuraOrdinePackaging;

-- non eseguire
create or replace trigger WfChiusuraOrdinePackaging
after update of DataFineLavorazione on OrdineDiLavoroPackaging
for each row
when (new.DataFineLavorazione is not null)
declare
    cnt number;
begin
    -- non va bene, va aggiornata solo se tutti gli ordini di packaging sono chiusi, il problema è che in questo trigger
    -- non posso fare select su OrdineDiLavoroPackaging per controllare se ci sono altri ordini di lavoro aperti

    update Spedizione
    set Stato = 'LavorataPackaging'
    where Id = :new.IdSpedizione;

    -- se esistono già pacchi li elimino
    --delete from Pacco where IdOrdineLavoroOrigine = :new.Id;

    --GeneraPacchiByIdOrdinePackaging(:new.Id, :new.IdMagazzino, :new.IdIndirizzoSpedizione, :new.IdSpedizione);
end;