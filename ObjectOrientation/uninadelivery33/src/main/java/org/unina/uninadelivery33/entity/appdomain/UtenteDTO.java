package org.unina.uninadelivery33.entity.appdomain;


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
    @NonNull
    private String username;
    @NonNull
    private String password;

    private String matricolaUnina;

    @NonNull
    private String profilo;
    @NonNull
    private List<String> funzioni;
}
