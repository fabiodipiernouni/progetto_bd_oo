/*
    Nome: CkWfAggiornamentoStatoSpedizione
    scopo: gestione workflow per post aggiornamento stato di una spedizione
    Descrizione:
        se la spedizione va in LavorataSpedizione aggiorna l'ordine chiudendolo
 */
create or replace trigger WfAggiornamentoStatoSpedizione
  after update of STATO on SPEDIZIONE
  for each row
when (new.STATO = 'LavorataSpedizione')
declare
    v_count number;
begin
    -- aggiorno l'ordine del cliente in
    update ordinecliente set STATO = 'Lavorato', dataFineLavorazione = sysdate
    where id = :new.IDORDINECLIENTE;
end;