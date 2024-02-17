/*
    Nome: InizioLavorazioneSpedizione
    Descrizione: Procedura che permette di impostare la data di inizio lavorazione di un ordine di lavoro di spedizione
 */
create or replace procedure InizioLavorazioneSpedizione
(
  pIdOrdineLavoroSpedizione in number,
  pDataInizioLavorazione in date,
  pIdMezzoDiTrasporto in integer)
as
    vIdGruppoCorriere GruppoCorriere.Id%type;
    vIdMezzoDiTrasporto MezzoDiTrasporto.Id%type;
    vStato OrdineDiLavoroSpedizione.Stato%type;
    vDataInizioLavorazione date;
begin
    if pDataInizioLavorazione is null then
        vDataInizioLavorazione := sysdate;
    else
        vDataInizioLavorazione := pDataInizioLavorazione;
    end if;

    select stato, idGruppoCorriere into vStato, vIdGruppoCorriere
    from OrdineDiLavoroSpedizione
    where Id = pIdOrdineLavoroSpedizione;

    if vStato <> 'Assegnato' then
        raise_application_error(-20000, 'Lo stato dell''ordine di lavoro di spedizione non è Assegnato');
    end if;

    if pIdMezzoDiTrasporto is null then
        -- recupero il mezzo di trasporto disponibile, scelgo in ordine di capacità crescente (prendo il più piccolo)
        -- Supponiamo che la spedizione debba avvenire nei prossimi 7gg


        select id into vIdMezzoDiTrasporto
        from (
            select mt.Id
            from MEZZODITRASPORTO mt
            where
              mt.idGruppoCorriere = vIdGruppoCorriere
              and not exists (
                select distinct 1
                from IMPEGNOMEZZO im
                where
                    im.IDMEZZO = mt.ID
                    and (
                      (im.dataFine is null or im.dataFine > sysdate) or
                      (im.dataInizio between trunc(sysdate) and trunc(sysdate) + 7)
                    )
              )
            order by mt.pesotrasportabile
        )
        where rownum = 1;
    else
        vIdMezzoDiTrasporto := pIdMezzoDiTrasporto;
    end if;

    update OrdineDiLavoroSpedizione
    set DATAINIZIOLAVORAZIONE = vDataInizioLavorazione, IDMEZZODITRASPORTO = vIdMezzoDiTrasporto
    where Id = pIdOrdineLavoroSpedizione;
end InizioLavorazioneSpedizione;