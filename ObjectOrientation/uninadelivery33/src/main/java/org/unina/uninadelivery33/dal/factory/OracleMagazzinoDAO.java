package org.unina.uninadelivery33.dal.factory;

import lombok.NonNull;
import org.unina.uninadelivery33.dal.exception.ConsistencyException;
import org.unina.uninadelivery33.dal.exception.PersistenceException;
import org.unina.uninadelivery33.entity.geodomain.IndirizzoDTO;
import org.unina.uninadelivery33.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery33.entity.orgdomain.MagazzinoDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

class OracleMagazzinoDAO implements MagazzinoDAO {
    private final Connection connection = DatabaseSingleton.getInstance().connect();

    private MagazzinoDTO getMagazzinoByResultSet(ResultSet resultSet) throws SQLException, PersistenceException {
        long id = resultSet.getLong("id");
        String nome = resultSet.getString("nome");
        long idIndirizzo = resultSet.getLong("idIndirizzo");
        long idFiliale = resultSet.getLong("idFiliale");

        Optional<IndirizzoDTO> indirizzo = Factory.buildIndirizzoDAO().selectById(idIndirizzo);
        if(indirizzo.isEmpty())
            throw new ConsistencyException("Indirizzo non trovato");

        Optional<FilialeDTO> filiale = Factory.buildFilialeDAO().selectById(idFiliale);
        if(filiale.isEmpty())
            throw new ConsistencyException("Filiale non trovata");

        return new MagazzinoDTO(id, nome, indirizzo.get(), filiale.get());
    }

    public Optional<MagazzinoDTO> selectById(long id) throws PersistenceException {
        Optional<MagazzinoDTO> magazzino = Optional.empty();

        Statement statement = null;
        ResultSet resultSet = null;

        String nome;
        Optional<IndirizzoDTO> indirizzo;
        Optional<FilialeDTO> filiale;

        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Magazzino WHERE id= " + id);

            if(resultSet.next())
                magazzino = Optional.of(getMagazzinoByResultSet(resultSet));

            return magazzino;

        }
        catch(SQLException throwables) {
            throw new PersistenceException(throwables.getMessage());
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

    public List<MagazzinoDTO> selectMagazziniByIdDettaglioOrdine(long idDettaglioOrdine) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        List<MagazzinoDTO> listaMagazzini = new LinkedList<MagazzinoDTO>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("""
                SELECT Magazzino.*
                FROM DettaglioOrdineMagazzino
                JOIN Magazzino
                on Magazzino.id = DettaglioOrdineMagazzino.IdMagazzino
                WHERE idDettaglioOrdine= """ + idDettaglioOrdine);

            while (resultSet.next())
                listaMagazzini.add(getMagazzinoByResultSet(resultSet));


            return listaMagazzini;
        }
        catch(SQLException sqe) {
            throw new PersistenceException(sqe.getMessage());
        }
        finally {
            //libero le risorse
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
            }
            catch(SQLException sqe) {
                //non faccio nulla
            }

        }
    }
}
