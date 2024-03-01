package org.uninadelivery.appdomain;

import lombok.Data;
import org.uninadelivery.orgdomain.FilialeDto;

@Data
public class OperatoreFilialeDto extends UtenteDto{
    private FilialeDto filiale;
}
