package org.unina.uninadelivery.entity.customerdomain;

import lombok.*;

@ToString
@Getter
@Setter
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

    public ClienteDTO(long id, @NonNull String nome, @NonNull String cognome, @NonNull String email, @NonNull String codiceFiscale) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.ragioneSociale = null;
        this.email = email;
        this.codiceFiscale = codiceFiscale;
        this.partitaIVA = null;
    }

    public ClienteDTO(long id, @NonNull String nome, @NonNull String cognome, @NonNull String email, @NonNull String ragioneSociale, @NonNull String partitaIVA) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.ragioneSociale = ragioneSociale;
        this.email = email;
        this.codiceFiscale = null;
        this.partitaIVA = partitaIVA;
    }

    public String getIntestazione() {
        if(ragioneSociale != null && ragioneSociale.length()>0)
            return ragioneSociale;

        return nome + " " + cognome;
    }

}
