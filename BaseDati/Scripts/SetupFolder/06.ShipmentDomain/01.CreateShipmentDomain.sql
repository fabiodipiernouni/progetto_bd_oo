begin
    execute immediate 'drop table Spedizione cascade constraints purge';
exception when others then null;
end;
/

create table Spedizione (
    Id integer not null,
    DataCreazione date default sysdate not null,
    DataInizioLavorazione date,
    DataFineLavorazione date,
    IdOrdine integer not null,
    Stato varchar2(50) not null, -- DaLavorare, InLavorazione, Lavorata
    TrackingNumber varchar2(50) not null, -- codice univoco alfanumerico
    TrackingStatus varchar2(50) not null, -- Registrata, InPartenza, InTransito, InConsegna, Consegnata
    Destinazione varchar2(50) not null,
    Costo number(10,2) not null,
    constraint pk_Spedizione primary key (Id),
    constraint fk_Spedizione_IdOrdine foreign key (IdOrdine) references ordine (Id),
    constraint uq_Spedizione_TrackingNumber unique (TrackingNumber),
    constraint check_Spedizione_TrackingStatus check (TrackingStatus in ('Registrata', 'InPartenza', 'InTransito', 'InConsegna', 'Consegnata')),
    constraint check_Spedizione_Stato check (Stato in ('DaLavorare', 'InLavorazione', 'Lavorata'))
);

begin
    execute immediate 'drop table OrdineDiLavoro cascade constraints purge';
exception when others then null;
end;
/

create table OrdineDiLavoro (
    Id integer not null,
    DataCreazione date default sysdate not null,
    DataInizioLavorazione date,
    DataFineLavorazione date,
    IdSpedizione integer not null,
    IdGruppoCorriere integer,
    IdMezzoDiTrasporto integer,
    Stato varchar2(50) generated always as (case
        when IdGruppoCorriere is null then 'DaAssegnare'
        when IdGruppoCorriere is not null and DataInizioLavorazione is null then 'Assegnato'
        when IdGruppoCorriere is not null and DataInizioLavorazione is not null and DataFineLavorazione is null then 'InLavorazione'
        when IdGruppoCorriere is not null and DataInizioLavorazione is not null and DataFineLavorazione is not null then 'Lavorato'
    end) virtual, -- la scelta di rendere virtuale lo stato è dovuta al fatto che non c'è necessità di indicizzare lo stato, inoltre si vuole evitare l'uso di trigger che potrebbero rallentare il sistema
    constraint pk_OrdineDiLavoro primary key (Id),
    constraint fk_OrdineDiLavoro_Spedizione foreign key (IdSpedizione) references Spedizione (Id),
    constraint fk_OrdineDiLavoro_GruppoCorriere foreign key (IdGruppoCorriere) references GruppoCorriere (Id),
    constraint fk_OrdineDiLavoro_MezzoDiTrasporto foreign key (IdMezzoDiTrasporto) references MezzoDiTrasporto (Id)
);