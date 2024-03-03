package org.uninadelivery.entity.appdomain;

import lombok.Data;
import org.uninadelivery.entity.orgdomain.GruppoCorriereDto;

@Data
public class OperatoreCorriereDto extends UtenteDto{
    private GruppoCorriereDto gruppoCorriere;
}
