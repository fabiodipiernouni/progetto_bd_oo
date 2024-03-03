package org.unina.uninadelivery33.entity.appdomain;

import lombok.Data;
import org.unina.uninadelivery33.entity.orgdomain.FilialeDto;

@Data
public class OperatoreFilialeDto extends UtenteDto{
    private FilialeDto filiale;
}
