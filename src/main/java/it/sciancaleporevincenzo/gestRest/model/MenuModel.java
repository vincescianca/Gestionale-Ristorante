package it.sciancaleporevincenzo.gestRest.model;

import it.sciancaleporevincenzo.gestRest.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@Data
@AllArgsConstructor
public class MenuModel {
    private Long id;
    private String descrizione;
    private Date dataCreazione;
    private String attivo;
    private String azione;

    public MenuModel(Menu po) {
        this.id = po.getId();
        this.descrizione = po.getDescrizione();
        this.dataCreazione = po.getDataCreazione();
        this.attivo = (po.isAttivo()) ? "Attivo" : "Non attivo";
    }
}
