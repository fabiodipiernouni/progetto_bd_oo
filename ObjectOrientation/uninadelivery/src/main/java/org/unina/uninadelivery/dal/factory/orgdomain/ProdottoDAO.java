package org.unina.uninadelivery.dal.factory.orgdomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.orgdomain.ProdottoDTO;

import java.util.Optional;

public interface ProdottoDAO {
    Optional<ProdottoDTO> select(long id) throws PersistenceException;
}
