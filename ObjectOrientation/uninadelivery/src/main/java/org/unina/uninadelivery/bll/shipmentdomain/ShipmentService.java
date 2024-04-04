package org.unina.uninadelivery.bll.shipmentdomain;

import javafx.concurrent.Task;
import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.DatabaseSingleton;
import org.unina.uninadelivery.dal.factory.customerdomain.FactoryCustomerDomain;
import org.unina.uninadelivery.dal.factory.customerdomain.OrdineClienteDAO;
import org.unina.uninadelivery.dal.factory.orgdomain.FactoryOrgDomain;
import org.unina.uninadelivery.dal.factory.orgdomain.MezzoDiTrasportoDAO;
import org.unina.uninadelivery.dal.factory.shipmentdomain.*;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;
import org.unina.uninadelivery.entity.orgdomain.MagazzinoDTO;
import org.unina.uninadelivery.entity.orgdomain.MezzoDiTrasportoDTO;
import org.unina.uninadelivery.entity.shipmentdomain.*;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ShipmentService {

    /**** SPEDIZIONI ****/

    public Task<Integer> getCountSpedizioniDaLavorare(OperatoreFilialeDTO operatoreFilialeDTO) throws ServiceException {
        return new Task<>() {
            @Override
            protected Integer call() throws ServiceException {
                int ret;
                try {
                    SpedizioneDAO dao = FactoryShipmentDomain.buildSpedizioneDAO();
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
                try {
                    SpedizioneDAO dao = FactoryShipmentDomain.buildSpedizioneDAO();
                    return dao.getCountSpedizioniCreate(dataInizio, dataFine);
                } catch (PersistenceException e) {
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
                try {
                    SpedizioneDAO dao = FactoryShipmentDomain.buildSpedizioneDAO();
                    return dao.getCountSpedizioniConcluse(dataInizio, dataFine);
                } catch (PersistenceException e) {
                    e.printStackTrace();
                    throw new ServiceException("Errore nel reperire il numero totale di spedizioni concluse");
                }
            }
        };
    }

    /**** ORDINI DI LAVORO PACKAGING ****/

    public Task<Integer> getCountOrdiniDiLavoroPackagingDaPrendereInCarico(FilialeDTO filiale) throws ServiceException {
        try {
            return new Task<>() {
                @Override
                protected Integer call() throws ServiceException {
                    int ret;
                    try {
                        OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
                        ret = dao.getCount(filiale, null, false, false, "DaAssegnare");
                    } catch (PersistenceException e) {
                        throw new ServiceException("Errore nel reperire il numero di ordini di packaging da prendere in carico");
                    }
                    return ret;
                }
            };
        } catch (Exception e) {
            throw new ServiceException("Errore nel reperire il numero di ordini di packaging da prendere in carico");
        }
    }

    public Task<Integer> getCountOrdiniDiLavoroPackagingConclusiAttesaTrasporto(FilialeDTO filiale) throws ServiceException {
        try {
            return new Task<>() {
                @Override
                protected Integer call() throws ServiceException {
                    int ret;
                    try {
                        OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
                        ret = dao.getCountLavoratiNonSpediti(filiale);
                    } catch (PersistenceException e) {
                        throw new ServiceException("Errore nel reperire il numero di ordini di packaging conclusi in attesa di trasporto");
                    }
                    return ret;
                }
            };
        } catch (Exception e) {
            throw new ServiceException("Errore nel reperire il numero di ordini di packaging conclusi in attesa di trasporto");
        }
    }


    public Task<List<OrdineDiLavoroPackagingDTO>> getListaOrdiniDiLavoroPackaging() throws ServiceException {
        return new Task<>() {
            @Override
            protected List<OrdineDiLavoroPackagingDTO> call() throws ServiceException {
                try {
                    OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
                    return dao.select();
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire la lista di ordini di packaging");
                }
            }
        };
    }

    public Task<List<OrdineDiLavoroPackagingDTO>> getListaOrdiniDiLavoroPackaging(FilialeDTO filiale) {
        return new Task<>() {
            @Override
            protected List<OrdineDiLavoroPackagingDTO> call() throws ServiceException {
                try {
                    OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
                    return dao.select(filiale);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire la lista di ordini di packaging");
                }
            }
        };
    }

    public Task<List<OrdineDiLavoroPackagingDTO>> getListaOrdiniDiLavoroPackaging(GruppoCorriereDTO gruppoCorriere) {
        return new Task<>() {
            @Override
            protected List<OrdineDiLavoroPackagingDTO> call() throws ServiceException {
                try {
                    OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
                    return dao.select(gruppoCorriere);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire la lista di ordini di packaging");
                }
            }
        };
    }

    public Task<List<OrdineDiLavoroPackagingDTO>> getListaOrdiniDiLavoroPackaging(OperatoreCorriereDTO operatoreCorriere) {
        return new Task<>() {
            @Override
            protected List<OrdineDiLavoroPackagingDTO> call() throws ServiceException {
                try {
                    OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
                    return dao.select(operatoreCorriere);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire la lista di ordini di packaging");
                }
            }
        };
    }

    public Task<List<OrdineDiLavoroPackagingDTO>> getListaOrdiniDiLavoroPackaging(FilialeDTO filiale, String stato) {
        return new Task<>() {
            @Override
            protected List<OrdineDiLavoroPackagingDTO> call() throws ServiceException {
                try {
                    OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
                    return dao.select(filiale, stato);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire la lista di ordini di packaging in stato " + stato);
                }
            }
        };
    }

    public void getUpdateOrdinePackaging(OrdineDiLavoroPackagingDTO ordine) throws ServiceException {
        try {
            OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
            dao.update(ordine);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new ServiceException("Errore nell'aggiornare l'ordine di packaging");
        }
    }

    public Task<Void> getUpdateOrdinePackagingTask(OrdineDiLavoroPackagingDTO ordine) {
        return new Task<>() {
            @Override
            protected Void call() throws ServiceException {
                try {
                    OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
                    dao.update(ordine);
                } catch (PersistenceException e) {
                    e.printStackTrace();
                    throw new ServiceException("Errore nell'aggiornare l'ordine di packaging");
                }
                return null;
            }
        };
    }

    public Task<List<OrdineDiLavoroPackagingDTO>> getListaOrdiniDiLavoroPackaging(OperatoreFilialeDTO operatoreFilialeDTO) throws ServiceException {
        return new Task<>() {
            @Override
            protected List<OrdineDiLavoroPackagingDTO> call() throws ServiceException {
                try {
                    OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
                    return dao.select(operatoreFilialeDTO);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire la lista di ordini di packaging");
                }
            }
        };
    }

    /**** ORDINI DI LAVORO SPEDIZIONE ****/

    public Task<Integer> getCountOrdiniDiLavoroTrasportoDaPrendereInCarico(FilialeDTO filiale) throws ServiceException {
        try {
            return new Task<>() {
                @Override
                protected Integer call() throws ServiceException {
                    int ret;
                    try {
                        OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
                        ret = dao.getCount(filiale, null, false, false, "DaAssegnare");
                    } catch (PersistenceException e) {
                        throw new ServiceException("Errore nel reperire il numero di ordini di trasporto da prendere in carico");
                    }
                    return ret;
                }
            };
        } catch (Exception e) {
            throw new ServiceException("Errore nel reperire il numero di ordini di trasporto da prendere in carico");
        }
    }

    public Task<Integer> getCountOrdiniDiLavoroSpedizioneDaTerminare(FilialeDTO filiale) throws ServiceException {
        try {
            return new Task<>() {
                @Override
                protected Integer call() throws ServiceException {
                    int ret;
                    try {
                        OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
                        ret = dao.getCountNonConclusi(filiale);

                    } catch (PersistenceException e) {
                        throw new ServiceException("Errore nel reperire il numero di ordini di trasporto aperti");
                    }
                    return ret;
                }
            };
        } catch (Exception e) {
            throw new ServiceException("Errore nel reperire il numero di ordini di trasporto aperti");
        }
    }

    public Task<List<OrdineDiLavoroSpedizioneDTO>> getListaOrdiniDiLavoroSpedizione() {
        return new Task<>() {
            @Override
            protected List<OrdineDiLavoroSpedizioneDTO> call() throws ServiceException {
                try {
                    OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
                    return dao.select();
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire la lista di ordini di trasporto");
                }
            }
        };
    }

    public Task<List<OrdineDiLavoroSpedizioneDTO>> getListaOrdiniDiLavoroSpedizione(FilialeDTO filiale) {
        return new Task<>() {
            @Override
            protected List<OrdineDiLavoroSpedizioneDTO> call() throws ServiceException {
                try {
                    OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
                    return dao.select(filiale);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire la lista di ordini di trasporto");
                }
            }
        };
    }

    public Task<List<OrdineDiLavoroSpedizioneDTO>> getListaOrdiniDiLavoroSpedizione(GruppoCorriereDTO gruppoCorriere) {
        return new Task<>() {
            @Override
            protected List<OrdineDiLavoroSpedizioneDTO> call() throws ServiceException {
                try {
                    OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
                    return dao.select(gruppoCorriere);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire la lista di ordini di trasporto");
                }
            }
        };
    }

    public Task<List<OrdineDiLavoroSpedizioneDTO>> getListaOrdiniDiLavoroSpedizione(OperatoreCorriereDTO operatoreCorriere) {
        return new Task<>() {
            @Override
            protected List<OrdineDiLavoroSpedizioneDTO> call() throws ServiceException {
                try {
                    OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
                    return dao.select(operatoreCorriere);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire la lista di ordini di trasporto");
                }
            }
        };
    }

    public Task<List<OrdineDiLavoroSpedizioneDTO>> getListaOrdiniDiLavoroSpedizione(FilialeDTO filiale, String stato) {
        return new Task<>() {
            @Override
            protected List<OrdineDiLavoroSpedizioneDTO> call() throws ServiceException {
                try {
                    OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
                    return dao.select(filiale, stato);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire la lista di ordini di trasporto in stato " + stato);
                }
            }
        };
    }

    public Task<List<SpedizioneDTO>> getListaSpedizioni(FilialeDTO filiale) throws ServiceException {
        return new Task<>() {
            @Override
            protected List<SpedizioneDTO> call() throws ServiceException {
                try {
                    SpedizioneDAO dao = FactoryShipmentDomain.buildSpedizioneDAO();
                    return dao.select(filiale);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire la lista di spedizioni");
                }
            }
        };
    }

    public Task<List<SpedizioneDTO>> getListaSpedizioni() throws ServiceException {
        return getListaSpedizioni((FilialeDTO) null);
    }

    public Task<List<SpedizioneDTO>> getListaSpedizioni(OperatoreFilialeDTO operatoreFilialeDTO) throws ServiceException {
        return new Task<>() {
            @Override
            protected List<SpedizioneDTO> call() throws ServiceException {
                try {
                    SpedizioneDAO dao = FactoryShipmentDomain.buildSpedizioneDAO();
                    return dao.select(operatoreFilialeDTO);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire la lista di spedizioni");
                }
            }
        };
    }

    public Task<Integer> getCountOrdiniDiLavoroPackagingBySpedizione(FilialeDTO filiale, SpedizioneDTO spedizione) throws ServiceException {
        return new Task<>() {
            @Override
            protected Integer call() throws Exception {
                try {
                    OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
                    return dao.getCount(filiale, spedizione, false, false, null);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire il numero di ordini di lavoro di packaging");
                }
            }
        };
    }

    public Task<Integer> getCountPacchiGeneratiBySpedizione(FilialeDTO filiale, SpedizioneDTO spedizione) throws ServiceException {
        return new Task<>() {
            @Override
            protected Integer call() throws Exception {
                try {
                    PaccoDAO dao = FactoryShipmentDomain.buildPaccoDAO();
                    return dao.getCount(filiale, spedizione, null);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire il numero di pacchi generati");
                }
            }
        };
    }

    public Task<Integer> getCountPacchiDaSpedireBySpedizione(FilialeDTO filiale, SpedizioneDTO spedizione) throws ServiceException {
        return new Task<>() {
            @Override
            protected Integer call() throws Exception {
                try {
                    PaccoDAO dao = FactoryShipmentDomain.buildPaccoDAO();
                    return dao.getCount(filiale, spedizione, false);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire il numero di pacchi da spedire");
                }
            }
        };
    }

    public Task<Integer> getCountOrdiniDiLavoroTrasportoBySpedizione(FilialeDTO filiale, SpedizioneDTO spedizione) throws ServiceException {
        return new Task<>() {
            @Override
            protected Integer call() throws Exception {
                try {
                    OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
                    return dao.getCount(filiale, spedizione, false, false, null);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire il numero di ordini di lavoro di trasporto");
                }
            }
        };
    }

    public Task<Integer> getCountOrdiniDiLavoroTrasportoDaCompletareBySpedizione(FilialeDTO filiale, SpedizioneDTO spedizione) throws ServiceException {
        return new Task<>() {
            @Override
            protected Integer call() throws Exception {
                try {
                    OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
                    return dao.getCount(filiale, spedizione, true, true, "DaAssegnare,Lavorato");
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire il numero di ordini di lavoro di trasporto da completare");
                }
            }
        };
    }

    public Task<Integer> getCountOrdiniDiLavoroPackagingDaCompletareBySpedizione(FilialeDTO filiale, SpedizioneDTO spedizione) throws ServiceException {
        return new Task<>() {
            @Override
            protected Integer call() throws Exception {
                try {
                    OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
                    return dao.getCount(filiale, spedizione, true, true, "DaAssegnare,Lavorato");
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire il numero di ordini di lavoro di packaging da completare");
                }
            }
        };
    }

    public void updateOrdineSpedizione(OrdineDiLavoroSpedizioneDTO ordine) throws ServiceException {
        try {
            OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
            dao.update(ordine);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nell'aggiornare l'ordine di spedizione");
        }
    }

    public Task<Void> getUpdateOrdineSpedizioneTask(OrdineDiLavoroSpedizioneDTO ordine) {
        return new Task<>() {
            @Override
            protected Void call() throws ServiceException {
                updateOrdineSpedizione(ordine);
                return null;
            }
        };
    }

    public Task<List<OrdineDiLavoroSpedizioneDTO>> getListaOrdiniDiLavoroSpedizione(OperatoreFilialeDTO operatoreFilialeDTO) {
        return new Task<>() {
            @Override
            protected List<OrdineDiLavoroSpedizioneDTO> call() throws ServiceException {
                try {
                    OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
                    return dao.select(operatoreFilialeDTO);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire la lista di ordini di trasporto");
                }
            }
        };
    }


    public OrdineDiLavoroPackagingDTO getOrdineDiLavoroPackaging(MagazzinoDTO magazzinoDTO, SpedizioneDTO spedizioneDTO) throws ServiceException {
        try {
            OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
            return dao.select(magazzinoDTO, spedizioneDTO);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire l'ordine di lavoro");
        }
    }

    public OrdineDiLavoroSpedizioneDTO getOrdineDiLavoroTrasporto(SpedizioneDTO spedizione, FilialeDTO filiale) throws ServiceException {
        try {
            OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
            return dao.select(spedizione, filiale);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire l'ordine di lavoro");
        }

    }

    public SpedizioneDTO getSpedizione(OrdineClienteDTO ordineCliente) throws ServiceException {
        try {
            Optional<SpedizioneDTO> spedizione;
            spedizione = FactoryShipmentDomain.buildSpedizioneDAO().select(ordineCliente);

            return spedizione.orElse(null);
        } catch (PersistenceException e) {
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

                    for (FilialeDTO filiale : filiali) {
                        //genero gli ordini di lavoro di packaging per la filiale
                        ordineDiLavoroPackagingDAO.genera(spedizione.getOrdineCliente(), filiale);
                    }


                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nella creazione dell'ordine di lavoro di packaging");
                }

                return null;
            }
        };

        return ret;
    }

    public Task<List<OrdineDiLavoroPackagingDTO>> getListaOrdiniDiLavoroPackaging(UtenteDTO utente, OrdineClienteDTO ordineCliente) throws ServiceException {
        return new Task<>() {
            @Override
            protected List<OrdineDiLavoroPackagingDTO> call() throws ServiceException {
                try {
                    FilialeDTO filiale = utente.getProfilo().equals("Manager") ? null : (utente.getProfilo().equals("Operatore") ? ((OperatoreFilialeDTO) utente).getFiliale() : ((OperatoreCorriereDTO) utente).getGruppoCorriere().getFiliale());

                    OrdineDiLavoroPackagingDAO ordineDiLavoroPackagingDAO = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
                    SpedizioneDAO spedizioneDAO = FactoryShipmentDomain.buildSpedizioneDAO();
                    SpedizioneDTO spedizione = spedizioneDAO.select(ordineCliente).orElse(null);
                    if (spedizione == null) {
                        return null;
                    }
                    return ordineDiLavoroPackagingDAO.select(filiale, spedizione);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire la lista di ordini di packaging");
                }
            }
        };
    }

    public Task<Void> creaOdlTrasporto(SpedizioneDTO spedizione) {
        return new Task<>() {
            @Override
            protected Void call() throws ServiceException {
                try {
                    OrdineDiLavoroSpedizioneDAO ordineDiLavoroSpedizioneDAO = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
                    ordineDiLavoroSpedizioneDAO.genera(spedizione);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nella creazione dell'ordine di lavoro di trasporto");
                }

                return null;
            }
        };
    }

    public List<PaccoDTO> getListaPacchi(FilialeDTO filiale, SpedizioneDTO spedizione) throws ServiceException {
        try {
            PaccoDAO paccoDAO = FactoryShipmentDomain.buildPaccoDAO();

            return paccoDAO.select(filiale, spedizione);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista di pacchi");
        }
    }

    public Task<List<OrdineDiLavoroPackagingDetailDTO>> getOrdineDiLavoroPackagingDetailsTask(FilialeDTO filiale, OrdineDiLavoroPackagingDTO ordine) {
        return new Task<>() {
            @Override
            protected List<OrdineDiLavoroPackagingDetailDTO> call() throws ServiceException {
                try {
                    OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
                    return dao.selectDetails(filiale, ordine);
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire i dettagli dell'ordine di lavoro di packaging");
                }
            }
        };
    }

    public Task<Integer> getCountOrdiniDiLavoroPackagingDaTerminare(FilialeDTO filiale) throws ServiceException {
        try {
            return new Task<>() {
                @Override
                protected Integer call() throws ServiceException {
                    int ret;
                    try {
                        OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
                        ret = dao.getCount(filiale, null, true, true, "DaAssegnare,Lavorato");
                    } catch (PersistenceException e) {
                        throw new ServiceException("Errore nel reperire il numero di ordini di packaging da terminare");
                    }
                    return ret;
                }
            };
        } catch (Exception e) {
            throw new ServiceException("Errore nel reperire il numero di ordini di packaging da terminare");
        }
    }

    public void generaPacchi(OrdineDiLavoroPackagingDTO ordineDiLavoroPackagingDTO) throws ServiceException {
        try {
            if (!ordineDiLavoroPackagingDTO.getStato().equals("Lavorato")) {
                throw new ServiceException("Errore: non è possibile generare i pacchi per un ordine di lavoro non ancora lavorato");
            }

            PaccoDAO paccoDAO = FactoryShipmentDomain.buildPaccoDAO();
            List<PaccoDTO> pacchi = paccoDAO.select(ordineDiLavoroPackagingDTO);
            if (pacchi.size() > 0) {
                throw new ServiceException("Errore: i pacchi per questo ordine di lavoro sono già stati generati");
            }

            paccoDAO.genera(ordineDiLavoroPackagingDTO);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nella generazione dei pacchi");
        }

    }

    public SpedizioneDTO refreshSpedizione(SpedizioneDTO spedizione) throws ServiceException {
        try {
            SpedizioneDAO spedizioneDao = FactoryShipmentDomain.buildSpedizioneDAO();
            return spedizioneDao.select(spedizione.getId()).get();
        } catch (PersistenceException e) {
            throw new ServiceException("Errore di accesso ai dati della spedizione.");
        }
    }

    public Task<Void> setOdlPackagingCompletato(OrdineDiLavoroPackagingDTO ordineDiLavoroPackagingDTO, String noteCorriere) {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                DatabaseSingleton db = DatabaseSingleton.getInstance();
                Connection conn = db.getTheConnection();
                try {
                    conn.setAutoCommit(false);

                    OrdineDiLavoroPackagingDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
                    ordineDiLavoroPackagingDTO.setDataFineLavorazione(LocalDate.now());
                    ordineDiLavoroPackagingDTO.setNoteAggiuntiveOperatore(noteCorriere);
                    dao.update(ordineDiLavoroPackagingDTO);

                    //Verifico se per la stessa spedizione ci sono altri odl aperti
                    OrdineDiLavoroPackagingDAO odlPackagingDao = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO();
                    int numOrdiniAperti = odlPackagingDao.getCount(
                            null,
                            ordineDiLavoroPackagingDTO.getSpedizione(),
                            false,
                            true,
                            "Lavorato"
                    );

                    if (numOrdiniAperti == 0) {
                        SpedizioneDAO spedizioneDao = FactoryShipmentDomain.buildSpedizioneDAO();
                        spedizioneDao.update(ordineDiLavoroPackagingDTO.getSpedizione().getId(), null, null, null, null, "InPartenza");
                    }

                    conn.commit();
                } catch (PersistenceException e) {
                    conn.rollback();
                    throw new ServiceException("Errore nel completare l'ordine di lavoro di packaging");
                } finally {
                    conn.setAutoCommit(true);
                }
                return null;
            }
        };
    }

    public List<MezzoDiTrasportoDTO> getMezziDiTrasportoLiberi(GruppoCorriereDTO gruppoCorriere, LocalDate now) throws ServiceException {
        try {
            MezzoDiTrasportoDAO dao = FactoryOrgDomain.buildMezzoDiTrasportoDAO();
            return dao.select(gruppoCorriere, now, now);
        } catch (PersistenceException e) {
            throw new ServiceException("Errore nel reperire la lista di mezzi di trasporto");
        }
    }

    public void checkSeTuttiConclusiEAggiornaSpedizione(SpedizioneDTO spedizione) throws ServiceException {
        try {
            OrdineDiLavoroSpedizioneDAO dao = FactoryShipmentDomain.buildOrdineDiLavoroSpedizioneDAO();
            List<OrdineDiLavoroSpedizioneDTO> ordiniTrasporto = dao.select(spedizione);

            if(ordiniTrasporto == null) {
                throw new ServiceException("Errore: non ci sono ordini di lavoro di trasporto per questa spedizione");
            }

            long cnt = ordiniTrasporto.stream().filter(ordine -> !ordine.getStato().equals("Lavorato")).count();

            if(cnt == 0) {
                SpedizioneDAO spedizioneDAO = FactoryShipmentDomain.buildSpedizioneDAO();
                spedizioneDAO.update(spedizione.getId(), null, LocalDate.now(), "LavorataSpedizione", null, "Consegnata");
            }
        }
        catch (PersistenceException e) {
            throw new ServiceException("Errore nel check e aggiornamento spedizione.");
        }
    }
}

