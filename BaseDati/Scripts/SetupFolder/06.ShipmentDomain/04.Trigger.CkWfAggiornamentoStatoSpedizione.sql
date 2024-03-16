/*
    Nome: CkWfAggiornamentoStatoSpedizione
    scopo: risoluzione vincolo VI.34 (fare riferimento al documento di analisi)
    Descrizione:
        Trigger per evitare che lo stato di una spedizione venga modificato in 'LavorataSpedizione' se non tutti gli ordini di lavoro
        ad essa associati sono in stato 'Lavorato'

    **NOTA**: modificato dopo la consegna a BD
 */
create or replace trigger CkWfAggiornamentoStatoSpedizione
  before update of STATO on SPEDIZIONE
  for each row
when (new.STATO = 'LavorataSpedizione')
declare
    vNumNonLavorato number;
    vNumLavorato number;
begin
    select sum(case when stato = 'Lavorato' then 0 else 1 end) as numNonLavorato,
           sum(case when stato = 'Lavorato' then 1 else 0 end) as numLavorato
           into vNumNonLavorato, vNumLavorato
    from ORDINEDILAVOROSPEDIZIONE ols
    where ols.IDSPEDIZIONE = :new.ID and ols.STATO <> 'Lavorato';

    if (vNumLavorato = 0 AND vNumNonLavorato = 0) -- non ci sono ordini di spedizione
       or (vNumNonLavorato > 0) then -- ci sono ordini di spedizione ma non terminati
       :new.STATO := :old.STATO; --annulla la modifica allo stato
    end if;
end;