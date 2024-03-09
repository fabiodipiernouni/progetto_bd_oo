package org.unina.uninadelivery.dal.factory.orgdomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;
import org.unina.uninadelivery.entity.orgdomain.MezzoDiTrasportoDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MezzoDiTrasportoDAO {
    Optional<MezzoDiTrasportoDTO> select(long idMezzo) throws PersistenceException;


    List<MezzoDiTrasportoDTO> select(GruppoCorriereDTO gruppoCorriere, LocalDate dataInizio, LocalDate dataFine) throws PersistenceException;
}
