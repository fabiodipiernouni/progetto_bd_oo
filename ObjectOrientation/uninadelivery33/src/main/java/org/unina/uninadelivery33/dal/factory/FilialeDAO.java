package org.unina.uninadelivery33.dal.factory;

import org.unina.uninadelivery33.dal.exception.PersistenceException;
import org.unina.uninadelivery33.entity.orgdomain.FilialeDTO;

public interface FilialeDAO {
    FilialeDTO selectById(long id) throws PersistenceException;
}
