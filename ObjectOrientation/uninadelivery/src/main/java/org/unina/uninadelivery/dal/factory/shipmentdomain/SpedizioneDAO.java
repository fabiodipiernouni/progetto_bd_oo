package org.unina.uninadelivery.dal.factory.shipmentdomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SpedizioneDAO {
    Optional<SpedizioneDTO> select(long id) throws PersistenceException;

    Optional<SpedizioneDTO> select(OrdineClienteDTO ordineCliente) throws PersistenceException;


    int getCount(String Stato, FilialeDTO filiale) throws PersistenceException;

    int getCount(String Stato, OperatoreFilialeDTO operatoreFiliale) throws PersistenceException;

    int getCountSpedizioniCreate(LocalDate dataInizio, LocalDate dataFine) throws PersistenceException;

    int getCountSpedizioniConcluse(LocalDate dataInizio, LocalDate dataFine) throws PersistenceException;

    int getCount(OperatoreFilialeDTO operatoreFiliale) throws PersistenceException;

    int getCount(FilialeDTO filiale) throws PersistenceException;

    SpedizioneDTO insert(OrdineClienteDTO ordineCliente, OperatoreFilialeDTO operatore) throws PersistenceException;

    List<SpedizioneDTO> select(FilialeDTO filiale) throws PersistenceException;

    List<SpedizioneDTO> select(OperatoreFilialeDTO operatoreFiliale) throws PersistenceException;

    void update(Long numeroSpedizione, LocalDate dataInizioLavorazione, LocalDate dataFineLavorazione, String stato, String trackingNumber, String trackingStatus) throws PersistenceException;
}
