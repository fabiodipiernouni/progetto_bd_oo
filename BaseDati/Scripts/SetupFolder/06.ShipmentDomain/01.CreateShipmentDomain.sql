-- convezione nomi constraint: <Ck|Uk|Fk|Pk|WeakRel(fk con on delete cascade)><NomeTabella><Vincolo|NomeColonna>
-- convezione nomi: non si usano underscore e i nomi sono in camelcase

create table Spedizione (
    Id integer not null,
    DataCreazione date default sysdate not null,
    DataInizioLavorazione date,
    DataFineLavorazione date,
    IdOrdine integer not null,
    IdUtenteOrganizzatore integer not null,
    Stato varchar2(50) generated always as (case
        when DataInizioLavorazione is null then 'DaLavorare'
        when DataInizioLavorazione is not null and DataFineLavorazione is null then 'InLavorazione'
        when DataInizioLavorazione is not null and DataFineLavorazione is not null then 'Lavorata'
    end) virtual, -- la scelta di rendere virtuale lo stato è dovuta al fatto che non c'è necessità di indicizzare lo stato, inoltre si vuole evitare l'uso di trigger che potrebbero rallentare il sistema
    TrackingNumber varchar2(50) not null, -- codice univoco alfanumerico
    TrackingStatus varchar2(50) default 'Registrata' not null, -- Registrata, InPartenza, InTransito, InConsegna, Consegnata
    constraint PkSpedizione primary key (Id),
    constraint FkSpedizioneIdOrdine foreign key (IdOrdine) references OrdineCliente (Id),
    constraint UqSpedizioneTrackingNumber unique (TrackingNumber),
    constraint CkSpedizioneTrackingStatus check (TrackingStatus in ('Registrata', 'InPartenza', 'InTransito', 'InConsegna', 'Consegnata')),
    constraint FkSpedizioneIdUtenteOrganizzatore foreign key (IdUtenteOrganizzatore) references Utente (Id),
    -- se DataFineLavorazione è valorizzato allora DataInizioLavorazione deve essere valorizzato
    constraint CkSpedizioneDateLavorazione check (DataFineLavorazione is null or (DataInizioLavorazione is not null AND DataFineLavorazione >= DataInizioLavorazione)),
    constraint CkSpedizioneDataInizioLavorazione check (DataInizioLavorazione is null or DataInizioLavorazione >= DataCreazione)
);

create table OrdineDiLavoro (
    Id integer not null,
    DataCreazione date default sysdate not null,
    DataInizioLavorazione date,
    DataFineLavorazione date,
    IdSpedizione integer not null,
    IdGruppoCorriere integer,
    IdMezzoDiTrasporto integer,
    IdUtenteOperatore integer not null,
    Stato varchar2(50) generated always as (case
        when IdGruppoCorriere is null then 'DaAssegnare'
        when IdGruppoCorriere is not null and DataInizioLavorazione is null then 'Assegnato'
        when IdGruppoCorriere is not null and DataInizioLavorazione is not null and DataFineLavorazione is null then 'InLavorazione'
        when IdGruppoCorriere is not null and DataInizioLavorazione is not null and DataFineLavorazione is not null then 'Lavorato'
    end) virtual, -- la scelta di rendere virtuale lo stato è dovuta al fatto che non c'è necessità di indicizzare lo stato, inoltre si vuole evitare l'uso di trigger che potrebbero rallentare il sistema
    constraint PkOrdineDiLavoro primary key (Id),
    constraint FkOrdineDiLavoroSpedizione foreign key (IdSpedizione) references Spedizione (Id),
    constraint FkOrdineDiLavoroGruppoCorriere foreign key (IdGruppoCorriere) references GruppoCorriere (Id),
    constraint FkOrdineDiLavoroMezzoDiTrasporto foreign key (IdMezzoDiTrasporto) references MezzoDiTrasporto (Id),
    constraint FkOrdineDiLavoroIdUtenteOperatore foreign key (IdUtenteOperatore) references Utente (Id),
    -- Le date di lavorazione possono essere non valorizzate o valorizzata solo DataInizioLavorazione o DataInizioLavorazione e DataFineLavorazione. DataInizioLavorazione, se valorizzata, deve essere sempre maggiore o uguale alla DataCreazione. Se DataFineLavorazione è valorizzato deve essere valorizzato anche DataInizioLavorazione e DataFineLavorazione deve essere maggiore o uguale a DataInizioLavorazione
    constraint CkOrdineDiLavoroDateLavorazione check (DataFineLavorazione is null or (DataInizioLavorazione is not null AND DataFineLavorazione >= DataInizioLavorazione)),
    constraint CkOrdineDiLavoroDataInizioLavorazione check (DataInizioLavorazione is null or DataInizioLavorazione >= DataCreazione),
    -- IdMezzoDiTrasporto e DataInizioLavorazione devono essere valorizzati entrambi o nessuno dei due
    constraint CkOrdineDiLavoroMezzoDiTrasporto check ((IdMezzoDiTrasporto is null and DataInizioLavorazione is null) or (IdMezzoDiTrasporto is not null and DataInizioLavorazione is not null)),
    -- se IdGruppoCorriere è valorizzato allora anche IdUtenteOperatore deve essere valorizzato
    constraint CkOrdineDiLavoroGruppoCorriere check ((IdGruppoCorriere is null and IdUtenteOperatore is null) or (IdGruppoCorriere is not null and IdUtenteOperatore is not null)),
);