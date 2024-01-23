import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection
{
    // istanza statica e privata di questa classe
    private static DBConnection dbcon = null;
    // istanza privata della connessione ad SQL
    private Connection conn = null;

    // costruttore private
    private DBConnection(){}

    // metodo pubblico per ottenere una istanza della classe privata
    public static DBConnection getDBConnection()
    {   // se la classe connessione è nulla, la crea
        if (dbcon == null) {
            dbcon = new DBConnection();
        }
        // e la restituisce
        return dbcon;
    }

    // metodo pubblico per ottenere la connessione
    public Connection getConnection()
    {
        try
        {   // se la connessione non esiste oppure è stata chiusa
            if(conn==null || conn.isClosed()) {
                // registra il driver
                Class.forName("oracle.jdbc.driver.OracleDriver");
                // chiama il DriverManager e chiedi la connessione
                conn = DriverManager.getConnection("jdbc:oracle:thin:@bpk3181nciydujmq_medium", "uninadev", "Unidevtest4010$");
            }
        } 
        catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return conn;
    }

/*
    // metodo pubblico per ottenere la connessione, in questo caso passo anche il nome dello schema a cui connettermi
    public Connection getConnectionBySchema(String schema_name)
    {
        String pwd = null;
        BufferedReader b = null;
        if (Objects.equals(schema_name, ""))
            throw new RuntimeException("Schema name is empty");
        try
        {   // se la connessione non esiste oppure è stata chiusa
            if(conn==null || conn.isClosed())
            {   //legge la pwd dal file
                b = new BufferedReader(new FileReader(new File("src/pwdfile")));
                pwd = b.readLine();
                // registra il driver
                Class.forName("org.postgresql.Driver");
                // chiama il DriverManager e chiedi la connessione
                String s_url="jdbc:postgresql://localhost:5432/db_bdd_unina?currentSchema="+schema_name;
                System.out.println("surl" + s_url);
                conn = DriverManager.getConnection(s_url, "postgres", pwd);

            }
        } catch (SQLException | ClassNotFoundException | IOException throwables) {
            throwables.printStackTrace();
        }

        return conn;
    }
*/
}
