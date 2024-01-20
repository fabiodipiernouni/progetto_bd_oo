--crea tabella oracle Org in camel case senza underscore e con la prima lettera maiuscola

begin
    execute immediate 'drop table Org cascade constraints purge';
exception when others then null;
end;

create table Org (
    id number(10) not null, --pk
    Paese varchar2(255 char) not null,
    RagioneSociale varchar2(255 char) not null,
    PartitaIva varchar2(255 char) not null,
    SedeLegaleIndirizzo number not null,
    constraint Org_pk primary key (id),
    constraint Org_SedeLegaleIndirizzo_fk foreign key (SedeLegaleIndirizzo) references Indirizzo (id)
);

begin
    execute immediate 'drop table filiale cascade constraints purge';
exception when others then null;
end;

create table filiale (
    id number(10) not null, --pk
    Sede number not null,
    constraint filiale_pk primary key (id),
    constraint filiale_Sede_fk foreign key (Sede) references Indirizzo (id)
);

begin
    execute immediate 'drop table Magazzino cascade constraints purge';
exception when others then null;
end;

create table Magazzino (
    id number(10) not null, --pk
    Nome varchar2(255 char) not null,
    Indirizzo number not null,
    constraint Magazzino_pk primary key (id),
    constraint Magazzino_Indirizzo_fk foreign key (Indirizzo) references Indirizzo (id)
);

begin
    execute immediate 'drop table CatalogoProdotti cascade constraints purge';
exception when others then null;
end;

create table CatalogoProdotti (
    id number(10) not null, --pk
    CodiceEan varchar2(255 char) not null,
    Nome varchar2(255 char) not null,
    Descrizione varchar2(255 char) not null,
    UrlPhoto varchar2(255 char) not null,
    Tipo varchar2(20 byte) not null, --enum Abbigliamento, Alimentari, Elettronica, Casa, Sport, Giardino, Altro
    Prezzo number(12,2) not null,
    Peso number,
    Larghezza number,
    Altezza number,
    Profondita number,
    Pericolosita varchar2(20 byte) not null, --enum Nessuna, Infiammabile, Esplosivo, Tossico, Chimico, Corrosivo, Infettante, Radioattivo
    constraint CatalogoProdotti_pk primary key (id),
    constraint ProfonditaCheck check (Pericolosita in ('Nessuna', 'Infiammabile', 'Esplosivo', 'Tossico', 'Chimico', 'Corrosivo', 'Infettante', 'Radioattivo')),
    constraint CheckTipo check( Tipo in ('Abbigliamento', 'Alimentari', 'Elettronica', 'Casa', 'Sport', 'Giardino', 'Altro') )
);

begin
    execute immediate 'drop table MerceStoccata cascade constraints purge';
exception when others then null;
end;

create table MerceStoccata (
    id number(10) not null, --pk
    IdProdotto number not null,
    Quantita number not null,
    IdMagazzino number not null,
    SettoreMagazzino varchar2(255 char),
    constraint MerceStoccata_pk primary key (id),
    constraint MerceStoccata_IdProdotto_fk foreign key (IdProdotto) references CatalogoProdotti (id),
    constraint MerceStoccata_IdMagazzino_fk foreign key (IdMagazzino) references Magazzino (id)
);

begin
    execute immediate 'drop table GruppoCorriere cascade constraints purge';
exception when others then null;
end;

create table GruppoCorriere (
    id number(10) not null, --pk
    CodiceCorriere varchar2(255 char) not null,
    NumeroDipendenti number not null,
    IdFiliale number not null,
    constraint GruppoCorriere_pk primary key (id),
    constraint GruppoCorriere_IdFiliale_fk foreign key (IdFiliale) references filiale (id)
);

begin
    execute immediate 'drop table MezziTrasporto cascade constraints purge';
exception when others then null;
end;

create table MezziTrasporto (
    id number(10) not null, --pk
    Targa varchar2(255 char) not null,
    TipoMezzo varchar2(255 char) not null, --enum Treno, Camion, Furgone, Auto, Moto, Bicicletta
    constraint MezziTrasporto_pk primary key (id),
    constraint CheckTipoMezzo check( TipoMezzo in ('Treno', 'Camion', 'Furgone', 'Auto', 'Moto', 'Bicicletta'))
);

