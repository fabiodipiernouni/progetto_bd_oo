package org.unina.uninadelivery.dal.factory.shipmentdomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroSpedizioneDTO;

import java.util.List;

class OracleOrdineDiLavoroSpedizioneDAO implements OrdineDiLavoroSpedizioneDAO {

    public List<OrdineDiLavoroSpedizioneDTO> select(FilialeDTO filiale) throws PersistenceException {
        return null; //TODO
    }


    public List<OrdineDiLavoroSpedizioneDTO> select(OperatoreFilialeDTO operatoreFiliale) throws PersistenceException {
        return null; //TODO
    }


    public List<OrdineDiLavoroSpedizioneDTO> select(GruppoCorriereDTO gruppoCorriere) throws PersistenceException {
        return null; //TODO
    }


    public List<OrdineDiLavoroSpedizioneDTO> select(OperatoreCorriereDTO operatoreCorriere) throws PersistenceException {
        return null; //TODO
    }


    public int getCount(FilialeDTO filiale, String stato) throws PersistenceException {
        return 0;//TODO
    }


    public void update(OrdineDiLavoroSpedizioneDTO ordineDiLavoroSpedizione) throws PersistenceException {
//TODO
    }
}
