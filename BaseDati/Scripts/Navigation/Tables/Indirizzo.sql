create table Indirizzo (
    Id integer not null,
    Nome varchar2(50) not null,
    Cognome varchar2(50) not null,
    CF_PIVA varchar2(16) not null,
    Indirizzo_1 varchar2(50) not null,
    Indirizzo_2 varchar2(50),
    CAP varchar2(5) not null,
    IdComune integer not null,
    noteAggiuntive varchar2(512 byte),
    constraint PkIndirizzo primary key (Id),
    constraint FkIndirizzoIdComune foreign key (IdComune) references ComuneFull(Id)
);
