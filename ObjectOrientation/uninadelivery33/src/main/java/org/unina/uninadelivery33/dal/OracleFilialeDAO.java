package org.unina.uninadelivery33.dal;

import org.unina.uninadelivery33.entity.orgdomain.FilialeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleFilialeDAO {
    private final Connection connection = DatabaseSingleton.getInstance().connect();

    public FilialeDTO selectById(long id) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        String nome;
        String orgCountry;
        String orgRagioneSociale;
        String orgPartitaIva;



        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery("""
                SELECT 
                    Filiale.nome,
                    Org.paese, Org.ragioneSociale, Org.partitaIVA
                    FROM Filiale
                LEFT JOIN Org ON Filiale.idOrg = Org.id
                WHERE Filiale.id = """ + id);

            if(!resultSet.next())
                throw new PersistenceException("Filiale non trovata");

            //sono sicuro che otterrò una sola tupla, non metto in un while

            nome = resultSet.getString("nome");
            orgCountry = resultSet.getString("paese");
            orgRagioneSociale = resultSet.getString("ragioneSociale");
            orgPartitaIva = resultSet.getString("partitaIVA");


            return new FilialeDTO(
                    id,
                    nome,
                    orgCountry,
                    orgRagioneSociale,
                    orgPartitaIva
            );

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