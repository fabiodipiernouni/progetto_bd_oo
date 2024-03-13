package org.unina.uninadelivery.entity.orgdomain;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class GruppoCorriereDTO {
    private long id;

    @NonNull
    private String nome;

    @NonNull
    private String codiceCorriere;

    private int numeroDipendenti;

    @NonNull
    private FilialeDTO filiale;
}