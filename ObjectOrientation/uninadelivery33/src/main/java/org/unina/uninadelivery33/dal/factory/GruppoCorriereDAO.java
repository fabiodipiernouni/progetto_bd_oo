package org.unina.uninadelivery33.dal.factory;

import org.unina.uninadelivery33.dal.exception.PersistenceException;
import org.unina.uninadelivery33.entity.orgdomain.GruppoCorriereDTO;

import java.util.Optional;

public interface GruppoCorriereDAO {
    Optional<GruppoCorriereDTO> selectById(long id) throws PersistenceException;
}
