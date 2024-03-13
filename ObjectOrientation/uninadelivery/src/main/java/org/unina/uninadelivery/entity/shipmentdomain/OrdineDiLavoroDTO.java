package org.unina.uninadelivery.entity.shipmentdomain;

import lombok.*;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.appdomain.OperatoreFilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Setter
@EqualsAndHashCode
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

}
