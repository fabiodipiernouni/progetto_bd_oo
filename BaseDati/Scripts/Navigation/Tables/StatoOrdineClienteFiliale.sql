/*
    Nome: StatoOrdineClienteFiliale
    Descrizione:
        contiene lo stato dell'ordine per ogni filiale. Questa tabella è necesssaria perché per un singolo ordine la gestione dei prodotti ordinati
        è assegnata alle filiali in base alla giacenza in magazzino e alla filiale di riferimento del magazzino.
        La tabella viene popolata dal trigger CkCompletaDettaglioOrdine
    ESEMPIO:
        Supponendo un ordine di 3 prodotti, 2 di questi sono disponibili in magazzino della filiale A e 1 in magazzino della filiale B.
        La filiale A gestirà l'ordine in relazione ai primi 2 prodotti e l'ordine avrà un workflow di stato relativo solo a questi due prodotti.
        La filiale B gestirà l'ordine in relazione al terzo prodotto e l'ordine avrà un workflow di stato relativo solo a questo prodotto.
 */
create table StatoOrdineClienteFiliale
(
    IdOrdineCliente integer not null,
    IdFiliale integer not null,  -- ogni dettaglio è di competenza di una filiale sulla base della giacenza in magazzino
    Stato VARCHAR2(64 byte) not null, -- Stato dell'ordine: Completato, Packaging, Spedito, Consegnato
    constraint PkStatoOrdineClienteFiliale primary key (IdOrdineCliente, IdFiliale),
    constraint FkStatoOrdineClienteFilialeOrdineCliente foreign key (IdOrdineCliente) references OrdineCliente (Id) on delete cascade,
    constraint FkStatoOrdineClienteFilialeFiliale foreign key (IdFiliale) references Filiale (Id),
    constraint CkStatoOrdineClienteFilialeStato check (Stato in ('Completato', 'Packaging', 'Spedito', 'Consegnato'))
);