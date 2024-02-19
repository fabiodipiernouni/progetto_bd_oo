create table DettaglioOrdineMagazzino
(
    IdDettaglioOrdine integer not null,
    IdMagazzino integer not null,
    constraint PkDettaglioOrdineMagazzino primary key (IdDettaglioOrdine, IdMagazzino),
    constraint FkDettaglioOrdineMagazzinoDettaglioOrdine foreign key (IdDettaglioOrdine) references DettaglioOrdine (Id) on delete cascade,
    constraint FkDettaglioOrdineMagazzinoMagazzino foreign key (IdMagazzino) references Magazzino (Id)
);