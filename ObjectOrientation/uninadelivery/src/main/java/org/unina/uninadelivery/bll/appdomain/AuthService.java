package org.unina.uninadelivery.bll.appdomain;

import org.unina.uninadelivery.dal.factory.Factory;
import org.unina.uninadelivery.dal.factory.UtenteDAO;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;

public class AuthService {
    public UtenteDTO login(String username, String password) throws PersistenceException {
        UtenteDAO dao = Factory.buildUtenteDAO();

        return dao.selectByUsernamePassword(username, password);
    }
}
