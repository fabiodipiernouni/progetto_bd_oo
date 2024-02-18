package org.uninadelivery;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;

/**
 * Classe per la connessione al database
 */
public class DatabaseSingleton {
    //istanza statica e privata di questa classe
    private static DatabaseSingleton istanza = null;

    //istanza privata della connessione ad SQL
    private Connection connection = null;

    // costruttore privato che sovrascrive quello predefinito
    private DatabaseSingleton(){}


    /**
     * Metodo pubblico per ottenere una istanza della classe privata.
     * Abbiamo messo static perché i metodi static possono essere chiamato senza creare un'istanza della classe.
     */
    public static DatabaseSingleton getIstance() {

        //se la classe connessione è nulla, la crea
        if(istanza == null)
            istanza = new DatabaseSingleton();

        //e la restituisce
        return istanza;
    }


    /**
     * Metodo pubblico per ottenere la connessione
     */
    public Connection connect() {
        try {

            //se la connessione non esiste oppure è stata chiusa
            if(connection == null || connection.isClosed()) {

                Properties props = new Properties();
                FileInputStream fis = new FileInputStream(
                        System.getProperty("user.dir") + File.separator + "app.properties"
                );

                props.load(fis);


                System.setProperty("oracle.net.tns_admin",
                        System.getProperty("user.dir") + File.separator + props.getProperty("database.wallet")
                );

                /*
                senza la seguente proprietà ottengo M oracle.simplefan.impl.FanManager configure
                SEVERE: attempt to configure ONS in FanManager failed with oracle.ons.NoServersAvailable: Subscription time out
                */
                System.setProperty("oracle.jdbc.fanEnabled", "false");


                //registra il driver
                Class.forName(props.getProperty("database.driver"));

                //chiama il DriverManager e chiedi la connessione
                connection = DriverManager.getConnection(
                        props.getProperty("database.url"),
                        props.getProperty("database.user"),
                        props.getProperty("database.password")
                );

                connection.setSchema(props.getProperty("database.schema"));
            }
        }
        catch(SQLException | ClassNotFoundException | IOException throwables) {
            throwables.printStackTrace();
        }

        return connection;
    }

}
