package org.unina.uninadelivery33.entity.customerdomain;

import lombok.*;
import org.unina.uninadelivery33.entity.orgdomain.MagazzinoDTO;
import org.unina.uninadelivery33.entity.orgdomain.ProdottoDTO;

import java.util.List;

@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class DettaglioOrdineDTO {

    @NonNull
    private ProdottoDTO prodotto;

    @NonNull
    private Integer quantita;

    @NonNull
    private List<MagazzinoDTO> magazzino;

}
