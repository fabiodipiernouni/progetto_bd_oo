package org.unina.uninadelivery.dal.factory.customerdomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.customerdomain.ClienteDTO;

import java.util.Optional;

public interface ClienteDAO {
    Optional<ClienteDTO> select(long idCliente) throws PersistenceException;
}
