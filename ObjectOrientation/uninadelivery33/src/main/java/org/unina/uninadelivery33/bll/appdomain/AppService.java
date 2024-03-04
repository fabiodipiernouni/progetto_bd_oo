package org.unina.uninadelivery33.bll.appdomain;

import org.unina.uninadelivery33.dal.factory.Factory;

public class AppService {

    public void chiusuraApplicativa() {
        Factory.buildUtenteDAO().closeConnection();
    }
}
