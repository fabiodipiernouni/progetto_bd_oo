package org.unina.uninadelivery.presentation.model.orgdomain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.unina.uninadelivery.presentation.model.Model;

@AllArgsConstructor
@Getter
public class HomeOpCorriereModel extends Model {
    private int numOrdiniPackagingDaPrendereInCarico;
    private int numOrdiniPackagingDaTerminare;
    private int numOrdiniTrasportoDaPrendereInCarico;
    private int numOrdiniTrasportoDaTerminare;
}
