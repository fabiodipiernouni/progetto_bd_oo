package org.unina.uninadelivery.dal.factory;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;

import java.util.List;

public interface UtenteDAO {
    List<String> getFunzioniByUtente(long idUtente) throws PersistenceException;

    UtenteDTO selectByUsernamePassword(String usernameIn, String passwordIn) throws PersistenceException;

    void closeConnection();
}
