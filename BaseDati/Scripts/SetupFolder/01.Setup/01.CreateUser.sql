--attenzione, da eseguire una solta e come utente admin della base dati. Lo schema, una volta creato per intero, andrà rivisto nelle sue grant per questioni di sicurezza
--e affiancato ad uno schema più sicuro per l'accesso ai dati e le modifiche estemporanee
create user uninadev identified by Unidevtest4010$;

grant create session to uninadev;

grant create table to uninadev;

grant create view to uninadev;

grant create sequence to uninadev;

grant unlimited tablespace to uninadev;