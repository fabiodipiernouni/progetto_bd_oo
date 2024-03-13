create table ImpegnoMezzo
(
    Id         integer generated by default on null as identity not null, --pk
    IdMezzo    integer not null,
    DataInizio date    not null,
    DataFine   date, -- se non valorizzata si intende fino a data indefinita
    constraint PkImpegnoMezzo primary key (Id),
    constraint FkImpegnoMezzoIdMezzo foreign key (IdMezzo) references MezzoDiTrasporto (Id),
    --dataFine se valorizzato deve essere maggiore di datainizio
    constraint CkImpegnoMezzoDataFine check ( DataFine is null or (DataInizio <= DataFine) ) --IdVincolo: VI.09
);