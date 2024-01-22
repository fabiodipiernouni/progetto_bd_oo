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
    PartitaIVA varchar2(255 char) not null, -- se valorizzata deve essere di 11 cifre
    SedeLegaleIndirizzo integer not null,
    constraint PkOrg primary key (id),
    constraint CkOrgPartitaIVA check (PartitaIVA is null or (length(PartitaIVA) = 11)),
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
    constraint PkFiliale primary key (id),
    constraint WeakRelOrg foreign key (IdOrg) references Org (Id) on delete cascade,
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
    constraint PkMagazzino primary key (id),
    constraint FkMagazzinoIndirizzo foreign key (IdIndirizzo) references Indirizzo (id),
    constraint FkMagazzinoFiliale foreign key (IdFiliale) references Filiale (id)
);

begin
    execute immediate 'drop table CatalogoProdotti cascade constraints purge';
exception when others then null;
end;

create table CatalogoProdotti (
    Id integer not null, --pk
    CodiceEAN varchar2(13 char) not null, --codice univoco, deve essere sempre lungo 13 caratteri
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
    constraint CkCatalogoProdottiCodiceEAN check (length(CodiceEAN) = 13),
    constraint CkCatalogoProdottiPericolosita check (Pericolosita in ('Nessuna', 'Infiammabile', 'Esplosivo', 'Tossico', 'Chimico', 'Corrosivo', 'Infettante', 'Radioattivo')),
    constraint CkCatalogoProdottiTipo check( Tipo in ('Abbigliamento', 'Alimentari', 'Elettronica', 'Casa', 'Sport', 'Giardino', 'Altro') )
);

begin
    execute immediate 'drop table MerceStoccata cascade constraints purge';
exception when others then null;
end;

create table MerceStoccata (
    Id integer not null, --pk
    IdProdotto integer not null,
    Quantita number not null, -- deve essere sempre maggiore di zero
    IdMagazzino integer not null,
    SettoreMagazzino varchar2(255 char),
    constraint PkMerceStoccatapk primary key (id),
    constraint FkMerceStoccataIdProdotto foreign key (IdProdotto) references CatalogoProdotti (id),
    constraint FkMerceStoccataIdMagazzino foreign key (IdMagazzino) references Magazzino (id),
    constraint CkMerceStoccataQuantita check (Quantita > 0)
);

begin
    execute immediate 'drop table GruppoCorriere cascade constraints purge';
exception when others then null;
end;

create table GruppoCorriere (
    Id integer not null, --pk
    CodiceCorriere varchar2(255 char) not null, --unique
    NumeroDipendenti integer not null, --deve essere sempre maggiore di zero
    IdFiliale integer not null,
    constraint PkGruppoCorriere primary key (Id),
    constraint UqGruppoCorriereCodiceCorriere unique (CodiceCorriere),
    constraint FkGruppoCorriereIdFiliale foreign key (IdFiliale) references filiale (id),
    constraint CkGruppoCorriereNumeroDipendenti check (NumeroDipendenti > 0)
);

begin
    execute immediate 'drop table MezzoDiTrasporto cascade constraints purge';
exception when others then null;
end;
/

create table MezzoDiTrasporto (
    Id integer GENERATED ALWAYS AS IDENTITY not null, --pk
    Targa varchar2(255 char) not null, -- unique
    TipoMezzo varchar2(255 char) not null, --enum Treno, Camion, Furgone, Auto, Moto, Bicicletta
    IdGruppoCorriere integer not null,
    constraint PkMezzoDiTrasporto primary key (id),
    constraint UkMezzoDiTrasportoTarga unique (Targa),
    constraint CkMezzoDiTrasportoTipoMezzo check( TipoMezzo in ('Treno', 'Camion', 'Furgone', 'Auto', 'Moto', 'Bicicletta')),
    constraint WeakRelGruppoCorriere foreign key (IdGruppoCorriere) references GruppoCorriere (Id) on delete cascade
);

begin
    execute immediate 'drop table ImpegnoMezzo cascade constraints purge';
exception when others then null;
end;
/

create table ImpegnoMezzo
(
    Id         integer not null, --pk
    IdMezzo    integer not null,
    DataInizio date    not null,
    DataFine   date, -- se non valorizzata si intende fino a data indefinita
    constraint PkImpegnoMezzo primary key (Id),
    constraint FkImpegnoMezzoIdMezzo foreign key (IdMezzo) references MezzoDiTrasporto (Id),
    --dataFine se valorizzato deve essere maggiore di datainizio
    constraint CkImpegnoMezzoDataFine check ( DataFine is null or (DataInizio <= DataFine) )
);

