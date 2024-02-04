/*
 *  Trigger: CkWfSpedizioneTrackingStatusOdm
 *  scopo: risoluzione vincolo VI.29 (fare riferimento al documento di analisi)
 *  descrizione:
 *      Il workflow di Spedizione.TrackingStatus deve rispettare le seguenti condizioni:

 *      'Registrata' -> 'InPartenza'
 *          Condizione: esiste almeno un OrdineDiLavoro in stato Assegnato
 *
 *      'InPartenza' -> 'InTransito'
 *          Condizione: esiste almeno un ordine di lavoro in stato InLavorazione
 *
 *      'InTransito' -> 'InConsegna'
 *          Condizione: la media delle percentuali di OrdineDiLavoro.PercentualeCompletamento legate alla spedizione deve essere maggiore di 80
 *
 *      'InConsegna' -> 'Consegnata'
 *          Condizione: solo se TUTTI gli ordini di lavoro sono in stato Lavorato"
 */

drop trigger CkWfSpedizioneTrackingStatusOdm;
-- non lo utilizzo più
create or replace trigger CkWfSpedizioneTrackingStatusOdm
    before update of TrackingStatus on Spedizione
    for each row
    when (new.TrackingStatus != old.TrackingStatus)
declare
    v_count number;
    v_sum number;
    v_avg number;
begin
    if :old.TrackingStatus = 'Registrata' and :new.TrackingStatus = 'InPartenza' then
        select count(*)
        into v_count
        from OrdineDiLavoroSpedizione odm
        where odm.IdSpedizione = :new.Id
        and Stato = 'Assegnato';

        if v_count = 0 then
            raise_application_error(-20001, 'Non esistono ordini di lavoro in stato Assegnato');
        end if;
    elsif :old.TrackingStatus = 'InPartenza' and :new.TrackingStatus = 'InTransito' then
        select count(*)
        into v_count
        from OrdineDiLavoro
        where SpedizioneId = :new.Id
        and Stato = 'InLavorazione';

        if v_count = 0 then
            raise_application_error(-20001, 'Non esistono ordini di lavoro in stato InLavorazione');
        end if;
    elsif :old.TrackingStatus = 'InTransito' and :new.TrackingStatus = 'InConsegna' then
        select sum(PercentualeCompletamento)
        into v_sum
        from OrdineDiLavoro
        where SpedizioneId = :new.Id;

        select count(*)
        into v_count
        from OrdineDiLavoro
        where SpedizioneId = :new.Id;

        v_avg := v_sum / v_count;

        if v_avg < 80 then
            raise_application_error(-20001, 'La media delle percentuali di completamento degli ordini di lavoro è inferiore a 80');
        end if;
    elsif :old.TrackingStatus = 'InConsegna' and :new.TrackingStatus = 'Consegnata' then
        select count(*)
        into v_count
        from OrdineDiLavoro
        where SpedizioneId = :new.Id
        and Stato != 'Lavorato';

        if v_count > 0 then
            raise_application_error(-20001, 'Non tutti gli ordini di lavoro sono in stato Lavorato');
        end if;
    else
        raise_application_error(-20001, 'Transizione di stato non valida');
    end if;

end;