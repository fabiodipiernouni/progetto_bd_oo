import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SpedizioneDAOOracle implements SpedizioneDAO {

    DBConnection Connection = null;

    public SpedizioneDAOOracle() throws Exception {
        //TODO: Che tipo di exception?

        Connection = DBConnection.getDBConnection();

        if (Connection == null)
            throw new Exception("Connessione NON riuscita!");

    }

    public Spedizione getSpedizione(int id) {
        Spedizione spedizione = null;
        try {
            CallableStatement cstmt = Connection.getConnection().prepareCall("{call GetSpedizione(?, ?)}");
            cstmt.setInt(1, id);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR); // oracle.jdbc.driver.OracleTypes.CURSOR  
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(2);
            if (rs.next()) {
                spedizione = new Spedizione();
                
                /*
                // Set the properties of the spedizione object using the values from the result set
                spedizione.setId(rs.getInt("ID"));
                spedizione.setNome(rs.getString("NOME"));
                // Set other properties as needed
                */
            }
            rs.close();
            cstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spedizione;
    }

    public List<Spedizione> getSpedizioni() {
        /* esempio dalle slide
        CallableStatement upperFunc = conn.prepareCall("{? = call upper( ? ) }");
        upperFunc.registerOutParameter(1, Types.VARCHAR);
        upperFunc.setString(2, "lowercase to uppercase");
        upperFunc.execute();
        String upperCased = upperFunc.getString(1);
        upperFunc.close();

        */
        return null;

    }
    public boolean updateSpedizione(Spedizione spedizione) {
        return false;
    }
    public boolean deleteSpedizione(int id) {
        return false;
    }
    public boolean insertSpedizione(Spedizione spedizione) {
        return false;
    }

}