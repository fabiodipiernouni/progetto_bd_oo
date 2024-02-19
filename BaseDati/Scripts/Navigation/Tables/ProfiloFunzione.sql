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