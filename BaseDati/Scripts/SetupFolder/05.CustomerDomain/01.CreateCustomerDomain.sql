begin
    execute immediate 'DROP TABLE "Cliente" CASCADE CONSTRAINTS PURGE';
exception when others then null;
end;

create table "Cliente"
(
    IDCliente int not null,
    Nome      VARCHAR2(64 byte) not null, -- se valorizzato RagioneSociale rappresenta il nome del referente
    Cognome   VARCHAR2(64 byte) not null, -- se valorizzato RagioneSociale rappresenta il cognome del referente
    RagioneSociale VARCHAR2(64 byte),
    Email    VARCHAR2(64 byte) not null unique,
    CodiceFiscale VARCHAR2(16 byte),
    partitaIVA VARCHAR2(16 byte),
    constraint "Cliente_PK" primary key ("IDCliente")
);

begin
    execute immediate 'DROP TABLE OrdineCliente CASCADE CONSTRAINTS PURGE';
exception when others then null;
end;

create table OrdineCliente
(
    ID int not null,
    DataOrdine DATE not null,
    ImportoTotale NUMBER(10,2) not null,
    Stato VARCHAR2(64 byte) not null, -- Stato dell'ordine: Confermato, Spedito, Consegnato, ResoInCorso, Reso, Annullato
    IDCliente int not null,
    IdIndirizzoFatturazione int not null,
    IdIndirizzoSpedizione int, -- valorizzato se diverso da indirizzo di fatturazione
    constraint "OrdineCliente_PK" primary key ("IDOrdine"),
    constraint "OrdineCliente_Stato_CK" check ("Stato" in ('Confermato', 'Spedito', 'Consegnato', 'ResoInCorso', 'Reso', 'Annullato')),
    constraint "OrdineCliente_Cliente_FK" foreign key ("IDCliente") references "Cliente" ("IDCliente"),
    constraint "OrdineCliente_IndirizzoFatturazione_FK" foreign key ("IdIndirizzoFatturazione") references "Indirizzo" ("Id"),
    constraint "OrdineCliente_IndirizzoSpedizione_FK" foreign key ("IdIndirizzoSpedizione") references "Indirizzo" ("Id")
);

create table DettaglioOrdine (
    IDOrdine int not null,
    IDProdotto int not null,
    Quantita int not null,
    constraint "DettaglioOrdine_PK" primary key ("IDOrdine", "IDProdotto"),
    constraint "DettaglioOrdine_OrdineCliente_FK" foreign key ("IDOrdine") references "OrdineCliente" ("Id"),
    constraint "DettaglioOrdine_Prodotto_FK" foreign key ("IDProdotto") references "CatalogoProdotti" ("Id")
);





