/*
    Nome: CkWfNuovoOrdinePackaging
    Descrizione:
        a seguito di inserimento nuovo ordine packaging verifica se la spedizione in stato 'DaLavorare' e
        nel caso aggiorna lo stato a 'InLavorazionePackeging'
 */
create or replace trigger CkWfNuovoOrdinePackaging
after insert on OrdineDiLavoroPackaging
for each row
declare
    vStatoSpedizione spedizione.Stato%type;
begin
    select stato into vStatoSpedizione
    from spedizione
    where id = :new.IdSpedizione;

    if vStatoSpedizione = 'DaLavorare' then
        update Spedizione
        set Stato = 'InLavorazionePackeging', DataInizioLavorazione = sysdate
        where Id = :new.IdSpedizione and Stato = 'DaLavorare';
    end if;
end;



