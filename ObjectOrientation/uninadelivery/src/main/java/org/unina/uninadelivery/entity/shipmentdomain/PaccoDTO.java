package org.unina.uninadelivery.entity.shipmentdomain;

import lombok.*;
import org.unina.uninadelivery.entity.geodomain.IndirizzoDTO;
import org.unina.uninadelivery.entity.orgdomain.MagazzinoDTO;


@AllArgsConstructor
@ToString
@Getter
@Setter
public class PaccoDTO {

    private long id;

    @NonNull
    private String codicePacco;

    private float peso;

    @NonNull
    private MagazzinoDTO magazzino;

    @NonNull
    private IndirizzoDTO indirizzoDestinazione;

    @NonNull
    private SpedizioneDTO spedizione;

    private OrdineDiLavoroPackagingDTO ordineDiLavoroPackaging;

}
