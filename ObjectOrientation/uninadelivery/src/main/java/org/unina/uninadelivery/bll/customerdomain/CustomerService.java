package org.unina.uninadelivery.bll.customerdomain;

import javafx.concurrent.Task;
import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.customerdomain.FactoryCustomerDomain;
import org.unina.uninadelivery.dal.factory.customerdomain.OrdineClienteDAO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;

public class CustomerAsyncService {
    public Task<Integer> getCountOrdiniDaLavorareTask(FilialeDTO filiale) {
        return new Task<>() {
            @Override
            protected Integer call() throws ServiceException {
                Integer ret;
                OrdineClienteDAO dao = FactoryCustomerDomain.buildOrdineClienteDAO();
                try {
                    if (filiale != null)
                        ret = dao.getCount(filiale, "Completato");
                    else
                        ret = dao.getCount("Completato");
                } catch (PersistenceException e) {
                    throw new ServiceException("Errore nel reperire il numero di ordini da lavorare");
                }
                return ret;
            }
        };
    }
}
