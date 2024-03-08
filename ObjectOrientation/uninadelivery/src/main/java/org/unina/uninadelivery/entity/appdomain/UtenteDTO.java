package org.unina.uninadelivery.entity.appdomain;


import lombok.*;

import java.util.Collections;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class UtenteDTO {
    @NonNull
    private Long id;

    private String username = "";

    private String password = "";

    private String matricolaUnina = "";

    private String profilo = "";

    private String filiale = "";

    private String gruppoCorriere = "";

    private List<String> funzioni = Collections.emptyList();
}
