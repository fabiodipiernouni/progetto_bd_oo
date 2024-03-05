package org.unina.uninadelivery33.dal.factory;

import org.unina.uninadelivery33.dal.exception.ConsistencyException;
import org.unina.uninadelivery33.dal.exception.PersistenceException;
import org.unina.uninadelivery33.entity.customerdomain.ClienteDTO;
import org.unina.uninadelivery33.entity.customerdomain.DettaglioOrdineDTO;
import org.unina.uninadelivery33.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery33.entity.geodomain.IndirizzoDTO;
import org.unina.uninadelivery33.entity.orgdomain.FilialeDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

class OracleOrdineClienteDAO implements OrdineClienteDAO {
    private final Connection connection = DatabaseSingleton.getInstance().connect();


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
        Optional<ClienteDTO> cliente = Factory.buildClienteDAO().selectById(idCliente);
        if(cliente.isEmpty())
            throw new ConsistencyException("Cliente non trovato");


        long idIndirizzoFatturazione = resultSet.getLong("idIndirizzoFatturazione");
        Optional<IndirizzoDTO> indirizzoFatturazione = Factory.buildIndirizzoDAO().selectById(idIndirizzoFatturazione);
        if(indirizzoFatturazione.isEmpty())
            throw new ConsistencyException("Indirizzo fatturazione non trovato");


        Long idIndirizzoSpedizione = resultSet.getLong("idIndirizzoSpedizione"); //uso Long e non long perché voglio poter assegnare null
        if(resultSet.wasNull())
            idIndirizzoSpedizione = null;

        Optional<IndirizzoDTO> indirizzoSpedizione = Optional.empty();
        if(idIndirizzoSpedizione != null) {
            indirizzoSpedizione = Factory.buildIndirizzoDAO().selectById(idIndirizzoSpedizione);
            if(indirizzoSpedizione.isEmpty())
                throw new ConsistencyException("Indirizzo spedizione non trovato");
        }

        String numeroOrdine = resultSet.getString("numeroOrdine");

        List<DettaglioOrdineDTO> dettaglio = Factory.buildDettaglioOrdineDAO().getDettagliOrdineByIdOrdine(id);

        if(dettaglio.isEmpty())
            throw new ConsistencyException("Deve esserci almeno un dettaglio oridine");

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



    public List<OrdineClienteDTO> selectOrdiniCliente(FilialeDTO filiale, String stato, ClienteDTO cliente, LocalDate dataInizio, LocalDate dataFine) throws PersistenceException {

        /*TODO: non so quanto sia utile, mi basterebbe fare
            return getOrdiniCliente(filiale, stato, cliente, dataInizio, dataFine, null, null, null);
        */

        return new LinkedList<>();

    }

    public List<OrdineClienteDTO> selectOrdiniCliente(FilialeDTO filiale, String stato, ClienteDTO cliente, LocalDate dataInizio, LocalDate dataFine, Integer pageSize, Integer pageNumber, String sortCriteria) throws PersistenceException {
        //costruisco la query
        String query = "SELECT * FROM OrdineCliente WHERE 1=1";

        if(stato != null)
            query += "AND stato = ?";
        if(dataFine != null)
            query += "AND dataOrdine <= ?";
        if(dataInizio != null)
            query += "AND dataOrdine >= ?";

        if(sortCriteria != null)
            query += "ORDER BY " + sortCriteria;

        //TODO: implementare gli altri filtri


        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<OrdineClienteDTO> ordiniCliente = new LinkedList<>();

        try {
            preparedStatement = connection.prepareStatement(query);

            int index = 1;

            if(stato != null)
                preparedStatement.setString(index++, stato);
            if(dataFine != null)
                preparedStatement.setObject(index++, dataFine);
            if(dataInizio != null)
                preparedStatement.setObject(index++, dataInizio);


            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
                ordiniCliente.add(getOrdineClienteByResultSet(resultSet));

        }
        catch(SQLException sqe) {
            throw new PersistenceException(sqe.getMessage());
        }
        finally {
            //libero le risorse

            try {
                if(resultSet != null)
                    resultSet.close();
                if(preparedStatement != null)
                    preparedStatement.close();

            }
            catch(SQLException sqe) {
                //non faccio nulla
            }
        }

        return ordiniCliente;
    }



}
