package org.unina.uninadelivery.entity.orgdomain;

import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class ProdottoDTO {

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

    private double prezzo;

    private float peso;

    private Float altezza;

    private Float larghezza;

    private Float profondita;

    @NonNull
    private String pericolosita;

    //altezza, larghezza, profondita sono nullable perché potremmo non disporre di tale informazione

}
