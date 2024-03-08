package org.unina.uninadelivery.dal.factory.orgdomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;

import java.util.Optional;

public interface FilialeDAO {
    Optional<FilialeDTO> select(long id) throws PersistenceException;
}
