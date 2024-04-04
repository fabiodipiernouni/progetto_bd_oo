package org.unina.uninadelivery.presentation.model.customerdomain;

import javafx.concurrent.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;
import org.unina.uninadelivery.presentation.model.Model;

import java.time.LocalDate;


@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
public class SpedizioneModel extends Model {
    private String numeroSpedizione;
    private String ragioneSocialeCliente;
    private String stato;
    private LocalDate dataCreazione;
    private LocalDate dataInizioLavorazione;
    private LocalDate dataFineLavorazione;

    private String organizzatore;

    private String trackingNumber;
    private Task<Integer> numeroOrdiniPackaging;
    private Task<Integer> numeroOrdiniPackagingDaCompletare;
    private Task<Integer> numeroPacchiGenerati;
    private Task<Integer> numeroPacchiDaSpedire;
    private Task<Integer> numeroOrdiniTrasporto;
    private Task<Integer> numeroOrdiniTrasportoDaCompletare;
    private OrdineClienteDTO ordineCliente;
    private SpedizioneDTO spedizioneDTO;
    private FilialeDTO filialeDTO;
}
