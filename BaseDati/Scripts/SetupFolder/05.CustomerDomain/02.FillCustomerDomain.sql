-- inserimento di 10 clienti di esempio
insert into Cliente (IDCliente, Nome, Cognome, Email, CodiceFiscale, partitaIVA) values (1, 'Mario', 'Rossi', 'mario.rossi@mail.it', 'RSSMRA80A01H501A', null);
insert into Cliente (IDCliente, Nome, Cognome, Email, CodiceFiscale, partitaIVA) values (2, 'Luigi', 'Verdi', 'luigi.verdi@mail.it', 'VRDLGU80A01H501A', null);
insert into Cliente (IDCliente, Nome, Cognome, Email, CodiceFiscale, partitaIVA) values (3, 'Giovanni', 'Bianchi', 'giovanni.bianchi@mail.it', 'BNCVNN80A01H501A', null);
insert into Cliente (IDCliente, Nome, Cognome, Email, CodiceFiscale, partitaIVA) values (4, 'Giuseppe', 'Neri', 'giuseppe.neri@mail.it', 'NRIGPP80A01H501A', null);
insert into Cliente (IDCliente, Nome, Cognome, RagioneSociale, Email, CodiceFiscale, partitaIVA) values (5, 'Pippo', 'Ravazzi', 'Pippo & Co.', 'pippo.ravazzi@mail.it', null, '01234567890');
insert into Cliente (IDCliente, Nome, Cognome, RagioneSociale, Email, CodiceFiscale, partitaIVA) values (6, 'Pluto', 'Ravazzi', 'Pluto & Co.', 'pluto.ravazzi@mail.it', null, '01234567891');
insert into Cliente (IDCliente, Nome, Cognome, RagioneSociale, Email, CodiceFiscale, partitaIVA) values (7, 'Paperino', 'Peperino', 'Paperino & Co.', 'paperino.peperino@mail.it', null, '01234567892');
insert into Cliente (IDCliente, Nome, Cognome, RagioneSociale, Email, CodiceFiscale, partitaIVA) values (8, 'Emilio', 'Estevez', 'Billy spa', 'emilio@email.it', null, '01234567893');
insert into Cliente (IDCliente, Nome, Cognome, RagioneSociale, Email, CodiceFiscale, partitaIVA) values (9, 'Silvester', 'Stallone', 'Rocky srl', 'silvester.stallone@mail.it', null, '01234567894');
insert into Cliente (IDCliente, Nome, Cognome, RagioneSociale, Email, CodiceFiscale, partitaIVA) values (10, 'Arnold', 'Schwarzenegger', 'Terminator spa', 'arnold@mail.it', null, '01234567895');



-- inserimento di 2 ordini per cliente
insert into OrdineCliente (IDOrdine, DataOrdine, ImportoTotale, Stato, IDCliente, IdIndirizzoFatturazione) values (1, '2020-01-01', 100.00, 'Confermato', 1, 1);
insert into OrdineCliente (IDOrdine, DataOrdine, ImportoTotale, Stato, IDCliente, IdIndirizzoFatturazione) values (2, '2020-01-02', 20.00, 'Confermato', 1, 1);
insert into OrdineCliente (IDOrdine, DataOrdine, ImportoTotale, Stato, IDCliente, IdIndirizzoFatturazione) values (3, '2020-01-03', 15.00, 'Confermato', 2, 2);
insert into OrdineCliente (IDOrdine, DataOrdine, ImportoTotale, Stato, IDCliente, IdIndirizzoFatturazione) values (4, '2020-01-04', 10.00, 'Confermato', 2, 2);
insert into OrdineCliente (IDOrdine, DataOrdine, ImportoTotale, Stato, IDCliente, IdIndirizzoFatturazione) values (5, '2020-01-05', 5.00, 'Confermato', 3, 3);
insert into OrdineCliente (IDOrdine, DataOrdine, ImportoTotale, Stato, IDCliente, IdIndirizzoFatturazione) values (6, '2020-01-06', 13.00, 'Confermato', 3, 3);
insert into OrdineCliente (IDOrdine, DataOrdine, ImportoTotale, Stato, IDCliente, IdIndirizzoFatturazione) values (7, '2020-01-07', 25.00, 'Confermato', 4, 4);
insert into OrdineCliente (IDOrdine, DataOrdine, ImportoTotale, Stato, IDCliente, IdIndirizzoFatturazione) values (8, '2020-01-08', 30.00, 'Confermato', 4, 4);
insert into OrdineCliente (IDOrdine, DataOrdine, ImportoTotale, Stato, IDCliente, IdIndirizzoFatturazione) values (9, '2020-01-09', 40.00, 'Confermato', 5, 5);
insert into OrdineCliente (IDOrdine, DataOrdine, ImportoTotale, Stato, IDCliente, IdIndirizzoFatturazione, IdIndirizzoSpedizione) values (10, '2020-01-10', 50.00, 'Confermato', 5, 5, 6);


-- inserimento di 1 dettaglio ordine per ogni ordine
insert into DettaglioOrdine (IDOrdine, IDProdotto, Quantita) values (1, 1, 1);
insert into DettaglioOrdine (IDOrdine, IDProdotto, Quantita) values (2, 2, 2);
insert into DettaglioOrdine (IDOrdine, IDProdotto, Quantita) values (3, 3, 3);
insert into DettaglioOrdine (IDOrdine, IDProdotto, Quantita) values (4, 4, 4);
insert into DettaglioOrdine (IDOrdine, IDProdotto, Quantita) values (5, 5, 5);
insert into DettaglioOrdine (IDOrdine, IDProdotto, Quantita) values (6, 6, 6);
insert into DettaglioOrdine (IDOrdine, IDProdotto, Quantita) values (7, 7, 7);
insert into DettaglioOrdine (IDOrdine, IDProdotto, Quantita) values (8, 8, 8);
insert into DettaglioOrdine (IDOrdine, IDProdotto, Quantita) values (9, 9, 9);
insert into DettaglioOrdine (IDOrdine, IDProdotto, Quantita) values (10, 10, 10);

commit;