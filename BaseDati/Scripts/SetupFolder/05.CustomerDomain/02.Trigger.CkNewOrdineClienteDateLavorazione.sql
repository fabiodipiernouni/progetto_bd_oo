/*
 *  Trigger: CkNewOrdineClienteDateLavorazione
 *  scopo: risoluzione vincolo VI.17, VI.18 (fare riferimento al documento di analisi)
 *  descrizione:
 *     DataInizioLavorazione deve essere a null in fase di insert.
*      DataFIneLavorazione deve essere a null in fase di insert.
*/

create or replace trigger CkNewOrdineClienteDateLavorazione
before insert on OrdineCliente
for each row
when (new.Stato = 'Bozza')
begin
    if (:new.DataInizioLavorazione is not null) then
        raise_application_error(-20001, 'Impossibile inserire una data di inizio lavorazione quando l''ordine è in fase di Bozza');
    end if;
    if (:new.DataFineLavorazione is not null) then
        raise_application_error(-20001, 'Impossibile inserire una data di fine lavorazione quando l''ordine è in fase di Bozza');
    end if;

end;
