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
import org.unina.uninadelivery.entity.geodomain.IndirizzoDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;
import org.unina.uninadelivery.entity.orgdomain.MagazzinoDTO;
import org.unina.uninadelivery.entity.shipmentdomain.OrdineDiLavoroPackagingDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public List<OrdineDiLavoroPackagingDTO> select(FilialeDTO filiale) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        List<OrdineDiLavoroPackagingDTO> listaOrdini = new LinkedList<OrdineDiLavoroPackagingDTO>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM OrdineDiLavoroPackaging WHERE idFiliale = " + filiale.getId());

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


    public List<OrdineDiLavoroPackagingDTO> select(OperatoreFilialeDTO operatoreFiliale) throws PersistenceException {
        return null; //TODO
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
        return 0;//TODO
    }


    public void update(OrdineDiLavoroPackagingDTO ordineDiLavoroPackaging) throws PersistenceException {
//TODO
    }
}
