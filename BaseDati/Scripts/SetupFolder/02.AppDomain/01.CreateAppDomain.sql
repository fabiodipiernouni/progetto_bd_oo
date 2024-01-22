-- convezione nomi constraint: <Ck|Uk|Fk|Pk|WeakRel(fk con on delete cascade)><NomeTabella><Vincolo|NomeColonna>
-- convezione nomi: non si usano underscore e i nomi sono in camelcase

begin
    execute immediate 'DROP TABLE Utente CASCADE CONSTRAINTS PURGE';
exception when others then null;
end;
/

create table Utente
(
    Id integer not null,
    Username varchar2(255) not null,
    Password Varchar2(128) not null,
    IdFilialeOperatore integer,
    constraint PkUtente primary key (Id),
    constraint UqUtenteUsername unique (Username)
)
/

comment on table "Utente" is 'La tabella contiene le utenze che hanno accesso all''applicativo uninaDelivery'
/

comment on column "Utente"."Username" is 'Username è intesa la matricola UNINA'
/

begin
    execute immediate 'DROP TABLE Profilo CASCADE CONSTRAINTS PURGE';
exception when others then null;
end;
/

create table Profilo
(
    Id integer not null,
    Profilo VARCHAR2(64 byte) not null,
    constraint PkProfilo primary key (Id),
    constraint UqProfiloProfilo unique (Profilo)
)
/

comment on table "Profilo" is 'Contiene i diversi tipi di profili sui quali sono configurate le opportune funzionalità dell''applicativo UninaDelivery'
/

comment on column "Profilo"."Profilo" is 'Profilo che sarà assegnato ad un utente.'
/

begin
    execute immediate 'DROP TABLE Funzione CASCADE CONSTRAINTS PURGE';
exception when others then null;
end;
/

create table Funzione
(
    Id integer not null,
    "Funzione" VARCHAR2(64 byte),
    constraint PkFunzione primary key (Id)
    constraint UqFunzioneFunzione unique (Funzione)
)
/

comment on table Funzione is 'La tabella contiene un elenco di funzionalità che andranno poi a caratterizzare parte dell''applicativo UninaDelivery.'
/

comment on column Funzione.Funzione is 'Funzione che sarà assegnata ad un profilo.'
/

begin
    execute immediate 'DROP TABLE ProfiloFunzione CASCADE CONSTRAINTS PURGE';
exception when others then null;
end;
/

create table ProfiloFunzione
(
    "IdProfilo"  int,
    "IdFunzione" int,
    constraint PkProfiloFunzione primary key (IdProfilo, IdFunzione),
    constraint FkProfiloFunzioneIdProfilo foreign key (IdProfilo) references Profilo (Id),
    constraint FkProfiloFunzioneIdFunzione foreign key (IdFunzione) references Funzione (Id)
)
/

comment on table "ProfiloFunzione" is 'Tabella associativa dove per ogni profilo sono associate una o più funzioni.'
/
