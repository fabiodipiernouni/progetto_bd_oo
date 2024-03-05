package org.unina.uninadelivery33.entity.orgdomain;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
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