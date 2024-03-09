package org.unina.uninadelivery.dal.factory.orgdomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.orgdomain.MezzoDiTrasportoDTO;

import java.util.Optional;

public interface MezzoDiTrasportoDAO {
    Optional<MezzoDiTrasportoDTO> select(long idMezzo) throws PersistenceException;


    //TODO: metodo per restituire i mezzi di trasporto disponibili per una spedizione
}
