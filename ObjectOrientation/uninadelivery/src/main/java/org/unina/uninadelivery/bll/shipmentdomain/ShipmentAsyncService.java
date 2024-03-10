package org.unina.uninadelivery.bll.shipmentdomain;

import javafx.concurrent.Task;
import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.shipmentdomain.FactoryShipmentDomain;
import org.unina.uninadelivery.dal.factory.shipmentdomain.OrdineDiLavoroPackagingDAO;
import org.unina.uninadelivery.dal.factory.shipmentdomain.OrdineDiLavoroSpedizioneDAO;
import org.unina.uninadelivery.dal.factory.shipmentdomain.SpedizioneDAO;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;

public class ShipmentAsyncService {

    public Task<Integer> getCountSpedizioniDaLavorare(OperatoreFilialeDTO operatoreFilialeDTO) {
        return new Task<>() {
            @Override
            protected Integer call() throws ServiceException {
                int ret;
                SpedizioneDAO dao = FactoryShipmentDomain.buildSpedizioneDAO();
                try {
                    ret = dao.getCount("DaLavorare", operatoreFilialeDTO);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire il numero di spedizioni da lavorare");
                }
                return ret;
            }
        };
    }

    public Task<Integer> getCountOrdiniDiLavoroPackagingConclusiAttesaTrasporto(FilialeDTO filiale) {
        return new Task<>() {
            @Override
            protected Integer call() throws ServiceException {
                int ret;
                OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
                try {
                    ret = dao.getCountConclusiInAttesaTrasporto(filiale);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire il numero di ordini di trasporto conclusi in attesa di trasporto");
                }
                return ret;
            }
        };
    }

    public Task<Integer> getCountOrdiniDiLavoroSpedizioneAperti(FilialeDTO filiale) {
        return new Task<>() {
            @Override
            protected Integer call() throws ServiceException {
                int ret;
                OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
                try {
                    ret = dao.getCountAperti(filiale);

                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire il numero di ordini di trasporto aperti");
                }
                return ret;
            }
        };
    }
}
