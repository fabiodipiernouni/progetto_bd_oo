package org.unina.uninadelivery.dal.factory.orgdomain;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.DatabaseSingleton;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

class OracleFilialeDAO implements FilialeDAO {
    private final Connection connection;

    OracleFilialeDAO() throws PersistenceException {
        connection = DatabaseSingleton.getInstance().connect();
    }

    public Optional<FilialeDTO> select(long id) throws PersistenceException {
        Optional<FilialeDTO>  filiale = Optional.empty();


        Statement statement = null;
        ResultSet resultSet = null;

        String nome;
        String orgCountry;
        String orgRagioneSociale;
        String orgPartitaIva;


        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery("""
                SELECT Filiale.nome,
                    Org.paese, Org.ragioneSociale, Org.partitaIVA
                    FROM Filiale
                LEFT JOIN Org ON Filiale.idOrg = Org.id
                WHERE Filiale.id = """ + id);

            if(resultSet.next()) {
                //sono sicuro che otterrò una sola tupla, non metto in un while

                nome = resultSet.getString("nome");
                orgCountry = resultSet.getString("paese");
                orgRagioneSociale = resultSet.getString("ragioneSociale");
                orgPartitaIva = resultSet.getString("partitaIVA");

                filiale = Optional.of(new FilialeDTO(
                        id,
                        nome,
                        orgCountry,
                        orgRagioneSociale,
                        orgPartitaIva
                ));

            }

            return filiale;

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