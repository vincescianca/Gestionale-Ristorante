package it.sciancaleporevincenzo.gestRest.controllers;

import it.sciancaleporevincenzo.gestRest.entity.Ordine;
import it.sciancaleporevincenzo.gestRest.model.BFormSelectModel;
import it.sciancaleporevincenzo.gestRest.model.OrdineTableCustomModel;
import it.sciancaleporevincenzo.gestRest.services.OrdineService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@Log4j2
@RequestMapping("/private/api/v1/ordine")
public class OrdineController {
    @Autowired
    OrdineService ordineService;

    @GetMapping("/")
    public List<Ordine> findAll() throws Exception {
        log.info("OrdineController.findAll()");
        return ordineService.findAll();
    }

    @GetMapping("/{idOrdine}")
    public Ordine findOrdine(@PathVariable long idOrdine) throws Exception {
        log.info("OrdineController.findOrdine({})", idOrdine);
        return ordineService.findById(idOrdine);
    }

    @GetMapping("/table/custom/{idTavolo}")
    public List<OrdineTableCustomModel> findByTableCustom(@PathVariable long idTavolo) throws Exception {
        log.info("OrdineController.findByTableCustom({})", idTavolo);
        return ordineService.findByTableCustom(idTavolo);
    }

    @GetMapping("/table/custom/{idTavolo}/{tipologia}")
    public List<OrdineTableCustomModel> findByTableCustomTipologia(@PathVariable long idTavolo, @PathVariable String tipologia) throws Exception {
        log.info("OrdineController.findByTableCustomfindByTableCustomTipologia({},{})", idTavolo, tipologia);
        return ordineService.findByTableCustomTipologia(idTavolo, tipologia);
    }

    @PostMapping(value = "/aggiungi", consumes = {"application/json"})
    public void addOrdine(@RequestBody Ordine ordine) throws Exception {
        log.info("OrdineController.addOrdine()");
        ordineService.addOrdine(ordine);
    }

    @GetMapping("/elimina/{idOrdine}")
    public void eliminaOrdine(@PathVariable long idOrdine) throws Exception {
        try {
            log.info("OrdineController.eliminaOrdine({})", idOrdine);
            ordineService.eliminaOrdine(idOrdine);
        }catch (Exception e){ e.printStackTrace();}
    }

    @PostMapping(value = "/modifica", consumes = {"application/json"})
    public void editOrdine(@RequestBody Ordine Ordine) throws Exception {
        log.info("OrdineController.editOrdine()");
        ordineService.editOrdine(Ordine);
    }

    @GetMapping("/portate")
    public List<BFormSelectModel> getPortate() throws Exception {
        log.info("OrdineController.getPortate()");
        List<BFormSelectModel> listPortate = new ArrayList<BFormSelectModel>();
        BFormSelectModel portata = new BFormSelectModel("1", "Prima Portata");
        listPortate.add(portata);
        portata = new BFormSelectModel("2", "Seconda Portata");
        listPortate.add(portata);
        portata = new BFormSelectModel("3", "Terza Portata");
        listPortate.add(portata);
        return listPortate;
    }

    @GetMapping("/portata/{idPortata}")
    public BFormSelectModel getPortata(@PathVariable int idPortata) throws Exception {
        log.info("OrdineController.getPortata()");
        if (idPortata == 1) return new BFormSelectModel("1", "Prima Portata");
        else if (idPortata == 2) return new BFormSelectModel("2", "Seconda Portata");
        else if (idPortata == 3) return new BFormSelectModel("3", "Terza Portata");
        return null;
    }
}