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
    constraint CkClienteCForPIVA check (CodiceFiscale is not null or PartitaIVA is not null),
    constraint UkClienteCodiceFiscale unique (CodiceFiscale),
    constraint UkClientePartitaIVA unique (PartitaIVA),
    -- se è valorizzata RagioneSociale allora deve esserlo anche PartitaIVA
    constraint CkClienteRagioneSociale check (RagioneSociale is null or PartitaIVA is not null)
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
    Stato VARCHAR2(64 byte) not null, -- Stato dell'ordine: Confermato, Spedito, Consegnato, ResoInCorso, ResoAvviato, ResoEffettuato, Annullato
    IDCliente int not null,
    IdIndirizzoFatturazione int not null,
    IdIndirizzoSpedizione int, -- valorizzato se diverso da indirizzo di fatturazione
    constraint PkOrdineCliente primary key ("IDOrdine"),
    constraint WeakRelOrdineClienteCliente foreign key ("IDCliente") references Cliente ("IDCliente") on delete cascade,
    constraint CkOrdineClienteStato check ("Stato" in ('Confermato', 'Spedito', 'Consegnato', 'ResoInCorso', 'ResoAvviato', 'ResoEffettuato', 'Annullato')),
    constraint FkOrdineClienteIndirizzoFatturazione foreign key ("IdIndirizzoFatturazione") references "Indirizzo" ("Id"),
    constraint FkOrdineClienteIndirizzoSpedizione foreign key ("IdIndirizzoSpedizione") references "Indirizzo" ("Id")
);

begin
    execute immediate 'DROP TABLE DettaglioOrdine CASCADE CONSTRAINTS PURGE';
exception when others then null;
end;

create table DettaglioOrdine (
    IdOrdine int not null,
    IdProdotto int not null,
    Quantita int not null, -- deve essere maggiore di zero
    constraint PkDettaglioOrdine primary key (IdOrdine, IdProdotto),
    constraint WeakRelDettaglioOrdineIdOrdine foreign key (IdOrdine) references OrdineCliente (IdOrdine) on delete cascade,
    constraint FkDettaglioOrdineIdProdotto foreign key (IdProdotto) references CatalogoProdotti (Id),
    constraint CkDettaglioOrdineQuantita check (Quantita > 0)
);
