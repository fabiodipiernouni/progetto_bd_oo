package org.uninadelivery;

public interface ComuneFullDAO {
    /**
     * Seleziona un ComuneFull tramite il suo id
     * @param id id del ComuneFull
     */
    public ComuneFull selectById(long id) throws PersistenceException;

    //Non vengono implementati altri metodi perché l'obbiettivo è mostrare il funzionamento di OrdineClienteDAO.

}
