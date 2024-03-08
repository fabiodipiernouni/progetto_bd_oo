package org.unina.uninadelivery.presentation.orchestrator;

import javafx.beans.property.Property;
import org.unina.uninadelivery.bll.appdomain.LogisticService;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.presentation.helper.Session;

import java.util.List;

public class OperatoreFilialeOrchestrator implements OperatoreFilialeOrchestration{
    private LogisticService logisticService = new LogisticService();

    public void visualizzaOrdiniClicked() {

        Session session = Session.getInstance();

        Property<UtenteDTO> user = session.getUserDto();

        OperatoreFilialeDTO operatoreFiliale = (OperatoreFilialeDTO) user.getValue();
        //faccio il downcast perché tanto sono sicuro sia un operatore filiale


        List<OrdineClienteDTO> listaOrdini;

        /*
        try {
            listaOrdini = logisticService.getOrdiniClienteByFilialeStato(operatoreFiliale.getFiliale(), "Confermato");
        } catch (PersistenceException e) {

            //TODO: gestire errore
        }
        */

        //TODO: show listaOrdini

    }

    public void creaSpedizioneClicked() {


    }


}
