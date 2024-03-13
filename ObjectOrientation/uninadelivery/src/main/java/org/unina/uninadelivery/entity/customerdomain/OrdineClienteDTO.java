package org.unina.uninadelivery.entity.customerdomain;

import lombok.*;
import org.unina.uninadelivery.entity.geodomain.IndirizzoDTO;

import java.time.LocalDate;
import java.util.List;


/**
 * OrdineCliente rappresenta un ordine effettuato da un cliente
 */


@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class OrdineClienteDTO {

    private long id;

    @NonNull
    private LocalDate dataOrdine;

    private double importoTotale;

    private LocalDate dataInizioLavorazione;

    private LocalDate dataFineLavorazione;

    @NonNull
    private String stato;

    @NonNull
    private ClienteDTO cliente;

    @NonNull
    private IndirizzoDTO indirizzoFatturazione;

    private IndirizzoDTO indirizzoSpedizione;

    @NonNull
    private String numeroOrdine;

    @NonNull
    private List<DettaglioOrdineDTO> dettaglio;

}
