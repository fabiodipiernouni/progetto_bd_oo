/*
    Nome: CreaOrdineDiSpedizione
    Descrizione: procedura che genera un ordine di lavoro di tipo 'Spedizione'
 */
create or replace procedure CreaOrdineDiSpedizione(pIdSpedizione integer)
as
    cursor cFilialiCoinvolte is
        select distinct m.IDFILIALE
        from Pacco p
        join MAGAZZINO m on p.IDMAGAZZINO = m.ID
        where p.IDSPEDIZIONE = pIdSpedizione;

    rec cFilialiCoinvolte%rowtype;
    vIdOrdineDiLavoroSpedizione integer;
    vStatoSpedizione spedizione.Stato%type;
    vCnt number;
begin
    -- verifico se non ci sono già altri ordini di lavoro per la spedizione
    select count(*) into vCnt from ORDINEDILAVOROSPEDIZIONE where IDSPEDIZIONE = pIdSpedizione;

    if vCnt > 0 then
        raise_application_error(-20000, 'Esiste già almeno un ordine di lavoro per la spedizione');
    end if;

    -- verifico prima di tutto se lo stato della spedizione è LavorataPackaging
    select stato into vStatoSpedizione
    from spedizione
    where id = pIdSpedizione;

    if vStatoSpedizione <> 'LavorataPackaging' then
        raise_application_error(-20000, 'La spedizione non è in stato LavorataPackaging');
    end if;

    open cFilialiCoinvolte;

    loop
        fetch cFilialiCoinvolte into rec;
        exit when cFilialiCoinvolte%notfound;

        insert into OrdineDiLavoroSpedizione (DataCreazione, IdSpedizione, IdFiliale)
        values (sysdate, pIdSpedizione, rec.IDFILIALE) returning Id into vIdOrdineDiLavoroSpedizione;

        for p in (select p.id from pacco p
                           join MAGAZZINO m on p.IDMAGAZZINO = m.ID
                           where p.IDSPEDIZIONE = pIdSpedizione and m.IDFILIALE = rec.IDFILIALE) loop
            insert into OrdineDiLavoroSpedizionePacchi (IdOrdineDiLavoroSpedizione, IdPacco)
            values (vIdOrdineDiLavoroSpedizione, p.Id);
        end loop;
    end loop;

    close cFilialiCoinvolte;

    commit;

end;
