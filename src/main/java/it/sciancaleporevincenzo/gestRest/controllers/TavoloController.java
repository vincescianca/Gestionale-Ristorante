package it.sciancaleporevincenzo.gestRest.controllers;

import it.sciancaleporevincenzo.gestRest.Utils;
import it.sciancaleporevincenzo.gestRest.entity.Tavolo;
import it.sciancaleporevincenzo.gestRest.model.OrdineTableCustomModel;
import it.sciancaleporevincenzo.gestRest.model.TavoloModel;
import it.sciancaleporevincenzo.gestRest.services.TavoloService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@Log4j2
@RequestMapping("/private/api/v1/tavolo")
public class TavoloController {
    @Autowired
    TavoloService tavoloService;

    @GetMapping("/")
    public List<TavoloModel> findAll() throws Exception {
        log.info("TavoloController.findAll()");
        return tavoloService.findAll();
    }

    @GetMapping("/oggi")
    public List<TavoloModel> findAllToday() throws Exception {
        log.info("TavoloController.findAllToday()");
        return tavoloService.findAll12HWData();
    }

    @GetMapping("/attivi")
    public List<TavoloModel> findAllAttivi() throws Exception {
        log.info("TavoloController.findAllAttivi()");
        return tavoloService.findAllAttivi();
    }

    @GetMapping("/{idTavolo}")
    public Tavolo findTavolo(@PathVariable long idTavolo) throws Exception {
        log.info("TavoloController.findTavolo({})", idTavolo);
        return tavoloService.findById(idTavolo);
    }

    @PostMapping(value = "/aggiungi", consumes = {"application/json"})
    public Long addTavolo(@RequestBody Tavolo tavolo) throws Exception {
        log.info("TavoloController.addTavolo()");
        return tavoloService.addTavolo(tavolo).getId();
    }

    @GetMapping("/chiudi/{idTavolo}")
    public void chiudiTavolo(@PathVariable long idTavolo) throws Exception {
        log.info("TavoloController.chiudiTavolo({})", idTavolo);
        tavoloService.chiudiTavolo(idTavolo);
    }

    @GetMapping("/apri/{idTavolo}")
    public void apriTavolo(@PathVariable long idTavolo) throws Exception {
        log.info("TavoloController.apriTavolo({})", idTavolo);
        tavoloService.apriTavolo(idTavolo);
    }

    @PostMapping(value = "/modifica", consumes = {"application/json"})
    public void editTavolo(@RequestBody Tavolo tavolo) throws Exception {
        log.info("TavoloController.editTavolo()");
        tavoloService.editTavolo(tavolo);
    }

    @GetMapping("/stampa/{idTavolo}/comanda") // STAMPA TUTTO
    public void stampaComanda(HttpServletResponse response, @PathVariable long idTavolo) throws Exception {
        log.info("TavoloController.stampaTavolo({})", idTavolo);
        byte[] bytea = tavoloService.stampaComanda(idTavolo);
       // Utils.downloadFile(response, bytea, "application/pdf");
    }

    @GetMapping("/stampa/{idTavolo}/cliente")
    public void stampaRicevutaNonFiscale(HttpServletResponse response, @PathVariable long idTavolo) throws Exception {
        log.info("TavoloController.stampaRicevutaNonFiscale({})", idTavolo);
        byte[] bytea = tavoloService.stampaRicevutaNonFiscale(idTavolo);
        //Utils.downloadFile(response, bytea, "application/pdf");
    }

    @PostMapping("/stampa/{idTavolo}")
    public void stampaParziale(@PathVariable long idTavolo,
                               @RequestBody List<OrdineTableCustomModel> ordini) throws Exception {
        log.info("TavoloController.stampaParziale({})", idTavolo);
        tavoloService.stampaParziale(idTavolo, ordini);

    }
}
