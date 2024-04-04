--attenzione, da eseguire una solta e come utente admin della base dati. Lo schema, una volta creato per intero, andrà rivisto nelle sue grant per questioni di sicurezza
--e affiancato ad uno schema più sicuro per l'accesso ai dati e le modifiche estemporanee
create user uninadev identified by Unidevtest4010$;

grant create session to uninadev;

grant create table to uninadev;

grant create view to uninadev;

grant create sequence to uninadev;

grant create any procedure to uninadev;

grant create any trigger to uninadev;

grant execute any procedure to uninadev;

grant execute any functions to uninadev;

grant create any sequence to uninadev;

grant unlimited tablespace to uninadev;

GRANT CREATE SYNONYM TO uninadev;

GRANT CREATE PUBLIC SYNONYM TO uninadev;

--attenzione, da eseguire una solta e come utente admin della base dati. Lo schema, una volta creato per intero, andrà rivisto nelle sue grant per questioni di sicurezza
--e affiancato ad uno schema più sicuro per l'accesso ai dati e le modifiche estemporanee
create user uninaTest identified by Unidevtest4010$;

grant create session to uninaTest;

grant create table to uninaTest;

grant create view to uninaTest;

grant create sequence to uninaTest;

grant create any procedure to uninaTest;

grant create any trigger to uninaTest;

grant execute any procedure to uninaTest;

grant create any sequence to uninaTest;

grant unlimited tablespace to uninaTest;

GRANT CREATE SYNONYM TO uninaTest;

GRANT CREATE PUBLIC SYNONYM TO uninaTest;

-- account di produzione

create user uninaDelivery identified by UnidevProd4010$;

grant create session to uninaDelivery;

grant create table to uninaDelivery;

grant create view to uninaDelivery;

grant create sequence to uninaDelivery;

grant create any procedure to uninaDelivery;

grant create any trigger to uninaDelivery;

grant execute any procedure to uninaDelivery;

grant create any sequence to uninaDelivery;

grant unlimited tablespace to uninaDelivery;

GRANT CREATE SYNONYM TO uninaDelivery;

GRANT CREATE PUBLIC SYNONYM TO uninaDelivery;
