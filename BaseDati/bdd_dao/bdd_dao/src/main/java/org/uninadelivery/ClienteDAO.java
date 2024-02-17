package org.uninadelivery;

public interface ClienteDAO {
    /**
     * Seleziona un cliente tramite il suo id
     * @param id id del cliente
     */
    public Cliente selectById(long id) throws PersistenceException;

    //Non vengono implementati altri metodi perché l'obbiettivo è mostrare il funzionamento di OrdineClienteDAO.

}
