package org.unina.uninadelivery.dal.factory.geodomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;

public class FactoryGeoDomain {
    public static IndirizzoDAO buildIndirizzoDAO() throws PersistenceException {
        return new OracleIndirizzoDAO();
    }
}
