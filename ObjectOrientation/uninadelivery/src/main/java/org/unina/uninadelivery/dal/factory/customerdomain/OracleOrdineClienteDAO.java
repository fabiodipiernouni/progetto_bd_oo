package org.unina.uninadelivery.dal.factory.customerdomain;

import org.unina.uninadelivery.dal.exception.ConsistencyException;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.DatabaseSingleton;
import org.unina.uninadelivery.dal.factory.geodomain.FactoryGeoDomain;
import org.unina.uninadelivery.entity.customerdomain.ClienteDTO;
import org.unina.uninadelivery.entity.customerdomain.DettaglioOrdineDTO;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.entity.geodomain.IndirizzoDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

class OracleOrdineClienteDAO implements OrdineClienteDAO {
    private final Connection connection = DatabaseSingleton.getInstance().connect();


    private OrdineClienteDTO getByResultSet(ResultSet resultSet) throws SQLException, PersistenceException {

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
        Optional<ClienteDTO> cliente = FactoryCustomerDomain.buildClienteDAO().select(idCliente);
        if(cliente.isEmpty())
            throw new ConsistencyException("Cliente non trovato");

        long idIndirizzoFatturazione = resultSet.getLong("idIndirizzoFatturazione");
        Optional<IndirizzoDTO> indirizzoFatturazione = FactoryGeoDomain.buildIndirizzoDAO().select(idIndirizzoFatturazione);
        if(indirizzoFatturazione.isEmpty())
            throw new ConsistencyException("Indirizzo fatturazione non trovato");


        Long idIndirizzoSpedizione = resultSet.getLong("idIndirizzoSpedizione"); //uso Long e non long perché voglio poter assegnare null
        if(resultSet.wasNull())
            idIndirizzoSpedizione = null;

        Optional<IndirizzoDTO> indirizzoSpedizione = Optional.empty();
        if(idIndirizzoSpedizione != null) {
            indirizzoSpedizione = FactoryGeoDomain.buildIndirizzoDAO().select(idIndirizzoSpedizione);
            if(indirizzoSpedizione.isEmpty())
                throw new ConsistencyException("Indirizzo spedizione non trovato");
        }

        String numeroOrdine = resultSet.getString("numeroOrdine");

        List<DettaglioOrdineDTO> dettaglio = FactoryCustomerDomain.buildDettaglioOrdineDAO().select(id);

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

    public Optional<OrdineClienteDTO> select(long id) throws PersistenceException {
        Optional<OrdineClienteDTO> ordineCliente = Optional.empty();

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM OrdineCliente WHERE id = " + id);

            if(resultSet.next())
                ordineCliente = Optional.of(getByResultSet(resultSet));


            return ordineCliente;

        }
        catch(SQLException sqe) {
            throw new PersistenceException(sqe.getMessage());
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
                //non faccio niente
            }
        }
    }

    public int getCount(FilialeDTO filiale, String statoOrdine) throws PersistenceException {

        String query = """
            SELECT COUNT(*)
            FROM OrdineCliente
            LEFT JOIN StatoOrdineClienteFIliale
            ON OrdineCliente.id = StatoOrdineClienteFiliale.idOrdineCliente 
            WHERE 1=1 """;
        if(filiale != null)
            query += "AND idFiliale = " + filiale.getId();
        if(statoOrdine != null)
            query += " AND OrdineCliente.Stato = '" + statoOrdine + "'";


        Statement statement = null;
        ResultSet resultSet = null;


        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if(!resultSet.next())
                throw new PersistenceException("Errore nel reperire il numero di ordini");

            return resultSet.getInt(1);

        }
        catch(SQLException sqe) {
            throw new PersistenceException(sqe.getMessage());
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
                //non faccio niente
            }
        }
    }


    public int getCount(String statoOrdine) throws PersistenceException {
        return getCount(null, statoOrdine);
    }


    public int getCount(FilialeDTO filiale) throws PersistenceException {
        return getCount(filiale, null);
    }

    public int getCount(LocalDate dataInizio, LocalDate dataFine) throws PersistenceException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            preparedStatement = connection.prepareStatement("""
            SELECT COUNT(*)
            FROM OrdineCliente
            WHERE dataOrdine BETWEEN ? AND ?""");
            preparedStatement.setObject(1, dataInizio);
            preparedStatement.setObject(2, dataFine);

            resultSet = preparedStatement.executeQuery();


            if(!resultSet.next())
                throw new PersistenceException("Errore nel reperire il numero di ordini");

            return resultSet.getInt(1);

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
                //non faccio niente
            }
        }
    }

    //in Java non esistono i valori di default per i parametri, quindi uso l'overloading dei metodi
    public List<OrdineClienteDTO> select(FilialeDTO filiale) throws PersistenceException {
        return select(filiale, null, null, null, null);
    }

    public List<OrdineClienteDTO> select(FilialeDTO filiale, String stato) throws PersistenceException {
        return select(filiale, stato, null, null, null);
    }

    public List<OrdineClienteDTO> select(FilialeDTO filiale, LocalDate dataInizio, LocalDate dataFine) throws PersistenceException {
        return select(filiale, null, null, dataInizio, dataFine);
    }

    public List<OrdineClienteDTO> select(FilialeDTO filiale, String statoOrdine, LocalDate dataInizio, LocalDate dataFine) throws PersistenceException {
        return select(filiale, statoOrdine, null, dataInizio, dataFine);
    }

    public List<OrdineClienteDTO> select(ClienteDTO cliente) throws PersistenceException {
        return select(null, null, cliente, null, null);
    }

    public List<OrdineClienteDTO> select(FilialeDTO filiale, ClienteDTO cliente) throws PersistenceException {
        return select(filiale, null, cliente, null, null);
    }

    public List<OrdineClienteDTO> select(FilialeDTO filiale, String statoOrdine, ClienteDTO cliente) throws PersistenceException {
        return select(filiale, statoOrdine, cliente, null, null);
    }

    public List<OrdineClienteDTO> select(FilialeDTO filiale, ClienteDTO cliente, LocalDate dataInizio, LocalDate dataFine) throws PersistenceException {
        return select(filiale, null, cliente, dataInizio, dataFine);
    }


    public List<OrdineClienteDTO> select(FilialeDTO filiale, String statoOrdine, ClienteDTO cliente, LocalDate dataInizio, LocalDate dataFine) throws PersistenceException {
        String query = """
            SELECT *
            FROM OrdineCliente
            LEFT JOIN StatoOrdineClienteFIliale
            ON OrdineCliente.id = StatoOrdineClienteFiliale.idOrdineCliente
            WHERE 1=1 """;

        if(filiale != null)
            query += "AND idFiliale = ? ";
        if(statoOrdine != null)
            query += "AND OrdineCliente.Stato = ? ";
        if(cliente != null)
            query += "AND idCliente = ? ";
        if(dataFine != null)
            query += "AND dataOrdine <= ? ";
        if(dataInizio != null)
            query += "AND dataOrdine >= ? ";



        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<OrdineClienteDTO> ordiniCliente = new LinkedList<>();

        try {
            preparedStatement = connection.prepareStatement(query);

            int index = 1;

            if(filiale != null)
                preparedStatement.setLong(index++, filiale.getId());
            if(statoOrdine != null)
                preparedStatement.setString(index++, statoOrdine);
            if(cliente != null)
                preparedStatement.setLong(index++, cliente.getId());
            if(dataFine != null)
                preparedStatement.setObject(index++, dataFine);
            if(dataInizio != null)
                preparedStatement.setObject(index++, dataInizio);


            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
                ordiniCliente.add(getByResultSet(resultSet));

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


    private Optional<OrdineClienteDTO> getOrdineNumeroDiProdotti(LocalDate dataInizio, LocalDate dataFine, String sortCriteria) throws PersistenceException {
        if( !(sortCriteria.equals("ASC") || sortCriteria.equals("DESC") ) )
            throw new PersistenceException("ordinamento non valido");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Optional<OrdineClienteDTO> ordineCliente = Optional.empty();

        try {
            preparedStatement = connection.prepareStatement("""
                    SELECT * FROM ordinecliente WHERE id = (
                        SELECT ordinecliente.id
                        FROM ordinecliente
                        JOIN dettaglioordine
                        ON dettaglioordine.idordine = ordinecliente.id
                        WHERE dataOrdine >= ? AND dataOrdine <= ?
                        GROUP BY ordinecliente.id
                        ORDER BY SUM(dettaglioordine.quantita) """ + sortCriteria + """
                        FETCH FIRST ROW ONLY
                    )""");

            preparedStatement.setObject(1, dataInizio);
            preparedStatement.setObject(2, dataFine);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
                ordineCliente = Optional.of(getByResultSet(resultSet));

            return ordineCliente;
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

    }


    public Optional<OrdineClienteDTO> getOrdineMaggiorNumeroDiProdotti(LocalDate dataInizio, LocalDate dataFine) throws PersistenceException {
        return getOrdineNumeroDiProdotti(dataInizio, dataFine, "DESC");
    }


    public Optional<OrdineClienteDTO> getOrdineMinorNumeroDiProdotti(LocalDate dataInizio, LocalDate dataFine) throws PersistenceException {
        return getOrdineNumeroDiProdotti(dataInizio, dataFine, "ASC");
    }


    public float getMediaOrdiniGiornaliera(LocalDate dataInizio, LocalDate dataFine)  throws PersistenceException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("""
                WITH date_range(dt) AS (
                    SELECT ? FROM dual
                    UNION ALL
                    SELECT dt + INTERVAL '1' DAY FROM date_range
                    WHERE dt < ?
                )
                SELECT AVG(COUNT(ordineCliente.id))
                FROM date_range
                LEFT JOIN ordineCliente ON date_range.dt = ordineCliente.dataOrdine
                GROUP BY date_range.dt""");
            /*
            * WITH date_range(dt) AS:
            This is a Common Table Expression (CTE) that generates a series of dates between dataInizio and dataFine.
            The CTE is named date_range and it has one column dt.
            * (SELECT ? FROM dual UNION ALL SELECT dt + INTERVAL '1' DAY FROM date_range WHERE dt < ?):
            This is the recursive part of the CTE.
            It starts with the dataInizio date and then adds one day at a time until it reaches the dataFine date.
            * SELECT AVG(COUNT(ordineCliente.id)):
            This is the main query that calculates the average number of orders per day.
            It does this by first counting the number of orders (ordineCliente.id) for each date in the date_range and
            then taking the average of these counts.
            * FROM date_range LEFT JOIN ordineCliente ON date_range.dt = ordineCliente.dataOrdine:
            This joins the date_range CTE with the ordineCliente table on the dataOrdine field.
            The LEFT JOIN ensures that all dates in the date_range are included in the result, even if there are no
            orders for a particular date.
            * GROUP BY date_range.dt: This groups the result by date, which allows the COUNT function to count the
            number of orders for each date.
            */

            preparedStatement.setObject(1, dataInizio);
            preparedStatement.setObject(2, dataFine);


            resultSet = preparedStatement.executeQuery();

            if(!resultSet.next())
                throw new PersistenceException("non ho ottenuto il risultato");

            return resultSet.getFloat(1);

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

    }


    /*public List<OrdineClienteDTO> selectOrdiniCliente(FilialeDTO filiale, String statoOrdine, ClienteDTO cliente, LocalDate dataInizio, LocalDate dataFine, Integer pageSize, Integer pageNumber, String sortCriteria) throws PersistenceException {

        return new LinkedList<>();
    }*/



}
