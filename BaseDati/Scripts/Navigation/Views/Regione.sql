create or replace view Regione as
    select
        distinct CodiceRegione as CodIstat,
        DenominazioneRegione as Nome
    from ComuneFull
    order by denominazioneRegione;