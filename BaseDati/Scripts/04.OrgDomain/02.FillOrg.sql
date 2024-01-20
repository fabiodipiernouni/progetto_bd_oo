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

/*
 create table filiale (
    id integer not null, --pk
    nome varchar2(50) not null,
    Sede integer not null,
    constraint filiale_pk primary key (id),
    constraint filiale_Sede_fk foreign key (Sede) references Indirizzo (id)
);
 */

insert into FILIALE (id, Sede) values (1, 1); --continuare

/*
create table GruppoCorriere (
    id integer not null, --pk
    CodiceCorriere varchar2(255 char) not null, -- unico
    NumeroDipendenti integer not null,
    IdFiliale integer not null,
    constraint GruppoCorriere_pk primary key (id),
    constraint GruppoCorriere_IdFiliale_fk foreign key (IdFiliale) references filiale (id)
);
 */

insert into GruppoCorriere (id, CodiceCorriere, NumeroDipendenti, IdFiliale) values (1, '12345678901', 10, 1); --continuare