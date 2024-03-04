package org.unina.uninadelivery33.bll.appdomain;

import org.unina.uninadelivery33.dal.factory.Factory;
import org.unina.uninadelivery33.dal.factory.UtenteDAO;
import org.unina.uninadelivery33.dal.exception.PersistenceException;
import org.unina.uninadelivery33.entity.appdomain.UtenteDTO;

public class AuthService {
    public UtenteDTO login(String username, String password) throws PersistenceException {
        UtenteDAO dao = Factory.buildUtenteDAO();

        return dao.selectByUsernamePassword(username, password);
    }
}
