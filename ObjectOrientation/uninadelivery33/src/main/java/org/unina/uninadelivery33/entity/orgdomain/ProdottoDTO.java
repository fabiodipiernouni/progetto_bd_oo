package org.unina.uninadelivery33.entity.orgdomain;

import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class ProdottoDTO {

    @NonNull
    private long id;

    @NonNull
    private String codiceEAN;

    @NonNull
    private String nome;

    @NonNull
    private String descrizione;

    @NonNull
    private String URLPhoto;

    @NonNull
    private String tipo;

    @NonNull
    private double prezzo;

    @NonNull
    private float peso;

    private Float altezza;

    private Float larghezza;

    private Float profondita;

    @NonNull
    private String pericolosita;

    //altezza, larghezza, profondita sono nullable perché potremmo non disporre di tale informazione

    //TODO: lista magazzini in cui si trova un prodotto oppure lista magazzini da cui pacco preleva le quantità
}
