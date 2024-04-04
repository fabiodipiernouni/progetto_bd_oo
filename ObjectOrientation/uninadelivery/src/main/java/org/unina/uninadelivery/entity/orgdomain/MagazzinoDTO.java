package org.unina.uninadelivery.entity.orgdomain;

import javafx.stage.Stage;
import lombok.*;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.entity.geodomain.IndirizzoDTO;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class MagazzinoDTO {

    private long id;

    @NonNull
    private String nome;

    @NonNull
    private IndirizzoDTO indirizzo;

    @NonNull
    private FilialeDTO filiale;

}
