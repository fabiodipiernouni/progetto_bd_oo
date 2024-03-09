package org.unina.uninadelivery.bll.appdomain;

import org.unina.uninadelivery.dal.factory.appdomain.FactoryAppDomain;


public class AppService {

    public void chiusuraApplicativa() {
        FactoryAppDomain.buildUtenteDAO().closeConnection();
    }
}
