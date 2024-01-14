--crea tabella oracle Org in camel case senza underscore e con la prima lettera maiuscola
create table Org (
    id number(10) not null, --pk
    Paese varchar2(255 char) not null,
    RagioneSociale varchar2(255 char) not null,
    PartitaIva varchar2(255 char) not null,
    SedeLegaleIndirizzo number not null,
    constraint Org_pk primary key (id),
    constraint Org_SedeLegaleIndirizzo_fk foreign key (SedeLegaleIndirizzo) references Indirizzo (id)
);