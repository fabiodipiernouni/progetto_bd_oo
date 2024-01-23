-- Indirizzi filiali
truncate table Indirizzo;

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 1, 'Mario', 'Rossi', 'IT56740210663', 'Via Roma, 12', null, '00042', Id From Comune WHERE Nome = 'Roma';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 2, 'Luigi', 'Bianchi', 'IT71450370425', 'Via Milano, 32', null, '20019', Id From Comune WHERE Nome = 'Milano';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 3, 'Fabio', 'Di Pierno', 'IT10569000051', 'Via Napoli, 22', null, '80133', Id FROM Comune WHERE Nome = 'Napoli';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 4, 'Gianfranco', 'Capozzo', 'IT59076830526', 'Via Torino, 42', null, '10024', Id FROM Comune WHERE Nome = 'Torino';

INSERT INTO Indirizzo (Id, Nome, Cognome, CF_PIVA, Indirizzo_1, Indirizzo_2, CAP, IdComune)
SELECT 5, 'Fabio', 'Monzati', 'IT67907781008', 'Via Palermo, 72', null, '90132', Id FROM Comune WHERE Nome = 'Palermo';

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
SELECT 40, 'Alessandra', 'Sokolov', 'IT20064550484', 'Vicolo Gionata, 25', null, '70016', Id FROM Comune WHERE Nome = 'Noicattaro';

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

commit;
