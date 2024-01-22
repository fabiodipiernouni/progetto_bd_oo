-- convezione nomi constraint: <Ck|Uk|Fk|Pk|WeakRel(fk con on delete cascade)><NomeTabella><Vincolo|NomeColonna>
-- convezione nomi: non si usano underscore e i nomi sono in camelcase

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
    constraint PkOrg primary key (id),
    constraint FkOrgSedeLegaleIndirizzo foreign key (SedeLegaleIndirizzo) references Indirizzo (Id)
);

begin
    execute immediate 'drop table Filiale cascade constraints purge';
exception when others then null;
end;

create table Filiale (
    Id integer not null, --pk
    Nome varchar2(50) not null,
    IdOrg integer not null,
    Localita integer not null,
    constraint pk_Filiale primary key (id),
    constraint FkOrg foreign key (IdOrg) references Org (Id),
    constraint FkLocalita foreign key (Localita) references Indirizzo (Id)
);

alter table Utente add constraint FkUtenteFiliale foreign key (Filiale) references Filiale (Id);

begin
    execute immediate 'drop table Magazzino cascade constraints purge';
exception when others then null;
end;

create table Magazzino (
    Id integer not null, --pk
    Nome varchar2(255 char) not null,
    IdIndirizzo integer not null,
    IdFiliale integer not null,
    constraint PkMagazzino_ primary key (id),
    constraint FkMagazzino_Indirizzo foreign key (IdIndirizzo) references Indirizzo (id),
    constraint FkMagazzino_Filiale foreign key (IdFiliale) references Filiale (id)
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
    constraint PkCatalogoProdotti primary key (id),
    constraint UqCatalogoProdottiCodiceEAN unique (CodiceEAN),
    constraint CheckProfondita check (Pericolosita in ('Nessuna', 'Infiammabile', 'Esplosivo', 'Tossico', 'Chimico', 'Corrosivo', 'Infettante', 'Radioattivo')),
    constraint CheckTipo check( Tipo in ('Abbigliamento', 'Alimentari', 'Elettronica', 'Casa', 'Sport', 'Giardino', 'Altro') )
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
    constraint PkMerceStoccatapk primary key (id),
    constraint FkMerceStoccataIdProdotto foreign key (IdProdotto) references CatalogoProdotti (id),
    constraint FkMerceStoccataIdMagazzino foreign key (IdMagazzino) references Magazzino (id)
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
    constraint PkGruppoCorriere primary key (Id),
    constraint UqGruppoCorriereCodiceCorriere unique (CodiceCorriere),
    constraint FkGruppoCorriereIdFiliale foreign key (IdFiliale) references filiale (id)
);

begin
    execute immediate 'drop table MezziTrasporto cascade constraints purge';
exception when others then null;
end;
/

create table MezzoDiTrasporto (
    Id integer not null, --pk
    Targa varchar2(255 char) not null,
    TipoMezzo varchar2(255 char) not null, --enum Treno, Camion, Furgone, Auto, Moto, Bicicletta
    IdGruppoCorriere integer not null,
    constraint PkMezzoDiTrasporto primary key (id),
    constraint UqMezzoDiTrasporto_Targa unique (Targa),
    constraint CheckTipoMezzo check( TipoMezzo in ('Treno', 'Camion', 'Furgone', 'Auto', 'Moto', 'Bicicletta')),
    constraint FkMezzoDiTrasportoIdGruppoCorriere foreign key (IdGruppoCorriere) references GruppoCorriere (Id)
);

begin
    execute immediate 'drop table ImpegnoMezzo cascade constraints purge';
exception when others then null;
end;
/

create table ImpegnoMezzo (
    Id integer not null, --pk
    IdMezzo integer not null,
    DataInizio date not null,
    DataFine date,
    constraint PkImpegnoMezzo primary key (Id),
    constraint FkImpegnoMezzoIdMezzo foreign key (IdMezzo) references MezzoDiTrasporto (Id)
);
