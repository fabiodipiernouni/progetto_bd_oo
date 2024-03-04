package org.unina.uninadelivery33.dal.factory;

import org.unina.uninadelivery33.dal.exception.ConsistencyException;
import org.unina.uninadelivery33.dal.exception.PersistenceException;
import org.unina.uninadelivery33.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery33.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery33.entity.appdomain.UtenteDTO;
import org.unina.uninadelivery33.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery33.entity.orgdomain.GruppoCorriereDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class OracleUtenteDAO implements UtenteDAO {
    private final Connection connection = DatabaseSingleton.getInstance().connect();

    @Override
    public List<String> getFunzioniByUtente(Long idUtente) throws PersistenceException {
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
                throw new PersistenceException("Errore in OracleUtenteDAO: " + sqe.getMessage());
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
    public Optional<UtenteDTO> selectByUsernamePassword(String usernameIn, String passwordIn) throws PersistenceException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Optional<UtenteDTO> utente = Optional.empty();

        long id;
        String username;
        String password;
        String matricolaUnina;
        String profilo;

        //Long e non long perché potrebbe essere null
        Long idFilialeOperatore;

        List<String> funzioni;

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

            if(resultSet.next()) {

                id = resultSet.getLong("id");
                username = resultSet.getString("username");
                password = resultSet.getString("password");

                matricolaUnina = resultSet.getString("matricolaUnina");
                if (resultSet.wasNull())
                    matricolaUnina = null;

                profilo = resultSet.getString("profilo");

                Long idGruppoCorriere = resultSet.getLong("idGruppoCorriere");
                if (resultSet.wasNull())
                    idGruppoCorriere = null;

                idFilialeOperatore = resultSet.getLong("idFilialeOperatore");
                if (resultSet.wasNull())
                    idFilialeOperatore = null;

                funzioni = getFunzioniByUtente(id);

                switch (profilo) {
                    case "Operatore":
                        if(idFilialeOperatore == null)
                            throw new ConsistencyException("Errore in OracleUtenteDAO: idFilialeOperatore non può essere null");

                        Optional<FilialeDTO> filiale = new OracleFilialeDAO().selectById(idFilialeOperatore);

                        if (filiale.isEmpty())
                            throw new ConsistencyException("Errore in OracleUtenteDAO: filiale non trovata");

                        utente = Optional.of(new OperatoreFilialeDTO(
                                id,
                                username,
                                password,
                                matricolaUnina,
                                profilo,
                                funzioni,
                                filiale.get()
                        ));
                        break;
                    case "OperatoreCorriere":
                        if(idGruppoCorriere == null)
                            throw new ConsistencyException("Errore in OracleUtenteDAO: idGruppoCorriere non può essere null");


                        Optional<GruppoCorriereDTO> gruppoCorriere = new OracleGruppoCorriereDAO().selectById(idGruppoCorriere);

                        if (gruppoCorriere.isEmpty())
                            throw new ConsistencyException("Errore in OracleUtenteDAO: gruppo corriere non trovato");

                        utente = Optional.of(new OperatoreCorriereDTO(
                                id,
                                username,
                                password,
                                matricolaUnina,
                                profilo,
                                funzioni,
                                gruppoCorriere.get()
                        ));
                        break;

                    //TODO: gestire altri profili

                    default:
                        throw new ConsistencyException("Errore in OracleUtenteDAO: profilo non riconosciuto");
                }
            }


            return utente;

        }
        catch(SQLException sqe) {
            throw new PersistenceException("Errore in OracleOrdineClienteDAO: " + sqe.getMessage());
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