package org.unina.uninadelivery.entity.shipmentdomain;

import lombok.*;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;

import java.time.LocalDate;

@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class SpedizioneDTO {
    private long id;

    @NonNull
    private LocalDate dataCreazione;

    private LocalDate dataInizioLavorazione;

    private LocalDate dataFineLavorazione;

    @NonNull
    private OrdineClienteDTO ordineCliente;

    @NonNull
    private OperatoreFilialeDTO organizzatore;

    @NonNull
    private String stato;

    @NonNull
    private String trackingNumber;

    @NonNull
    private String trackingStatus;

}
