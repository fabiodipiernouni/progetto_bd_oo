package org.unina.uninadelivery.dal.factory.appdomain;

import org.unina.uninadelivery.dal.exception.ConsistencyException;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.DatabaseSingleton;

import org.unina.uninadelivery.dal.factory.orgdomain.FactoryOrgDomain;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class OracleUtenteDAO implements UtenteDAO {
    private final Connection connection = DatabaseSingleton.getInstance().connect();

    private UtenteDTO getByResultSet(ResultSet resultSet) throws SQLException, PersistenceException {

        UtenteDTO utente;

        long id = resultSet.getLong("id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");

        String matricolaUnina = resultSet.getString("matricolaUnina");
        if (resultSet.wasNull())
            matricolaUnina = null;

        String profilo = resultSet.getString("profilo");

        //Long e non long perché potrebbe essere null

        Long idGruppoCorriere = resultSet.getLong("idGruppoCorriere");
        if (resultSet.wasNull())
            idGruppoCorriere = null;


        Long idFilialeOperatore = resultSet.getLong("idFilialeOperatore");
        if (resultSet.wasNull())
            idFilialeOperatore = null;

        List<String> funzioni = selectFunzioni(id);

        switch (profilo) {
            case "Operatore":
                if(idFilialeOperatore == null)
                    throw new ConsistencyException("idFilialeOperatore non può essere null");

                Optional<FilialeDTO> filiale = FactoryOrgDomain.buildFilialeDAO().select(idFilialeOperatore);

                if (filiale.isEmpty())
                    throw new ConsistencyException("filiale non trovata");

                utente = new OperatoreFilialeDTO(
                        id,
                        username,
                        password,
                        matricolaUnina,
                        profilo,
                        funzioni,
                        filiale.get()
                );
                break;
            case "OperatoreCorriere":
                if(idGruppoCorriere == null)
                    throw new ConsistencyException("idGruppoCorriere non può essere null");


                Optional<GruppoCorriereDTO> gruppoCorriere = FactoryOrgDomain.buildGruppoCorriereDAO().select(idGruppoCorriere);

                if (gruppoCorriere.isEmpty())
                    throw new ConsistencyException("gruppo corriere non trovato");

                utente = new OperatoreCorriereDTO(
                        id,
                        username,
                        password,
                        matricolaUnina,
                        profilo,
                        funzioni,
                        gruppoCorriere.get()
                );
                break;

            //TODO: gestire altri profili

            default:
                throw new ConsistencyException("profilo non riconosciuto");
        }

        return utente;

    }


    public Optional<UtenteDTO> select(long id) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;
        Optional<UtenteDTO> utente = Optional.empty();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("""
                SELECT Utente.*, Profilo.profilo 
                FROM Utente
                LEFT JOIN Profilo ON Utente.idProfilo = Profilo.id
                WHERE Utente.id = """ + id);

            if(resultSet.next())
                utente = Optional.of(getByResultSet(resultSet));

            return utente;

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
                //non faccio nulla
            }
        }

    }

    public List<String> selectFunzioni(Long idUtente) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        List<String> funzioni = new ArrayList<String>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("""
                SELECT distinct funzione
                FROM Utente
                JOIN Profilo ON Utente.idProfilo = Profilo.id
                JOIN ProfiloFunzione ON Profilo.id = ProfiloFunzione.idProfilo
                JOIN Funzione ON ProfiloFunzione.idFunzione = Funzione.id
                WHERE Utente.id = """ + idUtente);


                while(resultSet.next())
                    funzioni.add(resultSet.getString("funzione"));

                return funzioni;
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
    public Optional<UtenteDTO> select(String usernameIn, String passwordIn) throws PersistenceException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Optional<UtenteDTO> utente = Optional.empty();



        try {

            //uso un PreparedStatement perché la password potrebbe contenere caratteri tipo le virgolette o l'apostrofo che causerebbero problemi nella query
            preparedStatement = connection.prepareStatement("""
                    SELECT Utente.*, Profilo.profilo 
                    FROM Utente
                    LEFT JOIN Profilo ON Utente.idProfilo = Profilo.id
                    WHERE LOWER(username) = LOWER(?) AND password = ?
                """);

            preparedStatement.setString(1, usernameIn);
            preparedStatement.setString(2, passwordIn);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
                utente = Optional.of(getByResultSet(resultSet));

            return utente;

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

    @Override
    public void closeConnection() {
        try {
            connection.close();
        }
        catch(Exception ex) {
            //non faccio nulla
        }
    }
}