package org.unina.uninadelivery33.entity.orgdomain;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class GruppoCorriereDTO {
    @NonNull
    private long id;
    @NonNull
    private String nome;
    @NonNull
    private String codiceCorriere;
    @NonNull
    private int numeroDipendenti;
    @NonNull
    private FilialeDTO filiale;
}