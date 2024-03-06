package org.unina.uninadelivery.bll.appdomain;

import org.unina.uninadelivery.dal.factory.Factory;

public class AppService {

    public void chiusuraApplicativa() {
        Factory.buildUtenteDAO().closeConnection();
    }
}
