package org.unina.uninadelivery.dal.factory.shipmentdomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;

public class FactoryShipmentDomain {
    public static OrdineDiLavoroPackagingDAO buildOrdineDiLavoroPackagingDAO() throws PersistenceException {
        return new OracleOrdineDiLavoroPackagingDAO();
    }
    public static OrdineDiLavoroSpedizioneDAO buildOrdineDiLavoroSpedizioneDAO() throws PersistenceException {
        return new OracleOrdineDiLavoroSpedizioneDAO();
    }
    public static PaccoDAO buildPaccoDAO() throws PersistenceException {
        return new OraclePaccoDAO();
    }
    public static SpedizioneDAO buildSpedizioneDAO() throws PersistenceException {
        return new OracleSpedizioneDAO();
    }
}
