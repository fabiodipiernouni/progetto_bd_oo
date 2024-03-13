/*
 *  Trigger: CkWfSpedizioneTrackingStatus
 *  scopo: risoluzione vincolo VI.33 (fare riferimento al documento di analisi)
 *  descrizione:
 *      Spedizione.TrackingStatus può variare solo secondo il seguente workflow:
 *          'Registrata' -> 'InPartenza'
 *          'InPartenza' -> 'InTransito'
 *          'InTransito' -> 'InConsegna'
 *          'InConsegna' -> 'Consegnata'
 */

create or replace trigger CkWfSpedizioneTrackingStatus
    before update of TRACKINGSTATUS on Spedizione
    for each row
    when (new.TrackingStatus != old.TrackingStatus)
declare
    oldStatus varchar2(50);
    newStatus varchar2(50);
begin
    oldStatus := :old.TrackingStatus;
    newStatus := :new.TrackingStatus;

    if (oldStatus = 'Registrata' and newStatus <> 'InPartenza') or
       (oldStatus = 'InPartenza' and newStatus <> 'InTransito') or
       (oldStatus = 'InTransito' and newStatus not in ('InConsegna', 'Consegnata')) or
       (oldStatus = 'InConsegna' and newStatus <> 'Consegnata') then
        raise_application_error(-20001, 'Errore: violazione workflow tracking status');
    end if;
end;