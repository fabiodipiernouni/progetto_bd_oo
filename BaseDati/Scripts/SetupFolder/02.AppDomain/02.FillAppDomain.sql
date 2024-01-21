insert into "Profilo" (IDProfilo, Profilo)
values (1, 'Operatore');
insert into "Profilo" (IDProfilo, Profilo)
values (2, 'OperatoreCorriere');
insert into "Profilo" (IDProfilo, Profilo)
values (3, 'Manager');
insert into "Profilo" (IDProfilo, Profilo)
values (4, 'Admin');

commit;

insert into "Funzione" (IDFunzione, Funzione)
values (1, 'GESTIONE_MAGAZZINI'); -- Menu di Gestione Magazzini
insert into "Funzione" (IDFunzione, Funzione)
values (2, 'VIEW_MAGAZZINI'); -- Pagina di elenco Magazzini, Visualizzazione Magazzino (dettaglio delle merci stocked)
insert into "Funzione" (IDFunzione, Funzione)
values (3, 'EDIT_MAGAZZINO'); -- Aggiornamento quantità magazzino, delete di merce stocked, inserimento di merce stocked
insert into "Funzione" (IDFunzione, Funzione)
values (4, 'GESTIONE_CLIENTI'); -- menu gestione clienti
insert into "Funzione" (IDFunzione, Funzione)
values (5, 'VIEW_ORDINICLIENTI'); -- visualizzazione elenco ordini clienti, visualizza dettaglio ordine
insert into "Funzione" (IDFunzione, Funzione)
values (6,  'GESTIONE_SPEDIZIONI'); -- menu gestione spedizioni
insert into "Funzione" (IDFunzione, Funzione)
values (7, 'VIEW_SPEDIZIONI'); -- visualizza elenco delle spedizioni, visualizza dettaglio spedizione
insert into "Funzione" (IDFunzione, Funzione)
values (8, 'VIEW_ORDINILAVORO'); -- Visualizza Ordini di lavoro
insert into "Funzione" (IDFunzione, Funzione)
values (9, 'EDIT_ORDINILAVORO'); -- delete ordiniLavoro, update OrdiniLavoro
insert into "Funzione" (IDFunzione, Funzione)
values (10, 'GESTIONE_REPORTS'); -- gestione reportistica
insert into "Funzione" (IDFunzione, Funzione)
values (11, 'VIEW_REPORTS'); -- gestione reportistica

commit;

insert into "ProfiloFunzione" ("IDProfilo", "IDFunzione") values (1, 1);
insert into "ProfiloFunzione" ("IDProfilo", "IDFunzione") values (1, 2);
insert into "ProfiloFunzione" ("IDProfilo", "IDFunzione") values (1, 3);
insert into "ProfiloFunzione" ("IDProfilo", "IDFunzione") values (1, 4);
insert into "ProfiloFunzione" ("IDProfilo", "IDFunzione") values (1, 5);
insert into "ProfiloFunzione" ("IDProfilo", "IDFunzione") values (1, 6);
insert into "ProfiloFunzione" ("IDProfilo", "IDFunzione") values (1, 7);
insert into "ProfiloFunzione" ("IDProfilo", "IDFunzione") values (1, 8);
insert into "ProfiloFunzione" ("IDProfilo", "IDFunzione") values (1, 9);

insert into "ProfiloFunzione" ("IDProfilo", "IDFunzione") values (2, 6);
insert into "ProfiloFunzione" ("IDProfilo", "IDFunzione") values (2, 8);
insert into "ProfiloFunzione" ("IDProfilo", "IDFunzione") values (2, 9);

insert into "ProfiloFunzione" ("IDProfilo", "IDFunzione") values (3, 10);
insert into "ProfiloFunzione" ("IDProfilo", "IDFunzione") values (3, 11);

commit;


