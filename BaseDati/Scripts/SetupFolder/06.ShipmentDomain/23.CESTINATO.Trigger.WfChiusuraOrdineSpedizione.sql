/*
    Nome: WfChiusuraOrdineSpedizione
    Descrizione: Trigger che si attiva quando viene modificata la data di fine lavorazione di un ordine di lavorazione spedizione.
                 Aggiorna lo stato della spedizione a 'LavorataSpedizione'.
                 Questo trigger è necessario per garantire che la spedizione venga marcata come lavorata solo quando
                 tutti gli ordini di lavorazione ad essa associati sono stati completati.
 */

    -- non va bene, per errore ORA-04091: La tabella UNINADEV.ORDINEDILAVOROSPEDIZIONE è in fase di modifica, il trigger/funzione non può leggerla
    -- il trigger scatena il trigger sulla spedizione che vuole leggere ORDINEDILAVOROSPEDIZIONE che risulta in modifica

/*
drop trigger WfChiusuraOrdineSpedizione;

create or replace trigger WfChiusuraOrdineSpedizione
after update of DATAFINELAVORAZIONE on ORDINEDILAVOROSPEDIZIONE
for each row
declare
    vCnt number;
begin
    -- attenzione, sarà lato trigger spedizione a verificare che non ci siano altri ordini di lavorazione in lavorazione

    update spedizione set STATO = 'LavorataSpedizione' where id = :new.IDSPEDIZIONE and STATO = 'InLavorazioneSpedizione';
end;
*/