package org.unina.uninadelivery.bll.shipmentdomain;

import javafx.concurrent.Task;
import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.customerdomain.FactoryCustomerDomain;
import org.unina.uninadelivery.dal.factory.customerdomain.OrdineClienteDAO;
import org.unina.uninadelivery.dal.factory.shipmentdomain.*;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;
import org.unina.uninadelivery.entity.orgdomain.MagazzinoDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroPackagingDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroSpedizioneDTO;
import org.unina.uninadelivery.entity.shipmentdomain.PaccoDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public Task<Integer> getNumeroSpedizioniCreate(LocalDate dataInizio, LocalDate dataFine) {
        return new Task<>() {
            @Override
            protected Integer call() throws ServiceException {
                SpedizioneDAO dao = FactoryShipmentDomain.buildSpedizioneDAO();

                try {
                    return dao.getCountSpedizioniCreate(dataInizio, dataFine);
                }
                catch (PersistenceException e) {
                    e.printStackTrace();
                    throw new ServiceException("Errore nel reperire il numero totale di spedizioni create");
                }
            }
        };
    }


    public Task<Integer> getNumeroSpedizioniCompletate(LocalDate dataInizio, LocalDate dataFine) {
        return new Task<>() {
            @Override
            protected Integer call() throws ServiceException {
                SpedizioneDAO dao = FactoryShipmentDomain.buildSpedizioneDAO();

                try {
                    return dao.getCountSpedizioniConcluse(dataInizio, dataFine);
                }
                catch (PersistenceException e) {
                    e.printStackTrace();
                    throw new ServiceException("Errore nel reperire il numero totale di spedizioni concluse");
                }
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
                    throw new ServiceException("Errore nel reperire il numero di ordini di packaging conclusi in attesa di trasporto");
                }
                return ret;
            }
        };
    }


    public List<OrdineDiLavoroPackagingDTO> getListaOrdiniDiLavoroPackaging() throws ServiceException {
        try {
            OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
            return dao.select();
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista di ordini di packaging");
        }

    }

    public List<OrdineDiLavoroPackagingDTO> getListaOrdiniDiLavoroPackaging(FilialeDTO filiale) throws ServiceException {
        try {
            OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
            return dao.select(filiale);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new ServiceException("Errore nel reperire la lista di ordini di packaging");
        }

    }

    public List<OrdineDiLavoroPackagingDTO> getListaOrdiniDiLavoroPackaging(GruppoCorriereDTO gruppoCorriere) throws ServiceException {
        try {
            OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
            return dao.select(gruppoCorriere);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista di ordini di packaging");
        }

    }

    public List<OrdineDiLavoroPackagingDTO> getListaOrdiniDiLavoroPackaging(OperatoreCorriereDTO operatoreCorriere) throws ServiceException {
        try {
            OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
            return dao.select(operatoreCorriere);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista di ordini di packaging");
        }

    }

    public List<OrdineDiLavoroPackagingDTO> getListaOrdiniDiLavoroPackaging(FilialeDTO filiale, String stato) throws ServiceException {
        try {
            OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
            return dao.select(filiale, stato);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista di ordini di packaging in stato " + stato);
        }

    }

    public void updateOrdinePackaging(OrdineDiLavoroPackagingDTO ordine) throws ServiceException {
        try {
            OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
            dao.update(ordine);
        } catch (PersistenceException e) {
            //e.printStackTrace();
            throw new ServiceException("Errore nell'aggiornare l'ordine di packaging");
        }
    }

    public List<OrdineDiLavoroPackagingDTO> getListaOrdiniDiLavoroPackaging(OperatoreFilialeDTO operatoreFilialeDTO) throws ServiceException {
        try {
            OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
            return dao.select(operatoreFilialeDTO);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista di ordini di packaging");
        }

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

    public List<OrdineDiLavoroSpedizioneDTO> getListaOrdiniDiLavoroSpedizione() throws ServiceException {
        try {
            OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
            return dao.select();
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista di ordini di trasporto");
        }

    }

    public List<OrdineDiLavoroSpedizioneDTO> getListaOrdiniDiLavoroSpedizione(FilialeDTO filiale) throws ServiceException {
        try {
            OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
            return dao.select(filiale);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista di ordini di trasporto");
        }

    }

    public List<OrdineDiLavoroSpedizioneDTO> getListaOrdiniDiLavoroSpedizione(GruppoCorriereDTO gruppoCorriere) throws ServiceException {
        try {
            OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
            return dao.select(gruppoCorriere);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista di ordini di trasporto");
        }

    }

    public List<OrdineDiLavoroSpedizioneDTO> getListaOrdiniDiLavoroSpedizione(OperatoreCorriereDTO operatoreCorriere) throws ServiceException {
        try {
            OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
            return dao.select(operatoreCorriere);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista di ordini di trasporto");
        }

    }

    public List<OrdineDiLavoroSpedizioneDTO> getListaOrdiniDiLavoroSpedizione(FilialeDTO filiale, String stato) throws ServiceException {
        try {
            OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
            return dao.select(filiale, stato);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista di ordini di trasporto in stato " + stato);
        }

    }

    public List<SpedizioneDTO> getListaSpedizioni(FilialeDTO filiale) throws ServiceException {
        try {
            SpedizioneDAO dao = FactoryShipmentDomain.buildSpedizioneDAO();
            return dao.select(filiale);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista di spedizioni");
        }
    }

    public List<SpedizioneDTO> getListaSpedizioni() throws ServiceException {
        try {
            SpedizioneDAO dao = FactoryShipmentDomain.buildSpedizioneDAO();
            FilialeDTO f = null;
            return dao.select(f);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista di spedizioni");
        }
    }

    public List<SpedizioneDTO> getListaSpedizioniPerOrganizzatore(OperatoreFilialeDTO operatoreFilialeDTO) throws ServiceException {
        try {
            SpedizioneDAO dao = FactoryShipmentDomain.buildSpedizioneDAO();
            return dao.select(operatoreFilialeDTO);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista di spedizioni");
        }
    }

    public int getCountOrdiniDiLavoroPackagingBySpedizione(SpedizioneDTO spedizione) throws ServiceException {
        return 0; //todo
    }

    public int getCountPacchiGeneratiBySpedizione(SpedizioneDTO spedizione) throws ServiceException {
        return 0; //todo
    }

    public int getCountPacchiDaSpedireBySpedizione(SpedizioneDTO spedizione) throws ServiceException {
        return 0; //todo
    }

    public int getCountOrdiniDiLavoroTrasportoBySpedizione(SpedizioneDTO spedizione) throws ServiceException {
        return 0; //todo
    }

    public int getCountOrdiniDiLavoroTrasportoDaCompletareBySpedizione(SpedizioneDTO spedizione) throws ServiceException {
        return 0; //todo
    }

    public int getCountOrdiniDiLavoroPackagingDaCompletareBySpedizione(SpedizioneDTO spedizione) throws ServiceException {
        return 0; //todo
    }

    public void updateOrdineSpedizione(OrdineDiLavoroSpedizioneDTO ordine) throws ServiceException {
        try {
            OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
            dao.update(ordine);
        } catch (PersistenceException e) {
            //e.printStackTrace();
            throw new ServiceException("Errore nell'aggiornare l'ordine di spedizione");
        }
    }

    public List<OrdineDiLavoroSpedizioneDTO> getListaOrdiniDiLavoroSpedizione(OperatoreFilialeDTO operatoreFilialeDTO) throws ServiceException {
        try {
            OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
            return dao.select(operatoreFilialeDTO);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista di ordini di trasporto");
        }
    }


    public OrdineDiLavoroPackagingDTO getOrdineDiLavoroPackaging(MagazzinoDTO magazzinoDTO, SpedizioneDTO spedizioneDTO) throws ServiceException {
        try {
            OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
            return dao.select(magazzinoDTO, spedizioneDTO);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire l'ordine di lavoro");
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

    public Task<Void> creaOdlPackaging(SpedizioneDTO spedizione) throws ServiceException {
        Task<Void> ret = new Task<>() {

            @Override
            protected Void call() throws ServiceException {
                try {
                    OrdineClienteDAO daoOrdineCliente = FactoryCustomerDomain.buildOrdineClienteDAO();
                    List<FilialeDTO> filiali = daoOrdineCliente.getFilialiCoinvolte(spedizione.getOrdineCliente());
                    OrdineDiLavoroPackagingDAO ordineDiLavoroPackagingDAO = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();

                    for(FilialeDTO filiale : filiali) {
                        //genero gli ordini di lavoro di packaging per la filiale
                        ordineDiLavoroPackagingDAO.genera(spedizione.getOrdineCliente(), filiale);
                    }


                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nella creazione dell'ordine di lavoro di packaging");
                }

                return null;
            }
        };

        return  ret;
    }

    public List<OrdineDiLavoroPackagingDTO> getListaOrdiniDiLavoroPackaging(OrdineClienteDTO ordineCliente) throws ServiceException {
        try {
            OrdineDiLavoroPackagingDAO ordineDiLavoroPackagingDAO = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
            SpedizioneDAO spedizioneDAO = FactoryShipmentDomain.buildSpedizioneDAO();
            SpedizioneDTO spedizione = spedizioneDAO.select(ordineCliente).orElse(null);
            if(spedizione == null) {
                return null;
            }
            return ordineDiLavoroPackagingDAO.select(spedizione);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista di ordini di packaging");
        }
    }

    public Task<Void> creaOdlTrasporto(SpedizioneDTO spedizione) {
        return new Task<>() {
            @Override
            protected Void call() throws ServiceException {
                try {
                    OrdineClienteDAO daoOrdineCliente = FactoryCustomerDomain.buildOrdineClienteDAO();
                    OrdineDiLavoroSpedizioneDAO ordineDiLavoroSpedizioneDAO = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();

                    ordineDiLavoroSpedizioneDAO.genera(spedizione);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nella creazione dell'ordine di lavoro di trasporto");
                }

                return null;
            }
        };
    }

    public List<PaccoDTO> getListaPacchi(OrdineClienteDTO ordineCliente) throws ServiceException {
        try {
            PaccoDAO paccoDAO = FactoryShipmentDomain.buildPaccoDAO();
            SpedizioneDAO spedizioneDAO = FactoryShipmentDomain.buildSpedizioneDAO();
            SpedizioneDTO spedizione = spedizioneDAO.select(ordineCliente).orElse(null);
            if(spedizione == null) {
                return null;
            }
            return paccoDAO.select(spedizione);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista di pacchi");
        }
    }
}

