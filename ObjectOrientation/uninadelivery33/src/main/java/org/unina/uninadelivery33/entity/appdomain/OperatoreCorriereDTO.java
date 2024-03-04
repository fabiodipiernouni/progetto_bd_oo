package org.unina.uninadelivery33.entity.appdomain;

import lombok.*;
import org.unina.uninadelivery33.entity.orgdomain.GruppoCorriereDTO;

import java.util.List;


@NoArgsConstructor
@ToString(callSuper=true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class OperatoreCorriereDTO extends UtenteDTO {
    @NonNull
    private GruppoCorriereDTO gruppoCorriere;

    public OperatoreCorriereDTO(@NonNull long id, @NonNull String username, @NonNull String password, String matricolaUnina, @NonNull String profilo, @NonNull List<String> funzioni, @NonNull GruppoCorriereDTO gruppoCorriere) {
        super(id, username, password, matricolaUnina, profilo, funzioni);
        this.gruppoCorriere = gruppoCorriere;
    }
}
