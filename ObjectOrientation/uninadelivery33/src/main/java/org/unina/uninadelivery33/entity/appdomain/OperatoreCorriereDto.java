package org.unina.uninadelivery33.entity.appdomain;

import lombok.Data;
import org.unina.uninadelivery33.entity.orgdomain.GruppoCorriereDto;

@Data
public class OperatoreCorriereDto extends UtenteDto{
    private GruppoCorriereDto gruppoCorriere;
}
