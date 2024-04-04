package org.unina.uninadelivery.dal.factory.customerdomain;

import org.unina.uninadelivery.dal.exception.ConsistencyException;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.DatabaseSingleton;
import org.unina.uninadelivery.entity.customerdomain.ClienteDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

class OracleClienteDAO implements ClienteDAO {

    private final Connection connection;

    OracleClienteDAO() throws PersistenceException {
        connection = DatabaseSingleton.getInstance().connect();
    }

    private ClienteDTO getByResultSet(ResultSet resultSet) throws SQLException, PersistenceException {
        long idCliente = resultSet.getLong("id");

        String nome = resultSet.getString("nome");
        String cognome = resultSet.getString("cognome");

        String ragioneSociale = resultSet.getString("ragioneSociale");
        if (resultSet.wasNull())
            ragioneSociale = null;

        String email = resultSet.getString("email");

        String codiceFiscale = resultSet.getString("codiceFiscale");
        if (resultSet.wasNull())
            codiceFiscale = null;

        String partitaIVA = resultSet.getString("partitaIVA");
        if (resultSet.wasNull())
            partitaIVA = null;

        if(codiceFiscale != null && partitaIVA == null && ragioneSociale == null)
            return new ClienteDTO(
                    idCliente,
                    nome,
                    cognome,
                    email,
                    codiceFiscale
            );
        else
            if(codiceFiscale == null && partitaIVA != null && ragioneSociale != null)
                return new ClienteDTO(
                        idCliente,
                        nome,
                        cognome,
                        ragioneSociale,
                        email,
                        partitaIVA
                );

       else
           throw new ConsistencyException("Problema di consistenza: il cliente deve avere o codice fiscale o entrambe ragione sociale e partita IVA");

    }

    public Optional<ClienteDTO> select(long idCliente) throws PersistenceException {
        Optional<ClienteDTO> cliente = Optional.empty();


        Statement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Cliente WHERE id = " + idCliente);

            if(resultSet.next())
                cliente = Optional.of(getByResultSet(resultSet));


            return cliente;

        }
        catch(SQLException sqe) {
            sqe.printStackTrace();
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
    public List<ClienteDTO> select() throws PersistenceException {
        return select(null);
    }


    /**
     * Restituisce la lista dei clienti per i quali almeno un ordine sta venendo gestito da una certa filiale
     *   @param filiale la filiale per la quale si vogliono i clienti
     *   @return la lista dei clienti per i quali almeno un ordine sta venendo gestito da una certa filiale
     *   @throws PersistenceException se si verifica un errore nel reperire i dati
     */
    public List<ClienteDTO> select(FilialeDTO filiale) throws PersistenceException {
        String query = """
                SELECT DISTINCT Cliente.*
                FROM Cliente
                JOIN OrdineCliente
                ON Cliente.id = OrdineCliente.idCliente
                JOIN statoOrdineClienteFiliale
                ON OrdineCliente.id = statoOrdineClienteFiliale.idOrdineCliente""";

        if(filiale != null)
                query += " WHERE idFiliale = " + filiale.getId();


        Statement statement = null;
        ResultSet resultSet = null;

        List<ClienteDTO> listaClienti = new LinkedList<>();

        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
                listaClienti.add(getByResultSet(resultSet));

            return listaClienti;

        }
        catch(SQLException sqe) {
            sqe.printStackTrace();
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
