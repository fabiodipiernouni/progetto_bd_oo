package org.unina.uninadelivery.dal.factory.shipmentdomain;

import org.unina.uninadelivery.dal.exception.ConsistencyException;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.DatabaseSingleton;
import org.unina.uninadelivery.dal.factory.appdomain.FactoryAppDomain;
import org.unina.uninadelivery.dal.factory.geodomain.FactoryGeoDomain;
import org.unina.uninadelivery.dal.factory.orgdomain.FactoryOrgDomain;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.entity.geodomain.IndirizzoDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;
import org.unina.uninadelivery.entity.orgdomain.MagazzinoDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroPackagingDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

class OracleOrdineDiLavoroPackagingDAO implements OrdineDiLavoroPackagingDAO {

    private final Connection connection = DatabaseSingleton.getInstance().connect();

    private OrdineDiLavoroPackagingDTO getByResultSet(ResultSet resultSet, FilialeDTO filiale, GruppoCorriereDTO gruppoCorriere, OperatoreCorriereDTO operatoreCorriere) throws SQLException, PersistenceException {
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

        String noteAggiuntiveCliente = resultSet.getString("noteAggiuntiveCliente");
        if(resultSet.wasNull())
            noteAggiuntiveCliente = null;

        String noteAggiuntiveOperatore = resultSet.getString("noteAggiuntiveOperatore");
        if(resultSet.wasNull())
            noteAggiuntiveOperatore = null;

        long idMagazzino = resultSet.getLong("idMagazzino");
        Optional<MagazzinoDTO> magazzino = FactoryOrgDomain.buildMagazzinoDAO().select(idMagazzino);
        if(magazzino.isEmpty())
            throw new ConsistencyException("Magazzino non trovato");

        long idIndirizzoSpedizione = resultSet.getLong("idIndirizzoSpedizione");
        Optional<IndirizzoDTO> indirizzoSpedizione = FactoryGeoDomain.buildIndirizzoDAO().select(idIndirizzoSpedizione);
        if(indirizzoSpedizione.isEmpty())
            throw new ConsistencyException("Indirizzo di spedizione non trovato");

        return new OrdineDiLavoroPackagingDTO(id, dataCreazione, dataInizioPianificazione, dataInizioLavorazione, dataFineLavorazione, gruppoCorriere, operatoreCorriere, filiale, spedizione.get(), stato, noteAggiuntiveCliente, noteAggiuntiveOperatore, magazzino.get(), indirizzoSpedizione.get());
    }

    private OrdineDiLavoroPackagingDTO getByResultSet(ResultSet resultSet) throws PersistenceException, SQLException {

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

    private OrdineDiLavoroPackagingDTO getByResultSet(ResultSet resultSet, FilialeDTO filiale) throws SQLException, PersistenceException {

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

    private OrdineDiLavoroPackagingDTO getByResultSet(ResultSet resultSet, GruppoCorriereDTO gruppoCorriere) throws SQLException, PersistenceException {


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

    private OrdineDiLavoroPackagingDTO getByResultSet(ResultSet resultSet, OperatoreCorriereDTO operatoreCorriere) throws SQLException, PersistenceException {


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

    public List<OrdineDiLavoroPackagingDTO> select(FilialeDTO filiale, String stato) throws PersistenceException {
        String query = "SELECT * FROM OrdineDiLavoroPackaging WHERE 1=1";

        if(filiale != null)
            query += " AND idFiliale = " + filiale.getId();

        if(stato != null)
            query += " AND stato = '" + stato + "'";

        Statement statement = null;
        ResultSet resultSet = null;

        List<OrdineDiLavoroPackagingDTO> listaOrdini = new LinkedList<OrdineDiLavoroPackagingDTO>();

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

    public OrdineDiLavoroPackagingDTO select(MagazzinoDTO magazzinoDTO, SpedizioneDTO spedizioneDTO) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM OrdineDiLavoroPackaging WHERE idMagazzino = " + magazzinoDTO.getId() + " AND idSpedizione = " + spedizioneDTO.getId());

            if(!resultSet.next())
                return null;

            return getByResultSet(resultSet);
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

    public List<OrdineDiLavoroPackagingDTO> select() throws PersistenceException {
        return select((FilialeDTO) null, null);
    }

    public List<OrdineDiLavoroPackagingDTO> select(FilialeDTO filiale) throws PersistenceException {
        return select(filiale, null);
    }


    /**
     * Metodo che restituisce la lista degli ordini di lavoro di packaging relativi ad una filiale
     * @param operatoreFiliale l'operatore di filiale
     * @return la lista degli ordini di lavoro di packaging relativi ad una filiale
     * @throws PersistenceException se si verifica una eccezione a livello di persistenza
     */
    public List<OrdineDiLavoroPackagingDTO> select(OperatoreFilialeDTO operatoreFiliale) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        List<OrdineDiLavoroPackagingDTO> listaOrdini = new LinkedList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("""
            SELECT OrdineDiLavoroPackaging.* 
            FROM OrdineDiLavoroPackaging 
            JOIN Spedizione
            ON OrdineDiLavoroPackaging.idSpedizione = Spedizione.id
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

    public List<OrdineDiLavoroPackagingDTO> select(GruppoCorriereDTO gruppoCorriere) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        List<OrdineDiLavoroPackagingDTO> listaOrdini = new LinkedList<OrdineDiLavoroPackagingDTO>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM OrdineDiLavoroPackaging WHERE idGruppoCorriere = " + gruppoCorriere.getId());

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


    public List<OrdineDiLavoroPackagingDTO> select(OperatoreCorriereDTO operatoreCorriere) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        List<OrdineDiLavoroPackagingDTO> listaOrdini = new LinkedList<OrdineDiLavoroPackagingDTO>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM OrdineDiLavoroPackaging WHERE idOperatoreCorriere = " + operatoreCorriere.getId());

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
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM OrdineDiLavoroPackaging WHERE idFiliale = " + filiale.getId() + " AND stato = '" + stato + "'");

            if(!resultSet.next())
                throw new PersistenceException("Errore nel conteggio degli ordini di lavoro di packaging");

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

    public int getCountLavoratiNonSpediti(FilialeDTO filiale) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("""
            SELECT COUNT(*)
            FROM OrdineDiLavoroPackaging odp
            JOIN Pacco p
            ON odp.IdSpedizione = p.IdSpedizione
            WHERE 
                odp.Stato = 'Lavorato' and 
                not exists(
                    SELECT distinct 1
                    FROM OrdineDiLavoroSpedizionePacchi 
                    WHERE OrdineDiLavoroSpedizionePacchi.IdPacco = p.Id
                )
                AND idFiliale = """ + filiale.getId());
            //TODO: controllare la correttezza della query


            if(!resultSet.next())
                throw new PersistenceException("Errore nel conteggio degli ordini di lavoro di packaging");

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

    public void update(OrdineDiLavoroPackagingDTO ordineDiLavoroPackaging) throws PersistenceException {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("""
            UPDATE OrdineDiLavoroPackaging
            SET dataCreazione = ?,
                dataInizioPianificazione = ?,
                dataInizioLavorazione = ?,
                dataFineLavorazione = ?,
                idGruppoCorriere = ?,
                idOperatoreCorriere = ?,
                idFiliale = ?,
                idSpedizione = ?,
                noteAggiuntiveCliente = ?,
                noteAggiuntiveOperatore = ?,
                idMagazzino = ?,
                idIndirizzoSpedizione = ?
            WHERE id = ?""");

            preparedStatement.setObject(1, ordineDiLavoroPackaging.getDataCreazione());
            preparedStatement.setObject(2, ordineDiLavoroPackaging.getDataInizioPianificazione());
            preparedStatement.setObject(3, ordineDiLavoroPackaging.getDataInizioLavorazione());
            preparedStatement.setObject(4, ordineDiLavoroPackaging.getDataFineLavorazione());
            preparedStatement.setLong(5, ordineDiLavoroPackaging.getGruppoCorriere().getId());
            preparedStatement.setLong(6, ordineDiLavoroPackaging.getOperatoreCorriere().getId());
            preparedStatement.setLong(7, ordineDiLavoroPackaging.getFiliale().getId());
            preparedStatement.setLong(8, ordineDiLavoroPackaging.getSpedizione().getId());
            preparedStatement.setString(9, ordineDiLavoroPackaging.getNoteAggiuntiveCliente());
            preparedStatement.setString(10, ordineDiLavoroPackaging.getNoteAggiuntiveOperatore());
            preparedStatement.setLong(11, ordineDiLavoroPackaging.getMagazzino().getId());
            preparedStatement.setLong(12, ordineDiLavoroPackaging.getIndirizzoSpedizione().getId());
            preparedStatement.setLong(13, ordineDiLavoroPackaging.getId());

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


    @Override
    public void genera(OrdineClienteDTO ordineCliente, FilialeDTO filiale) throws PersistenceException{
        CallableStatement statement = null;

        try {
            statement = connection.prepareCall("{ call CREAORDINIPACKAGINGBYIDORDINE( ?, ? ) }");
            statement.setLong(1, ordineCliente.getId());
            statement.setLong(2, filiale.getId());

            statement.execute();
        }
        catch(SQLException sqe) {
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
    public List<OrdineDiLavoroPackagingDTO> select(SpedizioneDTO spedizione) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        List<OrdineDiLavoroPackagingDTO> listaOrdini = new LinkedList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM OrdineDiLavoroPackaging WHERE idSpedizione = " + spedizione.getId());

            while (resultSet.next())
                listaOrdini.add(getByResultSet(resultSet));

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

    @Override
    public Optional<OrdineDiLavoroPackagingDTO> select(long idOrdineLavoroOrigine) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM OrdineDiLavoroPackaging WHERE id = " + idOrdineLavoroOrigine);

            if(!resultSet.next())
                return Optional.empty();

            return Optional.of(getByResultSet(resultSet));
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
}
