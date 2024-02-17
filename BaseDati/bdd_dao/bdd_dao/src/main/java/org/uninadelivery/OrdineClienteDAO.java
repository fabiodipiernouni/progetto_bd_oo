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

    public void insert(OrdineCliente ordineCliente) throws PersistenceException;

    public void delete(OrdineCliente ordineCliente) throws PersistenceException;

    public void update(OrdineCliente ordineCliente) throws PersistenceException;

    public List<OrdineCliente> selectByCliente(Cliente cliente) throws PersistenceException;

    public List<OrdineCliente> selectByIntervalloDiTempo(LocalDate dataInizio, LocalDate dataFine) throws PersistenceException;

    public int getNumeroMedioDiOrdiniByMese(YearMonth mese) throws PersistenceException;

    public OrdineCliente selectOrdineMaxQuantitaInMese(YearMonth mese) throws PersistenceException;

    public OrdineCliente selectOrdineMinQuantitaInMese(YearMonth mese) throws PersistenceException;

}
