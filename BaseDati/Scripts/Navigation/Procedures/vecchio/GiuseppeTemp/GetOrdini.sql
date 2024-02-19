CREATE OR REPLACE PROCEDURE GetOrdiniPerCliente (IdCliente IN Cliente.IdCliente%TYPE, ListaOrdiniCursor OUT SYS_REFCURSOR)
AS
DECLARE
       ClienteEsiste integer := 0;
BEGIN

    IF IdCliente < 1 THEN
        RAISE_APPLICATION_ERROR(-20000, 'IdCliente deve essere maggiore di zero');
    END IF;

    SELECT COUNT(*)
    INTO ClienteEsiste
    FROM Cliente WHERE IdCliente = IdCliente;

    IF ClienteEsiste = 0 THEN
        RAISE_APPLICATION_ERROR(-20000, 'Il cliente non esiste');
    END IF;

    OPEN ListaOrdiniCurosor FOR
    SELECT * FROM OrdineCliente WHERE OrdineCliente = IdCliente;

END GetOrdiniPerCliente;


CREATE OR REPLACE PROCEDURE GetOrdiniPerIntervalliDiTempo (DataInizio IN DATE, DataFine IN DATE, ListaOrdiniCursor OUT SYS_REFCURSOR)
AS
BEGIN

    IF DataInizio IS NULL OR DataFine IS NULL THEN
        RAISE_APPLICATION_ERROR(-20000, 'DataInizio e DataFine devono essere valorizzati');
    END IF;

    IF DataInizio > DataFine THEN
        RAISE_APPLICATION_ERROR(-20000, 'DataInizio deve essere minore di DataFine');
    END IF;

    OPEN ListaOrdiniCurosor FOR
    SELECT * FROM Ordini WHERE DataOrdine BETWEEN DataInizio AND DataFine;

END GetOrdiniPerIntervalliDiTempo;
