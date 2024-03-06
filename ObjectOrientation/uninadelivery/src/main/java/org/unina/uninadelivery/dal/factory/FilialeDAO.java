package org.unina.uninadelivery.dal.factory;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;

public interface FilialeDAO {
    FilialeDTO selectById(long id) throws PersistenceException;
}
