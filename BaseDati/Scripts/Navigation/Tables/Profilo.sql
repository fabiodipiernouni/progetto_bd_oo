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