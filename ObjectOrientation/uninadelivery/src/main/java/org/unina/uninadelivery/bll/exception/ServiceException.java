package org.unina.uninadelivery.bll.exception;

/**
 * ServiceException estende la libreria standard RunTimeException. Questa eccezione
 * viene lanciata da una qualsiasi classe Service
 */

public class ServiceException extends Exception {
    //estendo Exception e non RuntimeException perché voglio che sia checked


    /**
     * Costruttore
     * @param errorMessage    stringa che spiega qual è la condizione dell'eccezione
     */
    public ServiceException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Costruttore (in questo caso non c'è nessuna stringa che spiega qual è la condizione dell'eccezione)
     */
    public ServiceException() {
        super();
    }

}
