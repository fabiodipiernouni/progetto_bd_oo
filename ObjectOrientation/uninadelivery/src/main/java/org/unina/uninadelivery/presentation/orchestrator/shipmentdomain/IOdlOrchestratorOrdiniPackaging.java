package org.unina.uninadelivery.presentation.orchestrator.shipmentdomain;

import javafx.concurrent.Task;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroPackagingDTO;
import org.unina.uninadelivery.presentation.exception.SpedizioniException;

public interface IOdlOrchestratorOrdiniPackaging {
    void paginaOrdiniPackagingPronta() throws SpedizioniException;
    void filtroOrdiniPackagingEmessiDaMeClicked() throws SpedizioniException;
    void filtroOrdiniPackagingTuttiClicked() throws SpedizioniException;
    void filtroOrdiniPackagingGruppoCorriereClicked() throws SpedizioniException;
    void filtroOrdiniPackagingPresiInCaricoClicked() throws SpedizioniException;
    void filtroOrdiniPackagingDaPrendereInCaricoClicked() throws SpedizioniException;
    void prendiInCaricoOrdinePackagingClicked(OrdineDiLavoroPackagingDTO ordine) throws SpedizioniException;
    void visualizzaOrdinePackagingClicked(OrdineDiLavoroPackagingDTO ordine);
    Task<Void> generaOdlPackagingClicked(OrdineClienteDTO ordineCliente) throws SpedizioniException;
    void visualizzaOrdiniPackagingClicked(OrdineClienteDTO ordineCliente) throws SpedizioniException;

    void visualizzaPacchiClicked(OrdineClienteDTO ordineCliente) throws SpedizioniException;

    Task<Void> generaOdlTrasportoClicked(OrdineClienteDTO ordineCliente) throws SpedizioniException;
}
