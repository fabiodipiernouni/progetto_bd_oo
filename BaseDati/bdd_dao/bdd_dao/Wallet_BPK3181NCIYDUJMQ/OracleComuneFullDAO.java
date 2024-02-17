package org.uninadelivery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleComuneFullDAO implements ComuneFullDAO {
    private final Connection connection = DatabaseSingleton.getIstance().connect();

    public ComuneFull selectById(long id) throws PersistenceException {
        
        Statement statement = null;
        ResultSet resultSet = null;

        String codiceComuneFormatoNumerico;
        String codiceProvincia;
        String codiceRegione;
        String denominazioneCittaMetropolitana;
        String denominazioneInItaliano;
        String denominazioneProvincia;
        String denominazioneRegione;

        try {
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM ComuneFull WHERE id = " + id);

            if(!resultSet.next())
                throw new PersistenceException("ComuneFull non trovato");

            //sono sicuro che otterrò una sola tupla, non metto in un while

            codiceComuneFormatoNumerico = resultSet.getString("codiceComuneFormatoNumerico");
            codiceProvincia = resultSet.getString("codiceProvincia");
            codiceRegione = resultSet.getString("codiceRegione");

            denominazioneCittaMetropolitana = resultSet.getString("denominazioneCittaMetropolitana");
            if(resultSet.wasNull())
                denominazioneCittaMetropolitana = null;

            denominazioneInItaliano = resultSet.getString("denominazioneInItaliano");

            denominazioneProvincia = resultSet.getString("denominazioneProvincia");
            if(resultSet.wasNull())
                denominazioneProvincia = null;

            denominazioneRegione = resultSet.getString("denominazioneRegione");
        }
        catch(SQLException sqe) {
            throw new PersistenceException("Errore in OracleComuneFullDAO: " + sqe.getMessage());
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
                throw new PersistenceException("Errore in OracleComuneFullDAO: " + sqe.getMessage());
            }
        }

        return new ComuneFull(
                id,
                codiceComuneFormatoNumerico,
                codiceProvincia,
                codiceRegione,
                denominazioneCittaMetropolitana,
                denominazioneInItaliano,
                denominazioneProvincia,
                denominazioneRegione
        );
    }
}
