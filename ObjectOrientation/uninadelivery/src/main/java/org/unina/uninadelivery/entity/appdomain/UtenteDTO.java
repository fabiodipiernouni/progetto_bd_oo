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

    @NonNull
    private String username = "";

    @NonNull
    private String password = "";

    @NonNull
    private String matricolaUnina = "";

    @NonNull
    private String profilo = "";

    @NonNull
    private List<String> funzioni = Collections.emptyList();
}
