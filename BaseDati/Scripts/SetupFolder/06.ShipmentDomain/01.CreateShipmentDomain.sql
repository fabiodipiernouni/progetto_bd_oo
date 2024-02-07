-- convezione nomi constraint: <Ck|Uk|Fk|Pk|WeakRel(fk con on delete cascade)><NomeTabella><Vincolo|NomeColonna>
-- convezione nomi: non si usano underscore e i nomi sono in camelcase

/*
    Nome: Cliente
    Descrizione: contiene i dati del cliente che effettua l'ordine
 */
create table Cliente
(
    IdCliente integer generated by default on null as identity not null,
    Nome VARCHAR2(64 byte) not null, -- se valorizzato RagioneSociale rappresenta il nome del referente
    Cognome VARCHAR2(64 byte) not null, -- se valorizzato RagioneSociale rappresenta il cognome del referente
    RagioneSociale VARCHAR2(64 byte),
    Email    VARCHAR2(64 byte) not null unique,
    CodiceFiscale VARCHAR2(16 byte), -- ammette null ma i valori sono unique
    PartitaIVA VARCHAR2(16 byte), -- ammette null ma i valori sono unique
    constraint PKCliente primary key (IDCliente),
    --uno tra CodiceFiscale e partitaIVA deve essere valorizzato
    constraint CkClienteCForPIVA check ((CodiceFiscale is not null and PartitaIVA is null) or (CodiceFiscale is null and PartitaIVA is not null)),
    constraint UkClienteCodiceFiscale unique (CodiceFiscale),
    constraint UkClientePartitaIVA unique (PartitaIVA),
    -- se è valorizzata RagioneSociale allora deve esserlo anche PartitaIVA
    constraint CkClienteRagioneSociale check (RagioneSociale is null or PartitaIVA is not null)
);

/*
    Nome: OrdineCliente
    Descrizione: contiene i dati dell'ordine effettuato dal cliente
 */
create table OrdineCliente
(
    Id integer generated by default on null as identity not null,
    DataOrdine DATE not null,
    ImportoTotale NUMBER(10,2) not null,
	DataInizioLavorazione date,
	DataFineLavorazione date,
    Stato VARCHAR2(64 byte) not null, -- Stato dell'ordine: Bozza, Confermato, Completato, InLavorazione, Lavorato
    IDCliente int not null,
    IdIndirizzoFatturazione int not null,
    IdIndirizzoSpedizione int, -- valorizzato se diverso da indirizzo di fatturazione
    constraint PkOrdineCliente primary key (Id),
    constraint WeakRelOrdineClienteCliente foreign key (IDCliente) references Cliente (IDCliente) on delete cascade,
    constraint CkOrdineClienteStato check (Stato in ('Bozza', 'Confermato', 'Completato', 'InLavorazione', 'Lavorato')),
	constraint CkOrdineClienteDate check (DataInizioLavorazione is null and DataFineLavorazione is null or (DataInizioLavorazione is not null and (DataFineLavorazione is null or DataFineLavorazione >= DataInizioLavorazione))),
    constraint FkOrdineClienteIndirizzoFatturazione foreign key (IdIndirizzoFatturazione) references Indirizzo (Id),
    constraint FkOrdineClienteIndirizzoSpedizione foreign key (IdIndirizzoSpedizione) references Indirizzo (Id)
);

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
    Id integer generated by default on null as identity not null,
    IdOrdineCliente int not null,
    IdFiliale int not null,  -- ogni dettaglio è di competenza di una filiale sulla base della giacenza in magazzino
    Stato VARCHAR2(64 byte) not null, -- Stato dell'ordine: Completato, Packaging, Spedito, Consegnato
    constraint PkStatoOrdineClienteFiliale primary key (Id),
    constraint FkStatoOrdineClienteFilialeOrdineCliente foreign key (IdOrdineCliente) references OrdineCliente (Id) on delete cascade,
    constraint FkStatoOrdineClienteFilialeFiliale foreign key (IdFiliale) references Filiale (Id),
    constraint CkStatoOrdineClienteFilialeStato check (Stato in ('Completato', 'Packaging', 'Spedito', 'Consegnato'))
);

/*
    Nome: DettaglioOrdine
    Descrizione: contiene i dati dei prodotti ordinati e le relative quantità e la filiale di riferimento. La tabella viene popolata dal sistema
        che aggiorna la filiale di riferimento in base alla giacenza in magazzino
 */
create table DettaglioOrdine (
    Id integer generated by default on null as identity not null,
    IdOrdine int not null,
    IdProdotto int not null,
    Quantita int not null, -- deve essere maggiore di zero
    FlagCompletato char(1) default 'N' not null, -- Y se il dettaglio è stato completato, N altrimenti
    FlagQuantitaDisponibile char(1) default 'Y' not null, -- Y se la quantità richiesta è disponibile, N altrimenti, il suo valore viene aggiornato dal trigger WorkerCompletaDettaglioOrdine
    constraint PkDettaglioOrdine primary key (Id),
    constraint WeakRelDettaglioOrdineIdOrdine foreign key (IdOrdine) references OrdineCliente (Id) on delete cascade,
    constraint FkDettaglioOrdineIdProdotto foreign key (IdProdotto) references CatalogoProdotti (Id),
    constraint CkDettaglioOrdineQuantita check (Quantita > 0),
    constraint CkDettaglioOrdineFlagCompletato check (FlagCompletato in ('Y', 'N')),
    constraint CkDettaglioOrdineFlagQuantitaDisponibile check (FlagQuantitaDisponibile in ('Y', 'N'))
);
/*
    Nome: LocationDettaglioOrdine
    Descrizione:
        contiene i dati della filiale e del magazzino di riferimento per ogni dettaglio ordine.
        La tabella viene popolata dal sistema (trigger WorkerCompletaDettaglioOrdine) che aggiorna la filiale di riferimento in base alla
        giacenza in magazzino
 */
create table LocationDettaglioOrdine (
    Id integer generated by default on null as identity not null,
    IdDettaglioOrdine integer not null,
    IdFilialeRiferimento integer not null,
    IdMagazzinoRiferimento integer not null,
    DataAssegnazione DATE default sysdate not null,
    constraint PkLocationDettaglioOrdine primary key (Id),
    constraint FkLocationDettaglioOrdineDettaglioOrdine foreign key (IdDettaglioOrdine) references DettaglioOrdine (Id) on delete cascade,
    constraint FkLocationDettaglioOrdineFiliale foreign key (IdFilialeRiferimento) references Filiale (Id)
);





-- convezione nomi constraint: <Ck|Uk|Fk|Pk|WeakRel(fk con on delete cascade)><NomeTabella><Vincolo|NomeColonna>
-- convezione nomi: non si usano underscore e i nomi sono in camelcase

/*
    Nome: Spedizione
    Descrizione: rappresenta una spedizione di un ordine cliente, la cardinalità è 1-1 con OrdineCliente
 */
create table Spedizione (
    Id integer generated by default on null as identity not null,
    DataCreazione date default sysdate not null,
    DataInizioLavorazione date,
    DataFineLavorazione date,
    IdOrdineCliente integer not null, -- unique, possiamo vere una spedizione per ogni ordine cliente
    IdUtenteOrganizzatore integer not null, --operatore che organizza la spedizione
    Stato varchar2(25) default 'DaLavorare' not null, -- DaLavorare, InLavorazionepackaging, Lavoratapackaging, InLavorazioneSpedizione, LavorataSpedizione
    TrackingNumber varchar2(50) not null, -- codice univoco alfanumerico
    TrackingStatus varchar2(50) default 'Registrata' not null, -- Registrata, InPartenza, InTransito, InConsegna, Consegnata
    constraint PkSpedizione primary key (Id),
    constraint FkSpedizioneIdOrdineCliente foreign key (IdOrdineCliente) references OrdineCliente (Id),
    constraint UqSpedizioneTrackingNumber unique (TrackingNumber),
    constraint CkSpedizioneTrackingStatus check (TrackingStatus in ('Registrata', 'InPartenza', 'InTransito', 'InConsegna', 'Consegnata')),
    constraint FkSpedizioneIdUtenteOrganizzatore foreign key (IdUtenteOrganizzatore) references Utente (Id),
    -- se DataFineLavorazione è valorizzato allora DataInizioLavorazione deve essere valorizzato
    constraint CkSpedizioneDateLavorazione check (DataFineLavorazione is null or (DataInizioLavorazione is not null AND DataFineLavorazione >= DataInizioLavorazione)),
    constraint CkSpedizioneStato check (Stato in ('DaLavorare', 'InLavorazionepackaging', 'Lavoratapackaging', 'InLavorazioneSpedizione', 'LavorataSpedizione')),
    constraint CkSpedizioneDataInizioLavorazione check (DataInizioLavorazione is null or DataInizioLavorazione >= DataCreazione)
);

/*
    Nome: OrdineDiLavoroPackaging
    Descrizione: rappresenta un ordine di lavoro di tipo 'Packaging', la cardinalità è in funzione del peso dei prodotti a magazzino.
 */

create table OrdineDiLavoroPackaging (
    Id integer generated by default on null as identity not null,
    DataCreazione date default sysdate not null,
    DataInizioPianificazione date, -- se valorizzato indica che l'ordine di lavoro è stato pianificato, sarà quindi possibile prendere l'ordine in carico dal gruppo corriere solo a a partire da questa data
    DataInizioLavorazione date, -- se valorizzata DataInizioPiannificazione deve essere maggiore o uguale ad essa
    DataFineLavorazione date, -- aggiornato dall'operatore corriere quando ha finito di lavorare l'ordine di lavoro
    IdGruppoCorriere integer, -- il gruppo corriere che prende in carico l'ordine di lavoro
    IdOperatoreCorriere integer, -- l'operatore corriere che prende in carico l'ordine di lavoro
    IdFiliale integer not null, -- filiale dove viene gestito l'ordine, i gruppi corrieri sono quelli che fanno riferimento a questa filiale
    IdMagazzino integer not null, -- magazzino in cui prelevare il materiale da imballare e dove depositare il materiale imballato
    IdSpedizione integer not null, -- spedizione di riferimento
    IdIndirizzoSpedizione int, -- indirizzo di spedizione del cliente
    Stato varchar2(50) generated always as (case
        when IdGruppoCorriere is null then 'DaAssegnare'
        when IdGruppoCorriere is not null and DataInizioLavorazione is null then 'Assegnato'
        when IdGruppoCorriere is not null and DataInizioLavorazione is not null and DataFineLavorazione is null then 'InLavorazione'
        when IdGruppoCorriere is not null and DataInizioLavorazione is not null and DataFineLavorazione is not null then 'Lavorato'
    end) virtual not null, -- la scelta di rendere virtuale lo stato è dovuta al fatto che non c'è necessità di indicizzare lo stato, inoltre si vuole evitare l'uso di trigger che potrebbero rallentare il sistema
    noteAggiuntiveCliente varchar2(512 byte),
    noteAggiuntiveOperatore varchar2(512 byte),
    constraint PkOrdineDiLavoroPackaging primary key (Id),
    constraint FkOrdineDiLavoroPackagingGruppoCorriere foreign key (IdGruppoCorriere) references GruppoCorriere (Id),
    constraint FkOrdineDiLavoroPackagingIdOperatoreCorriere foreign key (IdOperatoreCorriere) references Utente (Id),
    -- Le date di lavorazione possono essere non valorizzate o valorizzata solo DataInizioLavorazione o DataInizioLavorazione e DataFineLavorazione. DataInizioLavorazione, se valorizzata, deve essere sempre maggiore o uguale alla DataCreazione. Se DataFineLavorazione è valorizzato deve essere valorizzato anche DataInizioLavorazione e DataFineLavorazione deve essere maggiore o uguale a DataInizioLavorazione
    constraint CkOrdineDiLavoroPackagingDataInizioPianificazione check (DataInizioPianificazione is null or DataInizioPianificazione >= DataCreazione),
    constraint CkOrdineDiLavoroPackagingDataInizioLavorazione check (DataInizioLavorazione is null or DataInizioLavorazione >= nvl(DataInizioPianificazione, DataCreazione)),
    constraint CkOrdineDiLavoroPackagingDateLavorazione check (DataFineLavorazione is null or (DataInizioLavorazione is not null AND DataFineLavorazione >= DataInizioLavorazione)),
    constraint FkOrdineDiLavoroPackagingIdFiliale foreign key (IdFiliale) references Filiale (Id),
    constraint FkOrdineDiLavoroPackagingIdMagazzino foreign key (IdMagazzino) references Magazzino (Id),
    constraint FkOrdineDiLavoroPackagingIdSpedizione foreign key (IdSpedizione) references Spedizione (Id),
    -- se IdGruppoCorriere è valorizzato allora anche IdUtenteOperatore deve essere valorizzato
    constraint CkOrdineDiLavoroPackagingGruppoCorriere check ((IdGruppoCorriere is null and IdOperatoreCorriere is null) or (IdGruppoCorriere is not null and IdOperatoreCorriere is not null))
);

/*
    Nome: PackagingDetails
    Descrizione: riporta i dettagli per quali parti di ordine del cliente occorre effettuare il package
 */
create table PackagingDetails
(
    Id integer generated by default on null as identity not null,
    IdOrdineDiLavoroPackaging integer not null,
    IdDettaglioOrdine integer not null,
    IdMerceStoccataRiferiemento integer not null,
    Pericolosita varchar2(20 byte) not null, --enum Nessuna, Infiammabile, Esplosivo, Tossico, Chimico, Corrosivo, Infettante, Radioattivo
	CodicePropostaPacco integer not null,
    constraint PkPackagingDetails primary key (Id),
    constraint FkPackagingDetailsIdOrdineDiLavoroPackaging foreign key (IdOrdineDiLavoroPackaging) references OrdineDiLavoroPackaging (Id),
    constraint FkPackagingDetailsIdDettaglioOrdine foreign key (IdDettaglioOrdine) references DettaglioOrdine (Id),
    constraint FkPackagingDetailsIdMerceStoccataRiferiemento foreign key (IdMerceStoccataRiferiemento) references MerceStoccata (Id),
    constraint CkPackagingDetailsPericolosita check (Pericolosita in ('Nessuna', 'Infiammabile', 'Esplosivo', 'Tossico', 'Chimico', 'Corrosivo', 'Infettante', 'Radioattivo'))
);

create table Pacco (
    Id integer generated by default on null as identity not null,
    CodicePacco varchar2(50) not null, -- codice univoco alfanumerico
    Peso decimal not null, -- peso del pacco in kg, solitamente il peso unitario per il numero di prodotti ordinati
    IdMagazzino integer not null, --magazzino da cui prelevare il pacco
    IdIndirizzoDestinazione integer not null, -- indirizzo di destinazione del pacco
    IdOrdineLavoroOrigine integer not null, -- ordine di lavoro di tipo 'Packaging' che genera il pacco
    IdSpedizione integer not null, -- spedizione a cui è destinato il pacco
    constraint PkPacco primary key (Id),
    constraint UqPaccoCodicePacco unique (CodicePacco),
    constraint FkPaccoIdMagazzino foreign key (IdMagazzino) references Magazzino (Id),
    constraint FkPaccoIdIndirizzoDestinazione foreign key (IdIndirizzoDestinazione) references Indirizzo (Id),
    constraint FkPaccoIdOrdineLavoroOrigine foreign key (IdOrdineLavoroOrigine) references OrdineDiLavoroPackaging (Id),
    constraint FkPaccoIdSpedizione foreign key (IdSpedizione) references Spedizione (Id)
);

/*
    Nome: PaccoDettaglioOrdine
    Descrizione: riporta quali prodotti ordinati dal cliente contiene il pacco. E' un derivato di PackagingDetails.
 */
create table PaccoDettaglioOrdine (
    Id integer generated by default on null as identity not null,
    IdPacco integer not null,
    IdDettaglioOrdine integer not null,
    constraint PkPaccoDettaglioOrdine primary key (Id),
    constraint FkPaccoDettaglioOrdineIdPacco foreign key (IdPacco) references Pacco (Id),
    constraint FkPaccoDettaglioOrdineIdDettaglioOrdine foreign key (IdDettaglioOrdine) references DettaglioOrdine (Id)
);

/*
    Nome: OrdineDiLavoroSpedizione
    Descrizione: rappresenta un ordine di lavoro di tipo 'Spedizione', esiste una spedizione per ogni ordine cliente,
    lo stato diventa 'Lavorato' quando tutti i pacchi sono stati consegnati
 */
create table OrdineDiLavoroSpedizione (
    Id integer generated by default as identity not null,
    DataCreazione date default sysdate not null,
    DataInizioPianificazione date, -- se valorizzato indica che l'ordine di lavoro è stato pianificato, sarà quindi possibile prendere l'ordine in carico dal gruppo corriere solo a a partire da questa data
    DataInizioLavorazione date, -- se valorizzata DataInizioPiannificazione deve essere maggiore o uguale ad essa
    DataFineLavorazione date, -- aggiornato dall'operatore corriere quando ha finito di lavorare l'ordine di lavoro
    IdGruppoCorriere integer, -- il gruppo corriere che prende in carico l'ordine di lavoro
    IdOperatoreCorriere integer, -- l'operatore corriere che prende in carico l'ordine di lavoro
    IdMezzoDiTrasporto integer, -- valorizzato solo se TipoOrdine = 'Spedizione'
    IdFiliale integer not null, -- filiale dove viene gestito l'ordine, i gruppi corrieri sono quelli che fanno riferimento a questa filiale
    IdSpedizione integer not null, -- spedizione a cui è destinato il pacco
    Stato varchar2(50) generated always as (case
        when IdGruppoCorriere is null then 'DaAssegnare'
        when IdGruppoCorriere is not null and DataInizioLavorazione is null then 'Assegnato'
        when IdGruppoCorriere is not null and DataInizioLavorazione is not null and DataFineLavorazione is null then 'InLavorazione'
        when IdGruppoCorriere is not null and DataInizioLavorazione is not null and DataFineLavorazione is not null then 'Lavorato'
    end) virtual not null, -- la scelta di rendere virtuale lo stato è dovuta al fatto che non c'è necessità di indicizzare lo stato, inoltre si vuole evitare l'uso di trigger che potrebbero rallentare il sistema
    constraint PkOrdineDiLavoroSpedizione primary key (Id),
    constraint FkOrdineDiLavoroSpedizioneIdSpedizione foreign key (IdSpedizione) references Spedizione (Id),
    constraint FkOrdineDiLavoroSpedizioneIdFiliale foreign key (IdFiliale) references Filiale (Id),
    constraint FkOrdineDiLavoroSpedizioneGruppoCorriere foreign key (IdGruppoCorriere) references GruppoCorriere (Id),
    constraint FkOrdineDiLavoroSpedizioneMezzoDiTrasporto foreign key (IdMezzoDiTrasporto) references MezzoDiTrasporto (Id),
    constraint FkOrdineDiLavoroSpedizioneIdOperatoreCorriere foreign key (IdOperatoreCorriere) references Utente (Id),
    -- Le date di lavorazione possono essere non valorizzate o valorizzata solo DataInizioLavorazione o DataInizioLavorazione e DataFineLavorazione. DataInizioLavorazione, se valorizzata, deve essere sempre maggiore o uguale alla DataCreazione. Se DataFineLavorazione è valorizzato deve essere valorizzato anche DataInizioLavorazione e DataFineLavorazione deve essere maggiore o uguale a DataInizioLavorazione
    constraint CkOrdineDiLavoroSpedizioneDataInizioPianificazione check (DataInizioPianificazione is null or DataInizioPianificazione >= DataCreazione),
    constraint CkOrdineDiLavoroSpedizioneDataInizioLavorazione check (DataInizioLavorazione is null or DataInizioLavorazione >= nvl(DataInizioPianificazione, DataCreazione)),
    constraint CkOrdineDiLavoroSpedizioneDateLavorazione check (DataFineLavorazione is null or (DataInizioLavorazione is not null AND DataFineLavorazione >= DataInizioLavorazione)),
    -- IdMezzoDiTrasporto e DataInizioLavorazione devono essere valorizzati entrambi o nessuno dei due
    constraint CkOrdineDiLavoroSpedizioneMezzoDiTrasporto check ((IdMezzoDiTrasporto is null and DataInizioLavorazione is null) or (IdMezzoDiTrasporto is not null and DataInizioLavorazione is not null)),
    -- se IdGruppoCorriere è valorizzato allora anche IdUtenteOperatore deve essere valorizzato
    constraint CkOrdineDiLavoroSpedizioneGruppoCorriere check ((IdGruppoCorriere is null and IdOperatoreCorriere is null) or (IdGruppoCorriere is not null and IdOperatoreCorriere is not null))
);

/*
    Nome: OrdineDiLavoroSpedizionePacchi
    Descrizione: riporta i pacchi che compongono l'ordine di lavoro di tipo 'Spedizione'
 */
create table OrdineDiLavoroSpedizionePacchi (
    Id integer generated by default as identity not null,
    IdOrdineDiLavoroSpedizione integer not null,
    IdPacco integer not null,
    constraint PkOrdineDiLavoroSpedizionePacchi primary key (Id),
    constraint FkOrdineDiLavoroSpedizionePacchiIdOrdineDiLavoroSpedizione foreign key (IdOrdineDiLavoroSpedizione) references OrdineDiLavoroSpedizione (Id),
    constraint FkOrdineDiLavoroSpedizionePacchiIdPacco foreign key (IdPacco) references Pacco (Id)
);
-------------------------

