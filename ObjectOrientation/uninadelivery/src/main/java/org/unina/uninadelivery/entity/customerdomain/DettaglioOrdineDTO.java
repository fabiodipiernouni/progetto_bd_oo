package org.unina.uninadelivery.entity.customerdomain;

import lombok.*;
import org.unina.uninadelivery.entity.orgdomain.MagazzinoDTO;
import org.unina.uninadelivery.entity.orgdomain.ProdottoDTO;

import java.util.List;

@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class DettaglioOrdineDTO {
    @NonNull
    private long id;

    @NonNull
    private ProdottoDTO prodotto;


    private int quantita;

    @NonNull
    private List<MagazzinoDTO> magazzini;


    public DettaglioOrdineDTO(long id, ProdottoDTO prodotto, int quantita) {
        this.id = id;
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

}
