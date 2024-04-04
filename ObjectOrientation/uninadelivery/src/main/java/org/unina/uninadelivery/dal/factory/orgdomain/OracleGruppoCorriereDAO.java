package org.unina.uninadelivery.dal.factory.orgdomain;

import org.unina.uninadelivery.dal.exception.ConsistencyException;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.DatabaseSingleton;

import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

class OracleGruppoCorriereDAO implements GruppoCorriereDAO {
    private final Connection connection;

    OracleGruppoCorriereDAO() throws PersistenceException {
        connection = DatabaseSingleton.getInstance().connect();
    }

    @Override
    public Optional<GruppoCorriereDTO> select(long id) throws PersistenceException {
        Optional<GruppoCorriereDTO>  gruppoCorriere = Optional.empty();

        Statement statement = null;
        ResultSet resultSet = null;

        String nome;
        String codiceCorriere;
        int numeroDipendenti;
        long idFiliale;

        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM GruppoCorriere WHERE id = " + id);

            if(resultSet.next()) {
                //sono sicuro che otterrò una sola tupla, non metto in un while

                nome = resultSet.getString("nome");
                codiceCorriere = resultSet.getString("codiceCorriere");
                numeroDipendenti = resultSet.getInt("numeroDipendenti");

                idFiliale = resultSet.getLong("idFiliale");

                Optional<FilialeDTO> filiale = FactoryOrgDomain.buildFilialeDAO().select(idFiliale);
                if(filiale.isEmpty())
                    throw new ConsistencyException( "Filiale non trovata");

                gruppoCorriere = Optional.of(new GruppoCorriereDTO(id, nome, codiceCorriere, numeroDipendenti, filiale.get()));

            }

            return gruppoCorriere;
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
                //non faccio nulla
            }
        }

    }

}
