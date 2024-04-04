package org.unina.uninadelivery.dal.factory.orgdomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;

public class FactoryOrgDomain {
    public static FilialeDAO buildFilialeDAO() throws PersistenceException {
        return new OracleFilialeDAO();
    }
    public static GruppoCorriereDAO buildGruppoCorriereDAO() throws PersistenceException {
        return new OracleGruppoCorriereDAO();
    }
    public static MagazzinoDAO buildMagazzinoDAO() throws PersistenceException {
        return new OracleMagazzinoDAO();
    }
    public static MezzoDiTrasportoDAO buildMezzoDiTrasportoDAO() throws PersistenceException {
        return new OracleMezzoDiTrasportoDAO();
    }
    public static ProdottoDAO buildProdottoDAO() throws PersistenceException {
        return new OracleProdottoDAO();
    }
}
