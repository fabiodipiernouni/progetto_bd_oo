create table GruppoCorriere (
    Id integer not null, --pk
    Nome varchar2(255 char) not null,
    CodiceCorriere varchar2(255 char) not null, --unique
    NumeroDipendenti integer not null, --deve essere sempre maggiore di zero
    IdFiliale integer not null,
    constraint PkGruppoCorriere primary key (Id),
    constraint UqGruppoCorriereCodiceCorriere unique (CodiceCorriere),
    constraint FkGruppoCorriereIdFiliale foreign key (IdFiliale) references filiale (id),
    constraint CkGruppoCorriereNumeroDipendenti check (NumeroDipendenti > 0) --IdVincolo: VI.07
);