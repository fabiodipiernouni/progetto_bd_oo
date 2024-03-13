package org.unina.uninadelivery.dal.factory.geodomain;

public class FactoryGeoDomain {
    public static IndirizzoDAO buildIndirizzoDAO() {
        return new OracleIndirizzoDAO();
    }
}
