package it.sciancaleporevincenzo.gestRest.controllers;

import it.sciancaleporevincenzo.gestRest.entity.Categoria;
import it.sciancaleporevincenzo.gestRest.enums.Tipologia;
import it.sciancaleporevincenzo.gestRest.model.BFormSelectModel;
import it.sciancaleporevincenzo.gestRest.model.CategoriaModel;
import it.sciancaleporevincenzo.gestRest.services.CategoriaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@Log4j2
@RequestMapping("/private/api/v1/categoria")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/")
    public List<CategoriaModel> findAll() throws Exception {
        log.info("CategoriaController.findAll()");
        return categoriaService.findAll();
    }

    @GetMapping("/{idCategoria}")
    public CategoriaModel findCategoria(@PathVariable long idCategoria) throws Exception {
        log.info("CategoriaController.findCategoria({})", idCategoria);
        return new CategoriaModel(categoriaService.findById(idCategoria).get());
    }


    @GetMapping("/menu/{idMenu}")
    public List<Categoria> findByMenu(@PathVariable long idMenu) throws Exception {
        log.info("CategoriaController.findByMenu({})", idMenu);
        return categoriaService.findByMenu(idMenu);
    }

    @GetMapping("/menu/b-form-select/{idMenu}")
    public List<BFormSelectModel> findAllBFormSelect(@PathVariable long idMenu) throws Exception {
        log.info("CategoriaController.findAllBFormSelect()");
        return categoriaService.findByMenu(idMenu).stream().map(x -> new BFormSelectModel("" + x.getId(), x.getDescrizione())).collect(Collectors.toList());
    }

    @GetMapping("/tipologie")
    public List<BFormSelectModel> getListaTipologie() throws Exception {
        log.info("CategoriaController.getListaTipologie()");
        return Tipologia.getListaTipologie();
    }

    @PostMapping(value = "/aggiungi", consumes = {"application/json"})
    public void addCategoria(@RequestBody Categoria categoria) throws Exception {
        log.info("CategoriaController.addCategoria()");
        categoriaService.addCategoria(categoria);
    }

    @GetMapping("/elimina/{idCategoria}")
    public void eliminaCategoria(@PathVariable long idCategoria) throws Exception {
        log.info("CategoriaController.eliminaCategoria({})", idCategoria);
        categoriaService.eliminaCategoria(idCategoria);
    }

    @PostMapping(value = "/modifica", consumes = {"application/json"})
    public void editCategoria(@RequestBody Categoria categoria) throws Exception {
        log.info("CategoriaController.editCategoria()");
        categoriaService.editCategoria(categoria);
    }

    @GetMapping("/portata/{idCategoria}")
    public int getPortata(@PathVariable long idCategoria) throws Exception {
        log.info("CategoriaController.getPortata({})", idCategoria);
        return categoriaService.getPortata(idCategoria);
    }
}
