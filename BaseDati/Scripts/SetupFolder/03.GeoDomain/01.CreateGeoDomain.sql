-- convezione nomi constraint: <Ck|Uk|Fk|Pk|WeakRel(fk con on delete cascade)><NomeTabella><Vincolo|NomeColonna>
-- convezione nomi: non si usano underscore e i nomi sono in camelcase

create or replace view Regione as
    select
        distinct CodiceRegione as CodIstat,
        DenominazioneRegione as Nome
    from ComuneFull
    order by denominazioneRegione;

create or replace view Provincia as
    select
        distinct CodiceProvincia as CodIstat,
        case
            when DenominazioneCittaMetropolitana = '-' then DenominazioneProvincia
            else DenominazioneCittaMetropolitana end as Nome,
        CodiceRegione as CodIstatRegione
    from ComuneFull
    order by 2;

create or replace view Comune as
    select
        distinct Id,
        CodiceComuneFormatoNumerico as CodIstat,
        DenominazioneInItaliano as Nome,
        CodiceProvincia as CodIstatProvincia
    from ComuneFull
    order by 3;

begin
    execute immediate 'drop table Indirizzo cascade constraints purge';
exception when others then
    null;
end;
/

create table Indirizzo (
    Id integer not null,
    Nome varchar2(50) not null,
    Cognome varchar2(50) not null,
    CF_PIVA varchar2(16) not null,
    Indirizzo_1 varchar2(50) not null,
    Indirizzo_2 varchar2(50),
    CAP varchar2(5) not null,
    IdComune integer not null,
    constraint PkIndirizzo primary key (Id),
    constraint FkIndirizzoIdComune foreign key (IdComune) references ComuneFull(Id)
);
