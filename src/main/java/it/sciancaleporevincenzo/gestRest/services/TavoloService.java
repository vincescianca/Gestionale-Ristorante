package it.sciancaleporevincenzo.gestRest.services;

import it.sciancaleporevincenzo.gestRest.entity.Ordine;
import it.sciancaleporevincenzo.gestRest.entity.Tavolo;
import it.sciancaleporevincenzo.gestRest.model.DashboardModel;
import it.sciancaleporevincenzo.gestRest.model.ElementoScontrino;
import it.sciancaleporevincenzo.gestRest.model.OrdineTableCustomModel;
import it.sciancaleporevincenzo.gestRest.model.TavoloModel;
import it.sciancaleporevincenzo.gestRest.repositories.OrdineRepository;
import it.sciancaleporevincenzo.gestRest.repositories.TavoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TavoloService {

    @Autowired
    TavoloRepository tavoloRepository;

    @Autowired
    OrdineRepository ordineRepository;

    @Autowired
    OrdineService ordineService;

    @Autowired
    TavoloService tavoloService;

    @Autowired
    PrinterService printerService;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<TavoloModel> findAll() {
        return tavoloRepository.findAll().
                stream().map(t -> new TavoloModel(t, ordineService.findByTableCustom(t.getId()))).
                sorted(Comparator.comparingLong(TavoloModel::getId).reversed())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<TavoloModel> findAll24H() {
        return tavoloRepository.findByDataInserimento(getTimetampIeri()).
                stream().map(t -> new TavoloModel(t)).
                sorted(Comparator.comparingLong(TavoloModel::getId).reversed())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<TavoloModel> findAllTodayDa12Prev() {
        return tavoloRepository.findByDataInserimento(getTimestampDa12Prev()).
                stream().map(t -> new TavoloModel(t)).
                sorted(Comparator.comparingLong(TavoloModel::getId).reversed())
                .collect(Collectors.toList());
    }

    private Timestamp getTimestampDa12Prev() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(ts);
        cal.add(Calendar.HOUR, -12);
        ts.setTime(cal.getTime().getTime()); // or
        ts = new Timestamp(cal.getTime().getTime());
        return ts;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<TavoloModel> findAll24HWData() {
        return tavoloRepository.findByDataInserimento(getTimetampIeri()).
                stream().map(t -> new TavoloModel(t, ordineService.findByTableCustom(t.getId()))).
                sorted(Comparator.comparingLong(TavoloModel::getId).reversed())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<TavoloModel> findAll12HWData() {
        return tavoloRepository.findByDataInserimento(getTimestampDa12Prev()).
                stream().map(t -> new TavoloModel(t, ordineService.findByTableCustom(t.getId()))).
                sorted(Comparator.comparingLong(TavoloModel::getId).reversed())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<TavoloModel> findDa12PrevHWData() {
        return tavoloRepository.findByDataInserimento(getTimetampIeri()).
                stream().map(t -> new TavoloModel(t, ordineService.findByTableCustom(t.getId()))).
                sorted(Comparator.comparingLong(TavoloModel::getId).reversed())
                .collect(Collectors.toList());
    }

    private Timestamp getTimetampIeri() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(ts);
        cal.add(Calendar.DAY_OF_WEEK, -1);
        ts.setTime(cal.getTime().getTime()); // or
        ts = new Timestamp(cal.getTime().getTime());
        return ts;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Tavolo findById(long idTavolo) {
        return tavoloRepository.findById(idTavolo).get();
    }

    @Transactional(rollbackFor = Exception.class)
    public Tavolo addTavolo(Tavolo tavolo) {
        tavolo.setDataInserimento(new Timestamp(System.currentTimeMillis()));
        tavolo.setAperto(true);
        return tavoloRepository.save(tavolo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void chiudiTavolo(long idTavolo) {
        Tavolo tmp = tavoloRepository.findById(idTavolo).get();
        tmp.setAperto(false);
        tavoloRepository.save(tmp);
    }

    @Transactional(rollbackFor = Exception.class)
    public void apriTavolo(long idTavolo) {
        Tavolo tmp = tavoloRepository.findById(idTavolo).get();
        tmp.setAperto(true);
        tavoloRepository.save(tmp);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editTavolo(Tavolo tavolo) {
        tavoloRepository.save(tavolo);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public DashboardModel getTavoliAttualiStats() {
        DashboardModel dm = new DashboardModel();
        dm.setType("warning");
        dm.setIcon("ti-alert");
        dm.setTitle("Tavoli in corso");
        dm.setValue("" + findAll24H().stream().filter(x -> x.isAperto()).count());
        dm.setFooterText("Aggiornato adesso");
        dm.setFooterIcon("ti-reload");
        return dm;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public DashboardModel getTavoliOggiStats() {
        DashboardModel dm = new DashboardModel();
        dm.setType("success");
        dm.setIcon("ti-control-forward");
        dm.setTitle("Tavoli");
        dm.setValue("" + findAllTodayDa12Prev().stream().count());
        dm.setFooterText("Nelle ultime 12H");
        dm.setFooterIcon("ti-timer");
        return dm;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public byte[] stampaRicevutaNonFiscale(long idTavolo) throws Exception {
        List<ElementoScontrino> scontrino = getScontrino(idTavolo);
        Tavolo tavolo = tavoloRepository.findById(idTavolo).get();
        return printerService.sendReportCliente(scontrino, tavolo.getNumero());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public byte[] stampaComanda(long idTavolo) throws Exception {
        List<ElementoScontrino> scontrino = getScontrino(idTavolo);
        return printerService.sendReportComanda(scontrino, tavoloRepository.findById(idTavolo).get());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<ElementoScontrino> getScontrino(long idTavolo) {
        List<ElementoScontrino> lista = new ArrayList<ElementoScontrino>();
        Tavolo tavolo = tavoloRepository.findById(idTavolo).get();
        List<Ordine> ordini = ordineService.findByTable(idTavolo);
        for (Ordine ordine : ordini) lista.add(new ElementoScontrino(ordine));
        lista.add(new ElementoScontrino(tavolo));
        return lista;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public void stampaParziale(long idTavolo, List<OrdineTableCustomModel> ordini) {
        List<ElementoScontrino> lista = new ArrayList<ElementoScontrino>();
        ordini.stream()
                .filter(x -> x.isSelected())
                .forEach(x -> {
                    ElementoScontrino tmp = new ElementoScontrino(ordineRepository.findById(x.getId()).get());
                    tmp.setQuantita(x.getQuantitaDaStampare());
                    lista.add(tmp);
                });
        printerService.sendReportComanda(lista, tavoloRepository.findById(idTavolo).get());
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<TavoloModel> findAllAttivi() {
        try (Stream<Tavolo> stream = tavoloRepository.findAllAttivi()) {
            return stream
                    .map(t -> new TavoloModel(t, ordineService.findByTableCustom(t.getId()))).
                    sorted(Comparator.comparingLong(TavoloModel::getId).reversed())
                    .collect(Collectors.toList());
        }
    }
}
