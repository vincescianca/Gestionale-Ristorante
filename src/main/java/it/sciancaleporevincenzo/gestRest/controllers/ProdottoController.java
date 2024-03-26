package it.sciancaleporevincenzo.gestRest.controllers;

import it.sciancaleporevincenzo.gestRest.entity.ProdottoCustom;
import it.sciancaleporevincenzo.gestRest.entity.ProdottoOriginale;
import it.sciancaleporevincenzo.gestRest.model.ProdottoModel;
import it.sciancaleporevincenzo.gestRest.services.ProdottoCustomService;
import it.sciancaleporevincenzo.gestRest.services.ProdottoOriginaleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@Log4j2
@RequestMapping("/private/api/v1/prodotto")
public class ProdottoController {
    @Autowired
    ProdottoOriginaleService prodottoOriginaleService;
    @Autowired
    ProdottoCustomService prodottoCustomService;

    @GetMapping("/")
    public List<ProdottoModel> findAll() throws Exception {
        log.info("ProdottiController.findAll()");
        return prodottoOriginaleService.findAll();
    }

    @GetMapping("/attivi")
    public List<ProdottoModel> findAllAttivi() throws Exception {
        log.info("ProdottiController.findAllAttivi()");
        return prodottoOriginaleService.findAllAttivi();
    }

    @GetMapping("/{idProdotto}")
    public Optional<ProdottoOriginale> findProdotto(@PathVariable long idProdotto) throws Exception {
        log.info("ProdottiController.findProdotto({})", idProdotto);
        return prodottoOriginaleService.findById(idProdotto);
    }

    @PostMapping(value = "/aggiungi", consumes = {"application/json"})
    public void addProdotto(@RequestBody ProdottoOriginale prodotto) throws Exception {
        log.info("ProdottiController.addProdotto()");
        prodottoOriginaleService.addProdotto(prodotto);
    }

    @PostMapping(value = "/custom/aggiungi", consumes = {"application/json"})
    public Long addProdottoCustom(@RequestBody ProdottoCustom prodotto) throws Exception {
        log.info("ProdottiController.addProdottoCustom()");
        return prodottoCustomService.addProdottoCustom(prodotto).getId();
    }

    @GetMapping("/elimina/{idProdotto}")
    public void eliminaProdotto(@PathVariable long idProdotto) throws Exception {
        log.info("ProdottiController.eliminaProdotto({})", idProdotto);
        prodottoOriginaleService.eliminaProdotto(idProdotto);
    }

    @PostMapping(value = "/modifica", consumes = {"application/json"})
    public void editProdotto(@RequestBody ProdottoOriginale prodotto) throws Exception {
        log.info("ProdottiController.editProdotto()");
        prodottoOriginaleService.editProdotto(prodotto);
    }
}
