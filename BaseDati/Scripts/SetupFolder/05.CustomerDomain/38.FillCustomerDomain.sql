-- inserimento di 10 clienti di esempio

truncate table DettaglioOrdine;
truncate table StatoOrdineClienteFiliale;
truncate table OrdineCliente;
truncate table Cliente;

INSERT INTO Cliente (Id, Nome, Cognome, Email, CodiceFiscale, PartitaIVA)
VALUES(1, 'Mario', 'Rossi', 'mario.rossi@mail.it', 'RSSMRA80A01H501A', null);

INSERT INTO Cliente (Id, Nome, Cognome, Email, CodiceFiscale, PartitaIVA)
VALUES(2, 'Luigi', 'Verdi', 'luigi.verdi@mail.it', 'VRDLGU80A01H501A', null);

INSERT INTO Cliente (Id, Nome, Cognome, Email, CodiceFiscale, PartitaIVA)
VALUES(3, 'Giovanni', 'Bianchi', 'giovanni.bianchi@mail.it', 'BNCVNN80A01H501A', null);

INSERT INTO Cliente (Id, Nome, Cognome, Email, CodiceFiscale, PartitaIVA)
VALUES(4, 'Giuseppe', 'Neri', 'giuseppe.neri@mail.it', 'NRIGPP80A01H501A', null);

INSERT INTO Cliente (Id, Nome, Cognome, RagioneSociale, Email, CodiceFiscale, PartitaIVA)
VALUES(5, 'Pippo', 'Ravazzi', 'Pippo & Co.', 'pippo.ravazzi@mail.it', null, '01234567890');

INSERT INTO Cliente (Id, Nome, Cognome, RagioneSociale, Email, CodiceFiscale, PartitaIVA)
VALUES(6, 'Pluto', 'Ravazzi', 'Pluto & Co.', 'pluto.ravazzi@mail.it', null, '01234567891');

INSERT INTO Cliente (Id, Nome, Cognome, RagioneSociale, Email, CodiceFiscale, PartitaIVA)
VALUES(7, 'Paperino', 'Peperino', 'Paperino & Co.', 'paperino.peperino@mail.it', null, '01234567892');

INSERT INTO Cliente (Id, Nome, Cognome, RagioneSociale, Email, CodiceFiscale, PartitaIVA)
VALUES(8, 'Emilio', 'Estevez', 'Billy spa', 'emilio@email.it', null, '01234567893');

INSERT INTO Cliente (Id, Nome, Cognome, RagioneSociale, Email, CodiceFiscale, PartitaIVA)
VALUES(9, 'Silvester', 'Stallone', 'Rocky srl', 'silvester.stallone@mail.it', null, '01234567894');

INSERT INTO Cliente (Id, Nome, Cognome, RagioneSociale, Email, CodiceFiscale, PartitaIVA)
VALUES(10, 'Arnold', 'Schwarzenegger', 'Terminator spa', 'arnold@mail.it', null, '01234567895');


-- inserimento di 2 ordini per cliente

INSERT INTO OrdineCliente (Id, DataOrdine, ImportoTotale, Stato, IdCliente, IdIndirizzoFatturazione) VALUES(1, to_date('20200101', 'YYYYMMDD'), 100.00, 'Bozza', 1, 1);
INSERT INTO OrdineCliente (Id, DataOrdine, ImportoTotale, Stato, IdCliente, IdIndirizzoFatturazione) VALUES(2, to_date('20200102', 'YYYYMMDD'), 20.00, 'Bozza', 1, 1);
INSERT INTO OrdineCliente (Id, DataOrdine, ImportoTotale, Stato, IdCliente, IdIndirizzoFatturazione) VALUES(3, to_date('20200103', 'YYYYMMDD'), 15.00, 'Bozza', 2, 2);
INSERT INTO OrdineCliente (Id, DataOrdine, ImportoTotale, Stato, IdCliente, IdIndirizzoFatturazione) VALUES(4, to_date('20200104', 'YYYYMMDD'), 10.00, 'Bozza', 2, 2);
INSERT INTO OrdineCliente (Id, DataOrdine, ImportoTotale, Stato, IdCliente, IdIndirizzoFatturazione) VALUES(5, to_date('20200105', 'YYYYMMDD'), 5.00, 'Bozza', 3, 3);
INSERT INTO OrdineCliente (Id, DataOrdine, ImportoTotale, Stato, IdCliente, IdIndirizzoFatturazione) VALUES(6, to_date('20200106', 'YYYYMMDD'), 13.00, 'Bozza', 3, 3);
INSERT INTO OrdineCliente (Id, DataOrdine, ImportoTotale, Stato, IdCliente, IdIndirizzoFatturazione) VALUES(7, to_date('20200107', 'YYYYMMDD'), 25.00, 'Bozza', 4, 4);
INSERT INTO OrdineCliente (Id, DataOrdine, ImportoTotale, Stato, IdCliente, IdIndirizzoFatturazione) VALUES(8, to_date('20200108', 'YYYYMMDD'), 30.00, 'Bozza', 4, 4);
INSERT INTO OrdineCliente (Id, DataOrdine, ImportoTotale, Stato, IdCliente, IdIndirizzoFatturazione) VALUES(9, to_date('20200109', 'YYYYMMDD'), 40.00, 'Bozza', 5, 5);
INSERT INTO OrdineCliente (Id, DataOrdine, ImportoTotale, Stato, IdCliente, IdIndirizzoFatturazione, IdIndirizzoSpedizione) VALUES(10, to_date('20200110', 'YYYYMMDD'), 50.00, 'Bozza', 5, 5, 6);

-- inserimento di 1 dettaglio ordine per ogni ordine

INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(1, 1, 1);
INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(1, 76, 4);

INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(2, 2, 2);
INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(2, 59, 1);
INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(2, 61, 1);
INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(2, 62, 1);


INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(3, 7, 1);
INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(3, 49, 1);
INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(3, 50, 1);

INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(4, 4, 1);
INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(4, 8, 1);
INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(4, 6, 1);
INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(4, 2, 1);

INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(5, 16, 10);
INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(5, 15, 2);
INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(5, 14, 1);
INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(5, 18, 2);
INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(5, 11, 5);
INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(5, 12, 1);

INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(6, 23, 1);

INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(7, 24, 1);
INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(7, 25, 1);

INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(8, 28, 1);

INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(9, 9, 9);

INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(10, 94, 1);
INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(10, 95, 3);
INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(10, 96, 1);
INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita) VALUES(10, 97, 1);

commit;

update OrdineCliente set Stato = 'Confermato' where Id between 1 and 10;

commit;
