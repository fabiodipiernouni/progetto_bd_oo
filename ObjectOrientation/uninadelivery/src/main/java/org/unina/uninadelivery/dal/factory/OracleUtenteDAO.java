package org.unina.uninadelivery.dal.factory;

import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.appdomain.UtenteDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class OracleUtenteDAO implements UtenteDAO {
    private final Connection connection = DatabaseSingleton.getInstance().connect();

    @Override
    public List<String> getFunzioniByUtente(long idUtente) throws PersistenceException {
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
                }

        }

    }


    @Override
    public UtenteDTO selectByUsernamePassword(String usernameIn, String passwordIn) throws PersistenceException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

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

            if(!resultSet.next())
                throw new PersistenceException("Errore in OracleUtenteDAO: non ho ottenuto il risultato");

            id = resultSet.getLong("id");
            username = resultSet.getString("username");
            password = resultSet.getString("password");

            matricolaUnina = resultSet.getString("matricolaUnina");
            if(resultSet.wasNull())
                matricolaUnina = null;

            profilo = resultSet.getString("profilo");

            Long idGruppoCorriere = resultSet.getLong("idGruppoCorriere");
            if(resultSet.wasNull())
                idGruppoCorriere = null;

            idFilialeOperatore = resultSet.getLong("idFilialeOperatore");
            if(resultSet.wasNull())
                idFilialeOperatore = null;

            funzioni = getFunzioniByUtente(id);


            if(profilo.equals("Operatore"))
                return new OperatoreFilialeDTO(
                        id,
                        username,
                        password,
                        matricolaUnina,
                        profilo,
                        funzioni,
                        new OracleFilialeDAO().selectById(idFilialeOperatore)
                );

            if(profilo.equals("OperatoreCorriere"))
                return new OperatoreCorriereDTO(
                        id,
                        username,
                        password,
                        matricolaUnina,
                        profilo,
                        funzioni,
                        //new OracleGruppoCorriereDAO().selectById(idGruppoCorriere)
                        null
                );


            throw new PersistenceException("Errore in OracleUtenteDAO: profilo non riconosciuto"

            );


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