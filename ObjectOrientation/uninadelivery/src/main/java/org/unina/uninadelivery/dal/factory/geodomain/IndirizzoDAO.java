package org.unina.uninadelivery.dal.factory.geodomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.geodomain.IndirizzoDTO;

import java.util.Optional;

public interface IndirizzoDAO {
    Optional<IndirizzoDTO> select(long id) throws PersistenceException;
}
