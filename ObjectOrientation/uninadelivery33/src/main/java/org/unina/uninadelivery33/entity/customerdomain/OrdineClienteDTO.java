package org.unina.uninadelivery33.entity.customerdomain;

import lombok.*;
import org.unina.uninadelivery33.entity.geodomain.IndirizzoDTO;
import org.unina.uninadelivery33.entity.orgdomain.ProdottoDTO;

import java.time.LocalDate;
import java.util.Map;


/**
 * OrdineCliente rappresenta un ordine effettuato da un cliente
 */


@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class OrdineClienteDTO {

    @NonNull
    private long id;

    @NonNull
    private LocalDate dataOrdine;

    @NonNull
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
    private Map<ProdottoDTO, Integer> dettaglio;

}
