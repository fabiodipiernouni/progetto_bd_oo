/*
 *  Trigger: CkWfUpdateOrdineClienteStato
 *  scopo: risoluzione vincolo VI.20 (fare riferimento al documento di analisi)
 *  descrizione:
 *      OrdineCliente.Stato può variare solo seguendo workflow:

 *      'Confermato' -> 'Spedito'
 *      'Spedito' -> 'Consegnato'
 *      'Consegnato' -> 'ResoAvviato'
 *      'ResoAvviato' -> 'ResoInCorso'
 *      'ResoInCorso' -> 'ResoEffettuato'
 *      'Confermato' -> 'Annullato'
 */

create or replace trigger CkWfUpdateOrdineClienteStato
before update on OrdineCliente
for each row
begin
    if
        (:new.Stato = 'Confermato' and (:old.Stato != 'Spedito' or :old.Stato != 'Annullato')) or
        (:new.Stato = 'Spedito' and :old.Stato != 'Consegnato') or
        (:new.Stato = 'Consegnato' and :old.Stato != 'ResoAvviato') or
        (:new.Stato = 'ResoAvviato' and :old.Stato != 'ResoInCorso') or
        (:new.Stato = 'ResoInCorso' and :old.Stato != 'ResoEffettuato') or
        (:new.Stato = 'Confermato' and :old.Stato != 'Annullato') then
            raise_application_error(-20002, 'Stato non valido');
    end if;
end;