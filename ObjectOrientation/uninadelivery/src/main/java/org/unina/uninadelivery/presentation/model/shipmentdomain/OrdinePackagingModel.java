package org.unina.uninadelivery.presentation.model.shipmentdomain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.unina.uninadelivery.entity.orgdomain.MagazzinoDTO;
import org.unina.uninadelivery.entity.shipmentdomain.SpedizioneDTO;
import org.unina.uninadelivery.presentation.model.Model;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class OrdinePackagingModel extends Model {

    private Integer codiceOrdine;
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
    private MagazzinoDTO magazzino;
    private SpedizioneDTO  spedizione;
}
