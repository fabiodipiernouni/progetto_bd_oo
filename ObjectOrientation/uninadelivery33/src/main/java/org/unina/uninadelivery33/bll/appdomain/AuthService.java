package org.unina.uninadelivery33.bll.appdomain;

import org.unina.uninadelivery33.dal.OracleUtenteDAO;
import org.unina.uninadelivery33.dal.PersistenceException;
import org.unina.uninadelivery33.entity.appdomain.UtenteDTO;

public class AuthService {
    public UtenteDTO login(String username, String password) throws PersistenceException {
        UtenteDTO ret = null;
        OracleUtenteDAO dao = new OracleUtenteDAO();

        ret = dao.selectByUsernamePassword(username, password);

        return ret;
    }
}
