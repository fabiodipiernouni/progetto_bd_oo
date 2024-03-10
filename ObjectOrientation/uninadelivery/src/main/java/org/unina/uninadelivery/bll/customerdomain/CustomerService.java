package org.unina.uninadelivery.bll.customerdomain;

import javafx.concurrent.Task;
import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.customerdomain.FactoryCustomerDomain;
import org.unina.uninadelivery.dal.factory.customerdomain.OrdineClienteDAO;
import org.unina.uninadelivery.dal.factory.shipmentdomain.FactoryShipmentDomain;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.customerdomain.ClienteDTO;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CustomerService {
    public Task<Integer> getCountOrdiniDaLavorareTask(FilialeDTO filiale) {
        return new Task<>() {
            @Override
            protected Integer call() throws ServiceException {
                int ret;
                OrdineClienteDAO dao = FactoryCustomerDomain.buildOrdineClienteDAO();
                try {
                    if (filiale != null)
                        ret = dao.getCount(filiale, "Completato");
                    else
                        ret = dao.getCount("Completato");
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire il numero di ordini da lavorare");
                }
                return ret;
            }
        };
    }

    /*
    public Optional<OrdineClienteDTO> getOrdineCliente(long id) throws ServiceException {
        OrdineClienteDAO dao = FactoryCustomerDomain.buildOrdineClienteDAO();
        try {
            return dao.select(id);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire l'ordine");
        }
    }*/

    public List<ClienteDTO> getListaClienti(FilialeDTO filiale) throws ServiceException {
            try {
                return FactoryCustomerDomain.buildClienteDAO().select(filiale);
            } catch (PersistenceException e) {
                throw new ServiceException("Errore nel reperire la lista dei clienti");
            }

    }

    public List<OrdineClienteDTO> getOrdiniCliente(FilialeDTO filiale, ClienteDTO cliente) throws ServiceException{
        try {
            return FactoryCustomerDomain.buildOrdineClienteDAO().select(filiale, cliente);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista degli ordini del cliente");
        }
    }

    public List<OrdineClienteDTO> getOrdiniCliente(FilialeDTO filiale, ClienteDTO cliente, LocalDate dataInizio, LocalDate dataFine) throws ServiceException{
        try {
            return FactoryCustomerDomain.buildOrdineClienteDAO().select(filiale, cliente, dataInizio, dataFine);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista degli ordini del cliente");
        }
    }

    public void creaSpedizione(OrdineClienteDTO ordineCliente, OperatoreFilialeDTO operatoreFiliale) throws ServiceException {
        try {
            FactoryShipmentDomain.buildSpedizioneDAO().insert(ordineCliente, operatoreFiliale);
        }
        catch (PersistenceException e) {
            //e.printStackTrace();
            throw new ServiceException("Errore nel creare la spedizione");
        }
    }

    public SpedizioneDTO getSpedizione(OrdineClienteDTO ordineCliente) throws ServiceException {

        try {
            Optional<SpedizioneDTO> spedizione;
            spedizione = FactoryShipmentDomain.buildSpedizioneDAO().select(ordineCliente);

            return spedizione.orElse(null);
        }
        catch (PersistenceException e) {
            e.printStackTrace();
            throw new ServiceException("Errore nel selezionare la spedizione");
        }
    }
}
