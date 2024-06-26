create table MezzoDiTrasporto (
    Id integer generated by default on null as identity not null, --pk
    Targa varchar2(255 char) not null, -- unique
    TipoMezzo varchar2(255 char) not null, --enum Camion, Furgone, Auto
    IdGruppoCorriere integer not null,
    PesoTrasportabile number(12,2) not null, -- deve essere sempre maggiore di zero
    constraint PkMezzoDiTrasporto primary key (id),
    constraint UqMezzoDiTrasportoTarga unique (Targa),
    constraint CkMezzoDiTrasportoTipoMezzo check( TipoMezzo in ('Camion', 'Furgone', 'Auto')),
    constraint CkMezzoDiTrasportoPesoTrasportabile check (PesoTrasportabile > 0), --IdVincolo: VI.08
    constraint WeakRelGruppoCorriere foreign key (IdGruppoCorriere) references GruppoCorriere (Id) on delete cascade
);