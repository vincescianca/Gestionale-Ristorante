package it.sciancaleporevincenzo.gestRest.services;

import it.sciancaleporevincenzo.gestRest.entity.Ordine;
import it.sciancaleporevincenzo.gestRest.enums.Tipologia;
import it.sciancaleporevincenzo.gestRest.model.DashboardModel;
import it.sciancaleporevincenzo.gestRest.model.OrdineTableCustomModel;
import it.sciancaleporevincenzo.gestRest.repositories.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class OrdineService {

    @Autowired
    OrdineRepository ordineRepository;

    @Autowired
    TavoloService tavoloService;

    @Transactional(rollbackFor = Exception.class)
    public List<Ordine> findAll() {
        return ordineRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public Ordine findById(long idOrdine) {
        return ordineRepository.findById(idOrdine).get();
    }

    @Transactional(rollbackFor = Exception.class)
    public void addOrdine(Ordine ordine) {
        ordine.setDataInserimento(new Timestamp(System.currentTimeMillis()));
        if(ordine.getNumPortata()==0) ordine.setNumPortata(1);
        if (isOrdinePresente(ordine)) addQuantitaOrdine(ordine);
        else ordineRepository.save(ordine);
    }

    private boolean isOrdinePresente(Ordine ordine) {
        if (ordine.getIdProdottoOriginale() != null) {
            if (ordineRepository.findByIdTavoloAndIdProdottoOriginale(ordine.getIdTavolo(), ordine.getIdProdottoOriginale()).isPresent())
                return true;
            return false;
        } else return false;
    }

    private void addQuantitaOrdine(Ordine ordine) {
        Ordine tmp = ordineRepository.findByIdTavoloAndIdProdottoOriginale(ordine.getIdTavolo(), ordine.getIdProdottoOriginale()).get();
        tmp.setQuantita(tmp.getQuantita() + ordine.getQuantita());
        ordineRepository.save(tmp);
    }

    @Transactional(rollbackFor = Exception.class)
    public void eliminaOrdine(long idOrdine) {
        ordineRepository.deleteById(idOrdine);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editOrdine(Ordine ordine) {
        ordineRepository.save(ordine);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<OrdineTableCustomModel> findByTableCustom(long idTavolo) {
        return ordineRepository.findByIdTavolo(idTavolo).stream()
                .sorted(Comparator.comparingLong(Ordine::getId))
                .map(x -> new OrdineTableCustomModel(x))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<OrdineTableCustomModel> findByTableCustomTipologia(long idTavolo, String tipologia) {
        return ordineRepository.findByIdTavolo(idTavolo).stream()
                .filter(x -> x.getCategoria().getTipologia().equals(Tipologia.fromName(tipologia).getSigla()))
                .sorted(Comparator.comparingLong(Ordine::getId))
                .map(x -> new OrdineTableCustomModel(x))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Ordine> findByTable(long idTavolo) {
        return ordineRepository.findByIdTavolo(idTavolo);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public DashboardModel getOrdiniAttualiStats() {
        DashboardModel dm = new DashboardModel();
        dm.setType("danger");
        dm.setIcon("ti-pulse");
        dm.setTitle("Ordini da erogare");
        AtomicInteger countOrdini = new AtomicInteger();
        tavoloService.findAll24HWData().stream().filter(y -> y.isAperto()).forEach(x -> {
            if (x.getData() != null) countOrdini.addAndGet(x.getData().size());
        });
        dm.setValue(countOrdini.toString());
        dm.setFooterText("Aggiornato adesso");
        dm.setFooterIcon("ti-reload");
        return dm;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public DashboardModel getOrdiniOggiStats() {
        DashboardModel dm = new DashboardModel();
        dm.setType("info");
        dm.setIcon("ti-alert");
        dm.setTitle("Ordini erogati");
        AtomicInteger countOrdini = new AtomicInteger();
        tavoloService.findDa12PrevHWData().stream().forEach(x -> {
            if (x.getData() != null) countOrdini.addAndGet(x.getData().size());
        });
        dm.setValue(countOrdini.toString());
        dm.setFooterText("Nelle ultime 12H");
        dm.setFooterIcon("ti-timer");
        return dm;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Ordine> getUltimiNGG(Date date) {
        return ordineRepository.findLastNDays(date);
    }

}
