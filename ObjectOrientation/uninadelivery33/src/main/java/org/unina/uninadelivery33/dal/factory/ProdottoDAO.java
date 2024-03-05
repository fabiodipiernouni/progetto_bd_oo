package org.unina.uninadelivery33.dal.factory;

import org.unina.uninadelivery33.dal.exception.PersistenceException;
import org.unina.uninadelivery33.entity.orgdomain.ProdottoDTO;

import java.util.Optional;

public interface ProdottoDAO {
    Optional<ProdottoDTO> selectById(long id) throws PersistenceException;
}
