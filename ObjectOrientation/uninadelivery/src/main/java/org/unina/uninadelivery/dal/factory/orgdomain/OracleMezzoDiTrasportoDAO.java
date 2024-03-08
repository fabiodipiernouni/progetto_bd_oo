package org.unina.uninadelivery.dal.factory.orgdomain;

import org.unina.uninadelivery.dal.exception.ConsistencyException;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.DatabaseSingleton;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;
import org.unina.uninadelivery.entity.orgdomain.MezzoDiTrasportoDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

class OracleMezzoDiTrasportoDAO implements MezzoDiTrasportoDAO {

    private final Connection connection = DatabaseSingleton.getInstance().connect();

    public Optional<MezzoDiTrasportoDTO> select(long id) throws PersistenceException {
        Optional<MezzoDiTrasportoDTO> mezzo = Optional.empty();

        Statement statement = null;
        ResultSet resultSet = null;

        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM MezzoDiTrasporto WHERE MezzoDiTrasporto.id= " + id);

            if(resultSet.next()) {
                Optional<GruppoCorriereDTO> gruppoCorriere = FactoryOrgDomain.buildGruppoCorriereDAO().select(resultSet.getLong("idGruppoCorriere"));
                if(gruppoCorriere.isEmpty())
                    throw new ConsistencyException("Gruppo corriere non trovato");

                mezzo = Optional.of(new MezzoDiTrasportoDTO(
                        resultSet.getLong("id"),
                        resultSet.getString("targa"),
                        resultSet.getString("tipo"),
                        gruppoCorriere.get()
                ));
            }

            return mezzo;

        }
        catch (SQLException sqe){
            throw new PersistenceException(sqe.getMessage());
        }


    }
}
