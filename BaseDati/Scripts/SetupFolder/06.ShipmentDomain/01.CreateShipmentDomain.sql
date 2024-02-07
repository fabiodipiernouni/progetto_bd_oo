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

