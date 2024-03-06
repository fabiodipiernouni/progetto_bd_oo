package org.unina.uninadelivery.dal.factory;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;

public interface GruppoCorriereDAO {
    GruppoCorriereDTO selectById(long id) throws PersistenceException;
}
