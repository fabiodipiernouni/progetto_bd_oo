begin
    execute immediate 'DROP TABLE "Utente" CASCADE CONSTRAINTS PURGE';
exception when others then null;
end;
/

create table "Utente"
(
    "IdUtente"  int
        primary key,
    "Username"  varchar2(255) not null
        unique,
    "Password"  Varchar2(128) not null,
    "IDFiliale" int
)
/

comment on table "Utente" is 'La tabella contiene le utenze che hanno accesso all''applicativo uninaDelivery'
/

comment on column "Utente"."Username" is 'Username è intesa la matricola UNINA'
/

begin
    execute immediate 'DROP TABLE "Profilo" CASCADE CONSTRAINTS PURGE';
exception when others then null;
end;
/

create table "Profilo"
(
    "IDProfilo" int
        primary key,
    "Profilo"   VARCHAR2(64 byte) not null
        unique
)
/

comment on table "Profilo" is 'Contiene i diversi tipi di profili sui quali sono configurate le opportune funzionalità dell''applicativo UninaDelivery'
/

comment on column "Profilo"."Profilo" is 'Profilo che sarà assegnato ad un utente.'
/

begin
    execute immediate 'DROP TABLE "Funzione" CASCADE CONSTRAINTS PURGE';
exception when others then null;
end;
/

create table "Funzione"
(
    "IDFunzione" int
        primary key,
    "Funzione"   VARCHAR2(64 byte)
        unique
)
/

comment on table "Funzione" is 'La tabella contiene un elenco di funzionalità che andranno poi a caratterizzare parte dell''applicativo UninaDelivery.'
/

comment on column "Funzione"."Funzione" is 'Funzione che sarà assegnata ad un profilo.'
/

begin
    execute immediate 'DROP TABLE "ProfiloFunzione" CASCADE CONSTRAINTS PURGE';
exception when others then null;
end;
/

create table "ProfiloFunzione"
(
    "IDProfilo"  int,
    "IDFunzione" int,
    constraint "ProfiloFunzione_PK" primary key ("IDProfilo", "IDFunzione")
)
/

comment on table "ProfiloFunzione" is 'Tabella associativa dove per ogni profilo sono associate una o più funzioni.'
/

alter table "ProfiloFunzione"
    add (constraint "ProfiloFunzione_FK1" foreign key ("IDProfilo") references "Profilo" ("IDProfilo"),
    constraint "ProfiloFunzione_FK2" foreign key ("IDFunzione") references "Funzione" ("IDFunzione"));
/