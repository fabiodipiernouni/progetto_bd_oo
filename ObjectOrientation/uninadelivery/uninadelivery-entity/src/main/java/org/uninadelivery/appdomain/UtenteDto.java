package org.uninadelivery.appdomain;

import lombok.Data;
import org.uninadelivery.orgdomain.FilialeDto;

import java.util.ArrayList;

@Data
public class UtenteDto {
    private Long id;
    private String username;
    private String password;
    private String matricolaUnina;
    private String profilo;

    private ArrayList<String> funzioni;
}
