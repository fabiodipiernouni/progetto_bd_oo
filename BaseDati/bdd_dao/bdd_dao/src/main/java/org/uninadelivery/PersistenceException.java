package org.uninadelivery;

/**
 * PersistenceException estende la libreria standard RunTimeException. Questa eccezione
 * viene lanciata da una qualsiasi classe DAO
 */

public class PersistenceException extends Exception {
    //estendo Exception e non RuntimeException perché voglio che sia checked


    /**
     * Costruttore
     * @param errorMessage    stringa che spiega qual è la condizione dell'eccezione
     */
    public PersistenceException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Costruttore (in questo caso non c'è nessuna stringa che spiega qual è la condizione dell'eccezione)
     */
    public PersistenceException() {
        super();
    }

}
