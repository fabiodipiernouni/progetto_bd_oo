package org.unina.uninadelivery.dal.factory.shipmentdomain;


import org.unina.uninadelivery.dal.exception.ConsistencyException;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.DatabaseSingleton;
import org.unina.uninadelivery.dal.factory.appdomain.FactoryAppDomain;
import org.unina.uninadelivery.dal.factory.customerdomain.FactoryCustomerDomain;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;

import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
class OracleSpedizioneDAO implements SpedizioneDAO {
    private final Connection connection = DatabaseSingleton.getInstance().connect();

    private SpedizioneDTO getByResultSet(ResultSet resultSet) throws SQLException, PersistenceException {
        Optional<UtenteDTO> organizzatore = FactoryAppDomain.buildUtenteDAO().select(resultSet.getLong("idUtenteOrganizzatore"));
        if(organizzatore.isEmpty())
            throw new ConsistencyException("La spedizione non ha un organizzatore associato");

        return getByResultSet(resultSet, (OperatoreFilialeDTO) organizzatore.get());
    }

    private SpedizioneDTO getByResultSet(ResultSet resultSet, OperatoreFilialeDTO operatoreFiliale) throws SQLException, PersistenceException {
        long id = resultSet.getLong("id");

        LocalDate dataCreazione = resultSet.getObject("dataCreazione", LocalDate.class);
        LocalDate dataInizioLavorazione = resultSet.getObject("dataInizioLavorazione", LocalDate.class);
        if(resultSet.wasNull())
            dataInizioLavorazione = null;

        LocalDate dataFineLavorazione = resultSet.getObject("dataFineLavorazione", LocalDate.class);
        if(resultSet.wasNull())
            dataFineLavorazione = null;

        Optional<OrdineClienteDTO> ordineCliente = FactoryCustomerDomain.buildOrdineClienteDAO().select(resultSet.getLong("idOrdineCliente"));
        if(ordineCliente.isEmpty())
            throw new ConsistencyException("La spedizione non ha un ordine associato");

        String stato = resultSet.getString("stato");
        String trackingNumber = resultSet.getString("trackingNumber");
        String trackingStatus = resultSet.getString("trackingStatus");

        return new SpedizioneDTO(
                id,
                dataCreazione,
                dataInizioLavorazione,
                dataFineLavorazione,
                ordineCliente.get(),
                operatoreFiliale,
                stato,
                trackingNumber,
                trackingStatus
        );
    }


    public Optional<SpedizioneDTO> select(long id) throws PersistenceException {
        Optional<SpedizioneDTO>  spedizione = Optional.empty();


        Statement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * From Spedizione WHERE id = " + id);

            if(resultSet.next())
                spedizione = Optional.of(getByResultSet(resultSet));


            return spedizione;

        }
        catch(SQLException sqe) {
            //sqe.printStackTrace();
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

    @Override
    public Optional<SpedizioneDTO> select(OrdineClienteDTO ordineCliente) throws PersistenceException {
        Optional<SpedizioneDTO>  spedizione = Optional.empty();


        Statement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * From Spedizione WHERE idOrdineCliente = " + ordineCliente.getId());

            if(resultSet.next())
                spedizione = Optional.of(getByResultSet(resultSet));


            return spedizione;

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

    @Override
    public int getCount(FilialeDTO filiale) throws PersistenceException {
        return getCount(null, filiale);
    }

    @Override
    public int getCount(OperatoreFilialeDTO operatoreFiliale) throws PersistenceException {
        return getCount(null, operatoreFiliale);
    }

    public int getCount(String stato, OperatoreFilialeDTO operatoreFiliale) throws PersistenceException {

        String query = """
            SELECT COUNT(*)
            FROM Spedizione
            WHERE idUtenteOrganizzatore = """ + operatoreFiliale.getId();

        if(stato != null)
            query += " AND stato = '" + stato + "'";


        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if(!resultSet.next())
                throw new PersistenceException("Errore nel reperire il numero di spedizioni");

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

    private int getCountSpedizioni(LocalDate dataInizio, LocalDate dataFine, String colonna) throws PersistenceException {
        if(!colonna.equals("dataCreazione") && !colonna.equals("dataFineLavorazione"))
            throw new IllegalArgumentException("La colonna deve essere dataCreazione o dataFineLavorazione");

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM Spedizione WHERE " + colonna + " BETWEEN ? AND ?");
            preparedStatement.setObject(1, dataInizio);
            preparedStatement.setObject(2, dataFine);

            resultSet = preparedStatement.executeQuery();

            if(!resultSet.next())
                throw new PersistenceException("Errore nel reperire il numero di spedizioni");

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

    public int getCountSpedizioniCreate(LocalDate dataInizio, LocalDate dataFine) throws PersistenceException {
        return getCountSpedizioni( dataInizio, dataFine, "dataCreazione");
    }


    public int getCountSpedizioniConcluse(LocalDate dataInizio, LocalDate dataFine) throws PersistenceException {
        return getCountSpedizioni( dataInizio, dataFine, "dataFineLavorazione");
    }

    public int getCount(String stato, FilialeDTO filiale) throws PersistenceException {
        String query = """
            SELECT COUNT(*)
            FROM Spedizione
            JOIN StatoOrdineClienteFIliale
            ON Spedizione.idOrdineCliente = StatoOrdineClienteFiliale.idOrdineCliente
            WHERE idFiliale = """ + filiale.getId();
        if(stato != null)
            query += " AND stato = '" + stato + "'";


        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if(!resultSet.next())
                throw new PersistenceException("Errore nel reperire il numero di spedizioni");

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

    public SpedizioneDTO insert(OrdineClienteDTO ordineCliente, OperatoreFilialeDTO organizzatore) throws PersistenceException{
        long id;
        CallableStatement statement = null;

        try {
            statement = connection.prepareCall("{ call CreaSpedizione( ?, ?, ? ) }");
            statement.setLong(1, ordineCliente.getId());
            statement.setLong(2, organizzatore.getId());
            statement.registerOutParameter(3, Types.NUMERIC);

            statement.execute();

            id = statement.getLong(3);
        }
        catch(SQLException sqe) {
            throw new PersistenceException(sqe.getMessage());
        }
        finally {
            //libero le risorse

            try {
                if(statement != null)
                    statement.close();
            }
            catch(SQLException sqe) {
                //non faccio niente
            }
        }

        Optional<SpedizioneDTO> spedizione = select(id);

        if(spedizione.isEmpty())
            throw new ConsistencyException("Spedizione appena creata non trovata");

        return spedizione.get();

    }


    public List<SpedizioneDTO> select(FilialeDTO filiale) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        List<SpedizioneDTO> spedizioni = new LinkedList<SpedizioneDTO>();

        try {
            statement = connection.createStatement();
            String query = """
                SELECT
                    Spedizione.*
                FROM
                    Spedizione
                    JOIN StatoOrdineClienteFiliale ON StatoOrdineClienteFIliale.idOrdineCliente = Spedizione.idOrdineCliente""";

            if(filiale != null)
                query += " WHERE idFiliale = " + filiale.getId();

            resultSet = statement.executeQuery(query);

            while(resultSet.next())
                spedizioni.add(getByResultSet(resultSet));

            return spedizioni;
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


    public List<SpedizioneDTO> select(OperatoreFilialeDTO operatoreFiliale) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        List<SpedizioneDTO> spedizioni = new LinkedList<SpedizioneDTO>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Spedizione WHERE idUtenteOrganizzatore = " + operatoreFiliale.getId());

            while(resultSet.next())
                spedizioni.add(getByResultSet(resultSet, operatoreFiliale));

            return spedizioni;
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

}