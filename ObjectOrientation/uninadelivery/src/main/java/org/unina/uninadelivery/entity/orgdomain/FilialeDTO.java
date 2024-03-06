package org.unina.uninadelivery.entity.orgdomain;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
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