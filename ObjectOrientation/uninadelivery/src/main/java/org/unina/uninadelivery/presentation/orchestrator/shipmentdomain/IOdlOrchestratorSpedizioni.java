package org.unina.uninadelivery.presentation.orchestrator.shipmentdomain;

import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;
import org.unina.uninadelivery.presentation.exception.SpedizioniException;

public interface IOdlOrchestratorSpedizioni {
    void paginaSpedizioniPronta() throws SpedizioniException;
    void filtroTutteSpedizioniClicked() throws SpedizioniException;
    void filtroSpedizioniEmesseDaMeClicked() throws SpedizioniException;
    void visualizzaSpedizioneClicked(SpedizioneDTO spedizione) throws SpedizioniException;

}
