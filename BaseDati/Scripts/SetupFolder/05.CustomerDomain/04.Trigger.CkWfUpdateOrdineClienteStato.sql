/*
 *  Trigger: CkWfUpdateOrdineClienteStato
 *  scopo: risoluzione vincolo VI.19 (fare riferimento al documento di analisi)
 *  descrizione:
 *      OrdineCliente.Stato può variare solo seguendo workflow:

 *      'Bozza' -> 'Confermato'
 *      'Confermato' -> 'Completato'
 *      'Completato' -> 'InLavorazione'
 *      'InLavorazione' -> 'Lavorato'
 */

create or replace trigger CkWfUpdateOrdineClienteStato
before update of Stato on OrdineCliente
for each row
begin
    if
        (:old.Stato = 'Bozza' and :new.Stato not in ('Confermato', 'Completato')) or
        (:old.Stato = 'Confermato' and :new.Stato <> 'Completato') or
        (:old.Stato = 'Completato' and :new.Stato <> 'InLavorazione') or
        (:old.Stato = 'InLavorazione' and :new.Stato <> 'Lavorato') then
            raise_application_error(-20002, 'Stato non valido. IdOrdine: ' || :old.Id || ' Stato: ' || :old.Stato || ' -> ' || :new.Stato);
    end if;
end;