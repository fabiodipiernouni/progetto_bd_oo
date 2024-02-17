package org.uninadelivery;


import java.time.LocalDate;

/**
 * OrdineCliente rappresenta un ordine effettuato da un cliente
 */

public class OrdineCliente {

    private long id;
    private LocalDate dataOrdine;
    private double importoTotale;
    private LocalDate dataInizioLavorazione;
    private LocalDate dataFineLavorazione;
    private String stato;
    private Cliente cliente;
    private Indirizzo indirizzoFatturazione;
    private Indirizzo indirizzoSpedizione;
    private String numeroOrdine;

    /**
     * Constructor
     * @param id                      id dell'ordine (chiave primaria)
     * @param dataOrdine              data in cui è stato effettuato l'ordine
     * @param importoTotale           importo totale dell'ordine
     * @param dataInizioLavorazione   data in cui è iniziata la lavorazione dell'ordine
     * @param dataFineLavorazione     data in cui è finita la lavorazione dell'ordine
     * @param stato                   stato dell'ordine
     * @param cliente                 cliente che ha effettuato l'ordine
     * @param indirizzoFatturazione   indirizzo di fatturazione dell'ordine
     * @param indirizzoSpedizione     indirizzo di spedizione dell'ordine
     * @param numeroOrdine            numero dell'ordine
     */
    public OrdineCliente(long id, LocalDate dataOrdine, double importoTotale, LocalDate dataInizioLavorazione, LocalDate dataFineLavorazione, String stato, Cliente cliente, Indirizzo indirizzoFatturazione, Indirizzo indirizzoSpedizione, String numeroOrdine) {
        this.id = id;
        this.dataOrdine = dataOrdine;
        this.importoTotale = importoTotale;
        this.dataInizioLavorazione = dataInizioLavorazione;
        this.dataFineLavorazione = dataFineLavorazione;
        this.stato = stato;
        this.cliente = cliente;
        this.indirizzoFatturazione = indirizzoFatturazione;
        this.indirizzoSpedizione = indirizzoSpedizione;
        this.numeroOrdine = numeroOrdine;
    }

    @Override
    public String toString() {
        return "OrdineCliente{" +
                "id=" + id +
                ", dataOrdine=" + dataOrdine +
                ", importoTotale=" + importoTotale +
                ", dataInizioLavorazione=" + dataInizioLavorazione +
                ", dataFineLavorazione=" + dataFineLavorazione +
                ", stato='" + stato + '\'' +
                ", cliente=" + cliente +
                ", indirizzoFatturazione=" + indirizzoFatturazione +
                ", indirizzoSpedizione=" + indirizzoSpedizione +
                ", numeroOrdine='" + numeroOrdine + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public LocalDate getDataOrdine() {
        return dataOrdine;
    }

    public double getImportoTotale() {
        return importoTotale;
    }

    public LocalDate getDataInizioLavorazione() {
        return dataInizioLavorazione;
    }

    public LocalDate getDataFineLavorazione() {
        return dataFineLavorazione;
    }

    public String getStato() {
        return stato;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Indirizzo getIndirizzoFatturazione() {
        return indirizzoFatturazione;
    }

    public Indirizzo getIndirizzoSpedizione() {
        return indirizzoSpedizione;
    }

    public String getNumeroOrdine() {
        return numeroOrdine;
    }
}
