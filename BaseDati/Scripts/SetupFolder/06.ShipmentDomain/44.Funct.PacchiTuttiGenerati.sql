/*
    Nome: PacchiTuttiGenerati
    Descrizione:
        Verifica se tutti i pacchi sono stati generati
    Ritorno:
        0: Non tutti i pacchi sono stati generati
        1: Tutti i pacchi sono stati generati
 */
create or replace function PacchiTuttiGenerati(pIdSpedizione in number) return number is
    vNumeroLavorati number;
    vNumeroNonLavorati number;
    vNumeroPacchiDaCreare number;
    vNumeroPacchiGenerati number;
begin
    -- verifico se ci sono ordini di packaging non lavorati
    select
        sum(case when STATO = 'Lavorato' then 1 else 0 end) as numeroLavorati,
        sum(case when STATO = 'Lavorato' then 0 else 1 end) as numeroNonLavorati,
        count(distinct CODICEPROPOSTAPACCO) as numeroPacchi
    into
        vNumeroLavorati,
        vNumeroNonLavorati,
        vNumeroPacchiDaCreare
    from
        ORDINEDILAVOROPACKAGING olp
        join UNINADEV.PACKAGINGDETAILS P on olp.ID = P.IDORDINEDILAVOROPACKAGING
    where
        IDSPEDIZIONE = pIdSpedizione;

    if vNumeroNonLavorati > 0 then
        return 0;
    end if;

    --verifico se ci sono pacchi non generati
    select count(*) into vNumeroPacchiGenerati from pacco p where p.IDSPEDIZIONE = pIdSpedizione;

    if vNumeroPacchiGenerati < vNumeroPacchiDaCreare then
        return 0;
    end if;

    return 1;
exception when others then
    return 0;
end PacchiTuttiGenerati;
