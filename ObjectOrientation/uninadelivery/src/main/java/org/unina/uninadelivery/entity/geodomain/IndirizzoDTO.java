package org.unina.uninadelivery.entity.geodomain;

import lombok.*;

/**
 * Indirizzo rappresenta un indirizzo
 */

@AllArgsConstructor
@ToString
@Getter
@Setter
public class IndirizzoDTO {

    private long id;

    @NonNull
    private String nome;

    @NonNull
    private String cognome;

    @NonNull
    private String cf_pIVA;

    @NonNull
    private String indirizzo_1;

    private String indirizzo_2;

    @NonNull
    private String CAP;

    @NonNull
    private String comune;

    private String noteAggiuntive;
}
