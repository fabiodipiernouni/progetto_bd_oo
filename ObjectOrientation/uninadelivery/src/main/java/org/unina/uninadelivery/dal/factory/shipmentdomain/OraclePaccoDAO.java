package org.unina.uninadelivery.dal.factory.shipmentdomain;

import org.unina.uninadelivery.dal.exception.ConsistencyException;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.DatabaseSingleton;
import org.unina.uninadelivery.dal.factory.geodomain.FactoryGeoDomain;
import org.unina.uninadelivery.dal.factory.orgdomain.FactoryOrgDomain;
import org.unina.uninadelivery.entity.geodomain.IndirizzoDTO;
import org.unina.uninadelivery.entity.orgdomain.MagazzinoDTO;
import org.unina.uninadelivery.entity.orgdomain.MezzoDiTrasportoDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroPackagingDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroSpedizioneDTO;
import org.unina.uninadelivery.entity.shipmentdomain.PaccoDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

class OraclePaccoDAO implements PaccoDAO {

    private final Connection connection = DatabaseSingleton.getInstance().connect();

    private PaccoDTO getByResultSet(ResultSet resultSet, SpedizioneDTO spedizione) throws SQLException, PersistenceException {
        String codicePacco = resultSet.getString("codicePacco");
        float peso = resultSet.getFloat("peso");

        long idMagazzino = resultSet.getLong("idMagazzino");
        Optional<MagazzinoDTO> magazzino = FactoryOrgDomain.buildMagazzinoDAO().select(idMagazzino);
        if(magazzino.isEmpty())
            throw new ConsistencyException("Magazzino non trovato");

        long idIndirizzoDestinazione = resultSet.getLong("idIndirizzoDestinazione");
        Optional<IndirizzoDTO> indirizzoDestinazione = FactoryGeoDomain.buildIndirizzoDAO().select(idIndirizzoDestinazione);
        if(indirizzoDestinazione.isEmpty())
            throw new ConsistencyException("Indirizzo di destinazione non trovato");



        return new PaccoDTO(codicePacco, peso, magazzino.get(), indirizzoDestinazione.get(), spedizione);
    }

    private PaccoDTO getByResultSet(ResultSet resultSet) throws PersistenceException, SQLException {
        long idSpedizione = resultSet.getLong("idSpedizione");
        Optional<SpedizioneDTO> spedizione = FactoryShipmentDomain.buildSpedizioneDAO().select(idSpedizione);
        if(spedizione.isEmpty())
            throw new ConsistencyException("Spedizione non trovata");

        return getByResultSet(resultSet, spedizione.get());
    }

    public List<PaccoDTO> select(OrdineDiLavoroPackagingDTO ordineDiLavoroPackaging) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        List<PaccoDTO> listaPacchi = new LinkedList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Pacco WHERE idOrdineLavoroOrigine = " + ordineDiLavoroPackaging.getId());

            while (resultSet.next())
                listaPacchi.add(getByResultSet(resultSet));

            return listaPacchi;
        }
        catch (SQLException sqe) {
            throw new PersistenceException(sqe.getMessage());
        }
        finally {
            //libero le risorse
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
            } catch (SQLException sqe) {
                //non faccio nulla
            }
        }
    }

    public List<PaccoDTO> select(SpedizioneDTO spedizione) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        List<PaccoDTO> listaPacchi = new LinkedList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Pacco WHERE idSpedizione = " + spedizione.getId());

            while (resultSet.next())
                listaPacchi.add(getByResultSet(resultSet, spedizione));

            return listaPacchi;
        }
        catch (SQLException sqe) {
            throw new PersistenceException(sqe.getMessage());
        }
        finally {
            //libero le risorse
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
            } catch (SQLException sqe) {
                //non faccio nulla
            }
        }
    }

    public List<PaccoDTO> select(OrdineDiLavoroSpedizioneDTO ordineDiLavoroSpedizione) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        List<PaccoDTO> listaPacchi = new LinkedList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("""
            SELECT *
            FROM Pacco
            JOIN OrdineDiLavoroSpedizionePacchi
            ON Pacco.id = OrdineDiLavoroSpedizionePacchi.idPacco
            WHERE OrdineDiLavoroSpedizionePacchi.idOrdineDiLavoroSpedizione = """
            + ordineDiLavoroSpedizione.getId());

            while (resultSet.next())
                listaPacchi.add(getByResultSet(resultSet));

            return listaPacchi;
        }
        catch (SQLException sqe) {
            throw new PersistenceException(sqe.getMessage());
        }
        finally {
            //libero le risorse
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
            } catch (SQLException sqe) {
                //non faccio nulla
            }
        }
    }
}
