package org.unina.uninadelivery33.dal.factory;

import org.unina.uninadelivery33.dal.exception.PersistenceException;
import org.unina.uninadelivery33.entity.customerdomain.DettaglioOrdineDTO;

import java.util.List;

public interface DettaglioOrdineDAO {
    List<DettaglioOrdineDTO> getDettagliOrdineByIdOrdine(long idOrdine) throws PersistenceException;
}
