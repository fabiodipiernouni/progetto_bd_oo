package org.unina.uninadelivery33.dal.factory;

import org.unina.uninadelivery33.dal.exception.ConsistencyException;
import org.unina.uninadelivery33.dal.exception.PersistenceException;
import org.unina.uninadelivery33.entity.customerdomain.ClienteDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

class OracleClienteDAO implements ClienteDAO {

    private final Connection connection = DatabaseSingleton.getInstance().connect();

    public Optional<ClienteDTO> selectById(long idCliente) throws PersistenceException {
        Optional<ClienteDTO> cliente = Optional.empty();


        Statement statement = null;
        ResultSet resultSet = null;

        String nome;
        String cognome;
        String ragioneSociale;
        String email;
        String codiceFiscale;
        String partitaIVA;

        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Cliente WHERE id = " + idCliente);

            if(resultSet.next()) {

                nome = resultSet.getString("nome");
                cognome = resultSet.getString("cognome");

                ragioneSociale = resultSet.getString("ragioneSociale");
                if (resultSet.wasNull())
                    ragioneSociale = null;

                email = resultSet.getString("email");

                codiceFiscale = resultSet.getString("codiceFiscale");
                if (resultSet.wasNull())
                    codiceFiscale = null;

                partitaIVA = resultSet.getString("partitaIVA");
                if (resultSet.wasNull())
                    partitaIVA = null;

                cliente = Optional.of(new ClienteDTO(
                        idCliente,
                        nome,
                        cognome,
                        ragioneSociale,
                        email,
                        codiceFiscale,
                        partitaIVA
                ));

                if(codiceFiscale != null ^ (ragioneSociale != null && partitaIVA != null))
                    throw new ConsistencyException("Errore di consistenza: il cliente deve avere o codice fiscale o ragione sociale e partita IVA");

            }

            return cliente;

        }
        catch(SQLException sqe) {
            throw new PersistenceException("Errore in OracleClienteDAO: " + sqe.getMessage());
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
                throw new PersistenceException("Errore in OracleClienteDAO: " + sqe.getMessage());
            }
        }
    }
}
