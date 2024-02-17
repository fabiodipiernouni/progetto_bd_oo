package org.uninadelivery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleClienteDAO implements ClienteDAO {

    private final Connection connection = DatabaseSingleton.getIstance().connect();

    public Cliente selectById(long id) throws PersistenceException {
        
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
            resultSet = statement.executeQuery("SELECT * FROM Cliente WHERE id = " + id);

            if(!resultSet.next())
                throw new PersistenceException("Cliente non trovato");

            //sono sicuro che otterrò una sola tupla, non metto in un while

            nome = resultSet.getString("nome");
            cognome = resultSet.getString("cognome");

            ragioneSociale = resultSet.getString("ragioneSociale");
            if(resultSet.wasNull())
                ragioneSociale = null;

            email = resultSet.getString("email");

            codiceFiscale = resultSet.getString("codiceFiscale");
            if(resultSet.wasNull())
                codiceFiscale = null;

            partitaIVA = resultSet.getString("partitaIVA");
            if(resultSet.wasNull())
                partitaIVA = null;

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

        return new Cliente(
                id,
                nome,
                cognome,
                ragioneSociale,
                email,
                codiceFiscale,
                partitaIVA
        );

    }
}
