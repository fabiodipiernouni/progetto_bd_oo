package org.unina.uninadelivery.entity.orgdomain;

import lombok.*;


@ToString
@Getter
@Setter
@AllArgsConstructor
public class MerceStoccataDTO {

        private long id;

        @NonNull
        ProdottoDTO prodotto;

        private int quantitaReale;

        private int quantitaPrenotata;

        private int quantitaDisponibile;

        @NonNull
        private MagazzinoDTO magazzino;

        private Character settoreMagazzino;
}
