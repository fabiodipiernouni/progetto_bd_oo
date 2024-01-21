begin
    execute immediate 'drop table spedizione cascade constraints purge';
exception when others then null;
end;
/

create table spedizione (
    Id integer not null,
    DataCreazione date default sysdate not null,
    DataInizioLavorazione date,
    DataFineLavorazione date,
    IdOrdine integer not null,
    Stato varchar2(50) not null, -- DaLavorare, InLavorazione, Lavorata
    TrackingNumber varchar2(50) not null, -- codice univoco alfanumerico
    TrackingStatus varchar2(50) not null, -- Registrata, InPartenza, InTransito, InConsegna, Consegnata
    destinazione varchar2(50) not null,
    costo number(10,2) not null,
    constraint spedizione_pk primary key (Id),
    constraint spedizione_ordine_fk foreign key (IdOrdine) references ordine (Id),
    constraint spedizione_trackingnumber_uq unique (TrackingNumber),
    constraint spedizione_trackingstatus_ck check (TrackingStatus in ('Registrata', 'InPartenza', 'InTransito', 'InConsegna', 'Consegnata')),
    constraint spedizione_stato_ck check (Stato in ('DaLavorare', 'InLavorazione', 'Lavorata'))
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
    constraint ordinedilavoro_pk primary key (Id),
    constraint ordinedilavoro_spedizione_fk foreign key (IdSpedizione) references spedizione (Id),
    constraint ordinedilavoro_gruppocorriere_fk foreign key (IdGruppoCorriere) references gruppoCorriere (Id),
    constraint ordinedilavoro_mezzoditrasporto_fk foreign key (IdMezzoDiTrasporto) references mezzoDiTrasporto (Id)
);