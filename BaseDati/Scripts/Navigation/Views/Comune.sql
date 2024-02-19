create or replace view Comune as
    select
        distinct Id,
        CodiceComuneFormatoNumerico as CodIstat,
        DenominazioneInItaliano as Nome,
        CodiceProvincia as CodIstatProvincia
    from ComuneFull
    order by 3;