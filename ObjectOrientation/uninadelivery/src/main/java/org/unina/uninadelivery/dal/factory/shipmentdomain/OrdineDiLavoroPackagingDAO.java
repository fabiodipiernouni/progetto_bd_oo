package org.unina.uninadelivery.dal.factory.shipmentdomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;
import org.unina.uninadelivery.entity.orgdomain.MagazzinoDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroPackagingDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;

import java.util.List;

public interface OrdineDiLavoroPackagingDAO {

    OrdineDiLavoroPackagingDTO select(MagazzinoDTO magazzinoDTO, SpedizioneDTO spedizioneDTO) throws PersistenceException;

    List<OrdineDiLavoroPackagingDTO> select() throws PersistenceException;

    List<OrdineDiLavoroPackagingDTO> select(FilialeDTO filiale) throws PersistenceException;

    List<OrdineDiLavoroPackagingDTO> select(FilialeDTO filiale, String stato) throws PersistenceException;

    List<OrdineDiLavoroPackagingDTO> select(OperatoreFilialeDTO operatoreFiliale) throws PersistenceException;

    List<OrdineDiLavoroPackagingDTO> select(GruppoCorriereDTO gruppoCorriere) throws PersistenceException;

    List<OrdineDiLavoroPackagingDTO> select(OperatoreCorriereDTO operatoreCorriere) throws PersistenceException;

    int getCount(FilialeDTO filiale, String stato) throws PersistenceException;

    int getCountLavoratiNonSpediti(FilialeDTO filiale) throws PersistenceException;

    void update(OrdineDiLavoroPackagingDTO ordineDiLavoroPackaging) throws PersistenceException;


}
