package org.unina.uninadelivery.presentation.orchestrator.appdomain;

import javafx.beans.value.ChangeListener;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;

import javax.security.auth.login.LoginException;

public interface LoginOrchestration {
    Boolean doLoginClicked(
            String username, String password,
            ChangeListener<UtenteDTO> utenteDtoChanged,
            EventHandler<WorkerStateEvent> onRunning,
            EventHandler<WorkerStateEvent> onSucceeded,
            EventHandler<WorkerStateEvent> onFailed) throws LoginException;
}
