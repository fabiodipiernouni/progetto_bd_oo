-- convezione nomi constraint: <Ck|Uk|Fk|Pk|WeakRel(fk con on delete cascade)><NomeTabella><Vincolo|NomeColonna>
-- convezione nomi: non si usano underscore e i nomi sono in camelcase

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
    Email    VARCHAR2(64 byte) not null unique,
    CodiceFiscale VARCHAR2(16 byte), -- ammette null ma i valori sono unique
    PartitaIVA VARCHAR2(16 byte), -- ammette null ma i valori sono unique
    constraint PKCliente primary key ("IDCliente"),
    --uno tra CodiceFiscale e partitaIVA deve essere valorizzato
    constraint CheckClienteCForPIVA check (CodiceFiscale is not null or PartitaIVA is not null),
    constraint UkClienteCodiceFiscale unique (CodiceFiscale),
    constraint UkClientePartitaIVA unique (PartitaIVA)
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
    IDCliente int not null,
    IdIndirizzoFatturazione int not null,
    IdIndirizzoSpedizione int, -- valorizzato se diverso da indirizzo di fatturazione
    constraint "PkOrdineCliente" primary key ("IDOrdine"),
    constraint "CkOrdineClienteStato" check ("Stato" in ('Confermato', 'Spedito', 'Consegnato', 'ResoInCorso', 'Reso', 'Annullato')),
    constraint WeakRelOrdineCliente ,
    constraint "FkOrdineClienteIndirizzoFatturazione" foreign key ("IdIndirizzoFatturazione") references "Indirizzo" ("Id"),
    constraint "FkOrdineClienteIndirizzoSpedizione" foreign key ("IdIndirizzoSpedizione") references "Indirizzo" ("Id")
);

create table DettaglioOrdine (
    IdOrdine int not null,
    IdProdotto int not null,
    Quantita int not null,
    constraint pk_DettaglioOrdine primary key (IdOrdine, IdProdotto),
    constraint fk_DettaglioOrdine_IdOrdine foreign key (IdOrdine) references OrdineCliente (IdOrdine),
    constraint fk_DettaglioOrdine_IdProdotto foreign key (IdProdotto) references CatalogoProdotti (Id)
);
