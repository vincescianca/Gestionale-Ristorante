package it.sciancaleporevincenzo.gestRest.model;

import it.sciancaleporevincenzo.gestRest.entity.Categoria;
import it.sciancaleporevincenzo.gestRest.enums.Tipologia;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;


@Data
@AllArgsConstructor
public class CategoriaModel {
    private Long id;
    private String descrizione;
    private String menu;
    private long idMenu;
    private Date ultimaModifica;
    private String tipologia;
    private String tipologiaSigla;
    private String azione;
    private int numPortataStandard;

    public CategoriaModel(Categoria po) {
        this.id = po.getId();
        this.descrizione = po.getDescrizione();
        this.menu = po.getMenu().getDescrizione();
        this.idMenu = po.getMenu().getId();
        this.ultimaModifica = po.getUltimaModifica();
        this.numPortataStandard = po.getNumPortataStandard();
        if (po.getTipologia() != null) this.tipologia = Tipologia.fromCodTipologia(po.getTipologia()).name();
        if (po.getTipologia() != null) this.tipologiaSigla = Tipologia.fromCodTipologia(po.getTipologia()).getSigla();
    }
}
