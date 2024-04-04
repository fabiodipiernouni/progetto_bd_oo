package org.unina.uninadelivery.bll.appdomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.DatabaseSingleton;


public class AppService {

    public Boolean IsConnectionAlive() {
        DatabaseSingleton istance = DatabaseSingleton.getInstance();
        try {
            istance.connect();
            return true;
        } catch (PersistenceException e) {
            return false;
        }
    }

    public void chiusuraApplicativa() {
        DatabaseSingleton istance = DatabaseSingleton.getInstance();
        istance.closeConnection();
    }
}
