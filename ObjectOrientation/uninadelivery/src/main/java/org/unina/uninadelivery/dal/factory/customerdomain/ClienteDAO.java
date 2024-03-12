package org.unina.uninadelivery.dal.factory.customerdomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.customerdomain.ClienteDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;

import java.util.List;
import java.util.Optional;

public interface ClienteDAO {
    Optional<ClienteDTO> select(long idCliente) throws PersistenceException;

    List<ClienteDTO> select() throws PersistenceException;

    List<ClienteDTO> select(FilialeDTO filiale) throws PersistenceException;
}
