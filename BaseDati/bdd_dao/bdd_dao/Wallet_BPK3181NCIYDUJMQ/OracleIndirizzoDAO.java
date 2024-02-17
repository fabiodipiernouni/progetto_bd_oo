package org.uninadelivery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleIndirizzoDAO implements IndirizzoDAO {
    private final Connection connection = DatabaseSingleton.getIstance().connect();

    public Indirizzo selectById(long id) throws PersistenceException {
        
        Statement statement = null;
        ResultSet resultSet = null;

        String nome;
        String cognome;
        String cf_pIVA;
        String indirizzo_1;
        String indirizzo_2;
        String CAP;
        long idComune;
        String noteAggiuntive;

        try {
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Indirizzo WHERE id = " + id);

            if(!resultSet.next())
                throw new PersistenceException("Indirizzo non trovato");

            //sono sicuro che otterrò una sola tupla, non metto in un while

            nome = resultSet.getString("nome");
            cognome = resultSet.getString("cognome");
            cf_pIVA = resultSet.getString("cf_pIVA");
            indirizzo_1 = resultSet.getString("indirizzo_1");

            indirizzo_2 = resultSet.getString("indirizzo_2");
            if(resultSet.wasNull())
                indirizzo_2 = null;

            CAP = resultSet.getString("CAP");

            idComune = resultSet.getLong("idComune");


            noteAggiuntive = resultSet.getString("noteAggiuntive");
            if(resultSet.wasNull())
                noteAggiuntive = null;

        }
        catch(SQLException | PersistenceException throwables) {
            throw new PersistenceException("Errore in OracleIndirizzoDAO: " + throwables.getMessage());
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
                throw new PersistenceException("Errore in OracleIndirizzoDAO: " + sqe.getMessage());
            }
        }

        return new Indirizzo(
                id,
                nome,
                cognome,
                cf_pIVA,
                indirizzo_1,
                indirizzo_2,
                CAP,
                new OracleComuneFullDAO().selectById(idComune),
                noteAggiuntive
        );

    }
}
