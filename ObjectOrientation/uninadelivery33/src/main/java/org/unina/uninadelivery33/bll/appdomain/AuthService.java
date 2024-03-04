package org.unina.uninadelivery33.bll.appdomain;

import org.unina.uninadelivery33.bll.exception.ServiceException;
import org.unina.uninadelivery33.dal.exception.ConsistencyException;
import org.unina.uninadelivery33.dal.factory.Factory;
import org.unina.uninadelivery33.dal.factory.UtenteDAO;
import org.unina.uninadelivery33.dal.exception.PersistenceException;
import org.unina.uninadelivery33.entity.appdomain.UtenteDTO;

import java.util.Optional;

public class AuthService {
    public Optional<UtenteDTO> login(String username, String password) throws ServiceException {
        UtenteDAO dao = Factory.buildUtenteDAO();
        try {
            return dao.selectByUsernamePassword(username, password);
        }
        catch (ConsistencyException ce) {
            throw new ServiceException("I dati in nostro possesso non sono validi, contattare un amministratore");
        }
        catch (PersistenceException pe) {
            throw new ServiceException("Il servizio di autenticazione non è riuscito ad effettuare il login");
        }

    }
}
