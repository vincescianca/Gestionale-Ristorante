package it.sciancaleporevincenzo.gestRest.services;

import it.sciancaleporevincenzo.gestRest.model.DashboardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {
    @Autowired
    TavoloService tavoloService;

    @Autowired
    OrdineService ordineService;

    public List<DashboardModel> getStats() {
        List<DashboardModel> list = new ArrayList<DashboardModel>();
        list.add(tavoloService.getTavoliAttualiStats());
        list.add(tavoloService.getTavoliOggiStats());
        list.add(ordineService.getOrdiniAttualiStats());
        list.add(ordineService.getOrdiniOggiStats());

        return list;
    }

}