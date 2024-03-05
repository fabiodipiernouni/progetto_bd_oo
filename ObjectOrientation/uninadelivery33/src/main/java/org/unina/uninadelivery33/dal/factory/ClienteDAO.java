package org.unina.uninadelivery33.dal.factory;

import org.unina.uninadelivery33.dal.exception.PersistenceException;
import org.unina.uninadelivery33.entity.customerdomain.ClienteDTO;

import java.util.Optional;

public interface ClienteDAO {
    Optional<ClienteDTO> selectById(long idCliente) throws PersistenceException;
}
