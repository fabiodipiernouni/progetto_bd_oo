package org.unina.uninadelivery33.entity.orgdomain;

import lombok.Data;

@Data
public class GruppoCorriereDto {
    private Long id;
    private String nome;
    private String codiceCorriere;
    private int numeroDipendenti;
    private FilialeDto filiale;
}
