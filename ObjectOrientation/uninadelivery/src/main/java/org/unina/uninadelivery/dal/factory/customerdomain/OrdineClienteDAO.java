package org.unina.uninadelivery.dal.factory.customerdomain;


import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.customerdomain.ClienteDTO;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrdineClienteDAO {
    Optional<OrdineClienteDTO> select(long id) throws PersistenceException;

    int getCount(FilialeDTO filiale, String statoOrdine) throws PersistenceException;

    int getCount(String statoOrdine) throws PersistenceException;

    int getCount(FilialeDTO filiale) throws PersistenceException;

    int getCount(LocalDate dataInizio, LocalDate dataFine) throws PersistenceException;

    List<OrdineClienteDTO> select(FilialeDTO filiale) throws PersistenceException;

    List<OrdineClienteDTO> select(FilialeDTO filiale, String stato) throws PersistenceException;

    List<OrdineClienteDTO> select(FilialeDTO filiale, LocalDate dataInizio, LocalDate dataFine) throws PersistenceException;

    List<OrdineClienteDTO> select(FilialeDTO filiale, String statoOrdine, LocalDate dataInizio, LocalDate dataFine) throws PersistenceException;

    List<OrdineClienteDTO> select(ClienteDTO cliente) throws PersistenceException;

    List<OrdineClienteDTO> select(FilialeDTO filiale, ClienteDTO cliente) throws PersistenceException;

    List<OrdineClienteDTO> select(FilialeDTO filiale, String statoOrdine, ClienteDTO cliente) throws PersistenceException;

    List<OrdineClienteDTO> select(FilialeDTO filiale, ClienteDTO cliente, LocalDate dataInizio, LocalDate dataFine) throws PersistenceException ;

    List<OrdineClienteDTO> select(FilialeDTO filiale, String statoOrdine, ClienteDTO cliente, LocalDate dataInizio, LocalDate dataFine) throws PersistenceException;

    Optional<OrdineClienteDTO> getOrdineMaggiorNumeroDiProdotti(LocalDate dataInizio, LocalDate dataFine) throws PersistenceException;

    Optional<OrdineClienteDTO> getOrdineMinorNumeroDiProdotti(LocalDate dataInizio, LocalDate dataFine) throws PersistenceException;

    float getMediaOrdiniGiornaliera(LocalDate dataInizio, LocalDate dataFine)  throws PersistenceException;

    //List<OrdineClienteDTO> selectOrdiniCliente(FilialeDTO filiale, String statoOrdine, ClienteDTO cliente, LocalDate dataInizio, LocalDate dataFine, Integer pageSize, Integer pageNumber, String sortCriteria) throws PersistenceException;

}
