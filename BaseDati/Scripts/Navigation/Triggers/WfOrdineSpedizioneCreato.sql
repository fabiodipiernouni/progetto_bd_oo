/*
    Nome: WfOrdineSpedizioneCreato
    descrizione: azioni intraprese dopo la creazione di un ordine di lavoro di spedizione
 */
create or replace trigger WfOrdineSpedizioneCreato
after insert on ORDINEDILAVOROSPEDIZIONE
for each row
begin
    update spedizione set stato = 'InLavorazioneSpedizione', TRACKINGSTATUS = 'InTransito' where id = :new.idspedizione and stato = 'LavorataPackaging';
end;