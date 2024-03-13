package org.unina.uninadelivery.entity.orgdomain;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class FilialeDTO {
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