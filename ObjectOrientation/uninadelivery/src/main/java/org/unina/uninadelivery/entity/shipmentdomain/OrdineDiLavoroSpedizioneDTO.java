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
@EqualsAndHashCode(callSuper = true)
public class OrdineDiLavoroSpedizioneDTO extends OrdineDiLavoroDTO {

    private MezzoDiTrasportoDTO mezzoDiTrasporto;

    @NonNull
    private List<PaccoDTO> pacchi;

    public OrdineDiLavoroSpedizioneDTO(long id, @NonNull LocalDate dataCreazione, LocalDate dataInizioPianificazione, LocalDate dataInizioLavorazione, LocalDate dataFineLavorazione, GruppoCorriereDTO gruppoCorriere, OperatoreCorriereDTO operatoreCorriere, @NonNull FilialeDTO filiale, @NonNull SpedizioneDTO spedizione, @NonNull String stato, String noteAggiuntiveCliente, String noteAggiuntiveOperatore, MezzoDiTrasportoDTO mezzoDiTrasporto, @NonNull List<PaccoDTO> pacchi) {
        super(id, dataCreazione, dataInizioPianificazione, dataInizioLavorazione, dataFineLavorazione, gruppoCorriere, operatoreCorriere, filiale, spedizione, stato, noteAggiuntiveCliente, noteAggiuntiveOperatore);
        this.mezzoDiTrasporto = mezzoDiTrasporto;
        this.pacchi = pacchi;
    }
}
