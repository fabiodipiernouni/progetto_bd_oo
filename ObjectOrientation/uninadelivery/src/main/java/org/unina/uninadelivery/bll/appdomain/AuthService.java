package org.unina.uninadelivery.bll.appdomain;

import javafx.concurrent.Task;
import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.dal.exception.ConsistencyException;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.appdomain.FactoryAppDomain;
import org.unina.uninadelivery.dal.factory.appdomain.UtenteDAO;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;

import java.util.Optional;

public class AuthService {
    public Task<Optional<UtenteDTO>> login(String username, String password) {
        return new Task<>() {
            @Override
            protected Optional<UtenteDTO> call() throws ServiceException {
                try {
                    UtenteDAO dao = FactoryAppDomain.buildUtenteDAO();
                    return dao.select(username, password);
                }
                catch (ConsistencyException ce) {
                    ce.printStackTrace();
                    throw new ServiceException("I dati in nostro possesso non sono validi, contattare un amministratore");
                }
                catch (PersistenceException pe) {
                    throw new ServiceException("Il servizio di autenticazione non è riuscito ad effettuare il login");
                }
            }
        };
    }
}
