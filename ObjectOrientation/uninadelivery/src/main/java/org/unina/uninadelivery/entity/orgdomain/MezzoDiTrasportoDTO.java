package org.unina.uninadelivery.entity.orgdomain;

import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class MezzoDiTrasportoDTO {

    private long id;

    @NonNull
    private String targa;

    @NonNull
    private String tipo;

    @NonNull
    private GruppoCorriereDTO gruppoCorriere;
}
