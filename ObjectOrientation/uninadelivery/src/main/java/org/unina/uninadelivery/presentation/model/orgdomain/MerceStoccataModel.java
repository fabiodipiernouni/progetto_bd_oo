package org.unina.uninadelivery.presentation.model.orgdomain;

import lombok.Getter;
import org.unina.uninadelivery.entity.orgdomain.MerceStoccataDTO;

@Getter
public class MerceStoccataModel {
    private String codiceEAN;
    private String nome;
    private int quantita;
    private int quantitaPrenotata;
    private int quantitaDisponibile;
    private char settoreMagazzino;

    public MerceStoccataModel(MerceStoccataDTO merceStoccata) {
        this.codiceEAN = merceStoccata.getProdotto().getCodiceEAN();
        this.nome = merceStoccata.getProdotto().getNome();
        this.quantita = merceStoccata.getQuantitaReale();
        this.quantitaPrenotata = merceStoccata.getQuantitaPrenotata();
        this.quantitaDisponibile = merceStoccata.getQuantitaDisponibile();
        this.settoreMagazzino = merceStoccata.getSettoreMagazzino() == null ? Character.MIN_VALUE : merceStoccata.getSettoreMagazzino();
    }

    public String getSettoreMagazzinoAsString() {
        return settoreMagazzino == Character.MIN_VALUE ? "N/A" : String.valueOf(settoreMagazzino);
    }

}