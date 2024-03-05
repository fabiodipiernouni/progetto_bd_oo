package org.unina.uninadelivery33.dal.factory;

import org.unina.uninadelivery33.dal.exception.PersistenceException;
import org.unina.uninadelivery33.entity.orgdomain.MagazzinoDTO;

import java.util.List;
import java.util.Optional;

public interface MagazzinoDAO {
    Optional<MagazzinoDTO> selectById(long id) throws PersistenceException;
    List<MagazzinoDTO> selectMagazziniByIdDettaglioOrdine(long idDettaglioOrdine) throws PersistenceException;

}
