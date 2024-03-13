/*
 Nome: CorrierePresaInCaricoSpedizione
 Descrizione:
    la procedura prende in input idGruppoCorriere e idOperatoreCorriere e Id dell'ordine di lavoro di tipo 'Spedizione' e lo assegna al corriere
    settando la datainiziolavorazione.
 */
create or replace procedure CorrierePresaInCaricoSpedizione(
    pIdOperatoreCorriere in integer,
    pIdOrdineDiLavoroSpedizione in integer)
as
    vIdGruppoCorriere GruppoCorriere.Id%type;
    vProfilo Profilo.Profilo%type;
begin
    -- controllo che l'utente sia un operatore corriere e ne recupero il gruppo di appartenenza

    select ut.idGruppoCorriere, p.profilo into vIdGruppoCorriere, vProfilo
    from
        utente ut
        join profilo p on ut.idProfilo = p.id
    where ut.id = pIdOperatoreCorriere;

    if vProfilo <> 'OperatoreCorriere' then
        raise_application_error(-20001, 'L''utente non è un operatore corriere');
    end if;

    update OrdineDiLavoroSpedizione
    set IdGruppoCorriere = vIdGruppoCorriere,
        IdOperatoreCorriere = pIdOperatoreCorriere
    where Id = pIdOrdineDiLavoroSpedizione;

    -- eccezione volutamente non gestita. Transazione lasciata aperta e demandata al chiamante
end;