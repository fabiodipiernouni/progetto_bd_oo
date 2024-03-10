package org.unina.uninadelivery.presentation.model.customerdomain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.unina.uninadelivery.presentation.model.Model;

import java.time.LocalDate;


@AllArgsConstructor
@Getter
public class SpedizioneModel extends Model {
    private String numeroSpedizione;
    private String ragioneSocialeCliente;
    private String stato;
    private LocalDate dataCreazione;
    private LocalDate dataInizioLavorazione;
    private LocalDate dataFineLavorazione;

    private String organizzatore;

    private String trackingNumber;
    private int numeroOrdiniPackaging;
    private int numeroPacchiGenerati;
    private int numeroOrdiniTrasporto;
    private boolean tuttiOrdiniPackagingCompletati;
}
