CREATE OR REPLACE PROCEDURE ReportStatistico (
       Mese IN INTEGER,
       NumeroMedioDiOrdini OUT INTEGER,
       OrdineConMaggiorNumeroDiProdotti OUT INTEGER,
       OrdineConMinorNumeroDiProdotti OUT INTEGER
)
AS
BEGIN

    IF Mese < 1 OR Mese > 12 THEN
        RAISE_APPLICATION_ERROR(-20000, 'Mese deve essere compreso tra 1 e 12');
    END IF;

    --TODO: completare questa procedura
    -- dovremmo supporre un anno di inizio e un anno di fine per poter calcolare la media mensile?


END ReportStatistico;