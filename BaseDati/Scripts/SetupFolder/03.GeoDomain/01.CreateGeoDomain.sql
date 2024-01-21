create or replace view regione as
select distinct codiceRegione as codIstat, denominazioneRegione as nome from ComuneFull
order by denominazioneRegione;

create or replace view provincia as
select distinct codiceProvincia as codIstat,
                case when denominazioneCittaMetropolitana = '-' then denominazioneProvincia else denominazioneCittaMetropolitana end as nome,
                CodiceRegione as codIstatRegione from ComuneFull
order by 2;

create or replace view comune as
select distinct id, codiceComuneFormatoNumerico as codIstat, denominazioneInItaliano as nome, CodiceProvincia as codIstatProvincia from ComuneFull
order by 3;

begin
    execute immediate 'drop table indirizzo cascade constraints purge';
exception when others then
    null;
end;
/

create table indirizzo (
    id integer not null,
    nome varchar2(50) not null,
    cognome varchar2(50) not null,
    CF_PIVA varchar2(16) not null,
    Indirizzo_1 varchar2(50) not null,
    Indirizzo_2 varchar2(50),
    CAP varchar2(5) not null,
    idComune integer not null,
    constraint pk_indirizzo primary key (id),
    constraint fk_indirizzo_comune foreign key (idComune) references ComuneFull(id)
);
