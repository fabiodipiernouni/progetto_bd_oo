package org.unina.uninadelivery.entity.orgdomain;

import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class MezzoDiTrasportoDTO {

    private long id;

    @NonNull
    private String targa;

    @NonNull
    private String tipo;

    private float PesoTrasportabile;

    @NonNull
    private GruppoCorriereDTO gruppoCorriere;
}
