/*
    Nome: OrdineCliente
    Descrizione: contiene i dati dell'ordine effettuato dal cliente
 */
create table OrdineCliente
(
    Id integer generated by default on null as identity not null,
    NumeroOrdine VARCHAR2(16 byte) not null, -- univoco, numero dell'ordine, generato dal sistema
    DataOrdine DATE not null,
    ImportoTotale NUMBER(10,2) not null,
	DataInizioLavorazione date,
	DataFineLavorazione date,
    Stato VARCHAR2(64 byte) not null, -- Stato dell'ordine: Bozza, Confermato, Completato, InLavorazione, Lavorato
    IDCliente integer not null,
    IdIndirizzoFatturazione integer not null,
    IdIndirizzoSpedizione integer, -- valorizzato se diverso da indirizzo di fatturazione
    constraint PkOrdineCliente primary key (Id),
    constraint UqOrdineClienteNumeroOrdine unique (NumeroOrdine),
    constraint WeakRelOrdineClienteCliente foreign key (IDCliente) references Cliente (Id) on delete cascade,
    constraint CkOrdineClienteStato check (Stato in ('Bozza', 'Confermato', 'Completato', 'InLavorazione', 'Lavorato')),
	constraint CkOrdineClienteDate check (DataInizioLavorazione is null and DataFineLavorazione is null or (DataInizioLavorazione is not null and (DataFineLavorazione is null or DataFineLavorazione >= DataInizioLavorazione))), --IdVincolo: VI.13
    constraint FkOrdineClienteIndirizzoFatturazione foreign key (IdIndirizzoFatturazione) references Indirizzo (Id),
    constraint FkOrdineClienteIndirizzoSpedizione foreign key (IdIndirizzoSpedizione) references Indirizzo (Id)
);