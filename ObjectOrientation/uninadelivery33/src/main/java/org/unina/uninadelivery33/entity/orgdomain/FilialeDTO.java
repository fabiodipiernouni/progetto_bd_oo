package org.unina.uninadelivery33.entity.orgdomain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FilialeDTO {
    @NonNull
    private long id;
    @NonNull
    private String nome;
    @NonNull
    private String orgCountry;
    @NonNull
    private String orgRagioneSociale;
    @NonNull
    private String orgPartitaIva;
}