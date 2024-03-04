package org.unina.uninadelivery33.dal.factory;

import org.unina.uninadelivery33.dal.exception.ConsistencyException;
import org.unina.uninadelivery33.dal.exception.PersistenceException;
import org.unina.uninadelivery33.entity.geodomain.IndirizzoDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

class OracleIndirizzoDAO implements IndirizzoDAO {
    private final Connection connection = DatabaseSingleton.getInstance().connect();

    public Optional<IndirizzoDTO> selectById(long id) throws PersistenceException {
        Optional<IndirizzoDTO> indirizzo = Optional.empty();

        Statement statement = null;
        ResultSet resultSet = null;

        String nome;
        String cognome;
        String cf_pIVA;
        String indirizzo_1;
        String indirizzo_2;
        String CAP;
        String comune;
        String noteAggiuntive;

        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery("""
                SELECT
                    Indirizzo.*,
                    Comune.nome AS nomeComune
                FROM Indirizzo
                LEFT JOIN Comune ON Indirizzo.idComune = Comune.id
                WHERE Indirizzo.id= """ + id);

            if(resultSet.next()) {

                //sono sicuro che otterrò una sola tupla, non metto in un while

                nome = resultSet.getString("nome");
                cognome = resultSet.getString("cognome");
                cf_pIVA = resultSet.getString("cf_pIVA");
                indirizzo_1 = resultSet.getString("indirizzo_1");

                indirizzo_2 = resultSet.getString("indirizzo_2");
                if (resultSet.wasNull())
                    indirizzo_2 = null;

                CAP = resultSet.getString("CAP");

                comune = resultSet.getString("nomeComune");
                if(resultSet.wasNull())
                    throw new ConsistencyException("Comune non trovato");


                noteAggiuntive = resultSet.getString("noteAggiuntive");
                if (resultSet.wasNull())
                    noteAggiuntive = null;

                indirizzo = Optional.of(new IndirizzoDTO(
                        id,
                        nome,
                        cognome,
                        cf_pIVA,
                        indirizzo_1,
                        indirizzo_2,
                        CAP,
                        comune,
                        noteAggiuntive
                ));
            }

            return indirizzo;

        }
        catch(SQLException throwables) {
            throw new PersistenceException("Errore in OracleIndirizzoDAO: " + throwables.getMessage());
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
                throw new PersistenceException("Errore in OracleIndirizzoDAO: " + sqe.getMessage());
            }
        }

    }
}
