insert into org (id, RagioneSociale, Paese, partitaIva) values (1, 'Unina Delivery ITA', 'Italia', '12345678901');
commit;

/*
 create table indirizzo (
    id integer not null,
    nome varchar2(50) not null,
    cognome varchar2(50) not null,
    CF_PIVA varchar2(16) not null,
    Indirizzo_1 varchar2(50) not null,
    Indirizzo_2 varchar2(50),
    CAP varchar2(5) not null,
    idComune integer not null,
    constraint pk_indirizzo primary key (id),
    constraint fk_indirizzo_comune foreign key (idComune) references ComuneFull(id)
);

 */

-- Indirizzi filiali
insert into INDIRIZZO (id, nome, cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, idComune) values (1, 'Mario', 'Rossi', 'RSSMRA01A01A001A', 'Via Roma 12', null '80055', 5249); -- Roma
insert into INDIRIZZO (id, nome, cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, idComune) values (2, 'Giuseppe', 'Verdi', 'VRDGPP01A01A001A', 'Via Napoli 22', null, '80055', 6075); --Napoli
insert into INDIRIZZO (id, nome, cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, idComune) values (3, 'Luigi', 'Bianchi', 'BNCGLU01A01A001A', 'Via Milano 32', null, '80055', 1708); --Milano
insert into INDIRIZZO (id, nome, cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, idComune) values (4, 'Giovanni', 'Verdi', 'VRDGNN01A01A001A', 'Via Torino 42', null, '80055', 1708); --Torino
insert into INDIRIZZO (id, nome, cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, idComune) values (5, 'Giovanni', 'Rossi', 'RSSGNN01A01A001A', 'Via Firenze 52', null, '80055', 4284); --Firenze
insert into INDIRIZZO (id, nome, cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, idComune) values (6, 'Giovanni', 'Bianchi', 'BNCNNI01A01A001A', 'Via Bologna 62', null, '80055', 4514); --Bologna
insert into INDIRIZZO (id, nome, cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, idComune) values (7, 'Giovanni', 'Verdi', 'VRDGNN01A01A001A', 'Via Palermo 72', null, '80055', 6460); --Palermo

-- todo, inserire altri indirizzi causali

/*
 create table filiale (
    id integer not null, --pk
    nome varchar2(50) not null,
    Sede integer not null,
    constraint filiale_pk primary key (id),
    constraint filiale_Sede_fk foreign key (Sede) references Indirizzo (id)
);
 */

-- supponiamo una configurazione iniziale di 4 filiali
insert into FILIALE (id, nome, Sede) values (1, 'Filiale Napoli', 2);
insert into FILIALE (id, nome, Sede) values (2, 'Filiale Milano', 3);
insert into FILIALE (id, nome, Sede) values (3, 'Filiale Torino', 4);
insert into FILIALE (id, nome, Sede) values (4, 'Filiale Roma', 1);

/*
create table GruppoCorriere (
    id integer not null, --pk
    nome varchar2(50) not null,
    CodiceCorriere varchar2(255 char) not null, -- unico
    NumeroDipendenti integer not null,
    IdFiliale integer not null,
    constraint GruppoCorriere_pk primary key (id),
    constraint GruppoCorriere_IdFiliale_fk foreign key (IdFiliale) references filiale (id)
);
 */

-- gruppi corrieri della filiale 1, supponiamo 3 gruppi a filiale
insert into GruppoCorriere (id, nome, CodiceCorriere, NumeroDipendenti, IdFiliale) values (1, 'Gruppo Corrieri Napoli 1', 'NAP1', 10, 1);
insert into GruppoCorriere (id, nome, CodiceCorriere, NumeroDipendenti, IdFiliale) values (2, 'Gruppo Corrieri Napoli 2', 'NAP2', 10, 1);
insert into GruppoCorriere (id, nome, CodiceCorriere, NumeroDipendenti, IdFiliale) values (3, 'Gruppo Corrieri Napoli 3', 'NAP3', 10, 1);

-- gruppi corrieri della filiale 2, supponiamo 3 gruppi a filiale
insert into GruppoCorriere (id, nome, CodiceCorriere, NumeroDipendenti, IdFiliale) values (4, 'Gruppo Corrieri Milano 1', 'MIL1', 10, 2);
insert into GruppoCorriere (id, nome, CodiceCorriere, NumeroDipendenti, IdFiliale) values (5, 'Gruppo Corrieri Milano 2', 'MIL2', 10, 2);
insert into GruppoCorriere (id, nome, CodiceCorriere, NumeroDipendenti, IdFiliale) values (6, 'Gruppo Corrieri Milano 3', 'MIL3', 10, 2);

-- gruppi corrieri della filiale 3, supponiamo 3 gruppi a filiale
insert into GruppoCorriere (id, nome, CodiceCorriere, NumeroDipendenti, IdFiliale) values (7, 'Gruppo Corrieri Torino 1', 'TOR1', 10, 3);
insert into GruppoCorriere (id, nome, CodiceCorriere, NumeroDipendenti, IdFiliale) values (8, 'Gruppo Corrieri Torino 2', 'TOR2', 10, 3);
insert into GruppoCorriere (id, nome, CodiceCorriere, NumeroDipendenti, IdFiliale) values (9, 'Gruppo Corrieri Torino 3', 'TOR3', 10, 3);

-- gruppi corrieri della filiale 4, supponiamo 3 gruppi a filiale
insert into GruppoCorriere (id, nome, CodiceCorriere, NumeroDipendenti, IdFiliale) values (10, 'Gruppo Corrieri Roma 1', 'ROM1', 10, 4);
insert into GruppoCorriere (id, nome, CodiceCorriere, NumeroDipendenti, IdFiliale) values (11, 'Gruppo Corrieri Roma 2', 'ROM2', 10, 4);
insert into GruppoCorriere (id, nome, CodiceCorriere, NumeroDipendenti, IdFiliale) values (12, 'Gruppo Corrieri Roma 3', 'ROM3', 10, 4);

/*
create table Magazzino (
    id integer not null, --pk
    Nome varchar2(255 char) not null,
    IdIndirizzo integer not null,
    IdFiliale integer not null,
    constraint Magazzino_pk primary key (id),
    constraint Magazzino_Indirizzo_fk foreign key (IdIndirizzo) references Indirizzo (id),
    constraint Magazzino_Filiale_fk foreign key (IdFiliale) references Filiale (id)
);

 */

-- supponiamo una configurazione iniziale di 3 magazzini a filiale
insert into Magazzino (id, Nome, IdIndirizzo, IdFiliale) values (1, 'Magazzino Napoli 1', 2, 1);
insert into Magazzino (id, Nome, IdIndirizzo, IdFiliale) values (2, 'Magazzino Napoli 2', 2, 1);
insert into Magazzino (id, Nome, IdIndirizzo, IdFiliale) values (3, 'Magazzino Napoli 3', 2, 1);
insert into Magazzino (id, Nome, IdIndirizzo, IdFiliale) values (4, 'Magazzino Milano 1', 3, 2);
insert into Magazzino (id, Nome, IdIndirizzo, IdFiliale) values (5, 'Magazzino Milano 2', 3, 2);
insert into Magazzino (id, Nome, IdIndirizzo, IdFiliale) values (6, 'Magazzino Milano 3', 3, 2);
insert into Magazzino (id, Nome, IdIndirizzo, IdFiliale) values (7, 'Magazzino Torino 1', 4, 3);
insert into Magazzino (id, Nome, IdIndirizzo, IdFiliale) values (8, 'Magazzino Torino 2', 4, 3);
insert into Magazzino (id, Nome, IdIndirizzo, IdFiliale) values (9, 'Magazzino Torino 3', 4, 3);
insert into Magazzino (id, Nome, IdIndirizzo, IdFiliale) values (10, 'Magazzino Roma 1', 1, 4);
insert into Magazzino (id, Nome, IdIndirizzo, IdFiliale) values (11, 'Magazzino Roma 2', 1, 4);
insert into Magazzino (id, Nome, IdIndirizzo, IdFiliale) values (12, 'Magazzino Roma 3', 1, 4);

/*
create table CatalogoProdotti (
    id integer not null, --pk
    CodiceEan varchar2(13 char) not null, --codice numerico univoco
    Nome varchar2(255 char) not null,
    Descrizione varchar2(255 char) not null,
    UrlPhoto varchar2(255 char) not null,
    Tipo varchar2(20 byte) not null, --enum Abbigliamento, Alimentari, Elettronica, Casa, Sport, Giardino, Altro
    Prezzo double not null,
    Peso number,
    Larghezza number,
    Altezza number,
    Profondita number,
    Pericolosita varchar2(20 byte) not null, --enum Nessuna, Infiammabile, Esplosivo, Tossico, Chimico, Corrosivo, Infettante, Radioattivo
    constraint CatalogoProdotti_pk primary key (id),
    constraint ProfonditaCheck check (Pericolosita in ('Nessuna', 'Infiammabile', 'Esplosivo', 'Tossico', 'Chimico', 'Corrosivo', 'Infettante', 'Radioattivo')),
    constraint CheckTipo check( Tipo in ('Abbigliamento', 'Alimentari', 'Elettronica', 'Casa', 'Sport', 'Giardino', 'Altro') )
);
 */

-- supponiamo una configurazione iniziale di 100 prodotti di tipi diversi tra loro
insert into CatalogoProdotti (id, CodiceEan, Nome, Descrizione, UrlPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) values (1, '1234567890123', 'Pasta Barilla', 'Pasta Barilla', 'https://www.barilla.com/it-it/prodotti/pasta/classiche/spaghetti-n-5', 'Alimentari', 1.5, 0.5, 0.5, 0.5, 0.5, 'Nessuna');
insert into CatalogoProdotti (id, CodiceEan, Nome, Descrizione, UrlPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) values (2, '1234567890124', 'Pasta De Cecco', 'Pasta De Cecco', 'https://www.dececco.com/it_it/prodotti/pasta/classiche/spaghetti-n-12', 'Alimentari', 1.5, 0.5, 0.5, 0.5, 0.5, 'Nessuna');
insert into CatalogoProdotti (id, CodiceEan, Nome, Descrizione, UrlPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) values (3, '1234567890125', 'Pasta Divella', 'Pasta Divella', 'https://www.divella.it/it/prodotti/pasta/classiche/spaghetti-n-7', 'Alimentari', 1.5, 0.5, 0.5, 0.5, 0.5, 'Nessuna');
insert into CatalogoProdotti (id, CodiceEan, Nome, Descrizione, UrlPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) values (4, '1234567890126', 'Pasta Voiello', 'Pasta Voiello', 'https://www.voiello.it/it/prodotti/pasta/classiche/spaghetti-n-6', 'Alimentari', 1.5, 0.5, 0.5, 0.5, 0.5, 'Nessuna');
insert into CatalogoProdotti (id, CodiceEan, Nome, Descrizione, UrlPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) values (5, '1234567890127', 'Pasta Rummo', 'Pasta Rummo', 'https://www.rummo.it/it/prodotti/pasta/classiche/spaghetti-n-3', 'Alimentari', 1.5, 0.5, 0.5, 0.5, 0.5, 'Nessuna');
insert into CatalogoProdotti (id, CodiceEan, Nome, Descrizione, UrlPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) values (6, '1234567890128', 'Pasta Garofalo', 'Pasta Garofalo', 'https://www.pastagarofalo.it/it/prodotti/pasta/classiche/spaghetti-n-1', 'Alimentari', 1.5, 0.5, 0.5, 0.5, 0.5, 'Nessuna');
insert into CatalogoProdotti (id, CodiceEan, Nome, Descrizione, UrlPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) values (7, '1234567890129', 'Pasta Barilla', 'Pasta Barilla', 'https://www.barilla.com/it-it/prodotti/pasta/classiche/spaghetti-n-5', 'Alimentari', 1.5, 0.5, 0.5, 0.5, 0.5, 'Nessuna');
insert into CatalogoProdotti (id, CodiceEan, Nome, Descrizione, UrlPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) values (8, '1234567890130', 'Pasta De Cecco', 'Pasta De Cecco', 'https://www.dececco.com/it_it/prodotti/pasta/classiche/spaghetti-n-12', 'Alimentari', 1.5, 0.5, 0.5, 0.5, 0.5, 'Nessuna');
-- abbigliamento
insert into CatalogoProdotti (id, CodiceEan, Nome, Descrizione, UrlPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) values (9, '1234567890131', 'Polo Lacoste', 'Polo Lacoste', 'https://www.lacoste.it/polo-lacoste-uomo-in-piqu%C3%A9-di-cotone-regular-fit/PH4012-00.html?dwvar_PH4012-00_color=031', 'Abbigliamento', 100.0, 0.5, 0.5, 0.5, 0.5, 'Nessuna');