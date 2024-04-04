package org.unina.uninadelivery.dal.factory.customerdomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;

public class FactoryCustomerDomain {
    public static ClienteDAO buildClienteDAO() throws PersistenceException {
        return new OracleClienteDAO();
    }
    public static DettaglioOrdineDAO buildDettaglioOrdineDAO() throws PersistenceException {
        return new OracleDettaglioOrdineDAO();
    }
    public static OrdineClienteDAO buildOrdineClienteDAO() throws PersistenceException {
        return new OracleOrdineClienteDAO();
    }
}
