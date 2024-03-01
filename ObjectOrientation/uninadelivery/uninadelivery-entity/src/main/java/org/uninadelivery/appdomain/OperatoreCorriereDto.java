package org.uninadelivery.appdomain;

import lombok.Data;
import org.uninadelivery.orgdomain.GruppoCorriereDto;

@Data
public class OperatoreCorriereDto extends UtenteDto{
    private GruppoCorriereDto gruppoCorriere;
}
