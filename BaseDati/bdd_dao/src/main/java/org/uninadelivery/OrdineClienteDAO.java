package org.uninadelivery;

import java.time.LocalDate;
import java.time.YearMonth;

import java.util.List;

public interface OrdineClienteDAO {

    /**
     * Seleziona un ordineCliente tramite il suo id
     * @param id id dell'ordineCliente
     */
    public OrdineCliente selectById(long id) throws PersistenceException;

    /**
     * Inserisce un ordineCliente
     * @param ordineCliente ordineCliente da inserire
     */
    public void insert(OrdineCliente ordineCliente) throws PersistenceException;

    /**
     * Elimina un ordineCliente
     * @param ordineCliente ordineCliente da eliminare
     */
    public void delete(OrdineCliente ordineCliente) throws PersistenceException;

    /**
     * Aggiorna un ordineCliente
     * @param ordineCliente ordineCliente da aggiornare
     */
    public void update(OrdineCliente ordineCliente) throws PersistenceException;

    /**
     * Seleziona tutti gli ordiniCliente di un cliente
     * @param cliente cliente di cui si vogliono selezionare gli ordiniCliente
     */
    public List<OrdineCliente> selectByCliente(Cliente cliente) throws PersistenceException;

    /**
     * Seleziona tutti gli ordiniCliente in un periodo di riferimento
     * @param dataInizio data di inizio del periodo di riferimento
     * @param dataFine data di fine del periodo di riferimento
     */
    public List<OrdineCliente> selectByIntervalloDiTempo(LocalDate dataInizio, LocalDate dataFine) throws PersistenceException;

    /**
     * Seleziona il numero medio di ordini in un mese
     * @param mese mese di riferimento
     */
    public int getNumeroMedioDiOrdiniByMese(YearMonth mese) throws PersistenceException;

    /**
     * Seleziona l'ordine con maggior numero di prodotti in un mese
     * @param mese mese di riferimento
     */
    public OrdineCliente selectOrdineMaxQuantitaInMese(YearMonth mese) throws PersistenceException;

    /**
     * Seleziona l'ordine con minor numero di prodotti in un mese
     * @param mese mese di riferimento
     */
    public OrdineCliente selectOrdineMinQuantitaInMese(YearMonth mese) throws PersistenceException;

}
