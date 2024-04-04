package org.unina.uninadelivery.dal.factory.shipmentdomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroSpedizioneDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;

import java.util.List;

public interface OrdineDiLavoroSpedizioneDAO {

    List<OrdineDiLavoroSpedizioneDTO> select() throws PersistenceException;

    OrdineDiLavoroSpedizioneDTO select(SpedizioneDTO spedizione, FilialeDTO filiale) throws PersistenceException;
    List<OrdineDiLavoroSpedizioneDTO> select(FilialeDTO filiale) throws PersistenceException;

    List<OrdineDiLavoroSpedizioneDTO> select(FilialeDTO filiale, String stato) throws PersistenceException;

    List<OrdineDiLavoroSpedizioneDTO> select(OperatoreFilialeDTO operatoreFiliale) throws PersistenceException;

    List<OrdineDiLavoroSpedizioneDTO> select(GruppoCorriereDTO gruppoCorriere) throws PersistenceException;

    List<OrdineDiLavoroSpedizioneDTO> select(OperatoreCorriereDTO operatoreCorriere) throws PersistenceException;

    int getCount(FilialeDTO filiale, SpedizioneDTO spedizione, Boolean in, Boolean not, String stato) throws PersistenceException;

    void update(OrdineDiLavoroSpedizioneDTO ordineDiLavoroSpedizione) throws PersistenceException;
    int getCountNonConclusi(FilialeDTO filiale) throws PersistenceException;

    void genera(SpedizioneDTO spedizione) throws PersistenceException;

    List<OrdineDiLavoroSpedizioneDTO> select(SpedizioneDTO spedizione) throws PersistenceException;
}
