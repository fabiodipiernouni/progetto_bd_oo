/*
    Nome: CkWfAggiornamentoStatoSpedizione
    scopo: risoluzione vincolo VI.34 (fare riferimento al documento di analisi)
    Descrizione:
        Trigger per evitare che lo stato di una spedizione venga modificato in 'LavorataSpedizione' se non tutti gli ordini di lavoro
        ad essa associati sono in stato 'Lavorato'
 */
create or replace trigger CkWfAggiornamentoStatoSpedizione
  before update of STATO on SPEDIZIONE
  for each row
when (new.STATO = 'LavorataSpedizione')
declare
    v_count number;
begin
    select count(*) into v_count
    from ORDINEDILAVOROSPEDIZIONE ols
    where ols.IDSPEDIZIONE = :new.ID and ols.STATO <> 'Lavorato';

    if v_count > 0 then
       :new.STATO := :old.STATO; --annulla la modifica allo stato
    end if;
end;