package it.sciancaleporevincenzo.gestRest.controllers;

import it.sciancaleporevincenzo.gestRest.entity.Menu;
import it.sciancaleporevincenzo.gestRest.model.BFormSelectModel;
import it.sciancaleporevincenzo.gestRest.model.MenuModel;
import it.sciancaleporevincenzo.gestRest.services.MenuService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@Log4j2
@RequestMapping("/private/api/v1/menu")
public class MenuController {
    @Autowired
    MenuService menuService;

    @GetMapping("/")
    public List<MenuModel> findAll() throws Exception {
        log.info("MenuController.findAll()");
        return menuService.findAll().stream().map(x -> new MenuModel(x)).collect(Collectors.toList()).
                stream().sorted(Comparator.comparingLong(MenuModel::getId)).collect(Collectors.toList());
    }

    @GetMapping("/descrizione/{descrizione}")
    public Menu findByDescrizione(@PathVariable String descrizione) throws Exception {
        log.info("MenuController.findByDescrizione({})" + descrizione);
        return menuService.findByDescrizione(descrizione);
    }

    @GetMapping("/b-form-select")
    public List<BFormSelectModel> findAllBFormSelect() throws Exception {
        log.info("MenuController.findAllBFormSelect()");
        return menuService.findAll().stream().sorted(Comparator.comparing(Menu::isAttivo).reversed()).map(x -> new BFormSelectModel("" + x.getId(), x.getDescrizione())).collect(Collectors.toList());
    }

    @GetMapping("/{idMenu}")
    public Menu findMenu(@PathVariable long idMenu) throws Exception {
        log.info("MenuController.findMenu({})", idMenu);
        return menuService.findById(idMenu);
    }

    @GetMapping("/b-form-select/{idMenu}")
    public BFormSelectModel findMenuCustom(@PathVariable long idMenu) throws Exception {
        log.info("MenuController.findMenuCustom({})", idMenu);
        Menu menu = menuService.findById(idMenu);
        return new BFormSelectModel(menu.getId() + "", menu.getDescrizione());
    }

    @PostMapping(value = "/aggiungi", consumes = {"application/json"})
    public void addMenu(@RequestBody Menu menu) throws Exception {
        log.info("MenuController.addMenu()");
        menuService.addMenu(menu);
    }

    @GetMapping("/elimina/{idMenu}")
    public void eliminaMenu(@PathVariable long idMenu) throws Exception {
        log.info("MenuController.eliminaMenu({})", idMenu);
        menuService.eliminaMenu(idMenu);
    }

    @GetMapping("/stato/{idMenu}")
    public boolean cambiaStato(@PathVariable long idMenu) throws Exception {
        log.info("MenuController.cambiaStato({})", idMenu);
        return menuService.cambiaStato(idMenu);
    }

    @PostMapping(value = "/modifica", consumes = {"application/json"})
    public void editMenu(@RequestBody Menu menu) throws Exception {
        log.info("MenuController.editMenu()");
        menuService.editMenu(menu);
    }
}
