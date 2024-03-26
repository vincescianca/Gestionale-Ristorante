package it.sciancaleporevincenzo.gestRest.controllers;

import it.sciancaleporevincenzo.gestRest.model.DashboardModel;
import it.sciancaleporevincenzo.gestRest.services.DashboardService;
import it.sciancaleporevincenzo.gestRest.services.OrdineService;
import it.sciancaleporevincenzo.gestRest.services.PrinterService;
import it.sciancaleporevincenzo.gestRest.services.TavoloService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Log4j2
@RequestMapping("/private/api/v1/generic")
public class GenericController {
    @Autowired
    PrinterService printerService;

    @Autowired
    OrdineService ordineService;

    @Autowired
    TavoloService tavoloService;

    @Autowired
    DashboardService dashboardService;


    @GetMapping("/statistiche")
    public List<DashboardModel> getStat() throws Exception {
        log.info("GenericController.getStat()");
        return dashboardService.getStats();
    }

    @GetMapping("/logged")
    public String logged() throws Exception {
        log.info("GenericController.logged()");
        return "SI";
    }


}
