package org.unina.uninadelivery.dal.factory.orgdomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.customerdomain.DettaglioOrdineDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.MagazzinoDTO;
import org.unina.uninadelivery.entity.orgdomain.MerceStoccataDTO;

import java.util.List;
import java.util.Optional;

public interface MagazzinoDAO {
    Optional<MagazzinoDTO> select(long id) throws PersistenceException;

    List<MagazzinoDTO> select(DettaglioOrdineDTO dettaglioOrdine) throws PersistenceException;

    List<MagazzinoDTO> select(FilialeDTO filiale) throws PersistenceException;

    List<MerceStoccataDTO> selectMerciStoccate(MagazzinoDTO magazzino) throws PersistenceException;

    MerceStoccataDTO selectMerciStoccateById(long id) throws PersistenceException;
}
