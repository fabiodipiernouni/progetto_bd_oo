create or replace view Provincia as
    select
        distinct CodiceProvincia as CodIstat,
        case
            when DenominazioneCittaMetropolitana = '-' then DenominazioneProvincia
            else DenominazioneCittaMetropolitana end as Nome,
        CodiceRegione as CodIstatRegione
    from ComuneFull
    order by 2;