package org.unina.uninadelivery.entity.appdomain;

import lombok.*;
import org.unina.uninadelivery.entity.orgdomain.GruppoCorriereDTO;

import java.util.List;


@NoArgsConstructor
@ToString(callSuper=true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class OperatoreCorriereDTO extends UtenteDTO {
    @NonNull
    private GruppoCorriereDTO gruppoCorriere;

    public OperatoreCorriereDTO(long id, @NonNull String username, @NonNull String password, String matricolaUnina, @NonNull String profilo, @NonNull List<String> funzioni, @NonNull GruppoCorriereDTO gruppoCorriere) {
        super(id, username, password, matricolaUnina, profilo, funzioni);
        this.gruppoCorriere = gruppoCorriere;
    }
}
