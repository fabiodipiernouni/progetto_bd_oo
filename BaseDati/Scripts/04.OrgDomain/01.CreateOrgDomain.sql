begin
    execute immediate 'drop table Org cascade constraints purge';
exception when others then null;
end;

create table Org (
    Id integer not null, --pk
    Paese varchar2(255 char) not null,
    RagioneSociale varchar2(255 char) not null,
    PartitaIVA varchar2(255 char) not null,
    SedeLegaleIndirizzo integer not null,
    constraint pk_Org primary key (id),
    constraint fk_Org_SedeLegaleIndirizzo foreign key (SedeLegaleIndirizzo) references Indirizzo (Id)
);

begin
    execute immediate 'drop table Filiale cascade constraints purge';
exception when others then null;
end;

create table Filiale (
    Id integer not null, --pk
    Nome varchar2(50) not null,
    Sede integer not null,
    constraint pk_Filiale primary key (id),
    constraint fk_Filiale_Sede foreign key (Sede) references Indirizzo (Id)
);

begin
    execute immediate 'drop table Magazzino cascade constraints purge';
exception when others then null;
end;

create table Magazzino (
    Id integer not null, --pk
    Nome varchar2(255 char) not null,
    IdIndirizzo integer not null,
    IdFiliale integer not null,
    constraint pk_Magazzino_ primary key (id),
    constraint fk_Magazzino_Indirizzo foreign key (IdIndirizzo) references Indirizzo (id),
    constraint fk_Magazzino_Filiale foreign key (IdFiliale) references Filiale (id)
);

begin
    execute immediate 'drop table CatalogoProdotti cascade constraints purge';
exception when others then null;
end;

create table CatalogoProdotti (
    Id integer not null, --pk
    CodiceEAN varchar2(13 char) not null, --codice univoco
    Nome varchar2(255 char) not null,
    Descrizione varchar2(255 char) not null,
    URLPhoto varchar2(255 char) not null,
    Tipo varchar2(20 byte) not null, --enum Abbigliamento, Alimentari, Elettronica, Casa, Sport, Giardino, Altro
    Prezzo double precision not null,
    Peso number,
    Larghezza number,
    Altezza number,
    Profondita number,
    Pericolosita varchar2(20 byte) not null, --enum Nessuna, Infiammabile, Esplosivo, Tossico, Chimico, Corrosivo, Infettante, Radioattivo
    constraint pk_CatalogoProdotti primary key (id),
    constraint uq_CatalogoProdotti_CodiceEAN unique (CodiceEAN),
    constraint check_Profondita check (Pericolosita in ('Nessuna', 'Infiammabile', 'Esplosivo', 'Tossico', 'Chimico', 'Corrosivo', 'Infettante', 'Radioattivo')),
    constraint check_Tipo check( Tipo in ('Abbigliamento', 'Alimentari', 'Elettronica', 'Casa', 'Sport', 'Giardino', 'Altro') )
);

begin
    execute immediate 'drop table MerceStoccata cascade constraints purge';
exception when others then null;
end;

create table MerceStoccata (
    Id integer not null, --pk
    IdProdotto integer not null,
    Quantita number not null,
    IdMagazzino integer not null,
    SettoreMagazzino varchar2(255 char),
    constraint pk_MerceStoccata_pk primary key (id),
    constraint fk_MerceStoccata_IdProdotto foreign key (IdProdotto) references CatalogoProdotti (id),
    constraint fk_MerceStoccata_IdMagazzino foreign key (IdMagazzino) references Magazzino (id)
);

begin
    execute immediate 'drop table GruppoCorriere cascade constraints purge';
exception when others then null;
end;

create table GruppoCorriere (
    Id integer not null, --pk
    CodiceCorriere varchar2(255 char) not null, --unique
    NumeroDipendenti integer not null,
    IdFiliale integer not null,
    constraint pk_GruppoCorriere primary key (Id),
    constraint uq_GruppoCorriere_CodiceCorriere unique (CodiceCorriere),
    constraint fk_GruppoCorriere_IdFiliale foreign key (IdFiliale) references filiale (id)
);

begin
    execute immediate 'drop table MezziTrasporto cascade constraints purge';
exception when others then null;
end;

create table MezziTrasporto (
    Id integer not null, --pk
    Targa varchar2(255 char) not null,
    TipoMezzo varchar2(255 char) not null, --enum Treno, Camion, Furgone, Auto, Moto, Bicicletta
    constraint pk_MezziTrasporto primary key (id),
    constraint check_TipoMezzo check( TipoMezzo in ('Treno', 'Camion', 'Furgone', 'Auto', 'Moto', 'Bicicletta'))
);
