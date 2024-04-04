package org.unina.uninadelivery.bll.orgdomain;

import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.orgdomain.FactoryOrgDomain;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.MagazzinoDTO;
import org.unina.uninadelivery.entity.orgdomain.MerceStoccataDTO;

import java.util.List;

public class MagazzinoService {
    public List<MagazzinoDTO> getListaMagazzini(FilialeDTO filiale) throws ServiceException {
        try {
            return FactoryOrgDomain.buildMagazzinoDAO().select(filiale);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista dei magazzini");
        }

    }

    public List<MerceStoccataDTO> getMerciStoccate(MagazzinoDTO magazzino) throws ServiceException {
        try {
            return FactoryOrgDomain.buildMagazzinoDAO().selectMerciStoccate(magazzino);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista delle merci stoccate");
        }
    }

}
