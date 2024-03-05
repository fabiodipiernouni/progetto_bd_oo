package org.unina.uninadelivery33.dal.factory;


import org.unina.uninadelivery33.dal.exception.PersistenceException;
import org.unina.uninadelivery33.entity.customerdomain.ClienteDTO;
import org.unina.uninadelivery33.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery33.entity.orgdomain.FilialeDTO;

import java.time.LocalDate;
import java.util.List;

public interface OrdineClienteDAO {
    List<OrdineClienteDTO> selectOrdiniCliente(FilialeDTO filiale, String stato, ClienteDTO cliente, LocalDate dataInizio, LocalDate dataFine) throws PersistenceException;

    List<OrdineClienteDTO> selectOrdiniCliente(FilialeDTO filiale, String stato, ClienteDTO cliente, LocalDate dataInizio, LocalDate dataFine, Integer pageSize, Integer pageNumber, String sortCriteria) throws PersistenceException;

}
