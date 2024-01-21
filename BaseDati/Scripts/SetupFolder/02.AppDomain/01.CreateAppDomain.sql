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
    IdFiliale integer,
    constraint pk_Utente primary key (Id),
    constraint uq_Utente_Username unique (Username),
    constraint fk_Utente_IdFiliale foreign key (IdFiliale) references Filiale (Id)
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
    constraint pk_Profilo primary key (Id),
    constraint uq_Profilo_Profilo unique (Profilo)
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
    constraint pk_Funzione primary key (Id)
    constraint uq_Funzione_Funzione unique (Funzione)
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
    constraint pk_ProfiloFunzione primary key (IdProfilo, IdFunzione),
    constraint fk_ProfiloFunzione_IdProfilo foreign key (IdProfilo) references Profilo (Id),
    constraint fk_ProfiloFunzione_IdFunzione foreign key (IdFunzione) references Funzione (Id)
)
/

comment on table "ProfiloFunzione" is 'Tabella associativa dove per ogni profilo sono associate una o più funzioni.'
/
