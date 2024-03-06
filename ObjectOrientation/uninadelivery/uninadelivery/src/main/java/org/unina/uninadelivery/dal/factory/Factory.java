package org.unina.uninadelivery.dal.factory;

import java.sql.Connection;

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
}
