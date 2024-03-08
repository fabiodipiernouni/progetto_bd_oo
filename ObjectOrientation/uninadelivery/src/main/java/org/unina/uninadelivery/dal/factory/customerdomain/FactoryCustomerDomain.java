package org.unina.uninadelivery.dal.factory.customerdomain;

public class FactoryCustomerDomain {
    public static ClienteDAO buildClienteDAO() {
        return new OracleClienteDAO();
    }
    public static DettaglioOrdineDAO buildDettaglioOrdineDAO() {
        return new OracleDettaglioOrdineDAO();
    }
    public static OrdineClienteDAO buildOrdineClienteDAO() {
        return new OracleOrdineClienteDAO();
    }
}
