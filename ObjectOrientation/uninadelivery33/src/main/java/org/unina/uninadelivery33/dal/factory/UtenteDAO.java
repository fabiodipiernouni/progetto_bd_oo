package org.unina.uninadelivery33.dal.factory;

import org.unina.uninadelivery33.dal.exception.PersistenceException;
import org.unina.uninadelivery33.entity.appdomain.UtenteDTO;

import java.util.List;

public interface UtenteDAO {
    List<String> getFunzioniByUtente(long idUtente) throws PersistenceException;

    UtenteDTO selectByUsernamePassword(String usernameIn, String passwordIn) throws PersistenceException;

    void closeConnection();
}
