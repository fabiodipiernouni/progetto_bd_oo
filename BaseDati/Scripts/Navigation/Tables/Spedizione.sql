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
    Stato varchar2(25) default 'DaLavorare' not null, -- DaLavorare, InLavorazionePackaging, LavorataPackaging, InLavorazioneSpedizione, LavorataSpedizione
    TrackingNumber varchar2(50) not null, -- codice univoco alfanumerico
    TrackingStatus varchar2(50) default 'Registrata' not null, -- Registrata, InPartenza, InTransito, InConsegna, Consegnata
    constraint PkSpedizione primary key (Id),
    constraint FkSpedizioneIdOrdineCliente foreign key (IdOrdineCliente) references OrdineCliente (Id),
    constraint UqSpedizioneIdOrdineCliente unique (IdOrdineCliente), --IdVincolo: VI.20
    constraint UqSpedizioneTrackingNumber unique (TrackingNumber),
    constraint CkSpedizioneTrackingStatus check (TrackingStatus in (
                                                                    'Registrata', -- stato iniziale, la spedizione è stata registrata ma non è ancora partita
                                                                    'InPartenza', -- gli ordini di packaging sono stati chiusi tutti e i pacchi sono pronti per partire
                                                                    'InTransito', -- la prima spedizione è avvenuta, quindi almeno un ordine di spedizione è in lavorazione e i pacchi sono in transito
                                                                    'InConsegna', -- questo cambio di stato deve essere effettuato dall'operatore corriere quando ritiene di essere in consegna, non è un cambio obbligatorio
                                                                    'Consegnata' -- tutti gli ordini di spedizione sono andati a conclusione
                                                                   )),
    constraint FkSpedizioneIdUtenteOrganizzatore foreign key (IdUtenteOrganizzatore) references Utente (Id),
    -- se DataFineLavorazione è valorizzato allora DataInizioLavorazione deve essere valorizzato
    constraint CkSpedizioneDateLavorazione check (DataFineLavorazione is null or (DataInizioLavorazione is not null AND DataFineLavorazione >= DataInizioLavorazione)), --IdVincolo: VI.21
    constraint CkSpedizioneStato check (Stato in ('DaLavorare', 'InLavorazionePackaging', 'LavorataPackaging', 'InLavorazioneSpedizione', 'LavorataSpedizione')),
    constraint CkSpedizioneDataInizioLavorazione check (DataInizioLavorazione is null or DataInizioLavorazione >= DataCreazione) --IdVincolo: VI.22
);