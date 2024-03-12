package org.unina.uninadelivery.presentation.orchestrator.shipmentdomain;

import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.orgdomain.MagazzinoDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;
import org.unina.uninadelivery.presentation.exception.SpedizioniException;

public interface IGenericOdlOrchestrator {
    void assegnaOdlPackagingClicked(MagazzinoDTO magazzinoDTO, SpedizioneDTO spedizioneDTO, OperatoreCorriereDTO utente) throws SpedizioniException;
    void iniziaLavorazioneOdlPackagingClicked(MagazzinoDTO magazzinoDTO, SpedizioneDTO spedizioneDTO) throws SpedizioniException;
    void concludiLavorazioneOdlPackagingClicked(MagazzinoDTO magazzinoDTO, SpedizioneDTO spedizioneDTO, String noteCorriere) throws SpedizioniException;
    void visualizzaSpedizioneClicked(SpedizioneDTO spedizione) throws SpedizioniException;
}
