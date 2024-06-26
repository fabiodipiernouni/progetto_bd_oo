package org.unina.uninadelivery.entity.appdomain;

import lombok.*;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;

import java.util.List;

@NoArgsConstructor
@ToString(callSuper=true)
@Getter
@Setter
public class OperatoreFilialeDTO extends UtenteDTO {
    @NonNull
    private FilialeDTO filiale;

    public OperatoreFilialeDTO(long id, @NonNull String username, @NonNull String password, String matricolaUnina, @NonNull String profilo, @NonNull List<String> funzioni, @NonNull FilialeDTO filiale) {
        super(id, username, password, matricolaUnina, profilo, funzioni);
        this.filiale = filiale;
    }
}
