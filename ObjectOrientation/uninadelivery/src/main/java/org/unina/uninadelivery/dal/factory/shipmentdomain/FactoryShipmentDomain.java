package org.unina.uninadelivery.dal.factory.shipmentdomain;

public class FactoryShipmentDomain {
    public static OrdineDiLavoroPackagingDAO buildOrdineDiLavoroPackagingDAO() {
        return new OracleOrdineDiLavoroPackagingDAO();
    }
    public static OrdineDiLavoroSpedizioneDAO buildOrdineDiLavoroSpedizioneDAO() {
        return new OracleOrdineDiLavoroSpedizioneDAO();
    }
    public static PaccoDAO buildPaccoDAO() {
        return new OraclePaccoDAO();
    }
    public static SpedizioneDAO buildSpedizioneDAO() {
        return new OracleSpedizioneDAO();
    }
}
