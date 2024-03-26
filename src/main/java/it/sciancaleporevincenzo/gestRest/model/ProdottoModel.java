package it.sciancaleporevincenzo.gestRest.model;

import it.sciancaleporevincenzo.gestRest.entity.ProdottoOriginale;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@Data
@AllArgsConstructor
public class ProdottoModel {
    private Long id;
    private String nome;
    private String descrizione;
    private double prezzo;
    private String categoria;
    private String menu;
    private Date dataInserimento;
    private String azione;
    private String value;
    private String text;
    private int numPortataDefault;

    public ProdottoModel(ProdottoOriginale po) {
        this.id = po.getId();
        this.nome = po.getNome();
        this.descrizione = po.getDescrizione();
        this.prezzo = po.getPrezzo();
        this.categoria = po.getCategoria().getDescrizione();
        this.menu = po.getCategoria().getMenu().getDescrizione();
        this.dataInserimento = po.getDataInserimento();
        this.value = "" + po.getId();
        this.text = po.getNome() + " - " + po.getDescrizione();
        this.numPortataDefault = po.getCategoria().getNumPortataStandard();
    }
}
