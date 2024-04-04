package org.unina.uninadelivery.entity.shipmentdomain;

import lombok.*;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;
import org.unina.uninadelivery.entity.orgdomain.MezzoDiTrasportoDTO;

import java.time.LocalDate;
import java.util.List;


@ToString(callSuper=true)
@Getter
@Setter
public class OrdineDiLavoroSpedizioneDTO extends OrdineDiLavoroDTO {

    private MezzoDiTrasportoDTO mezzoDiTrasporto;

    public OrdineDiLavoroSpedizioneDTO(long id, @NonNull LocalDate dataCreazione, LocalDate dataInizioPianificazione, LocalDate dataInizioLavorazione, LocalDate dataFineLavorazione, GruppoCorriereDTO gruppoCorriere, OperatoreCorriereDTO operatoreCorriere, @NonNull FilialeDTO filiale, @NonNull SpedizioneDTO spedizione, @NonNull String stato, String noteAggiuntiveOperatore, MezzoDiTrasportoDTO mezzoDiTrasporto) {
        super(id, dataCreazione, dataInizioPianificazione, dataInizioLavorazione, dataFineLavorazione, gruppoCorriere, operatoreCorriere, filiale, spedizione, stato, noteAggiuntiveOperatore);
        this.mezzoDiTrasporto = mezzoDiTrasporto;
    }
}
