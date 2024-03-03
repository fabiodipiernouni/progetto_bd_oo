package org.uninadelivery.entity.orgdomain;

import lombok.Data;

@Data
public class FilialeDto {
    private Long id;
    private String orgCountry;
    private String orgRagioneSociale;
    private String orgPartitaIva;
    private String nome;
}
