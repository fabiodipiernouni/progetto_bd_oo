package org.unina.uninadelivery33.dal.factory;

public class Factory {
    public static FilialeDAO buildFilialeDAO() {
        return new OracleFilialeDAO();
    }

    public static UtenteDAO buildUtenteDAO() {
        return new OracleUtenteDAO();
    }

    public static GruppoCorriereDAO buildGruppoCorriereDAO() {
        return new OracleGruppoCorriereDAO();
    }

    public static OrdineClienteDAO buildOrdineClienteDAO() {
        return new OracleOrdineClienteDAO();
    }

    public static IndirizzoDAO buildIndirizzoDAO() {
        return new OracleIndirizzoDAO();
    }

    public static ProdottoDAO buildProdottoDAO() {
        return new OracleProdottoDAO();
    }

    public static MagazzinoDAO buildMagazzinoDAO() {
        return new OracleMagazzinoDAO();
    }

    public static DettaglioOrdineDAO buildDettaglioOrdineDAO() {
        return new OracleDettaglioOrdineDAO();
    }

    public static ClienteDAO buildClienteDAO() {
        return new OracleClienteDAO();
    }

}
