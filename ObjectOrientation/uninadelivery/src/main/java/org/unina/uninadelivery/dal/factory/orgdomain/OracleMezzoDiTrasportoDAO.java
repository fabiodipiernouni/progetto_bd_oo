package org.unina.uninadelivery.dal.factory.orgdomain;

import org.unina.uninadelivery.dal.exception.ConsistencyException;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.DatabaseSingleton;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;
import org.unina.uninadelivery.entity.orgdomain.MezzoDiTrasportoDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
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

    @Override
    public List<MezzoDiTrasportoDTO> select(GruppoCorriereDTO gruppoCorriere, LocalDate dataInizio, LocalDate dataFine) throws PersistenceException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<MezzoDiTrasportoDTO> mezzi = new LinkedList<>();

        try {
            preparedStatement = connection.prepareStatement("""
                    select *
                    from MezzoDiTrasporto mt
                    where
                      mt.idGruppoCorriere = ?
                      and not exists (
                        select distinct 1
                        from ImpegnoMezzo im
                        where
                            im.idMezzo = mt.id
                            and (
                              (im.dataFine is null or im.dataFine > trunc(?)) or
                              (im.dataInizio between trunc(?) and trunc(?))
                            )
                      )
                    order by mt.pesotrasportabile
   
            """);

            preparedStatement.setLong(1, gruppoCorriere.getId());
            preparedStatement.setObject(2, dataFine);
            preparedStatement.setObject(3, dataInizio);
            preparedStatement.setObject(4, dataFine);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
                mezzi.add(new MezzoDiTrasportoDTO(
                        resultSet.getLong("id"),
                        resultSet.getString("targa"),
                        resultSet.getString("tipo"),
                        gruppoCorriere
                ));


            return mezzi;


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
                //non faccio nulla
            }
        }

    }
}
