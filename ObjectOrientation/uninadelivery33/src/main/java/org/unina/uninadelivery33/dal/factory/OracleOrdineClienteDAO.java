package org.unina.uninadelivery33.dal.factory;

import org.unina.uninadelivery33.dal.exception.ConsistencyException;
import org.unina.uninadelivery33.dal.exception.PersistenceException;
import org.unina.uninadelivery33.entity.customerdomain.ClienteDTO;
import org.unina.uninadelivery33.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery33.entity.geodomain.IndirizzoDTO;
import org.unina.uninadelivery33.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery33.entity.orgdomain.ProdottoDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

class OracleOrdineClienteDAO implements OrdineClienteDAO {
    private final Connection connection = DatabaseSingleton.getInstance().connect();

    private Map<ProdottoDTO,Integer> getDettaglioOrdine(Long idOrdineCliente) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        Map<ProdottoDTO, Integer> dettaglio = new HashMap<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT idProdotto, quantita FROM dettaglioOrdine WHERE idOrdine = " + idOrdineCliente);


            Optional<ProdottoDTO> prodotto;
            while(resultSet.next()) {
                prodotto = new OracleProdottoDAO().selectById(resultSet.getLong("idProdotto"));

                if(prodotto.isEmpty())
                    throw new ConsistencyException("Prodotto non trovato");

                dettaglio.put(prodotto.get(), resultSet.getInt("quantita"));

            }

            return dettaglio;
        }
        catch(SQLException sqe) {
            throw new PersistenceException("Errore in OracleOrdineClienteDAO: " + sqe.getMessage());
        }
        finally {
            //libero le risorse
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
            }
            catch(SQLException sqe) {
                //non faccio nulla
            }

        }

    }

    private OrdineClienteDTO getOrdineClienteByResultSet(ResultSet resultSet) throws SQLException, PersistenceException {

        long id = resultSet.getLong("id");

        LocalDate dataOrdine = resultSet.getObject("dataOrdine", LocalDate.class);
        double importoTotale = resultSet.getDouble("importoTotale");

        LocalDate dataInizioLavorazione = resultSet.getObject("dataInizioLavorazione", LocalDate.class);
        if(resultSet.wasNull())
            dataInizioLavorazione = null;

        LocalDate dataFineLavorazione = resultSet.getObject("dataFineLavorazione", LocalDate.class);
        if(resultSet.wasNull())
            dataFineLavorazione = null;

        String stato = resultSet.getString("Stato");

        long idCliente = resultSet.getLong("idCliente");
        Optional<ClienteDTO> cliente = new OracleClienteDAO().selectById(idCliente);
        if(cliente.isEmpty())
            throw new ConsistencyException("Cliente non trovato");


        long idIndirizzoFatturazione = resultSet.getLong("idIndirizzoFatturazione");
        Optional<IndirizzoDTO> indirizzoFatturazione = new OracleIndirizzoDAO().selectById(idIndirizzoFatturazione);
        if(indirizzoFatturazione.isEmpty())
            throw new ConsistencyException("Indirizzo fatturazione non trovato");


        Long idIndirizzoSpedizione = resultSet.getLong("idIndirizzoSpedizione"); //uso Long e non long perché voglio poter assegnare null
        if(resultSet.wasNull())
            idIndirizzoSpedizione = null;

        Optional<IndirizzoDTO> indirizzoSpedizione = Optional.empty();
        if(idIndirizzoSpedizione != null) {
            indirizzoSpedizione = new OracleIndirizzoDAO().selectById(idIndirizzoSpedizione);
            if(indirizzoSpedizione.isEmpty())
                throw new ConsistencyException("Indirizzo spedizione non trovato");
        }

        String numeroOrdine = resultSet.getString("numeroOrdine");

        Map<ProdottoDTO, Integer> dettaglio = getDettaglioOrdine(id);

        if(dettaglio.isEmpty())
            throw new ConsistencyException("Dettaglio ordine non trovato");

        return new OrdineClienteDTO(
                id,
                dataOrdine,
                importoTotale,
                dataInizioLavorazione,
                dataFineLavorazione,
                stato,
                cliente.get(),
                indirizzoFatturazione.get(),
                idIndirizzoSpedizione == null ? null : indirizzoSpedizione.get(),
                numeroOrdine,
                dettaglio
        );


    }


    public List<OrdineClienteDTO> getOrdiniCliente(FilialeDTO filiale, String stato, ClienteDTO cliente, LocalDate dataInizio, LocalDate dataFine) throws PersistenceException {

        /*TODO: non so quanto sia utile, mi basterebbe fare
            return getOrdiniCliente(filiale, stato, cliente, dataInizio, dataFine, null, null, null);
        */

        return new LinkedList<>();

    }

    public List<OrdineClienteDTO> getOrdiniCliente(FilialeDTO filiale, String stato, ClienteDTO cliente, LocalDate dataInizio, LocalDate dataFine, int pageSize, int pageNumber, String sortCriteria) throws PersistenceException {
        //costruisco la query
        String query = "SELECT * FROM OrdineCliente WHERE 1=1";

        if(stato != null)
            query += "AND stato = '" + stato;
        if(dataFine != null)
            query += "AND dataOrdine <= " + dataFine;
        if(dataInizio != null)
            query += "AND dataOrdine >= " + dataInizio;

        //TODO: implementare gli altri filtri


        Statement statement = null;
        ResultSet resultSet = null;

        List<OrdineClienteDTO> ordiniCliente = new LinkedList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next())
                ordiniCliente.add(getOrdineClienteByResultSet(resultSet));

        }
        catch(SQLException sqe) {
            throw new PersistenceException("Errore in OracleOrdineClienteDAO: " + sqe.getMessage());
        }
        finally {
            //libero le risorse

            try {
                if(resultSet != null)
                    resultSet.close();
                if(statement != null)
                    statement.close();

            }
            catch(SQLException sqe) {
                throw new PersistenceException("Errore in OracleOrdineClienteDAO: " + sqe.getMessage());
            }
        }

        return ordiniCliente;
    }



}
