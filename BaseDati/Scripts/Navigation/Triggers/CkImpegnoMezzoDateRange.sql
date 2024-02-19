/*
 *  Trigger: CkImpegnoMezzoDateRange
 *  scopo: risoluzione vincolo VI.10 (fare riferimento al documento di analisi)
 *  descrizione:
 *      per il controllo della data di inizio e fine dell'impegno
 *      di un mezzo.
 *      Se la data di fine è nulla, allora la data di inizio non deve essere compresa
 *      tra la data di inizio e fine di un altro impegno.
 *      Se la data di fine non è nulla, allora la data di inizio non deve essere compresa
 *      tra la data di inizio e fine di un altro impegno, e la data di fine non deve essere compresa
 *      tra la data di inizio e fine di un altro impegno, e la data di inizio deve essere minore
 *      della data di inizio e fine di un altro impegno.
 */

create or replace trigger CkImpegnoMezzoDateRange
before insert or update on ImpegnoMezzo
for each row
declare
    cnt integer;
begin

    select count(*) into cnt
    from impegnomezzo
    where idMezzo = :old.idMezzo and id <> :old.id and
        ( (:new.DataFine is null and :new.DataInizio between DataInizio and DataFine ) or
            (:new.DataFine is not null and
                (
                   (:new.DataInizio between DataInizio and DataFine) or
                   (:new.DataFine between DataInizio and DataFine) or
                   (:new.DataInizio <= DataInizio and :new.DataFine >= DataFine)
                )
            )
        );

    if cnt > 0 then
        RAISE_APPLICATION_ERROR(-20001, 'Impossibile inserire o aggiornare l''impegno del mezzo ' || :old.idMezzo || ' perche'' in conflitto con un altro impegno');
    end if;
end;