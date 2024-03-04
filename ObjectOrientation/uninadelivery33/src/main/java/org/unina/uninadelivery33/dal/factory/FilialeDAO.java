package org.unina.uninadelivery33.dal.factory;

import org.unina.uninadelivery33.dal.exception.PersistenceException;
import org.unina.uninadelivery33.entity.orgdomain.FilialeDTO;

import java.util.Optional;

public interface FilialeDAO {
    Optional<FilialeDTO> selectById(long id) throws PersistenceException;
}
