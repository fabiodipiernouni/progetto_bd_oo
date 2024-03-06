package org.unina.uninadelivery.entity.appdomain;


import lombok.*;

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

    private List<String> funzioni = null;
}
