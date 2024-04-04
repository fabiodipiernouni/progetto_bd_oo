package org.unina.uninadelivery.dal.factory.customerdomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.customerdomain.DettaglioOrdineDTO;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;

import java.util.List;

public interface DettaglioOrdineDAO {
    List<DettaglioOrdineDTO> select(OrdineClienteDTO ordineCliente) throws PersistenceException;
    List<DettaglioOrdineDTO> select(long idOrdineCliente) throws PersistenceException;
}
