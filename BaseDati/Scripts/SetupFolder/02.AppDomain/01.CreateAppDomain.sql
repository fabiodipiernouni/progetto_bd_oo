-- convezione nomi constraint: <Ck|Uk|Fk|Pk|WeakRel(fk con on delete cascade)><NomeTabella><Vincolo|NomeColonna>
-- convezione nomi: non si usano underscore e i nomi sono in camelcase

create table Profilo
(
    Id integer not null,
    Profilo VARCHAR2(64 byte) not null,
    constraint PkProfilo primary key (Id),
    constraint UqProfiloProfilo unique (Profilo)
);
/

comment on table Profilo is 'Contiene i diversi tipi di profili sui quali sono configurate le opportune funzionalità dell''applicativo UninaDelivery'
/

comment on column Profilo.Profilo is 'Profilo che sarà assegnato ad un utente.'
/

create table Utente
(
    Id integer not null,
    Username varchar2(255) not null,
    Password Varchar2(128) not null,
    MatricolaUnina varchar2(255),
    IdProfilo integer not null,
    IdFilialeOperatore integer,
    IdGruppoCorriere integer,
    constraint PkUtente primary key (Id),
    constraint UqUtenteUsername unique (Username),
    constraint UqUtenteMatricolaUnina unique (MatricolaUnina),
    constraint FkUtenteProfilo foreign key (IdProfilo) references Profilo (Id)
)
/

comment on table Utente is 'La tabella contiene le utenze che hanno accesso all''applicativo uninaDelivery'
/

comment on column Utente.Username is 'Username è intesa la matricola UNINA'
/

create table Funzione
(
    Id integer not null,
    Funzione varchar2(64 byte) not null,
    constraint PkFunzione primary key (Id),
    constraint UqFunzioneFunzione unique (Funzione)
)
/

comment on table Funzione is 'La tabella contiene un elenco di funzionalità che andranno poi a caratterizzare parte dell''applicativo UninaDelivery.'
/

comment on column Funzione.Funzione is 'Funzione che sarà assegnata ad un profilo.'
/

create table ProfiloFunzione
(
    IdProfilo  integer not null,
    IdFunzione integer not null,
    constraint PkProfiloFunzione primary key (IdProfilo, IdFunzione),
    constraint FkProfiloFunzioneIdProfilo foreign key (IdProfilo) references Profilo (Id),
    constraint FkProfiloFunzioneIdFunzione foreign key (IdFunzione) references Funzione (Id)
)
/

comment on table ProfiloFunzione is 'Tabella associativa dove per ogni profilo sono associate una o più funzioni.'
/
