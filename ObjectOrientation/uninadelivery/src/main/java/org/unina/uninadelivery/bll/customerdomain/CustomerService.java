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
    public Task<Integer> getCountOrdiniDaLavorareTask(FilialeDTO filiale) throws ServiceException {
        return new Task<>() {
            @Override
            protected Integer call() throws ServiceException {
                int ret;
                try {
                    OrdineClienteDAO dao = FactoryCustomerDomain.buildOrdineClienteDAO();
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

    public List<ClienteDTO> getListaClienti() throws ServiceException {
        try {
            return FactoryCustomerDomain.buildClienteDAO().select();
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista dei clienti");
        }

    }

    public Task<List<OrdineClienteDTO>> getOrdiniCliente(LocalDate dataInizio, LocalDate dataFine, FilialeDTO filiale) throws ServiceException {
        Task<List<OrdineClienteDTO>> task = new Task<>() {
            @Override
            protected List<OrdineClienteDTO> call() throws ServiceException {
                try {
                    return FactoryCustomerDomain.buildOrdineClienteDAO().select(filiale, dataInizio, dataFine);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire la lista degli ordini del cliente");
                }
            }
        };

        return task;
    }

    public Task<List<OrdineClienteDTO>> getOrdiniCliente(FilialeDTO filiale, ClienteDTO cliente) throws ServiceException {
        Task<List<OrdineClienteDTO>> task = new Task<>() {
            @Override
            protected List<OrdineClienteDTO> call() throws ServiceException {
                try {
                    return FactoryCustomerDomain.buildOrdineClienteDAO().select(filiale, cliente);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire la lista degli ordini del cliente");
                }
            }
        };

        return task;
    }

    public Task<List<OrdineClienteDTO>> getOrdiniCliente(ClienteDTO cliente) throws ServiceException {
        Task<List<OrdineClienteDTO>> task = new Task<>() {
            @Override
            protected List<OrdineClienteDTO> call() throws ServiceException {
                try {
                    return FactoryCustomerDomain.buildOrdineClienteDAO().select(cliente);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire la lista degli ordini del cliente");
                }
            }
        };

        return task;
    }

    public Task<List<OrdineClienteDTO>> getOrdiniCliente(FilialeDTO filiale, ClienteDTO cliente, LocalDate dataInizio, LocalDate dataFine) throws ServiceException {
        Task<List<OrdineClienteDTO>> task = new Task<>() {
            @Override
            protected List<OrdineClienteDTO> call() throws ServiceException {
                try {
                    return FactoryCustomerDomain.buildOrdineClienteDAO().select(filiale, cliente, dataInizio, dataFine);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire la lista degli ordini del cliente");
                }
            }
        };

        return task;
    }

    public Task<List<OrdineClienteDTO>> getOrdiniCliente(ClienteDTO cliente, LocalDate dataInizio, LocalDate dataFine) throws ServiceException {
        Task<List<OrdineClienteDTO>> task = new Task<>() {
            @Override
            protected List<OrdineClienteDTO> call() throws ServiceException {
                try {
                    return FactoryCustomerDomain.buildOrdineClienteDAO().select(cliente, dataInizio, dataFine);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire la lista degli ordini del cliente");
                }
            }
        };

        return task;
    }

    public Task<List<OrdineClienteDTO>> getOrdiniCliente(LocalDate dataInizio, LocalDate dataFine) throws ServiceException {
        Task<List<OrdineClienteDTO>> task = new Task<>() {
            @Override
            protected List<OrdineClienteDTO> call() throws ServiceException {
                try {
                    return FactoryCustomerDomain.buildOrdineClienteDAO().select(dataInizio, dataFine);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire la lista degli ordini del cliente");
                }
            }
        };

        return task;
    }

    public SpedizioneDTO creaSpedizione(OrdineClienteDTO ordineCliente, OperatoreFilialeDTO operatoreFiliale) throws ServiceException {
        try {
            return FactoryShipmentDomain.buildSpedizioneDAO().insert(ordineCliente, operatoreFiliale);
        } catch (PersistenceException e) {
            //e.printStackTrace();
            throw new ServiceException("Errore nel creare la spedizione");
        }
    }

    public Task<Integer> getNumeroTotaleOrdini(LocalDate dataInizio, LocalDate dataFine) {
        return new Task<>() {
            @Override
            protected Integer call() throws ServiceException {
                try {
                    OrdineClienteDAO dao = FactoryCustomerDomain.buildOrdineClienteDAO();
                    return dao.getCount(dataInizio, dataFine);
                } catch (PersistenceException e) {
                    e.printStackTrace();
                    throw new ServiceException("Errore nel reperire il numero totale di ordini");
                }
            }
        };
    }

    public Task<Optional<OrdineClienteDTO>> getOrdineMaggiorNumeroProdotti(LocalDate dataInizio, LocalDate dataFine) {
        return new Task<Optional<OrdineClienteDTO>>() {
            @Override
            protected Optional<OrdineClienteDTO> call() throws ServiceException {
                try {
                    OrdineClienteDAO dao = FactoryCustomerDomain.buildOrdineClienteDAO();
                    return dao.getOrdineMaggiorNumeroDiProdotti(dataInizio, dataFine);
                } catch (PersistenceException e) {
                    e.printStackTrace();
                    throw new ServiceException("Errore nel reperire ordine con maggior numero di prodotti");
                }
            }
        };
    }

    public Task<Optional<OrdineClienteDTO>> getOrdineMinorNumeroProdotti(LocalDate dataInizio, LocalDate dataFine) {
        return new Task<Optional<OrdineClienteDTO>>() {
            @Override
            protected Optional<OrdineClienteDTO> call() throws ServiceException {
                try {
                    OrdineClienteDAO dao = FactoryCustomerDomain.buildOrdineClienteDAO();
                    return dao.getOrdineMinorNumeroDiProdotti(dataInizio, dataFine);
                } catch (PersistenceException e) {
                    e.printStackTrace();
                    throw new ServiceException("Errore nel reperire ordine con minor numero di prodotti");
                }
            }
        };
    }

    public Task<Float> getNumeroMedioOrdini(LocalDate dataInizio, LocalDate dataFine) {
        return new Task<>() {
            @Override
            protected Float call() throws ServiceException {
                try {
                    OrdineClienteDAO dao = FactoryCustomerDomain.buildOrdineClienteDAO();
                    return dao.getMediaOrdiniGiornaliera(dataInizio, dataFine);
                } catch (PersistenceException e) {
                    e.printStackTrace();
                    throw new ServiceException("Errore nel reperire il numero medio di ordini giornaliero");
                }
            }
        };
    }
}
