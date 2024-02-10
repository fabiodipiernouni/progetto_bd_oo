/*
    Nome: WfChiusuraOrdinePackaging
    Descrizione:
        il trigger scatta quando viene valorizzata la data fine elaborazione di un ordine di lavoro di tipo 'Packaging'.
        Si occupa di creari i pacchi seguendo le indicazioni dell'ordine di lavoro di tipo 'Packaging'
        invocando la procedura GeneraPacchiByIdOrdinePackaging(pIdOrdinePackaging integer)
 */
create or replace trigger WfChiusuraOrdinePackaging
after update of DataFineLavorazione on OrdineDiLavoroPackaging
for each row
when (new.DataFineLavorazione is not null)
declare
    cnt number;
begin
    update Spedizione
    set Stato = 'LavorataPackaging'
    where Id = :new.IdSpedizione;

    -- se esistono già pacchi li elimino
    delete from Pacco where IdOrdineLavoroOrigine = :new.Id;

    GeneraPacchiByIdOrdinePackaging(:new.Id, :new.IdMagazzino, :new.IdIndirizzoSpedizione, :new.IdSpedizione);
end;