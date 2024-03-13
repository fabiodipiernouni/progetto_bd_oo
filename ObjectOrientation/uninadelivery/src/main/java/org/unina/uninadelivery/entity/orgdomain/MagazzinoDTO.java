package org.unina.uninadelivery.entity.orgdomain;

import lombok.*;
import org.unina.uninadelivery.entity.geodomain.IndirizzoDTO;

@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class MagazzinoDTO {

    long id;

    @NonNull
    String nome;

    @NonNull
    IndirizzoDTO indirizzo;

    @NonNull
    FilialeDTO filiale;

}
