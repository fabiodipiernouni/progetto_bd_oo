package org.unina.uninadelivery.dal.factory.appdomain;

public class FactoryAppDomain {
    public static UtenteDAO buildUtenteDAO() {
        return new OracleUtenteDAO();
    }
}
