package org.unina.uninadelivery.dal.factory.appdomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;

import java.util.List;
import java.util.Optional;

public interface UtenteDAO {

    Optional<UtenteDTO> select(long id) throws PersistenceException;

    List<String> selectFunzioni(Long idUtente) throws PersistenceException;

    Optional<UtenteDTO> select(String usernameIn, String passwordIn) throws PersistenceException;


    void closeConnection();

}
