create table Funzione
(
    Id integer not null,
    Funzione varchar2(64 byte) not null,
    constraint PkFunzione primary key (Id),
    constraint UqFunzioneFunzione unique (Funzione)
)
/

comment on table Funzione is 'La tabella contiene un elenco di funzionalità che andranno poi a caratterizzare parte dell''applicativo UninaDelivery.'
/

comment on column Funzione.Funzione is 'Funzione che sarà assegnata ad un profilo.'
/