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

public class ShipmentService {

    /**** SPEDIZIONI ****/

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

    /**** ORDINI DI LAVORO PACKAGING ****/

    public Task<Integer> getCountOrdiniDiLavoroPackagingDaPrendereInCarico(FilialeDTO filiale) {
        return new Task<>() {
            @Override
            protected Integer call() throws ServiceException {
                int ret;
                OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
                try {
                    ret = dao.getCount(filiale, "DaAssegnare");
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire il numero di ordini di packaging da prendere in carico");
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
                    ret = dao.getCountLavoratiNonSpediti(filiale);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire il numero di ordini di trasporto conclusi in attesa di trasporto");
                }
                return ret;
            }
        };
    }

    /**** ORDINI DI LAVORO SPEDIZIONE ****/

    public Task<Integer> getCountOrdiniDiLavoroTrasportoDaPrendereInCarico(FilialeDTO filiale) {
        return new Task<>() {
            @Override
            protected Integer call() throws ServiceException {
                int ret;
                OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
                try {
                    ret = dao.getCount(filiale, "DaAssegnare");
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire il numero di ordini di trasporto da prendere in carico");
                }
                return ret;
            }
        };
    }

    public Task<Integer> getCountOrdiniDiLavoroSpedizioneDaTerminare(FilialeDTO filiale) {
        return new Task<>() {
            @Override
            protected Integer call() throws ServiceException {
                int ret;
                OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
                try {
                    ret = dao.getCountNonConclusi(filiale);

                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire il numero di ordini di trasporto aperti");
                }
                return ret;
            }
        };
    }
}
