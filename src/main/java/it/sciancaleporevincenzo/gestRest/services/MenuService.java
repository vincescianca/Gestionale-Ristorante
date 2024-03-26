package it.sciancaleporevincenzo.gestRest.services;

import it.sciancaleporevincenzo.gestRest.entity.Menu;
import it.sciancaleporevincenzo.gestRest.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Menu findByDescrizione(String descrizione) {
        return menuRepository.findByDescrizione(descrizione);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Menu findById(long idMenu) {
        return menuRepository.findById(idMenu).get();
    }

    @Transactional(rollbackFor = Exception.class)
    public void addMenu(Menu menu) {
        menu.setDataCreazione(new Date(System.currentTimeMillis()));
        menuRepository.save(menu);
    }

    @Transactional(rollbackFor = Exception.class)
    public void eliminaMenu(long idProdotto) {
        menuRepository.deleteById(idProdotto);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editMenu(Menu menu) {
        Menu tmp = menuRepository.findById(menu.getId()).get();
        tmp.setDescrizione(menu.getDescrizione());
        menuRepository.save(tmp);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean cambiaStato(long idMenu) throws Exception {
        Menu tmp = menuRepository.findById(idMenu).get();
        if (tmp.isAttivo()) {
            tmp.setAttivo(false);
            editMenu(tmp);
            return true;
        } else {
            if (menuRepository.findByAttivo(true).isPresent()) throw new Exception("Menu attivo presente");
            else {
                tmp.setAttivo(true);
                editMenu(tmp);
                return true;
            }
        }
    }
}
