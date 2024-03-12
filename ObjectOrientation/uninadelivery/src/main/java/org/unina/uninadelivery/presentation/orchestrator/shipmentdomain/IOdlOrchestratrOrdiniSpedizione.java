package org.unina.uninadelivery.presentation.orchestrator.shipmentdomain;

import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroSpedizioneDTO;
import org.unina.uninadelivery.presentation.exception.SpedizioniException;

public interface IOdlOrchestratrOrdiniSpedizione {
    void filtroOrdiniSpedizioneTuttiClicked() throws SpedizioniException;
    void filtroOrdiniSpedizioneGruppoCorriereClicked() throws SpedizioniException;
    void filtroOrdiniSpedizionePresiInCaricoClicked() throws SpedizioniException;
    void filtroOrdiniSpedizioneDaPrendereInCaricoClicked() throws SpedizioniException;
    void filtroOrdiniSpedizioneEmessiDaMeClicked() throws SpedizioniException;
    void prendiInCaricoOrdineSpedizioneClicked(OrdineDiLavoroSpedizioneDTO ordine) throws SpedizioniException;
    void visualizzaOrdineSpedizioneClicked(OrdineDiLavoroSpedizioneDTO ordine);

    void paginaOrdiniSpedizionePronta() throws SpedizioniException;

}
