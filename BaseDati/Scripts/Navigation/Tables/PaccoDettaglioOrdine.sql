/*
    Nome: PaccoDettaglioOrdine
    Descrizione: riporta quali prodotti ordinati dal cliente contiene il pacco. E' un derivato di PackagingDetails.
 */
create table PaccoDettaglioOrdine (
    IdPacco integer not null,
    IdDettaglioOrdine integer not null,
    constraint PkPaccoDettaglioOrdine primary key (IdPacco, IdDettaglioOrdine),
    constraint WeakRelPaccoDettaglioOrdineIdPacco foreign key (IdPacco) references Pacco (Id) on delete cascade,
    constraint FkPaccoDettaglioOrdineIdDettaglioOrdine foreign key (IdDettaglioOrdine) references DettaglioOrdine (Id)
);