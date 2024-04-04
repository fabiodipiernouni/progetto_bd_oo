package org.unina.uninadelivery.dal.factory;


import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;


/**
 * Classe per la connessione al database
 */
public class DatabaseSingleton {
    //istanza statica e privata di questa classe
    private static DatabaseSingleton istanza = null;

    //istanza privata della connessione ad SQL
    private Connection connection = null;


    // costruttore privato => non ereditabile
    private DatabaseSingleton(){
        Yaml yaml = new Yaml();

        try (InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("application.yml")) {

            yamlValues = yaml.load(inputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    private Map<String, Object> yamlValues;

    /**
     * Metodo pubblico per ottenere una istanza della classe privata.
     * Abbiamo messo static perché i metodi static possono essere chiamato senza creare un'istanza della classe.
     */
    public static DatabaseSingleton getInstance() {

        //se la classe connessione è nulla, la crea
        if(istanza == null)
            istanza = new DatabaseSingleton();

        //e la restituisce
        return istanza;
    }

    public  Connection getTheConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if(!isConnectionClosed())
                connection.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean isConnectionClosed() {
        try {
            return connection == null || connection.isClosed();
        }
        catch(SQLException e) {
            e.printStackTrace();
            return true;
        }
    }


    /**
     * Metodo pubblico per ottenere la connessione
     */
    public Connection connect() throws PersistenceException {
        try {
            //se la connessione non esiste oppure è stata chiusa
            if(isConnectionClosed()) {
                Map<String, Object> database = (Map<String, Object>) yamlValues.get("database");

                URI res = getClass().getResource("/" + (database.get("wallet"))).toURI();
                String walletPath = new File(res).getAbsolutePath();
                System.setProperty("oracle.net.tns_admin", walletPath);

                /*
                senza la seguente proprietà ottengo M oracle.simplefan.impl.FanManager configure
                SEVERE: attempt to configure ONS in FanManager failed with oracle.ons.NoServersAvailable: Subscription time out
                */
                System.setProperty("oracle.jdbc.fanEnabled", "false");


                //registra il driver
                Class.forName((String) database.get("driver"));

                //chiama il DriverManager e chiedi la connessione
                connection = DriverManager.getConnection(
                        (String)database.get("url"),
                        (String)database.get("user"),
                        (String)database.get("password")
                );

                connection.setSchema((String)database.get("schema"));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e.getMessage());
        }

        return connection;
    }

}
