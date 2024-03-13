package org.unina.uninadelivery.dal.factory.customerdomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.customerdomain.DettaglioOrdineDTO;

import java.util.List;

public interface DettaglioOrdineDAO {
    List<DettaglioOrdineDTO> select(long idOrdine) throws PersistenceException;
}
