package org.unina.uninadelivery.entity.customerdomain;

import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class ClienteDTO {

    private long id;

    @NonNull
    private String nome;

    @NonNull
    private String cognome;

    private String ragioneSociale;

    @NonNull
    private String email;

    private String codiceFiscale;

    private String partitaIVA;

    /*
    Avremmo potuto fare una gerarchia tra Cliente Aziendale e ClientePrivato,
    ma per mantenere un codice più semplice da gestire e siccome non ci serviva
    realmente questa distinzione abbiamo deciso di non farlo
    */

    //TODO: dovrei mettere anche nel costruttore il controllo: codiceFiscale != null XOR (ragioneSociale != NULL && partitaIVA != NULL) ???


}
