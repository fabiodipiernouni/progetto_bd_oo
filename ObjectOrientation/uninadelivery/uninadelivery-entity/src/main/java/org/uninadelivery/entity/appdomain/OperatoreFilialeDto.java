package org.uninadelivery.entity.appdomain;

import lombok.Data;
import org.uninadelivery.entity.orgdomain.FilialeDto;

@Data
public class OperatoreFilialeDto extends UtenteDto{
    private FilialeDto filiale;
}
