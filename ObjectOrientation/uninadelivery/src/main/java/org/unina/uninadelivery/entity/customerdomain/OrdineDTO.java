package org.unina.uninadelivery.entity.customerdomain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrdineDTO {
    public int Id;
    public LocalDate DataOrdine;

    public void setDataOrdine(LocalDate dataOrdine) {
        DataOrdine = dataOrdine;
    }
}
