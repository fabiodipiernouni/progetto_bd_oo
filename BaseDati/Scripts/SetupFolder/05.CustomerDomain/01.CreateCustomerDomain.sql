begin
    execute immediate 'DROP TABLE Cliente CASCADE CONSTRAINTS PURGE';
exception when others then null;
end;

create table Cliente
(
    IdCliente integer not null,
    Nome VARCHAR2(64 byte) not null, -- se valorizzato RagioneSociale rappresenta il nome del referente
    Cognome VARCHAR2(64 byte) not null, -- se valorizzato RagioneSociale rappresenta il cognome del referente
    RagioneSociale VARCHAR2(64 byte),
    Email VARCHAR2(64 byte) not null unique,
    CodiceFiscale VARCHAR2(16 byte),
    PartitaIVA VARCHAR2(16 byte),
    constraint pk_Cliente primary key ("IdCliente")
);

begin
    execute immediate 'DROP TABLE OrdineCliente CASCADE CONSTRAINTS PURGE';
exception when others then null;
end;

create table OrdineCliente
(
    ID integer not null,
    DataOrdine DATE not null,
    ImportoTotale NUMBER(10,2) not null,
    Stato VARCHAR2(64 byte) not null, -- Stato dell'ordine: Confermato, Spedito, Consegnato, ResoInCorso, Reso, Annullato
    IdCliente integer not null,
    IdIndirizzoFatturazione integer not null,
    IdIndirizzoSpedizione integer, -- valorizzato se diverso da indirizzo di fatturazione
    constraint pk_OrdineCliente primary key (IdOrdine),
    constraint check_OrdineCliente_Stato check (Stato in ('Confermato', 'Spedito', 'Consegnato', 'ResoInCorso', 'Reso', 'Annullato')),
    constraint fk_OrdineCliente_IdCliente foreign key (IdCliente) references Cliente (IdCliente),
    constraint fk_OrdineCliente_IdIndirizzoFatturazione foreign key (IdIndirizzoFatturazione) references Indirizzo (Id),
    constraint fk_OrdineCliente_IdIndirizzoSpedizione foreign key (IdIndirizzoSpedizione) references Indirizzo (Id)
);

create table DettaglioOrdine (
    IdOrdine int not null,
    IdProdotto int not null,
    Quantita int not null,
    constraint pk_DettaglioOrdine primary key (IdOrdine, IdProdotto),
    constraint fk_DettaglioOrdine_IdOrdine foreign key (IdOrdine) references OrdineCliente (IdOrdine),
    constraint fk_DettaglioOrdine_IdProdotto foreign key (IdProdotto) references CatalogoProdotti (Id)
);
