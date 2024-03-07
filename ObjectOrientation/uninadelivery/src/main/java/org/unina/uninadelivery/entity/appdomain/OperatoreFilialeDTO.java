package org.unina.uninadelivery.entity.appdomain;

import lombok.*;

import java.util.List;

import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;

@NoArgsConstructor
@ToString(callSuper=true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class OperatoreFilialeDTO extends UtenteDTO {
    @NonNull
    private FilialeDTO filiale;

    public OperatoreFilialeDTO(long id, @NonNull String username, @NonNull String password, String matricolaUnina, @NonNull String profilo, @NonNull List<String> funzioni, @NonNull FilialeDTO filiale) {
        super(id, username, password, matricolaUnina, profilo, funzioni);
        this.filiale = filiale;
    }
}
