/*
    Nome: CkInsUpdUtente
    Descrizione: Controllo inserimento o aggiornamento utente
 */
create or replace trigger CkInsUpdUtente
before insert or update ON Utente
for each row
declare
    vProfilo Profilo.Profilo%TYPE;
begin
    begin
        select Profilo into vProfilo
        from Profilo
        where Id = :new.IdProfilo;
    exception when others then return; -- vuol dire che l'idProfilo non è valido e lasciamo al DB la gestione dell'errore
    end;

    if vProfilo = 'OperatoreCorriere' and (:new.IdGruppoCorriere is null or :new.IdFilialeOperatore is not null) then
        RAISE_APPLICATION_ERROR(-20001, 'Operatore Corriere deve essere associato ad un gruppo corriere e non deve essere associato ad una filiale');
    end if;

    if vProfilo = 'Operatore' and (:new.IdGruppoCorriere is not null or :new.IdFilialeOperatore is null) then
        RAISE_APPLICATION_ERROR(-20001, 'Operatore deve essere associato ad una filiale e non deve essere associato ad un gruppo corriere');
    end if;

    if vProfilo in ('Manager', 'Admin') and (:new.IdGruppoCorriere is not null or :new.IdFilialeOperatore is not null) then
        RAISE_APPLICATION_ERROR(-20001, 'Manager non deve essere associato ad un gruppo corriere o ad una filiale');
    end if;
end;
