package org.unina.uninadelivery.entity.orgdomain;

import lombok.*;


@ToString
@Getter
@Setter
@EqualsAndHashCode
public class MerceStoccataDTO {

        long id;

        @NonNull
        ProdottoDTO prodotto;

        private int quantitaReale;

        private int quantitaPrenotata;

        private int quantitaDisponibile;

        @NonNull
        private MagazzinoDTO magazzino;

        private char settoreMagazzino;

        public MerceStoccataDTO(long id, ProdottoDTO prodotto, int quantitaReale, int quantitaPrenotata, int quantitaDisponibile, char settoreMagazzino) {
            this.id = id;
            this.prodotto = prodotto;
            this.quantitaReale = quantitaReale;
            this.quantitaPrenotata = quantitaPrenotata;
            this.quantitaDisponibile = quantitaDisponibile;
            this.settoreMagazzino = settoreMagazzino;
        }
}
