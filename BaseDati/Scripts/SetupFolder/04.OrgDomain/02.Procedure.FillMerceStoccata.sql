-- non ci interessa cosa c'è nei magazzini, inseriamo qualche dato casuale

CREATE OR REPLACE PROCEDURE FillMerceStoccata AS
DECLARE
    NUM_PRODOTTI INTEGER := 0;
    NUM_MAGAZZINI INTEGER := 0;

    quantita INTEGER := 0;

    settoreMagazzino CHAR := 'A';

BEGIN

    SELECT COUNT(*) INTO NUM_PRODOTTI FROM CatalogoProdotti;
    SELECT COUNT(*) INTO NUM_MAGAZZINI FROM Magazzino;

    FOR idProdotto IN 1..NUM_PRODOTTI LOOP
        FOR idMagazzino IN 1..NUM_MAGAZZINI LOOP

            --facciamo una possibilità su 10 di non avere il prodotto nel magazzino
            IF DBMS_RANDOM.VALUE(1, 11) = 1 THEN
                CONTINUE;
            END IF;

            -- seleziona un numero casuale tra 1 e 100
            quantita := DBMS_RANDOM.VALUE(1, 101);

           -- Generate a random number between 65 and 70 (ASCII values for 'A' to 'F')
           settoreMagazzino := CHR(DBMS_RANDOM.VALUE(65, 71));

            INSERT INTO MerceStoccata (IdProdotto, Quantita, IdMagazzino, SettoreMagazzino) VALUES (idProdotto, quantita, idMagazzino, settoreMagazzino);
        END LOOP;
    END LOOP;

END;
