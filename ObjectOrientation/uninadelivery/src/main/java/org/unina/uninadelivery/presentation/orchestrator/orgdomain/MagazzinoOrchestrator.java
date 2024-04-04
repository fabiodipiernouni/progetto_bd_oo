package org.unina.uninadelivery.presentation.orchestrator.orgdomain;

import javafx.stage.Stage;
import org.unina.uninadelivery.bll.exception.ServiceException;
import org.unina.uninadelivery.bll.orgdomain.MagazzinoService;
import org.unina.uninadelivery.entity.appdomain.OperatoreCorriereDTO;
import org.unina.uninadelivery.entity.orgdomain.MagazzinoDTO;
import org.unina.uninadelivery.entity.orgdomain.MerceStoccataDTO;
import org.unina.uninadelivery.presentation.controller.orgdomain.MagazziniController;
import org.unina.uninadelivery.presentation.controller.orgdomain.MagazzinoController;
import org.unina.uninadelivery.presentation.helper.Session;
import org.unina.uninadelivery.presentation.model.orgdomain.MerceStoccataModel;
import org.unina.uninadelivery.presentation.orchestrator.Orchestrator;


import java.util.LinkedList;
import java.util.List;

public class MagazzinoOrchestrator extends Orchestrator {

    private MagazziniController magazziniController;
    private MagazzinoService magazzinoService;

    public MagazzinoOrchestrator(Stage dashboardStage, MagazziniController magazziniController) {
        this(dashboardStage);
        this.magazziniController = magazziniController;
    }

    public MagazzinoOrchestrator(Stage dashboardStage) {
        super(dashboardStage);
        magazzinoService = new MagazzinoService();
    }

    public void paginaMagazziniPronta() {
        Session session = Session.getInstance();

        OperatoreCorriereDTO operatoreCorriereDTO = (OperatoreCorriereDTO) session.getUserDto().getValue();

        try {
            List<MagazzinoDTO> listaMagazzini = magazzinoService.getListaMagazzini(operatoreCorriereDTO.getGruppoCorriere().getFiliale());

            magazziniController.setListaMagazzini(listaMagazzini);

        } catch (ServiceException e) {
            //TODO: gestire errore
            e.printStackTrace();
        }
    }

    public void visualizzaMagazzinoClicked(MagazzinoDTO magazzino) {

        MagazzinoController magazzinoController = new MagazzinoController(dashboardStage, magazzino);
        //DashboardController dashboardController = (DashboardController) dashboardStage.getScene().getUserData();
        dashboardController.changeView("MAGAZZINO", "/views/orgdomain/magazzino-view.fxml", c -> magazzinoController);

        try {
            List<MerceStoccataDTO> merciStoccate = magazzinoService.getMerciStoccate(magazzino);

            List<MerceStoccataModel> merciStoccateModel = new LinkedList<>();

            for (MerceStoccataDTO merceStoccata : merciStoccate)
                merciStoccateModel.add(new MerceStoccataModel(merceStoccata));

            magazzinoController.setMerciStoccate(merciStoccateModel);
        }
        catch (ServiceException e) {
            //TODO: gestire errore
            e.printStackTrace();
        }


    }

}
