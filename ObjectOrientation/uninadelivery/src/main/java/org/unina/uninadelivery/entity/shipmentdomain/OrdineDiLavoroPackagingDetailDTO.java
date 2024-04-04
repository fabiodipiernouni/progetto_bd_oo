package org.unina.uninadelivery.entity.shipmentdomain;

import lombok.*;
import org.unina.uninadelivery.entity.customerdomain.DettaglioOrdineDTO;
import org.unina.uninadelivery.entity.orgdomain.MerceStoccataDTO;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class OrdineDiLavoroPackagingDetailDTO {
    private OrdineDiLavoroPackagingDTO ordineDiLavoroPackaging;
    private DettaglioOrdineDTO dettaglioOrdineCliente;
    private MerceStoccataDTO merceStoccata;
    private String pericolosita;
    private Integer CodicePropostaPacco;
}
