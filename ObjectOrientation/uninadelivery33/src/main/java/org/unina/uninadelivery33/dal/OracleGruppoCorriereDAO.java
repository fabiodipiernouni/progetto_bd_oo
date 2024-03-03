package org.unina.uninadelivery33.dal;

import org.unina.uninadelivery33.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery33.entity.orgdomain.GruppoCorriereDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleGruppoCorriereDAO {
    private final Connection connection = DatabaseSingleton.getInstance().connect();

    public GruppoCorriereDTO selectById(long id) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        String nome;
        String codiceCorriere;
        int numeroDipendenti;
        long idFiliale;

        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM GruppoCorriere WHERE id = " + id);

            if(!resultSet.next())
                throw new PersistenceException("GruppoCorriere non trovato");

            //sono sicuro che otterrò una sola tupla, non metto in un while

            nome = resultSet.getString("nome");
            codiceCorriere = resultSet.getString("codiceCorriere");
            numeroDipendenti = resultSet.getInt("numeroDipendenti");

            idFiliale = resultSet.getLong("idFiliale");

            FilialeDTO filiale = new OracleFilialeDAO().selectById(idFiliale);

            //return new GruppoCorriereDTO(id, nome, codiceCorriere, numeroDipendenti, null);
            return new GruppoCorriereDTO(0, "","", 0, null);
        }
        catch(SQLException sqe) {
            throw new PersistenceException("Errore in OracleComuneFullDAO: " + sqe.getMessage());
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
                throw new PersistenceException("Errore in OracleComuneFullDAO: " + sqe.getMessage());
            }
        }

    }

}
