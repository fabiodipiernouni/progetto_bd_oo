package org.unina.uninadelivery.entity.shipmentdomain;

import lombok.*;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;

import java.time.LocalDate;
import java.util.List;

@ToString
@Getter
@Setter
public class OrdineDiLavoroDTO {

    private long id;

    @NonNull
    private LocalDate dataCreazione;

    private LocalDate dataInizioPianificazione;

    private LocalDate dataInizioLavorazione;

    private LocalDate dataFineLavorazione;

    private GruppoCorriereDTO gruppoCorriere;

    private OperatoreCorriereDTO operatoreCorriere;

    @NonNull
    private FilialeDTO filiale;

    @NonNull
    private SpedizioneDTO spedizione;

    @NonNull
    private String stato;

    private String noteAggiuntiveOperatore;

    @NonNull
    private List<PaccoDTO> pacchi;

    protected OrdineDiLavoroDTO(long id, @NonNull LocalDate dataCreazione, LocalDate dataInizioPianificazione, LocalDate dataInizioLavorazione, LocalDate dataFineLavorazione, GruppoCorriereDTO gruppoCorriere, OperatoreCorriereDTO operatoreCorriere, @NonNull FilialeDTO filiale, @NonNull SpedizioneDTO spedizione, @NonNull String stato, String noteAggiuntiveOperatore) {
        this.id = id;
        this.dataCreazione = dataCreazione;
        this.dataInizioPianificazione = dataInizioPianificazione;
        this.dataInizioLavorazione = dataInizioLavorazione;
        this.dataFineLavorazione = dataFineLavorazione;
        this.gruppoCorriere = gruppoCorriere;
        this.operatoreCorriere = operatoreCorriere;
        this.filiale = filiale;
        this.spedizione = spedizione;
        this.stato = stato;
        this.noteAggiuntiveOperatore = noteAggiuntiveOperatore;
    }
}
