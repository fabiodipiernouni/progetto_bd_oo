package org.unina.uninadelivery.presentation.orchestrator.appdomain;

import javafx.beans.value.ChangeListener;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.presentation.exception.LoginErrorException;

public interface LoginOrchestration {
    Boolean loginClicked(String username, String password) throws LoginErrorException;
    Boolean loginClicked(String username, String password, ChangeListener<UtenteDTO> utenteDtoChanged) throws LoginErrorException;
}
