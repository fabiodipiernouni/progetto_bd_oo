package org.unina.uninadelivery.dal.factory.shipmentdomain;

import org.unina.uninadelivery.dal.exception.ConsistencyException;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.DatabaseSingleton;
import org.unina.uninadelivery.dal.factory.geodomain.FactoryGeoDomain;
import org.unina.uninadelivery.dal.factory.orgdomain.FactoryOrgDomain;
import org.unina.uninadelivery.entity.geodomain.IndirizzoDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.MagazzinoDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroPackagingDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroSpedizioneDTO;
import org.unina.uninadelivery.entity.shipmentdomain.PaccoDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

class OraclePaccoDAO implements PaccoDAO {

    private final Connection connection;

    OraclePaccoDAO() throws PersistenceException {
        connection = DatabaseSingleton.getInstance().connect();
    }

    private PaccoDTO getByResultSet(ResultSet resultSet, SpedizioneDTO spedizione, OrdineDiLavoroPackagingDTO ordineDiLavoroPackaging) throws SQLException, PersistenceException, ConsistencyException {
        long id = resultSet.getLong("id");
        String codicePacco = resultSet.getString("codicePacco");
        float peso = resultSet.getFloat("peso");
        long idMagazzino = resultSet.getLong("idMagazzino");
        long idIndirizzoDestinazione = resultSet.getLong("idIndirizzoDestinazione");

        Optional<MagazzinoDTO> magazzino = FactoryOrgDomain.buildMagazzinoDAO().select(idMagazzino);
        if (magazzino.isEmpty())
            throw new ConsistencyException("Magazzino con id " + idMagazzino + " non trovato");

        Optional<IndirizzoDTO> indirizzo = FactoryGeoDomain.buildIndirizzoDAO().select(idIndirizzoDestinazione);

        if (indirizzo.isEmpty())
            throw new ConsistencyException("Indirizzo con id " + idIndirizzoDestinazione + " non trovato");

        PaccoDTO ret = new PaccoDTO(
                id,
                codicePacco,
                peso,
                magazzino.get(),
                indirizzo.get(),
                spedizione,
                ordineDiLavoroPackaging
        );

        return ret;
    }

    private PaccoDTO getByResultSet(ResultSet resultSet) throws PersistenceException, SQLException {

        long idSpedizione = resultSet.getLong("idSpedizione");
        Optional<SpedizioneDTO> spedizione = FactoryShipmentDomain.buildSpedizioneDAO().select(idSpedizione);
        if (spedizione.isEmpty())
            throw new ConsistencyException("Spedizione non trovata");


        long idOrdineLavoroOrigine = resultSet.getLong("idOrdineLavoroOrigine");
        Optional<OrdineDiLavoroPackagingDTO> ordineDiLavoroPackaging = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO().select(idOrdineLavoroOrigine);
        if (ordineDiLavoroPackaging.isEmpty())
            throw new ConsistencyException("Ordine di lavoro packaging non trovato");


        return getByResultSet(resultSet, spedizione.get(), ordineDiLavoroPackaging.get());

    }

    private PaccoDTO getByResultSet(ResultSet resultSet, OrdineDiLavoroPackagingDTO ordineDiLavoroPackaging) throws PersistenceException, SQLException {

        long idSpedizione = resultSet.getLong("idSpedizione");
        Optional<SpedizioneDTO> spedizione = FactoryShipmentDomain.buildSpedizioneDAO().select(idSpedizione);
        if (spedizione.isEmpty())
            throw new ConsistencyException("Spedizione non trovata");


        return getByResultSet(resultSet, spedizione.get(), ordineDiLavoroPackaging);

    }

    private PaccoDTO getByResultSet(ResultSet resultSet, SpedizioneDTO spedizione) throws PersistenceException, SQLException {
        long idOrdineLavoroOrigine = resultSet.getLong("idOrdineLavoroOrigine");

        Optional<OrdineDiLavoroPackagingDTO> ordineDiLavoroPackaging = FactoryShipmentDomain.buildOrdineDiLavoroPackagingDAO().select(idOrdineLavoroOrigine);
        if (ordineDiLavoroPackaging.isEmpty())
            throw new ConsistencyException("Ordine di lavoro packaging non trovato");

        return getByResultSet(resultSet, spedizione, ordineDiLavoroPackaging.get());
    }

    public List<PaccoDTO> select(OrdineDiLavoroPackagingDTO ordineDiLavoroPackaging) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        List<PaccoDTO> listaPacchi = new LinkedList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Pacco WHERE idOrdineLavoroOrigine = " + ordineDiLavoroPackaging.getId());

            while (resultSet.next())
                listaPacchi.add(getByResultSet(resultSet, ordineDiLavoroPackaging));

            return listaPacchi;
        } catch (SQLException sqe) {
            sqe.printStackTrace();
            throw new PersistenceException(sqe.getMessage());
        } finally {
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

    public List<PaccoDTO> select(FilialeDTO filiale, SpedizioneDTO spedizione) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        List<PaccoDTO> listaPacchi = new LinkedList<>();

        try {
            statement = connection.createStatement();
            String query = """
                    SELECT *
                    FROM Pacco p
                    JOIN Magazzino m ON p.idMagazzino = m.id
                    JOIN OrdineDiLavoroPackaging odlp ON p.idOrdineLavoroOrigine = odlp.id
                    WHERE 1=1""";

            if (filiale != null)
                query += " AND m.idFiliale = " + filiale.getId();

            if (spedizione != null)
                query += " AND odlp.idSpedizione = " + spedizione.getId();

            resultSet = statement.executeQuery(query);

            while (resultSet.next())
                listaPacchi.add(getByResultSet(resultSet, spedizione));

            return listaPacchi;
        } catch (SQLException sqe) {
            sqe.printStackTrace();
            throw new PersistenceException(sqe.getMessage());
        } finally {
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
        } catch (SQLException sqe) {
            throw new PersistenceException(sqe.getMessage());
        } finally {
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

    @Override
    public void genera(OrdineDiLavoroPackagingDTO ordineDiLavoroPackagingDTO) throws PersistenceException {
        CallableStatement statement = null;

        try {
            statement = connection.prepareCall("{ call GeneraPacchiByIdOrdinePackaging( ? ) }");
            statement.setLong(1, ordineDiLavoroPackagingDTO.getId());
            statement.execute();
        } catch (SQLException sqe) {
            throw new PersistenceException(sqe.getMessage());
        } finally {
            //libero le risorse
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException sqe) {
                //non faccio niente
            }
        }
    }

    @Override
    public Integer getCount(FilialeDTO filiale, SpedizioneDTO spedizione, Boolean spediti) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            String query = """
                    SELECT count(*)
                    FROM 
                        Pacco p 
                        join Magazzino m on p.idMagazzino = m.id
                        join OrdineDiLavoroPackaging odlp on p.idOrdineLavoroOrigine = odlp.id
                    WHERE 1=1""";

            if (filiale != null)
                query += " AND m.idFiliale = " + filiale.getId();

            if (spedizione != null)
                query += " AND odlp.idSpedizione = " + spedizione.getId();

            if (spediti != null)
                query += " AND " + (spediti ? "" : "not") + " exists (select distinct 1 from OrdineDiLavoroSpedizionePacchi where idPacco = p.id)";

            resultSet = statement.executeQuery(query);
            if (!resultSet.next())
                throw new PersistenceException("Errore nel conteggio degli ordini di lavoro di packaging");

            return resultSet.getInt(1);
        } catch (SQLException sqe) {
            throw new PersistenceException(sqe.getMessage());
        } finally {
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
