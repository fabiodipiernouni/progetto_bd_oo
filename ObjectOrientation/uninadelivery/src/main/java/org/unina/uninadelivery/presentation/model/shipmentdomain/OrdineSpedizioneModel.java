package org.unina.uninadelivery.presentation.model.shipmentdomain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;
import org.unina.uninadelivery.presentation.model.Model;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
public class OrdineSpedizioneModel extends Model {
    private long codiceOrdineSpedizione;
    private LocalDate dataCreazione;
    private String cliente;
    private LocalDate dataInizioLavorazione;
    private LocalDate dataFineLavorazione;
    private String gruppoCorriere;
    private String nomeFiliale;
    private String indirizzoSpedizioneRiga1;
    private String indirizzoSpedizioneRiga2;
    private String stato;
    private String noteAggiuntiveCliente;
    private String noteAggiuntiveCorriere;
    private SpedizioneDTO spedizione;
    private FilialeDTO filiale;
    private GruppoCorriereDTO gruppoCorriereDTO;
}
