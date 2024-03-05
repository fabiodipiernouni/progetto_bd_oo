package org.unina.uninadelivery33.dal.factory;

import org.unina.uninadelivery33.dal.exception.PersistenceException;
import org.unina.uninadelivery33.entity.geodomain.IndirizzoDTO;

import java.util.Optional;

public interface IndirizzoDAO {
    Optional<IndirizzoDTO> selectById(long id) throws PersistenceException;
}
