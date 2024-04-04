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

    private final Connection connection;

    OracleOrdineDiLavoroSpedizioneDAO() throws PersistenceException {
        connection = DatabaseSingleton.getInstance().connect();
    }

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

        MezzoDiTrasportoDTO mezzoDiTrasporto;
        long idMezzoDiTrasporto = resultSet.getLong("idMezzoDiTrasporto");
        if(resultSet.wasNull())
            mezzoDiTrasporto = null;
        else {
            Optional<MezzoDiTrasportoDTO> mezzoDiTrasportoOpt = FactoryOrgDomain.buildMezzoDiTrasportoDAO().select(idMezzoDiTrasporto);
            if (mezzoDiTrasportoOpt.isEmpty())
                throw new ConsistencyException("Mezzo di Trasporto non trovato");

            mezzoDiTrasporto = mezzoDiTrasportoOpt.get();
        }

        OrdineDiLavoroSpedizioneDTO ordineDiLavoroSpedizione = new OrdineDiLavoroSpedizioneDTO(id, dataCreazione, dataInizioPianificazione, dataInizioLavorazione, dataFineLavorazione, gruppoCorriere, operatoreCorriere, filiale, spedizione.get(), stato, noteAggiuntiveOperatore, mezzoDiTrasporto);
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

    private OrdineDiLavoroSpedizioneDTO getByResultSet(ResultSet resultSet, OperatoreCorriereDTO operatoreCorriere) throws PersistenceException {

        try {
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
        } catch (SQLException e) {
            throw new PersistenceException("Errore nel recupero dell'ordine di lavoro di spedizione - " + e.getMessage());
        }
    }

    public List<OrdineDiLavoroSpedizioneDTO> select() throws PersistenceException {
        return select((FilialeDTO) null, null);
    }

    @Override
    public OrdineDiLavoroSpedizioneDTO select(SpedizioneDTO spedizione, FilialeDTO filiale) throws PersistenceException {
        List<OrdineDiLavoroSpedizioneDTO> l = select(filiale, null, spedizione);
        if(l != null && !l.isEmpty())
            return l.get(0);
        else
            return null;
    }

    public List<OrdineDiLavoroSpedizioneDTO> select(FilialeDTO filiale) throws PersistenceException {
        return select(filiale, null);
    }

    @Override
    public List<OrdineDiLavoroSpedizioneDTO> select(FilialeDTO filiale, String stato) throws PersistenceException {
        return select(filiale, stato, null);
    }

    public List<OrdineDiLavoroSpedizioneDTO> select(FilialeDTO filiale, String stato, SpedizioneDTO spedizione) throws PersistenceException {
        String query = "SELECT * FROM OrdineDiLavoroSpedizione WHERE 1=1";
        if(filiale != null)
            query += " AND idFiliale = " + filiale.getId();
        if(stato != null)
            query += " AND stato = '" + stato + "'";
        if(spedizione != null)
            query += " AND idSpedizione = " + spedizione.getId();

        Statement statement = null;
        ResultSet resultSet = null;

        List<OrdineDiLavoroSpedizioneDTO> listaOrdini = new LinkedList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if(filiale != null)
                while (resultSet.next())
                    listaOrdini.add(getByResultSet(resultSet, filiale));
            else
                while (resultSet.next())
                    listaOrdini.add(getByResultSet(resultSet));

            return listaOrdini;
        }
        catch(SQLException sqe) {
            sqe.printStackTrace();
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
            sqe.printStackTrace();
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
            sqe.printStackTrace();
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


    public int getCount(FilialeDTO filiale, SpedizioneDTO spedizione, Boolean in, Boolean not, String stato) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            String query = "SELECT COUNT(*) FROM OrdineDiLavoroSpedizione WHERE 1=1";

            if(filiale != null)
                query += " AND idFiliale = " + filiale.getId();
            if(spedizione != null)
                query += " AND idSpedizione = " + spedizione.getId();

            if(stato != null && !stato.isEmpty()) {
                if (!in) {
                    query += " AND stato " + (not ? "<>" : "=") + " '" + stato + "'";
                } else {
                    query += " and stato " + (not ? "not" : "") + " in ('" + stato.replace("'", "").replace(",", "','") + "')";
                }
            }

            resultSet = statement.executeQuery(query);

            if(!resultSet.next())
                throw new PersistenceException("Errore nel conteggio degli ordini di lavoro di spedizione");

            return resultSet.getInt(1);
        }
        catch(SQLException sqe) {
            sqe.printStackTrace();
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

            if(ordineDiLavoroSpedizione.getDataInizioPianificazione() != null)
                preparedStatement.setObject(2, ordineDiLavoroSpedizione.getDataInizioPianificazione());
            else
                preparedStatement.setNull(2, Types.DATE);

            if(ordineDiLavoroSpedizione.getDataInizioLavorazione() != null)
                preparedStatement.setObject(3, ordineDiLavoroSpedizione.getDataInizioLavorazione());
            else
                preparedStatement.setNull(3, Types.DATE);

            if(ordineDiLavoroSpedizione.getDataFineLavorazione() != null)
                preparedStatement.setObject(4, ordineDiLavoroSpedizione.getDataFineLavorazione());
            else
                preparedStatement.setNull(4, Types.DATE);

            if(ordineDiLavoroSpedizione.getGruppoCorriere() != null)
                preparedStatement.setLong(5, ordineDiLavoroSpedizione.getGruppoCorriere().getId());
            else
                preparedStatement.setNull(5, Types.BIGINT);

            if(ordineDiLavoroSpedizione.getOperatoreCorriere() != null)
                preparedStatement.setLong(6, ordineDiLavoroSpedizione.getOperatoreCorriere().getId());
            else
                preparedStatement.setNull(6, Types.BIGINT);

            preparedStatement.setLong(7, ordineDiLavoroSpedizione.getFiliale().getId());
            preparedStatement.setLong(8, ordineDiLavoroSpedizione.getSpedizione().getId());

            if(ordineDiLavoroSpedizione.getNoteAggiuntiveOperatore() != null)
                preparedStatement.setString(9, ordineDiLavoroSpedizione.getNoteAggiuntiveOperatore());
            else
                preparedStatement.setNull(9, Types.VARCHAR);

            if(ordineDiLavoroSpedizione.getMezzoDiTrasporto() != null)
                preparedStatement.setLong(10, ordineDiLavoroSpedizione.getMezzoDiTrasporto().getId());
            else
                preparedStatement.setNull(10, Types.BIGINT);

            preparedStatement.setLong(11, ordineDiLavoroSpedizione.getId());

            preparedStatement.executeUpdate();
        }
        catch (SQLException sqe) {
            sqe.printStackTrace();
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
            sqe.printStackTrace();
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

    @Override
    public void genera(SpedizioneDTO spedizione) throws PersistenceException{
        CallableStatement statement = null;

        try {
            statement = connection.prepareCall("{ call CreaOrdineDiSpedizione( ? ) }");
            statement.setLong(1, spedizione.getId());

            statement.execute();
        }
        catch(SQLException sqe) {
            sqe.printStackTrace();
            throw new PersistenceException(sqe.getMessage());
        }
        finally {
            //libero le risorse

            try {
                if(statement != null)
                    statement.close();
            }
            catch(SQLException sqe) {
                //non faccio niente
            }
        }
    }

    @Override
    public List<OrdineDiLavoroSpedizioneDTO> select(SpedizioneDTO spedizione) throws PersistenceException {
        return select(null, null, spedizione);
    }
}
