package org.unina.uninadelivery33.presentation.orchestrator.appdomain;

import javafx.beans.value.ChangeListener;
import org.unina.uninadelivery33.entity.appdomain.UtenteDTO;

public interface LoginOrchestration {
    void loginClicked(String username, String password);
    void loginClicked(String username, String password, ChangeListener<UtenteDTO> utenteDtoChanged);
}
