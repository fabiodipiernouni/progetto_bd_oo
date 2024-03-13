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

alter table Utente add constraint FkUtenteFiliale foreign key (IdFilialeOperatore) references Filiale (Id);

alter table Utente add constraint FkUtenteGruppoCorriere foreign key (IdGruppoCorriere) references GruppoCorriere (Id);