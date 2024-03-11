package org.unina.uninadelivery.dal.factory.shipmentdomain;

import org.unina.uninadelivery.dal.exception.ConsistencyException;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.DatabaseSingleton;
import org.unina.uninadelivery.dal.factory.appdomain.FactoryAppDomain;
import org.unina.uninadelivery.dal.factory.orgdomain.FactoryOrgDomain;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;
import org.unina.uninadelivery.entity.orgdomain.MezzoDiTrasportoDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroSpedizioneDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

class OracleOrdineDiLavoroSpedizioneDAO implements OrdineDiLavoroSpedizioneDAO {

    private final Connection connection = DatabaseSingleton.getInstance().connect();

    private OrdineDiLavoroSpedizioneDTO getByResultSet(ResultSet resultSet, FilialeDTO filiale, GruppoCorriereDTO gruppoCorriere, OperatoreCorriereDTO operatoreCorriere) throws SQLException, PersistenceException {
        long id = resultSet.getLong("id");

        LocalDate dataCreazione = resultSet.getObject("dataCreazione", LocalDate.class);

        LocalDate dataInizioPianificazione = resultSet.getObject("dataInizioPianificazione", LocalDate.class);
        if(resultSet.wasNull())
            dataInizioPianificazione = null;

        LocalDate dataInizioLavorazione = resultSet.getObject("dataInizioLavorazione", LocalDate.class);
        if(resultSet.wasNull())
            dataInizioLavorazione = null;

        LocalDate dataFineLavorazione = resultSet.getObject("dataFineLavorazione", LocalDate.class);
        if(resultSet.wasNull())
            dataFineLavorazione = null;

        long idSpedizione = resultSet.getLong("idSpedizione");
        Optional<SpedizioneDTO> spedizione = FactoryShipmentDomain.buildSpedizioneDAO().select(idSpedizione);
        if(spedizione.isEmpty())
            throw new ConsistencyException("Spedizione non trovata");

        String stato = resultSet.getString("stato");

        String noteAggiuntiveOperatore = resultSet.getString("noteAggiuntiveOperatore");
        if(resultSet.wasNull())
            noteAggiuntiveOperatore = null;

        long idMezzoDiTrasporto = resultSet.getLong("idMezzoDiTrasporto");
        Optional<MezzoDiTrasportoDTO> mezzoDiTrasporto = FactoryOrgDomain.buildMezzoDiTrasportoDAO().select(idMezzoDiTrasporto);
        if(mezzoDiTrasporto.isEmpty())
            throw new ConsistencyException("Mezzo di Trasporto non trovato");


        OrdineDiLavoroSpedizioneDTO ordineDiLavoroSpedizione = new OrdineDiLavoroSpedizioneDTO(id, dataCreazione, dataInizioPianificazione, dataInizioLavorazione, dataFineLavorazione, gruppoCorriere, operatoreCorriere, filiale, spedizione.get(), stato, noteAggiuntiveOperatore, mezzoDiTrasporto.get());
        ordineDiLavoroSpedizione.setPacchi(FactoryShipmentDomain.buildPaccoDAO().select(ordineDiLavoroSpedizione));

        return ordineDiLavoroSpedizione;
    }

    private OrdineDiLavoroSpedizioneDTO getByResultSet(ResultSet resultSet) throws PersistenceException, SQLException {

        long idFiliale = resultSet.getLong("idFiliale");
        Optional<FilialeDTO> filiale = FactoryOrgDomain.buildFilialeDAO().select(idFiliale);
        if(filiale.isEmpty())
            throw new ConsistencyException("Filiale non trovata");

        GruppoCorriereDTO gruppoCorriere = null;
        long idGruppoCorriere = resultSet.getLong("idGruppoCorriere");

        if(!resultSet.wasNull()) {

            Optional<GruppoCorriereDTO> gruppoCorriereOpt = FactoryOrgDomain.buildGruppoCorriereDAO().select(idGruppoCorriere);
            if(gruppoCorriereOpt.isEmpty())
                throw new ConsistencyException("Gruppo corriere non trovato");

            gruppoCorriere = gruppoCorriereOpt.get();

        }


        OperatoreCorriereDTO operatoreCorriere = null;
        long idOperatoreCorriere = resultSet.getLong("idOperatoreCorriere");

        if(!resultSet.wasNull()) {

            Optional<UtenteDTO> operatoreCorriereOpt = FactoryAppDomain.buildUtenteDAO().select(idOperatoreCorriere);
            if(operatoreCorriereOpt.isEmpty())
                throw new ConsistencyException("Operatore corriere non trovato");

            operatoreCorriere = (OperatoreCorriereDTO) operatoreCorriereOpt.get();
        }

        return getByResultSet(resultSet, filiale.get(), gruppoCorriere, operatoreCorriere);
    }

    private OrdineDiLavoroSpedizioneDTO getByResultSet(ResultSet resultSet, FilialeDTO filiale) throws SQLException, PersistenceException {

        GruppoCorriereDTO gruppoCorriere = null;
        long idGruppoCorriere = resultSet.getLong("idGruppoCorriere");

        if(!resultSet.wasNull()) {

            Optional<GruppoCorriereDTO> gruppoCorriereOpt = FactoryOrgDomain.buildGruppoCorriereDAO().select(idGruppoCorriere);
            if(gruppoCorriereOpt.isEmpty())
                throw new ConsistencyException("Gruppo corriere non trovato");

            gruppoCorriere = gruppoCorriereOpt.get();

        }


        OperatoreCorriereDTO operatoreCorriere = null;
        long idOperatoreCorriere = resultSet.getLong("idOperatoreCorriere");

        if(!resultSet.wasNull()) {

            Optional<UtenteDTO> operatoreCorriereOpt = FactoryAppDomain.buildUtenteDAO().select(idOperatoreCorriere);
            if(operatoreCorriereOpt.isEmpty())
                throw new ConsistencyException("Operatore corriere non trovato");

            operatoreCorriere = (OperatoreCorriereDTO) operatoreCorriereOpt.get();
        }

        return getByResultSet(resultSet, filiale, gruppoCorriere, operatoreCorriere);

    }

    private OrdineDiLavoroSpedizioneDTO getByResultSet(ResultSet resultSet, GruppoCorriereDTO gruppoCorriere) throws SQLException, PersistenceException {


        long idFiliale = resultSet.getLong("idFiliale");
        Optional<FilialeDTO> filiale = FactoryOrgDomain.buildFilialeDAO().select(idFiliale);
        if(filiale.isEmpty())
            throw new ConsistencyException("Filiale non trovata");


        OperatoreCorriereDTO operatoreCorriere = null;
        long idOperatoreCorriere = resultSet.getLong("idOperatoreCorriere");

        if(!resultSet.wasNull()) {

            Optional<UtenteDTO> operatoreCorriereOpt = FactoryAppDomain.buildUtenteDAO().select(idOperatoreCorriere);
            if(operatoreCorriereOpt.isEmpty())
                throw new ConsistencyException("Operatore corriere non trovato");

            operatoreCorriere = (OperatoreCorriereDTO) operatoreCorriereOpt.get();
        }

        return getByResultSet(resultSet, filiale.get(), gruppoCorriere, operatoreCorriere);

    }

    private OrdineDiLavoroSpedizioneDTO getByResultSet(ResultSet resultSet, OperatoreCorriereDTO operatoreCorriere) throws SQLException, PersistenceException {


        long idFiliale = resultSet.getLong("idFiliale");
        Optional<FilialeDTO> filiale = FactoryOrgDomain.buildFilialeDAO().select(idFiliale);
        if(filiale.isEmpty())
            throw new ConsistencyException("Filiale non trovata");


        GruppoCorriereDTO gruppoCorriere = null;
        long idGruppoCorriere = resultSet.getLong("idGruppoCorriere");

        if(!resultSet.wasNull()) {

            Optional<GruppoCorriereDTO> gruppoCorriereOpt = FactoryOrgDomain.buildGruppoCorriereDAO().select(idGruppoCorriere);
            if(gruppoCorriereOpt.isEmpty())
                throw new ConsistencyException("Gruppo corriere non trovato");

            gruppoCorriere = gruppoCorriereOpt.get();

        }

        return getByResultSet(resultSet, filiale.get(), gruppoCorriere, operatoreCorriere);

    }


    public List<OrdineDiLavoroSpedizioneDTO> select() throws PersistenceException {
        return select(null, null);
    }

    public List<OrdineDiLavoroSpedizioneDTO> select(FilialeDTO filiale) throws PersistenceException {
        return select(filiale, null);
    }


    public List<OrdineDiLavoroSpedizioneDTO> select(FilialeDTO filiale, String stato) throws PersistenceException {
        String query = "SELECT * FROM OrdineDiLavoroSpedizione WHERE 1=1";
        if(filiale != null)
            query += " AND idFiliale = " + filiale.getId();
        if(stato != null)
            query += " AND stato = '" + stato + "'";


        Statement statement = null;
        ResultSet resultSet = null;

        List<OrdineDiLavoroSpedizioneDTO> listaOrdini = new LinkedList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
                listaOrdini.add(getByResultSet(resultSet, filiale));

            return listaOrdini;
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


    public List<OrdineDiLavoroSpedizioneDTO> select(OperatoreFilialeDTO operatoreFiliale) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        List<OrdineDiLavoroSpedizioneDTO> listaOrdini = new LinkedList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("""
            SELECT OrdineDiLavoroSpedizione.* 
            FROM OrdineDiLavoroSpedizione 
            JOIN Spedizione
            ON OrdineDiLavoroSpedizione.idSpedizione = Spedizione.id
            WHERE Spedizione.idUtenteOrganizzatore = """ + operatoreFiliale.getId());

            while(resultSet.next())
                listaOrdini.add(getByResultSet(resultSet));

            return listaOrdini;
        }
        catch(SQLException sqe) {
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


    public List<OrdineDiLavoroSpedizioneDTO> select(GruppoCorriereDTO gruppoCorriere) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        List<OrdineDiLavoroSpedizioneDTO> listaOrdini = new LinkedList<OrdineDiLavoroSpedizioneDTO>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM OrdineDiLavoroSpedizione WHERE idGruppoCorriere = " + gruppoCorriere.getId());

            while (resultSet.next())
                listaOrdini.add(getByResultSet(resultSet, gruppoCorriere));

            return listaOrdini;
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


    public List<OrdineDiLavoroSpedizioneDTO> select(OperatoreCorriereDTO operatoreCorriere) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        List<OrdineDiLavoroSpedizioneDTO> listaOrdini = new LinkedList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM OrdineDiLavoroSpedizione WHERE idOperatoreCorriere = " + operatoreCorriere.getId());

            while (resultSet.next())
                listaOrdini.add(getByResultSet(resultSet, operatoreCorriere));

            return listaOrdini;
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


    public int getCount(FilialeDTO filiale, String stato) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM OrdineDiLavoroSpedizione WHERE idFiliale = " + filiale.getId() + " AND stato = '" + stato + "'");

            if(!resultSet.next())
                throw new PersistenceException("Errore nel conteggio degli ordini di lavoro di spedizione");

            return resultSet.getInt(1);
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


    public void update(OrdineDiLavoroSpedizioneDTO ordineDiLavoroSpedizione) throws PersistenceException {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("""
            UPDATE OrdineDiLavoroSpedizione
            SET dataCreazione = ?,
                dataInizioPianificazione = ?,
                dataInizioLavorazione = ?,
                dataFineLavorazione = ?,
                idGruppoCorriere = ?,
                idOperatoreCorriere = ?,
                idFiliale = ?,
                idSpedizione = ?,
                noteAggiuntiveOperatore = ?,
                idMezzoDiTrasporto = ?
            WHERE id = ?""");

            preparedStatement.setObject(1, ordineDiLavoroSpedizione.getDataCreazione());
            preparedStatement.setObject(2, ordineDiLavoroSpedizione.getDataInizioPianificazione());
            preparedStatement.setObject(3, ordineDiLavoroSpedizione.getDataInizioLavorazione());
            preparedStatement.setObject(4, ordineDiLavoroSpedizione.getDataFineLavorazione());
            preparedStatement.setLong(5, ordineDiLavoroSpedizione.getGruppoCorriere().getId());
            preparedStatement.setLong(6, ordineDiLavoroSpedizione.getOperatoreCorriere().getId());
            preparedStatement.setLong(7, ordineDiLavoroSpedizione.getFiliale().getId());
            preparedStatement.setLong(8, ordineDiLavoroSpedizione.getSpedizione().getId());
            preparedStatement.setString(9, ordineDiLavoroSpedizione.getNoteAggiuntiveOperatore());
            preparedStatement.setLong(10, ordineDiLavoroSpedizione.getMezzoDiTrasporto().getId());
            preparedStatement.setLong(11, ordineDiLavoroSpedizione.getId());

            preparedStatement.executeUpdate();

        }
        catch (SQLException sqe) {
            throw new PersistenceException(sqe.getMessage());
        }
        finally {
            //libero le risorse
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            }
            catch(SQLException sqe) {
                //non faccio nulla
            }
        }


    }


    public int getCountNonConclusi(FilialeDTO filiale) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM OrdineDiLavoroSpedizione WHERE idFiliale = " + filiale.getId() + " AND stato in ('Assegnato', 'InLavorazione') ");

            if(!resultSet.next())
                throw new PersistenceException("Errore nel conteggio degli ordini di lavoro di spedizione");

            return resultSet.getInt(1);
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
