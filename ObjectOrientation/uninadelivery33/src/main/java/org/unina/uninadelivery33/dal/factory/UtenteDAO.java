package org.unina.uninadelivery33.dal.factory;

import org.unina.uninadelivery33.dal.exception.PersistenceException;
import org.unina.uninadelivery33.entity.appdomain.UtenteDTO;

import java.util.List;
import java.util.Optional;

public interface UtenteDAO {
    List<String> getFunzioniByUtente(Long idUtente) throws PersistenceException;

    Optional<UtenteDTO> selectByUsernamePassword(String usernameIn, String passwordIn) throws PersistenceException;

    void closeConnection();
}
