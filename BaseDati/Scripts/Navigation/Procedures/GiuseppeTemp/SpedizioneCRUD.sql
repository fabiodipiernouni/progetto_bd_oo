CREATE OR REPLACE PROCEDURE GetSpedizione (
    IdSpedizione IN Spedizione.Id%TYPE,
    SpedizioneCursor OUT SYS_REFCURSOR
) AS
BEGIN
    OPEN SpedizioneCursor FOR
        SELECT * FROM Spedizione WHERE Id = IdSpedizione;
END;

CREATE OR REPLACE PROCEDURE GetSpedizioni (
    SpedizioniCursor OUT SYS_REFCURSOR
) AS
BEGIN
    OPEN SpedizioniCursor FOR
        SELECT * FROM Spedizione;
END;

CREATE OR REPLACE PROCEDURE DeleteSpedizione (
    IdSpedizione IN Spedizione.Id%TYPE
) AS
BEGIN
    DELETE FROM Spedizione WHERE Id = IdSpedizione;
END;

-- TODO: da implementare le altre operazioni CRUD quando avremo perfezionato lo schema della tabella Spedizione