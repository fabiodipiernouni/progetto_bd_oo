-- inserimento di 10 clienti di esempio

INSERT INTO Cliente (IdCliente, Nome, Cognome, Email, CodiceFiscale, PartitaIVA)
VALUES(1, 'Mario', 'Rossi', 'mario.rossi@mail.it', 'RSSMRA80A01H501A', null);

INSERT INTO Cliente (IdCliente, Nome, Cognome, Email, CodiceFiscale, PartitaIVA)
VALUES(2, 'Luigi', 'Verdi', 'luigi.verdi@mail.it', 'VRDLGU80A01H501A', null);

INSERT INTO Cliente (IdCliente, Nome, Cognome, Email, CodiceFiscale, PartitaIVA)
VALUES(3, 'Giovanni', 'Bianchi', 'giovanni.bianchi@mail.it', 'BNCVNN80A01H501A', null);

INSERT INTO Cliente (IdCliente, Nome, Cognome, Email, CodiceFiscale, PartitaIVA)
VALUES(4, 'Giuseppe', 'Neri', 'giuseppe.neri@mail.it', 'NRIGPP80A01H501A', null);

INSERT INTO Cliente (IdCliente, Nome, Cognome, RagioneSociale, Email, CodiceFiscale, PartitaIVA)
VALUES(5, 'Pippo', 'Ravazzi', 'Pippo & Co.', 'pippo.ravazzi@mail.it', null, '01234567890');

INSERT INTO Cliente (IdCliente, Nome, Cognome, RagioneSociale, Email, CodiceFiscale, PartitaIVA)
VALUES(6, 'Pluto', 'Ravazzi', 'Pluto & Co.', 'pluto.ravazzi@mail.it', null, '01234567891');

INSERT INTO Cliente (IdCliente, Nome, Cognome, RagioneSociale, Email, CodiceFiscale, PartitaIVA)
VALUES(7, 'Paperino', 'Peperino', 'Paperino & Co.', 'paperino.peperino@mail.it', null, '01234567892');

INSERT INTO Cliente (IdCliente, Nome, Cognome, RagioneSociale, Email, CodiceFiscale, PartitaIVA)
VALUES(8, 'Emilio', 'Estevez', 'Billy spa', 'emilio@email.it', null, '01234567893');

INSERT INTO Cliente (IdCliente, Nome, Cognome, RagioneSociale, Email, CodiceFiscale, PartitaIVA)
VALUES(9, 'Silvester', 'Stallone', 'Rocky srl', 'silvester.stallone@mail.it', null, '01234567894');

INSERT INTO Cliente (IdCliente, Nome, Cognome, RagioneSociale, Email, CodiceFiscale, PartitaIVA)
VALUES(10, 'Arnold', 'Schwarzenegger', 'Terminator spa', 'arnold@mail.it', null, '01234567895');


-- inserimento di 2 ordini per cliente

INSERT INTO OrdineCliente (IdOrdine, DataOrdine, ImportoTotale, Stato, IdCliente, IdIndirizzoFatturazione)
VALUES(1, '2020-01-01', 100.00, 'Confermato', 1, 1);

INSERT INTO OrdineCliente (IdOrdine, DataOrdine, ImportoTotale, Stato, IdCliente, IdIndirizzoFatturazione)
VALUES(2, '2020-01-02', 20.00, 'Confermato', 1, 1);

INSERT INTO OrdineCliente (IdOrdine, DataOrdine, ImportoTotale, Stato, IdCliente, IdIndirizzoFatturazione)
VALUES(3, '2020-01-03', 15.00, 'Confermato', 2, 2);

INSERT INTO OrdineCliente (IdOrdine, DataOrdine, ImportoTotale, Stato, IdCliente, IdIndirizzoFatturazione)
VALUES(4, '2020-01-04', 10.00, 'Confermato', 2, 2);

INSERT INTO OrdineCliente (IdOrdine, DataOrdine, ImportoTotale, Stato, IdCliente, IdIndirizzoFatturazione)
VALUES(5, '2020-01-05', 5.00, 'Confermato', 3, 3);

INSERT INTO OrdineCliente (IdOrdine, DataOrdine, ImportoTotale, Stato, IdCliente, IdIndirizzoFatturazione)
VALUES(6, '2020-01-06', 13.00, 'Confermato', 3, 3);

INSERT INTO OrdineCliente (IdOrdine, DataOrdine, ImportoTotale, Stato, IdCliente, IdIndirizzoFatturazione)
VALUES(7, '2020-01-07', 25.00, 'Confermato', 4, 4);

INSERT INTO OrdineCliente (IdOrdine, DataOrdine, ImportoTotale, Stato, IdCliente, IdIndirizzoFatturazione)
VALUES(8, '2020-01-08', 30.00, 'Confermato', 4, 4);

INSERT INTO OrdineCliente (IdOrdine, DataOrdine, ImportoTotale, Stato, IdCliente, IdIndirizzoFatturazione)
VALUES(9, '2020-01-09', 40.00, 'Confermato', 5, 5);

INSERT INTO OrdineCliente (IdOrdine, DataOrdine, ImportoTotale, Stato, IdCliente, IdIndirizzoFatturazione, IdIndirizzoSpedizione)
VALUES(10, '2020-01-10', 50.00, 'Confermato', 5, 5, 6);


-- inserimento di 1 dettaglio ordine per ogni ordine

INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita)
VALUES(1, 1, 1);

INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita)
VALUES(2, 2, 2);

INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita)
VALUES(3, 3, 3);

INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita)
VALUES(4, 4, 4);

INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita)
VALUES(5, 5, 5);

INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita)
VALUES(6, 6, 6);

INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita)
VALUES(7, 7, 7);

INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita)
VALUES(8, 8, 8);

INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita)
VALUES(9, 9, 9);

INSERT INTO DettaglioOrdine (IdOrdine, IDProdotto, Quantita)
VALUES(10, 10, 10);


commit;
