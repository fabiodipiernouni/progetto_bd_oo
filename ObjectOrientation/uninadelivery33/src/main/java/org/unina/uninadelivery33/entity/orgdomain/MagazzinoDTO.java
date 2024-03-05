package org.unina.uninadelivery33.entity.orgdomain;

import lombok.*;
import org.unina.uninadelivery33.entity.geodomain.IndirizzoDTO;

@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class MagazzinoDTO {
    @NonNull
    long id;

    @NonNull
    String nome;

    @NonNull
    IndirizzoDTO indirizzo;

    @NonNull
    FilialeDTO filiale;
}
