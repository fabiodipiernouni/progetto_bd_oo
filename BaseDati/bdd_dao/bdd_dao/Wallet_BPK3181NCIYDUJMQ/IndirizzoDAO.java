package org.uninadelivery;

public interface IndirizzoDAO {
    /**
     * Seleziona un indirizzo tramite il suo id
     * @param id id dell'indirizzo
     */
    public Indirizzo selectById(long id) throws PersistenceException;

    //Non vengono implementati altri metodi perché l'obbiettivo è mostrare il funzionamento di OrdineClienteDAO.
}
