package org.unina.uninadelivery.dal.factory.appdomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;

public class FactoryAppDomain {
    public static UtenteDAO buildUtenteDAO() throws PersistenceException {
        return new OracleUtenteDAO();
    }
}
