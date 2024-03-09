package org.unina.uninadelivery.bll.shipmentdomain;

import javafx.concurrent.Task;
import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.shipmentdomain.FactoryShipmentDomain;
import org.unina.uninadelivery.dal.factory.shipmentdomain.SpedizioneDAO;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;

public class ShipmentAsyncService {
    public Task<Integer> getCountSpedioniDaLavorare(OperatoreFilialeDTO operatoreFilialeDTO) {
        return new Task<>() {
            @Override
            protected Integer call() throws ServiceException {
                Integer ret;
                SpedizioneDAO dao = FactoryShipmentDomain.buildSpedizioneDAO();
                try {
                    ret = dao.getCount(operatoreFilialeDTO); //todo integrare con lo stato DaLavorare
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire il numero di ordini da lavorare");
                }
                return ret;
            }
        };
    }
}
