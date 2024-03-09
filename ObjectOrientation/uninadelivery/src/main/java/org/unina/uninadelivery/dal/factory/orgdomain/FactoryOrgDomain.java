package org.unina.uninadelivery.dal.factory.orgdomain;

public class FactoryOrgDomain {
    public static FilialeDAO buildFilialeDAO() {
        return new OracleFilialeDAO();
    }
    public static GruppoCorriereDAO buildGruppoCorriereDAO() {
        return new OracleGruppoCorriereDAO();
    }
    public static MagazzinoDAO buildMagazzinoDAO() {
        return new OracleMagazzinoDAO();
    }
    public static MezzoDiTrasportoDAO buildMezzoDiTrasportoDAO() {
        return new OracleMezzoDiTrasportoDAO();
    }
    public static ProdottoDAO buildProdottoDAO() {
        return new OracleProdottoDAO();
    }
}
