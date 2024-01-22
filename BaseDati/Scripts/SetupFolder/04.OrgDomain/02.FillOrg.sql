insert into Org (Id, RagioneSociale, Paese, partitaIva) values (1, 'Unina Delivery ITA', 'Italia', '12345678901');
commit;


-- Indirizzi filiali

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 1, 'Mario', 'Rossi', 'IT56740210663', 'Via Roma, 12', null, '00042', Id From Comune WHERE Nome = 'Roma';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 2, 'Luigi', 'Bianchi', 'IT71450370425', 'Via Milano, 32', null, '20019', Id From Comune WHERE Nome = 'Milano';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 3, 'Giuseppe', 'Verdi', 'IT10569000051', 'Via Napoli, 22', null, '80133', Id FROM Comune WHERE Nome = 'Napoli';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 4, 'Gianfranco', 'Capozzo', 'IT59076830526', 'Via Torino, 42', null, '10024', Id FROM Comune WHERE Nome = 'Torino';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 5, 'Fabio', 'Monzati', 'IT67907781008', 'Via Palermo, 72', null, '90132', Id FROM Comune WHERE Nome = 'Palerno';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 6, 'Dario', 'Lampa', 'IT94059260201', 'Via Genova, 52', null, '16100', Id FROM Comune WHERE Nome = 'Genova';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 7, 'Nicola', 'Bianchi', 'IT71718530281', 'Via Bologna, 62', null, '40100', Id FROM Comune WHERE Nome = 'Bologna';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 8, 'Pasquale', 'Reale', 'IT86207510065', 'Via Firenze, 32', null, '50100', Id FROM Comune WHERE Nome = 'Firenze';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 9, 'Giuseppe', 'Blu', 'IT51016750682', 'Via Bari, 522', null, '70100', Id FROM Comune WHERE Nome = 'Bari';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 10, 'Francesco', 'Cincione', 'IT58344140536', 'Via Catania, 212', null, '95100', Id FROM Comune WHERE Nome = 'Catania';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 11, 'Luigi', 'Fumagali', 'IT20517120802', 'Via Venezia, 1212', null, '20100', Id FROM Comune WHERE Nome = 'Venezia';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 12, 'Gianluca', 'Starace', 'IT23660210842', 'Via Verona, 5212', null, '37100', Id FROM Comune WHERE Nome = 'Verona';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 13, 'Riccardo', 'Santo', 'IT38241120518', 'Via Messina, 232', null, '98121', Id FROM Comune WHERE Nome = 'Messina';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 14, 'Maria', 'Fiore', 'IT23104990652', 'Via Padova, 23', null, '35100', Id FROM Comune WHERE Nome = 'Padova';


-- Indirizzi magazzini

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 15, 'Isa', 'Paganini', 'IT95013280615', 'Rotonda Via Sbarbaro, 4', null, '00010', Id FROM Comune WHERE Nome = 'Marcellina';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 16, 'Silvia', 'Zampa', 'IT32787900730', 'Vicolo Strada Adriano, 78', null, '00047', Id FROM Comune WHERE Nome = 'Marino';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 17, 'Giovanna', 'Pozzecco', 'IT45105070531', 'Contrada Vicolo Calbo, 97', null, '00013', Id FROM Comune WHERE Nome = 'Mentana';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 18, 'Morena', 'Flaiano', 'IT32868400162', 'Viale Contrada Benito, 26', null, '20081', Id FROM Comune WHERE Nome = 'Abbiategrasso';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 19, 'Ida', 'Donarelli', 'IT63191140837', 'Rotonda Strada Coriolano, 43', null, '20060', Id FROM Comune WHERE Nome = 'Masate';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 20, 'Donatella', 'Bajardi', 'IT45744280160', 'Canale Strada Spinola, 48', null, '20076', Id FROM Comune WHERE Nome = 'Mediglia';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 21, 'Victoria', 'Righi', 'IT95749290656', 'Rotonda Piazza Francesco, 47', null, '80035', Id FROM Comune WHERE Nome = 'Nola';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 22, 'Camilla', 'Taliani', 'IT07973260792', 'Rotonda Borgo Iacovelli, 98', null, '80044', Id FROM Comune WHERE Nome = 'Ottaviano';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 23, 'Stefania', 'Iacobucci', 'IT86181930214', 'Canale Via Fieramosca, 4', null, '80021', Id FROM Comune WHERE Nome = 'Afragola';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 24, 'Mercedes', 'Lucarelli', 'IT29049330037', 'Canale Strada Civaschi, 40', null, '10060', Id FROM Comune WHERE Nome = 'Airasca';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 25, 'Flora', 'Cherubini', 'IT44654940574', 'Incrocio Rotonda Doglioni, 71', null, '10080', Id FROM Comune WHERE Nome = 'Alpette';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 26, 'Amalia', 'Polani', 'IT34067120526', 'Rotonda Canale Alberto, 45', null, '10017', Id FROM Comune WHERE Nome = 'Montanaro';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 27, 'Rosalia', 'Chigi', 'IT72919200278', 'Rotonda Strada Gasperi, 31', null, '90021', Id FROM Comune WHERE Nome = 'Alia';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 28, 'Angelina', 'Marangoni', 'IT40257460598', 'Borgo Contrada Vincenza, 53', null, '90020', Id FROM Comune WHERE Nome = 'Alimena';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 29, 'Stefani', 'Porcellato', 'IT99830090785', 'Piazza Borgo Roero, 99', null, '90010', Id FROM Comune WHERE Nome = 'Isnello';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 30, 'Rosaria', 'Grossi', 'IT24054840053', 'Borgo Viale Luciano, 33', null, '16036', Id FROM Comune WHERE Nome = 'Avegno';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 31, 'Paloma', 'Falloppio', 'IT27750510664', 'Via Incrocio Giacobbe, 84', null, '16031', Id FROM Comune WHERE Nome = 'Bogliasco';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 32, 'Iolanda', 'Malpighi', 'IT70694320303', 'Borgo Strada Ubaldo, 30', null, '16012', Id FROM Comune WHERE Nome = 'Busalla';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 33, 'Maria', 'Tamburini', 'IT56258140476', 'Vicolo Rotonda Comolli, 36', null, '40036', Id FROM Comune WHERE Nome = 'Monzuno';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 34, 'Concetta', 'Pagnotto', 'IT33608190121', 'Vicolo Strada Livia, 11', null, '40026', Id FROM Comune WHERE Nome = 'Mordano';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 35, 'Paoletta', 'Gabba', 'IT46457390444', 'Stretto Canale Ramona, 53', null, '40055', Id FROM Comune WHERE Nome = 'Castenaso';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 36, 'Federica', 'Balotelli', 'IT37508730290', 'Stretto Vicolo Pellico, 18', null, '50068', Id FROM Comune WHERE Nome = 'Rufina';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 37, 'Jolanda', 'Prada', 'IT09277960457', 'Strada Stretto Malaparte, 95', null, '50059', Id FROM Comune WHERE Nome = 'Vinci';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 38, 'Vincenza', 'Cesarotti', 'IT79866780988', 'Contrada Rotonda Adriano, 21', null, '50023', Id FROM Comune WHERE Nome = 'Impruneta';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 39, 'Cecilia', 'Ungaretti', 'IT46377370997', 'Via Stretto Antonino, 96', null, '70015', Id FROM Comune WHERE Nome = 'Noci';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 40, 'Alessandra', 'Sokolov', 'IT20064550484', 'Vicolo Vicolo Gionata, 25', null, 'Noicattaro', Id FROM Comune WHERE Nome = '70016';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 41, 'Elisa', 'Chiesa', 'IT33889730520', 'Piazza Rotonda Eva, 9', null, '70020', Id FROM Comune WHERE Nome = 'Bitetto';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 42, 'Monica', 'Offredi', 'IT63233620853', 'Borgo Stretto Dellucci, 44', null, '95032', Id FROM Comune WHERE Nome = 'Belpasso';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 43, 'Hugo', 'Polesel', 'IT35457320485', 'Vicolo Borgo Roberta, 51', null,  '95034', Id FROM Comune WHERE Nome = 'Bronte';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 44, 'Enzo', 'Manunta', 'IT38271930927', 'Borgo Via Fiamma, 19', null,  '95014', Id FROM Comune WHERE Nome = 'Giarre';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 45, 'Franco', 'Sobrero', 'IT50704610042', 'Canale Piazza Flavio, 12', null,  '30020', Id FROM Comune WHERE Nome = 'Meolo';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 46, 'Giuseppe', 'Solari', 'IT33466410298', 'Strada Viale Gioacchino, 73', null,  '30034', Id FROM Comune WHERE Nome = 'Mira';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 47, 'Girolamo', 'Garibaldi', 'IT39559440738', 'Via Incrocio Giovanna, 7', null,  '30035', Id FROM Comune WHERE Nome = 'Mirano';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 48, 'Gionata', 'Ciani', 'IT29962181201', 'Strada Strada Florio, 71', null,  '37040', Id FROM Comune WHERE Nome = 'Zimella';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 49, 'Gianpaolo', 'Satriani', 'IT59798531006', 'Strada Canale Bandello, 20', null,  '37059', Id FROM Comune WHERE Nome = 'Zevio';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 50, 'Santino', 'Cavalcanti', 'IT01982230987', 'Piazza Piazza Alessandra, 66', null,  '37046', Id FROM Comune WHERE Nome = 'Minerbe';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 51, 'Ermanno', 'Padovano', 'IT94841100954', 'Rotonda Piazza Nadi, 5', null,  '98070', Id FROM Comune WHERE Nome = 'Acquedolci';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 52, 'Eraldo', 'Gussoni', 'IT18220880571', 'Piazza Piazza Morellato, 36', null,  '98060', Id FROM Comune WHERE Nome = 'Oliveri';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 53, 'Pasquale', 'Roccabonella', 'IT09751660458', 'Strada Contrada Amaldi, 55', null,  '98066', Id FROM Comune WHERE Nome = 'Patti';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 54, 'Giuliano', 'Mozart', 'IT47298220717', 'Via Incrocio Napoleone, 72', null,  '35021', Id FROM Comune WHERE Nome = 'Agna';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 55, 'Agnolo', 'Rocca', 'IT99585380415', 'Vicolo Canale Piermaria, 8', null,  '35020', Id FROM Comune WHERE Nome = 'Arre';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 56, 'Gustavo', 'Nadi', 'IT03017180336', 'Viale Vicolo Giunti, 95', null,  '35035', Id FROM Comune WHERE Nome = 'Mestrino';


-- supponiamo una configurazione iniziale di filiali

INSERT INTO Filiale (Id, Nome, Sede)
VALUES (1, 'Filiale Roma', 1);

INSERT INTO Filiale (Id, Nome, Sede)
VALUES (2, 'Filiale Milano', 2);

INSERT INTO Filiale (Id, Nome, Sede)
VALUES (3, 'Filiale Napoli', 3);

INSERT INTO Filiale (Id, Nome, Sede)
VALUES (4, 'Filiale Torino', 4);

INSERT INTO Filiale (Id, Nome, Sede)
VALUES (5, 'Filiale Palermo', 5);

INSERT INTO Filiale (Id, Nome, Sede)
VALUES (6, 'Filiale Genova', 6);

INSERT INTO Filiale (Id, Nome, Sede)
VALUES (7, 'Filiale Bologna', 7);

INSERT INTO Filiale (Id, Nome, Sede)
VALUES (8, 'Filiale Firenze', 8);

INSERT INTO Filiale (Id, Nome, Sede)
VALUES (9, 'Filiale Bari', 9);

INSERT INTO Filiale (Id, Nome, Sede)
VALUES (10, 'Filiale Catania', 10);

INSERT INTO Filiale (Id, Nome, Sede)
VALUES (11, 'Filiale Venezia', 11);

INSERT INTO Filiale (Id, Nome, Sede)
VALUES (12, 'Filiale Verona', 12);

INSERT INTO Filiale (Id, Nome, Sede)
VALUES (13, 'Filiale Messina', 13);

INSERT INTO Filiale (Id, Nome, Sede)
VALUES (14, 'Filiale Padova', 14);


-- gruppi corrieri della filiale, supponiamo 3 gruppi a filiale
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (1, 'Corriere Roma 1', 'ROM1', 10, 1);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (2, 'Corriere Roma 2', 'ROM2', 15, 1);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (3, 'Corriere Roma 3', 'ROM3', 12, 1);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (4, 'Corriere Milano 1', 'MIL1', 3, 2);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (5, 'Corriere Milano 2', 'MIL2', 33, 2);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (6, 'Corriere Milano 3', 'MIL3', 112, 2);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (7, 'Corriere Napoli 1', 'NAP1', 10, 3);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (8, 'Corriere Napoli 2', 'NAP2', 125, 3);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (9, 'Corriere Napoli 3', 'NAP3', 11, 3);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (10, 'Corriere Torino 1', 'TOR1', 10, 4);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (11, 'Corriere Torino 2', 'TOR2', 15, 4);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (12, 'Corriere Torino 3', 'TOR3', 12, 4);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (13, 'Corriere Palermo 1', 'PAL1', 3, 5);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (14, 'Corriere Palermo 2', 'PAL2', 33, 5);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (15, 'Corriere Palermo 3', 'PAL3', 112, 5);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (16, 'Corriere Genova 1', 'GEN1', 10, 6);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (17, 'Corriere Genova 2', 'GEN2', 125, 6);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (18, 'Corriere Genova 3', 'GEN3', 11, 6);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (19, 'Corriere Bologna 1', 'BOL1', 10, 7);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (20, 'Corriere Bologna 2', 'BOL2', 15, 7);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (21, 'Corriere Bologna 3', 'BOL3', 12, 7);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (22, 'Corriere Firenze 1', 'FIR1', 3, 8);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (23, 'Corriere Firenze 2', 'FIR2', 33, 8);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (24, 'Corriere Firenze 3', 'FIR3', 112, 8);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (25, 'Corriere Bari 1', 'BAR1', 10, 9);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (26, 'Corriere Bari 2', 'BAR2', 125, 9);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (27, 'Corriere Bari 3', 'BAR3', 11, 9);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (28, 'Corriere Catania 1', 'CAT1', 10, 10);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (29, 'Corriere Catania 2', 'CAT2', 15, 10);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (30, 'Corriere Catania 3', 'CAT3', 12, 10);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (31, 'Corriere Venezia 1', 'VEN1', 3, 11);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (32, 'Corriere Venezia 2', 'VEN2', 33, 11);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (33, 'Corriere Venezia 3', 'VEN3', 112, 11);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (34, 'Corriere Verona 1', 'VER1', 10, 12);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (35, 'Corriere Verona 2', 'VER2', 125, 12);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (36, 'Corriere Verona 3', 'VER3', 11, 12);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (37, 'Corriere Messina 1', 'MES1', 10, 13);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (38, 'Corriere Messina 2', 'MES2', 15, 13);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (39, 'Corriere Messina 3', 'MES3', 12, 13);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (40, 'Corriere Padova 1', 'PAD1', 3, 14);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (41, 'Corriere Padova 2', 'PAD2', 33, 14);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale)
VALUES (42, 'Corriere Padova 3', 'PAD3', 112, 14);


--  MezzoDiTrasporto

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('MD140ZG', 'Treno', 1);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('YQ139SI', 'Camion', 1);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('PB409CE', 'Furgone', 1);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('WS534BP', 'Auto', 1);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('RH318CP', 'Moto', 1);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('VI513ZS', 'Bicicletta', 1);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('HO147EH', 'Treno', 2);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('XV413VQ', 'Camion', 2);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('VX326NE', 'Furgone', 2);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('MN018MU', 'Auto', 2);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('AG416CM', 'Moto', 2);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('FK105FK', 'Bicicletta', 2);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('GQ061VT', 'Treno', 3);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('KL685BH', 'Camion', 3);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('GV137DQ', 'Furgone', 3);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('FS600ED', 'Auto', 3);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('GP210XS', 'Moto', 3);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('VK189UX', 'Bicicletta', 3);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('ZG483LQ', 'Treno', 4);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('RF293AL', 'Camion', 4);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('NS721DA', 'Furgone', 4);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('NX660JB', 'Auto', 4);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('VQ640ME', 'Moto', 4);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('ZG319AC', 'Bicicletta', 4);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('WX513IJ', 'Treno', 5);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('CO234MP', 'Camion', 5);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('HB407AC', 'Furgone', 5);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('XE261BA', 'Auto', 5);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('OI823CY', 'Moto', 5);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('KH616SB', 'Bicicletta', 5);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('PE663AT', 'Treno', 6);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('LK106PK', 'Camion', 6);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('EW036JK', 'Furgone', 6);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('XW284VD', 'Auto', 6);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('YL252MT', 'Moto', 6);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('VI335YP', 'Bicicletta', 6);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('SI148VU', 'Treno', 7);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('ZR695LO', 'Camion', 7);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('MB410PW', 'Furgone', 7);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('SS888SE', 'Auto', 7);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('CD775ZA', 'Moto', 7);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('WI587UI', 'Bicicletta', 7);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('MB247XU', 'Treno', 8);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('CP557FI', 'Camion', 8);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('WP804WR', 'Furgone', 8);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('GO001ED', 'Auto', 8);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('UY310RP', 'Moto', 8);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('WA057BD', 'Bicicletta', 8);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('YT498HT', 'Treno', 9);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('ZY681YG', 'Camion', 9);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('YT236EC', 'Furgone', 9);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('MP162SN', 'Auto', 9);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('XG720ZA', 'Moto', 9);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('ZL608XN', 'Bicicletta', 9);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('LV728IM', 'Treno', 10);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('DY002SF', 'Camion', 10);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('MB433EH', 'Furgone', 10);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('PX611DL', 'Auto', 10);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('KZ033IV', 'Moto', 10);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('VD830MG', 'Bicicletta', 10);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('KS795ID', 'Treno', 11);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('IX538AJ', 'Camion', 11);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('CN370OT', 'Furgone', 11);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('SO989AV', 'Auto', 11);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('DY569PA', 'Moto', 11);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('DX177RK', 'Bicicletta', 11);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('AY623YQ', 'Treno', 12);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('ZE809BJ', 'Camion', 12);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('CG315NT', 'Furgone', 12);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('FX461NI', 'Auto', 12);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('TA871IF', 'Moto', 12);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('JC013EZ', 'Bicicletta', 12);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('DG876JQ', 'Treno', 13);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('ZZ144MO', 'Camion', 13);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('BP719BT', 'Furgone', 13);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('ZB966JU', 'Auto', 13);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('CN312NL', 'Moto', 13);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('VJ838EL', 'Bicicletta', 13);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('QP481AE', 'Treno', 14);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('XL248WI', 'Camion', 14);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('ID695RF', 'Furgone', 14);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('XF555YR', 'Auto', 14);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('LM660NT', 'Moto', 14);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('ID286QI', 'Bicicletta', 14);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('DJ794YW', 'Treno', 15);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('SS413DD', 'Camion', 15);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('FM447SF', 'Furgone', 15);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('DJ653JQ', 'Auto', 15);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('VZ659OZ', 'Moto', 15);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('AO005CV', 'Bicicletta', 15);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('AE882CH', 'Treno', 16);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('CG375QO', 'Camion', 16);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('YG205QO', 'Furgone', 16);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('CP707AJ', 'Auto', 16);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('GB159OR', 'Moto', 16);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('OX691PO', 'Bicicletta', 16);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('NH248DU', 'Treno', 17);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('KY137XP', 'Camion', 17);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('QO860VK', 'Furgone', 17);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('IU619PZ', 'Auto', 17);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('YG826TJ', 'Moto', 17);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('AD704BN', 'Bicicletta', 17);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('ZZ026WF', 'Treno', 18);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('RU258VM', 'Camion', 18);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('TR680BM', 'Furgone', 18);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('DZ874OV', 'Auto', 18);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('NA998AW', 'Moto', 18);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('NT429RE', 'Bicicletta', 18);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('AP543PX', 'Treno', 19);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('OL844CB', 'Camion', 19);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('RV240VE', 'Furgone', 19);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('VZ975VH', 'Auto', 19);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('HL233XC', 'Moto', 19);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('RG171XG', 'Bicicletta', 19);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('KC591JM', 'Treno', 20);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('IX410TO', 'Camion', 20);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('JG791EQ', 'Furgone', 20);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('NX571AD', 'Auto', 20);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('YM023MP', 'Moto', 20);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('VF553IK', 'Bicicletta', 20);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('HQ742QZ', 'Treno', 21);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('IF929ZZ', 'Camion', 21);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('RF850EQ', 'Furgone', 21);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('AS321AQ', 'Auto', 21);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('KC262EN', 'Moto', 21);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('FY697CN', 'Bicicletta', 21);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('IQ996DB', 'Treno', 22);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('SV574ZZ', 'Camion', 22);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('ND790PP', 'Furgone', 22);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('EV891OC', 'Auto', 22);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('JF155NE', 'Moto', 22);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('RG931YS', 'Bicicletta', 22);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('SW352QM', 'Treno', 23);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('RW585GZ', 'Camion', 23);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('AO961LV', 'Furgone', 23);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('ZV100YN', 'Auto', 23);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('LW893EO', 'Moto', 23);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('CF348DY', 'Bicicletta', 23);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('IT817JU', 'Treno', 24);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('UX665KH', 'Camion', 24);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('CL671AP', 'Furgone', 24);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('HK727VD', 'Auto', 24);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('LE285OQ', 'Moto', 24);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('YG038XH', 'Bicicletta', 24);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('DM174SC', 'Treno', 25);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('VB927JG', 'Camion', 25);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('RL163RG', 'Furgone', 25);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('WR325TR', 'Auto', 25);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('UB477HY', 'Moto', 25);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('JX488VO', 'Bicicletta', 25);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('TI157VQ', 'Treno', 26);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('GV268OQ', 'Camion', 26);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('HP626DM', 'Furgone', 26);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('JP592ZV', 'Auto', 26);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('FH611RU', 'Moto', 26);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('EA802IN', 'Bicicletta', 26);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('IM072HN', 'Treno', 27);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('XX599WE', 'Camion', 27);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('ZZ599JM', 'Furgone', 27);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('IQ651XV', 'Auto', 27);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('XB959OT', 'Moto', 27);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('OE044BS', 'Bicicletta', 27);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('TY698YC', 'Treno', 28);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('AY420XG', 'Camion', 28);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('RX531KT', 'Furgone', 28);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('FS077VY', 'Auto', 28);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('BY435QN', 'Moto', 28);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('ZY976MZ', 'Bicicletta', 28);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('YN726QG', 'Treno', 29);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('KW895UH', 'Camion', 29);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('IV456XJ', 'Furgone', 29);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('SY916BF', 'Auto', 29);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('ZR224CI', 'Moto', 29);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('LK722YS', 'Bicicletta', 29);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('NK185NJ', 'Treno', 30);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('LV171JU', 'Camion', 30);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('UC373PX', 'Furgone', 30);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('CV541UE', 'Auto', 30);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('LE529FY', 'Moto', 30);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('NT939SH', 'Bicicletta', 30);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('HM834NY', 'Treno', 31);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('WM830FZ', 'Camion', 31);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('YY220NX', 'Furgone', 31);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('ZT527YZ', 'Auto', 31);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('RR251XC', 'Moto', 31);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('PH934FV', 'Bicicletta', 31);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('NC821TK', 'Treno', 32);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('EB302IP', 'Camion', 32);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('SD551XV', 'Furgone', 32);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('IA062HH', 'Auto', 32);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('TJ794FE', 'Moto', 32);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('QM317JX', 'Bicicletta', 32);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('XK162OW', 'Treno', 33);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('WK982SC', 'Camion', 33);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('JH751QP', 'Furgone', 33);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('CM778KB', 'Auto', 33);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('OT979NM', 'Moto', 33);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('RT509JI', 'Bicicletta', 33);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('LU754DM', 'Treno', 34);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('JT527WI', 'Camion', 34);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('KK928ME', 'Furgone', 34);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('LB652QQ', 'Auto', 34);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('CA836JI', 'Moto', 34);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('EK105MA', 'Bicicletta', 34);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('MO026LQ', 'Treno', 35);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('YG047JG', 'Camion', 35);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('NE971VJ', 'Furgone', 35);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('ER799QO', 'Auto', 35);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('DI369UA', 'Moto', 35);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('LV302HO', 'Bicicletta', 35);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('UL820GD', 'Treno', 36);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('MI169IC', 'Camion', 36);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('VU080SY', 'Furgone', 36);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('OI573QA', 'Auto', 36);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('JV181QX', 'Moto', 36);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('OA831GX', 'Bicicletta', 36);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('XD843HM', 'Treno', 37);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('FI545QN', 'Camion', 37);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('DP228DC', 'Furgone', 37);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('UR014KP', 'Auto', 37);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('DP660UT', 'Moto', 37);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('JY378PM', 'Bicicletta', 37);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('NH572QD', 'Treno', 38);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('EB389NW', 'Camion', 38);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('BM043CD', 'Furgone', 38);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('FY376NO', 'Auto', 38);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('YD861DE', 'Moto', 38);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('RS263PI', 'Bicicletta', 38);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('RC122LX', 'Treno', 39);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('IO475CK', 'Camion', 39);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('LV972MM', 'Furgone', 39);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('BZ740QC', 'Auto', 39);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('RB856CK', 'Moto', 39);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('NI240MO', 'Bicicletta', 39);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('RB592OX', 'Treno', 40);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('OO028LR', 'Camion', 40);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('HD854MD', 'Furgone', 40);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('WQ191OK', 'Auto', 40);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('VG803JX', 'Moto', 40);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('VX999VB', 'Bicicletta', 40);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('GD146IP', 'Treno', 41);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('YC779ZL', 'Camion', 41);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('VD279CT', 'Furgone', 41);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('LU302KL', 'Auto', 41);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('WI233CV', 'Moto', 41);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('ML375EC', 'Bicicletta', 41);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('PL008AX', 'Treno', 42);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('DB893ON', 'Camion', 42);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('KJ419QV', 'Furgone', 42);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('KX581GS', 'Auto', 42);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('DW369QY', 'Moto', 42);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere)
VALUES('VZ286HO', 'Bicicletta', 42);


-- supponiamo una configurazione iniziale di 3 magazzini a filiale

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Roma 1', 15, 1);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Roma 2', 16, 1);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Roma 3', 17, 1);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Milano 1', 18, 2);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Milano 2', 19, 2);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Milano 3', 20, 2);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Napoli 1', 21, 3);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Napoli 2', 22, 3);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Napoli 3', 23, 3);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Torino 1', 24, 4);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Torino 2', 25, 4);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Torino 3', 26, 4);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Palermo 1', 27, 5);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Palermo 2', 28, 5);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Palermo 3', 29, 5);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Genova 1', 30, 6);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Genova 2', 31, 6);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Genova 3', 32, 6);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Bologna 1', 33, 7);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Bologna 2', 34, 7);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Bologna 3', 35, 7);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Firenze 1', 36, 8);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Firenze 2', 37, 8);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Firenze 3', 38, 8);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Bari 1', 39, 9);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Bari 2', 40, 9);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Bari 3', 41, 9);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Catania 1', 42, 10);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Catania 2', 43, 10);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Catania 3', 44, 10);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Venezia 1', 45, 11);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Venezia 2', 46, 11);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Venezia 3', 47, 11);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Verona 1', 48, 12);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Verona 2', 49, 12);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Verona 3', 50, 12);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Messina 1', 51, 13);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Messina 2', 52, 13);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Messina 3', 53, 13);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Padova 1', 54, 14);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Padova 2', 55, 14);

INSERT INTO Magazzino(Nome, IdIndirizzo, IdFiliale)
VALUES ('Magazzino Padova 3', 56, 14);


-- supponiamo una configurazione iniziale di prodotti di tipi diversi tra loro

-- Abbigliamento

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('1234567890123', 'Maglia a Righe', 'Maglia a righe di cotone', 'http://www.example.com/maglia_righe.jpg', 'Abbigliamento', 29.99, 0.2, 40, 60, 5, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('2345678901234', 'Jeans Classici', 'Jeans denim blu', 'http://www.example.com/jeans_classici.jpg', 'Abbigliamento', 49.99, 0.5, 30, 70, 10, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('3456789012345', 'Scarpe Sportive', 'Scarpe da ginnastica leggere', 'http://www.example.com/scarpe_sportive.jpg', 'Abbigliamento', 59.99, 0.4, 10, 25, 8, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('4567890123456', 'Cappotto Invernale', 'Cappotto imbottito per l''inverno', 'http://www.example.com/cappotto_invernale.jpg', 'Abbigliamento', 79.99, 1.2, 50, 80, 15, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('5678901234567', 'Giacca di Pelle', 'Giacca di pelle nera', 'http://www.example.com/giacca_pelle.jpg', 'Abbigliamento', 99.99, 0.8, 45, 65, 12, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('6789012345678', 'Camicia Formale', 'Camicia bianca formale', 'http://www.example.com/camicia_formale.jpg', 'Abbigliamento', 39.99, 0.3, 35, 55, 8, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('7890123456789', 'Occhiali da Sole', 'Occhiali da sole neri', 'http://www.example.com/occhiali_sole.jpg', 'Abbigliamento', 29.99, 0.1, 15, 5, 5, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('8901234567890', 'Pantaloni Casual', 'Pantaloni casual beige', 'http://www.example.com/pantaloni_casual.jpg', 'Abbigliamento', 44.99, 0.4, 30, 75, 10, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('9012345678901', 'Borsa a Tracolla', 'Borsa a tracolla in pelle', 'http://www.example.com/borsa_tracolla.jpg', 'Abbigliamento', 59.99, 0.7, 25, 20, 10, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('0123456789012', 'Calze Sportive', 'Calze sportive leggere', 'http://www.example.com/calze_sportive.jpg', 'Abbigliamento', 9.99, 0.1, 8, 18, 2, 'Nessuna');


-- Alimentari

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('1234567890123', 'Pasta Penne', 'Penne rigate di semola di grano duro', 'http://www.example.com/pasta_penne.jpg', 'Alimentari', 1.99, 0.5, 5, 20, 5, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('2345678901234', 'Olio Extra Vergine di Oliva', 'Olio extravergine di oliva italiano', 'http://www.example.com/olio_oliva.jpg', 'Alimentari', 8.99, 0.75, 7, 25, 7, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('3456789012345', 'Riso Arborio', 'Riso Arborio per risotti', 'http://www.example.com/riso_arborio.jpg', 'Alimentari', 3.99, 1.0, 8, 18, 5, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('4567890123456', 'Salsa di Pomodoro', 'Salsa di pomodoro fresco', 'http://www.example.com/salsa_pomodoro.jpg', 'Alimentari', 2.49, 0.8, 6, 15, 4, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('5678901234567', 'Caffè Arabica', 'Caffè Arabica macinato', 'http://www.example.com/caffe_arabica.jpg', 'Alimentari', 5.99, 0.25, 10, 15, 5, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('6789012345678', 'Cioccolato Fondente', 'Tavoletta di cioccolato fondente', 'http://www.example.com/cioccolato_fondente.jpg', 'Alimentari', 3.49, 0.1, 8, 15, 2, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('7890123456789', 'Miele di Acacia', 'Miele di acacia 100% naturale', 'http://www.example.com/miele_acacia.jpg', 'Alimentari', 6.99, 0.5, 7, 12, 4, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('8901234567890', 'Tonno in Scatola', 'Tonno sott''olio in scatola', 'http://www.example.com/tonno_in_scatola.jpg', 'Alimentari', 4.79, 0.3, 6, 10, 3, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('9012345678901', 'Formaggio Parmigiano', 'Formaggio Parmigiano Reggiano DOP', 'http://www.example.com/formaggio_parmigiano.jpg', 'Alimentari', 12.99, 0.8, 10, 8, 6, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('0123456789012', 'Acqua Minerale', 'Acqua minerale naturale in bottiglia', 'http://www.example.com/acqua_minerale.jpg', 'Alimentari', 1.29, 1.5, 5, 25, 5, 'Nessuna');


-- Elettronica

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('1234567890123', 'Smartphone AndroId', 'Smartphone AndroId con schermo HD', 'http://www.example.com/smartphone_androId.jpg', 'Elettronica', 299.99, 0.2, 7, 15, 0.8, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('2345678901234', 'Laptop Ultraleggero', 'Laptop ultraleggero con processore i7', 'http://www.example.com/laptop_ultraleggero.jpg', 'Elettronica', 999.99, 1.0, 13, 18, 1.5, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('3456789012345', 'Televisore 4K', 'Televisore 4K da 55 pollici', 'http://www.example.com/tv_4k.jpg', 'Elettronica', 799.99, 20.0, 50, 30, 5, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('4567890123456', 'Cuffie Wireless', 'Cuffie wireless con cancellazione del rumore', 'http://www.example.com/cuffie_wireless.jpg', 'Elettronica', 129.99, 0.3, 6, 8, 4, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('5678901234567', 'Tablet AndroId', 'Tablet AndroId con schermo da 10 pollici', 'http://www.example.com/tablet_androId.jpg', 'Elettronica', 199.99, 0.6, 8, 12, 0.7, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('6789012345678', 'Fotocamera DSLR', 'Fotocamera DSLR professionale', 'http://www.example.com/fotocamera_dslr.jpg', 'Elettronica', 899.99, 2.0, 6, 5, 4, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('7890123456789', 'Stampante Multifunzione', 'Stampante multifunzione a colori', 'http://www.example.com/stampante_multifunzione.jpg', 'Elettronica', 129.99, 10.0, 15, 10, 8, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('8901234567890', 'Mouse Gaming', 'Mouse da gaming con illuminazione RGB', 'http://www.example.com/mouse_gaming.jpg', 'Elettronica', 49.99, 0.15, 3, 5, 1.5, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('9012345678901', 'Router Wi-Fi AC', 'Router Wi-Fi AC dual-band', 'http://www.example.com/router_wifi_ac.jpg', 'Elettronica', 79.99, 0.5, 8, 12, 2, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('0123456789012', 'Batteria Esterna', 'Batteria esterna da 10000 mAh', 'http://www.example.com/batteria_esterna.jpg', 'Elettronica', 29.99, 0.2, 5, 10, 2, 'Nessuna');


-- Casa

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('1234567890123', 'Tappeto Shaggy', 'Tappeto Shaggy a pelo lungo', 'http://www.example.com/tappeto_shaggy.jpg', 'Casa', 79.99, 5.0, 80, 120, 1, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('2345678901234', 'Set di Pentole Antiaderenti', 'Set di pentole e padelle antiaderenti', 'http://www.example.com/pentole_antiaderenti.jpg', 'Casa', 129.99, 6.0, 30, 40, 20, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('3456789012345', 'Divano a 3 Posti', 'Divano moderno a tre posti', 'http://www.example.com/divano_3_posti.jpg', 'Casa', 599.99, 30.0, 200, 90, 100, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('4567890123456', 'Lampada da Tavolo', 'Lampada da tavolo con base in legno', 'http://www.example.com/lampada_tavolo.jpg', 'Casa', 49.99, 2.0, 20, 40, 20, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('5678901234567', 'Asciugamani di Cotone', 'Set di asciugamani di cotone', 'http://www.example.com/asciugamani_cotone.jpg', 'Casa', 19.99, 1.0, 10, 15, 5, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('6789012345678', 'Tavolo da Pranzo', 'Tavolo da pranzo rettangolare in legno', 'http://www.example.com/tavolo_pranzo.jpg', 'Casa', 299.99, 40.0, 150, 75, 80, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('7890123456789', 'Cuscini Decorativi', 'Set di cuscini decorativi per divano', 'http://www.example.com/cuscini_decorativi.jpg', 'Casa', 24.99, 1.5, 18, 18, 6, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('8901234567890', 'Set di Posate', 'Set di posate in acciaio inossIdabile', 'http://www.example.com/posate_acciaio.jpg', 'Casa', 39.99, 2.0, 10, 25, 5, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('9012345678901', 'Portaoggetti da Parete', 'Portaoggetti da parete con ripiani', 'http://www.example.com/portaoggetti_parete.jpg', 'Casa', 29.99, 3.0, 30, 40, 10, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('0123456789012', 'Tenda oscurante', 'Tenda oscurante per finestre', 'http://www.example.com/tenda_oscurante.jpg', 'Casa', 19.99, 1.2, 50, 200, 1, 'Nessuna');


-- Sport

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('1234567890123', 'Scarpe da Corsa', 'Scarpe da corsa leggere e ammortizzate', 'http://www.example.com/scarpe_corsa.jpg', 'Sport', 79.99, 0.4, 10, 25, 8, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('2345678901234', 'Palla da Calcio', 'Palla da calcio professionale in cuoio', 'http://www.example.com/palla_calcio.jpg', 'Sport', 19.99, 0.5, 22, 22, 22, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('3456789012345', 'Tuta da Ginnastica', 'Tuta da ginnastica in tessuto traspirante', 'http://www.example.com/tuta_ginnastica.jpg', 'Sport', 49.99, 0.8, 15, 30, 5, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('4567890123456', 'Borsa da Palestra', 'Borsa da palestra resistente con scomparti', 'http://www.example.com/borsa_palestra.jpg', 'Sport', 34.99, 0.6, 25, 40, 15, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('5678901234567', 'Pallone da Basket', 'Pallone da basket ufficiale in gomma', 'http://www.example.com/pallone_basket.jpg', 'Sport', 24.99, 0.7, 29, 29, 29, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('6789012345678', 'Cyclette Pieghevole', 'Cyclette pieghevole per allenamento cardio', 'http://www.example.com/cyclette_pieghevole.jpg', 'Sport', 199.99, 15.0, 50, 100, 30, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('7890123456789', 'Tappetino Yoga', 'Tappetino yoga antiscivolo ed ecologico', 'http://www.example.com/tappetino_yoga.jpg', 'Sport', 29.99, 1.0, 24, 68, 0.6, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('8901234567890', 'Guanti da Boxe', 'Guanti da boxe in pelle con imbottitura', 'http://www.example.com/guanti_boxe.jpg', 'Sport', 49.99, 0.5, 12, 18, 6, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('9012345678901', 'T-shirt Tecnica', 'T-shirt tecnica traspirante per l''allenamento', 'http://www.example.com/tshirt_tecnica.jpg', 'Sport', 24.99, 0.3, 35, 50, 3, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('0123456789012', 'Pallina da Tennis', 'Pallina da tennis professionale in gomma', 'http://www.example.com/pallina_tennis.jpg', 'Sport', 3.99, 0.1, 5, 5, 5, 'Nessuna');


-- Giardino

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('1234567890123', 'Set di Mobili da Giardino', 'Set di mobili da giardino in rattan', 'http://www.example.com/mobili_giardino.jpg', 'Giardino', 499.99, 50.0, 150, 80, 60, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('2345678901234', 'Barbecue a Gas', 'Barbecue a gas con griglia in acciaio', 'http://www.example.com/barbecue_gas.jpg', 'Giardino', 299.99, 30.0, 80, 120, 40, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('3456789012345', 'Ombrellone da Giardino', 'Ombrellone rettangolare con base', 'http://www.example.com/ombrellone_giardino.jpg', 'Giardino', 89.99, 10.0, 300, 250, 20, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('4567890123456', 'Set di Attrezzi da Giardinaggio', 'Set completo di attrezzi da giardinaggio', 'http://www.example.com/attrezzi_giardinaggio.jpg', 'Giardino', 49.99, 5.0, 20, 40, 10, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('5678901234567', 'Lanterna Solare', 'Lanterna da giardino alimentata a energia solare', 'http://www.example.com/lanterna_solare.jpg', 'Giardino', 19.99, 1.0, 10, 25, 5, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('6789012345678', 'Piscina Gonfiabile', 'Piscina gonfiabile per bambini', 'http://www.example.com/piscina_gonfiabile.jpg', 'Giardino', 59.99, 7.0, 150, 30, 200, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('7890123456789', 'Ornamento da Giardino', 'Statua decorativa da giardino in ceramica', 'http://www.example.com/ornamento_giardino.jpg', 'Giardino', 39.99, 4.0, 15, 40, 15, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('8901234567890', 'Irrigatore Automatico', 'Irrigatore automatico programmabile', 'http://www.example.com/irrigatore_automatico.jpg', 'Giardino', 79.99, 1.5, 8, 20, 8, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('9012345678901', 'Amaca da Giardino', 'Amaca da giardino con supporto in acciaio', 'http://www.example.com/amaca_giardino.jpg', 'Giardino', 69.99, 15.0, 150, 200, 5, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('5123456789012', 'Vaso di Fiori in Ceramica', 'Vaso decorativo per piante in ceramica', 'http://www.example.com/vaso_fiori.jpg', 'Giardino', 29.99, 2.0, 10, 20, 10, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('1234567890123', 'Fertilizzante Universale', 'Fertilizzante per piante universale', 'http://www.example.com/fertilizzante_universale.jpg', 'Giardino', 12.99, 2.0, 5, 15, 3, 'Chimico');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('2345678901234', 'InsetticIda Naturale', 'InsetticIda naturale a base di oli essenziali', 'http://www.example.com/insetticIda_naturale.jpg', 'Giardino', 8.99, 1.0, 3, 10, 2, 'Tossico');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('3456789012345', 'Fioriera in Metallo', 'Fioriera decorativa in metallo', 'http://www.example.com/fioriera_metallo.jpg', 'Giardino', 39.99, 5.0, 30, 40, 20, 'Corrosivo');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('4567890123456', 'Luci Solari per Vialetto', 'Luci solari per illuminare il vialetto', 'http://www.example.com/luci_solari_vialetto.jpg', 'Giardino', 29.99, 1.5, 8, 15, 5, 'Radioattivo');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('5678901234567', 'Rasaerba Elettrico', 'Rasaerba elettrico per piccoli giardini', 'http://www.example.com/rasaerba_elettrico.jpg', 'Giardino', 149.99, 10.0, 20, 60, 40, 'Infiammabile');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('6789012345678', 'Piante Carnivore', 'Set di piante carnivore per il controllo degli insetti', 'http://www.example.com/piante_carnivore.jpg', 'Giardino', 19.99, 2.0, 10, 20, 10, 'Infettante');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('7890123456789', 'Spruzzatore Manuale', 'Spruzzatore manuale per liquIdi giardinaggio', 'http://www.example.com/spruzzatore_manuale.jpg', 'Giardino', 14.99, 0.5, 5, 15, 3, 'Chimico');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('8901234567890', 'Set di Semi di Fiori Selvatici', 'Set di semi di fiori selvatici per giardino naturale', 'http://www.example.com/semi_fiori_selvatici.jpg', 'Giardino', 9.99, 0.2, 5, 10, 2, 'Infettante');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('9012345678901', 'FungicIda Concentrato', 'FungicIda concentrato per piante', 'http://www.example.com/fungicIda_concentrato.jpg', 'Giardino', 17.99, 1.0, 3, 12, 2, 'Tossico');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('2123456789012', 'Pergolato da Giardino', 'Pergolato in legno per zone d''ombra', 'http://www.example.com/pergolato_giardino.jpg', 'Giardino', 299.99, 50.0, 300, 250, 100, 'Corrosivo');


-- Altro

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('2345678901234', 'Cuscino Decorativo', 'Cuscino decorativo per il soggiorno', 'http://www.example.com/cuscino_decorativo.jpg', 'Altro', 14.99, 0.5, 18, 18, 6, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('3456789012345', 'Set di Pennelli Artistici', 'Set di pennelli per pittura artistica', 'http://www.example.com/set_pennelli_artistici.jpg', 'Altro', 29.99, 0.3, 10, 30, 2, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('4567890123456', 'Orologio da Parete', 'Orologio decorativo da parete', 'http://www.example.com/orologio_parete.jpg', 'Altro', 49.99, 1.0, 30, 30, 5, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('5678901234567', 'Set di Chiavi a Cricchetto', 'Set di chiavi a cricchetto per lavori manuali', 'http://www.example.com/set_chiavi_cricchetto.jpg', 'Altro', 39.99, 2.0, 8, 25, 5, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('6789012345678', 'Puzzle 1000 Pezzi', 'Puzzle con immagine panoramica', 'http://www.example.com/puzzle_1000_pezzi.jpg', 'Altro', 19.99, 1.5, 30, 40, 0.2, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('7890123456789', 'Tazza da Colazione', 'Tazza con motivi divertenti per la colazione', 'http://www.example.com/tazza_colazione.jpg', 'Altro', 9.99, 0.3, 8, 10, 8, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('8901234567890', 'Portafoglio in Pelle', 'Portafoglio elegante in pelle', 'http://www.example.com/portafoglio_pelle.jpg', 'Altro', 29.99, 0.2, 10, 12, 2, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('9012345678901', 'Candela Profumata', 'Candela profumata con fragranza alla lavanda', 'http://www.example.com/candela_profumata.jpg', 'Altro', 12.99, 0.4, 5, 8, 5, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('0123456789012', 'Borsa da Viaggio', 'Borsa da viaggio resistente e capiente', 'http://www.example.com/borsa_viaggio.jpg', 'Altro', 49.99, 1.5, 25, 35, 15, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('1234567890123', 'Set di Tazze da Caffè', 'Set di tazze da caffè con piattini assortiti', 'http://www.example.com/set_tazze_caffe.jpg', 'Altro', 24.99, 1.0, 15, 10, 8, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('2345678901234', 'Altoparlante Bluetooth', 'Altoparlante portatile con connessione Bluetooth', 'http://www.example.com/altoparlante_bluetooth.jpg', 'Altro', 79.99, 0.8, 10, 15, 5, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('3456789012345', 'Cornice Foto in Legno', 'Cornice in legno per foto 10x15 cm', 'http://www.example.com/cornice_foto_legno.jpg', 'Altro', 14.99, 0.5, 15, 20, 2, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('4567890123456', 'Mouse Wireless', 'Mouse ottico wireless per computer', 'http://www.example.com/mouse_wireless.jpg', 'Altro', 29.99, 0.2, 6, 10, 3, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('5678901234567', 'Quaderno in Pelle', 'Quaderno elegante con copertina in pelle', 'http://www.example.com/quaderno_pelle.jpg', 'Altro', 17.99, 0.8, 12, 18, 1, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('6789012345678', 'Set di Posate da Dessert', 'Set di posate da dessert in acciaio inox', 'http://www.example.com/set_posate_dessert.jpg', 'Altro', 19.99, 0.5, 8, 18, 2, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('7890123456789', 'Zaino Antifurto', 'Zaino con chiusure antifurto e tasche nascoste', 'http://www.example.com/zaino_antifurto.jpg', 'Altro', 39.99, 1.2, 20, 40, 15, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('8901234567890', 'Set di Matite Colorate', 'Set di matite colorate per disegno artistico', 'http://www.example.com/set_matite_colorate.jpg', 'Altro', 9.99, 0.3, 5, 15, 1, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('9012345678901', 'Coperta MorbIda in Pile', 'Coperta morbIda e calda in pile', 'http://www.example.com/coperta_pile.jpg', 'Altro', 29.99, 1.0, 50, 60, 2, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('0123456789012', 'Tastiera Bluetooth', 'Tastiera compatta con connessione Bluetooth', 'http://www.example.com/tastiera_bluetooth.jpg', 'Altro', 49.99, 0.5, 30, 10, 2, 'Nessuna');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('1234567890123', 'Lucchetto Antifurto', 'Lucchetto robusto e antifurto per bagagli', 'http://www.example.com/lucchetto_antifurto.jpg', 'Altro', 19.99, 0.3, 8, 10, 2, 'Corrosivo');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('2345678901234', 'Rullo Verniciatore', 'Rullo per verniciatura professionale', 'http://www.example.com/rullo_verniciatore.jpg', 'Altro', 24.99, 0.8, 5, 20, 5, 'Infiammabile');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('3456789012345', 'Kit di Sopravvivenza', 'Kit completo di sopravvivenza outdoor', 'http://www.example.com/kit_sopravvivenza.jpg', 'Altro', 79.99, 2.5, 15, 25, 10, 'Radioattivo');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('4567890123456', 'Spray Anti-Insetti', 'Spray repellente per insetti', 'http://www.example.com/spray_anti_insetti.jpg', 'Altro', 9.99, 0.2, 3, 15, 3, 'Tossico');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('5678901234567', 'Guanti da Lavoro', 'Guanti resistenti per lavori manuali', 'http://www.example.com/guanti_lavoro.jpg', 'Altro', 14.99, 0.3, 10, 25, 5, 'Corrosivo');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('6789012345678', 'Portachiavi con Allarme', 'Portachiavi con allarme antifurto', 'http://www.example.com/portachiavi_allarme.jpg', 'Altro', 17.99, 0.1, 3, 5, 1, 'Infiammabile');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('7890123456789', 'Rilevatore di Movimento', 'Rilevatore di movimento per sicurezza', 'http://www.example.com/rilevatore_movimento.jpg', 'Altro', 39.99, 0.3, 5, 10, 2, 'Radioattivo');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('8901234567890', 'Set di Cacciaviti Magnetici', 'Set di cacciaviti con punte magnetiche', 'http://www.example.com/set_cacciaviti_magnetici.jpg', 'Altro', 19.99, 0.4, 8, 15, 3, 'Tossico');

INSERT INTO CatalogoProdotti (CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita)
VALUES ('9012345678901', 'Scatola di Pronto Soccorso', 'Scatola completa di primo soccorso', 'http://www.example.com/scatola_pronto_soccorso.jpg', 'Altro', 49.99, 1.5, 10, 20, 5, 'Infettante');
