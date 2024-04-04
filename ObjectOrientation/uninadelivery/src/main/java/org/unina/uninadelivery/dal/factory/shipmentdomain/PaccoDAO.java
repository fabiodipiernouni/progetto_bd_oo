package org.unina.uninadelivery.dal.factory.shipmentdomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroPackagingDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroSpedizioneDTO;
import org.unina.uninadelivery.entity.shipmentdomain.PaccoDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;

import java.util.List;

public interface PaccoDAO {

    List<PaccoDTO> select(OrdineDiLavoroPackagingDTO ordineDiLavoroPackaging) throws PersistenceException;
    List<PaccoDTO> select(FilialeDTO filiale, SpedizioneDTO spedizione) throws PersistenceException;
    List<PaccoDTO> select(OrdineDiLavoroSpedizioneDTO ordineDiLavoroSpedizione) throws PersistenceException;

    void genera(OrdineDiLavoroPackagingDTO ordineDiLavoroPackagingDTO) throws PersistenceException;

    Integer getCount(FilialeDTO filiale, SpedizioneDTO spedizione, Boolean spediti) throws PersistenceException;
}
