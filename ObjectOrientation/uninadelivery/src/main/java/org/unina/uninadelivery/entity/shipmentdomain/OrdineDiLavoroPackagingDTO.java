package org.unina.uninadelivery.entity.shipmentdomain;

import lombok.*;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.geodomain.IndirizzoDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;
import org.unina.uninadelivery.entity.orgdomain.MagazzinoDTO;

import java.time.LocalDate;

@ToString(callSuper=true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class OrdineDiLavoroPackagingDTO extends OrdineDiLavoroDTO {

    String noteAggiuntiveCliente;

    @NonNull
    private MagazzinoDTO magazzino;

    @NonNull
    private IndirizzoDTO indirizzoSpedizione;

    public OrdineDiLavoroPackagingDTO(long id, @NonNull LocalDate dataCreazione, LocalDate dataInizioPianificazione, LocalDate dataInizioLavorazione, LocalDate dataFineLavorazione, GruppoCorriereDTO gruppoCorriere, OperatoreCorriereDTO operatoreCorriere, @NonNull FilialeDTO filiale, @NonNull SpedizioneDTO spedizione, @NonNull String stato, String noteAggiuntiveCliente, String noteAggiuntiveOperatore, @NonNull MagazzinoDTO magazzino, @NonNull IndirizzoDTO indirizzoSpedizione) {
        super(id, dataCreazione, dataInizioPianificazione, dataInizioLavorazione, dataFineLavorazione, gruppoCorriere, operatoreCorriere, filiale, spedizione, stato, noteAggiuntiveOperatore);
        this.magazzino = magazzino;
        this.indirizzoSpedizione = indirizzoSpedizione;
        this.noteAggiuntiveCliente = noteAggiuntiveCliente;
    }

}
