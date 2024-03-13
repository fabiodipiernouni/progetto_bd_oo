package org.unina.uninadelivery.dal.factory.orgdomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;

import java.util.Optional;

public interface GruppoCorriereDAO {
    Optional<GruppoCorriereDTO> select(long id) throws PersistenceException;
}
