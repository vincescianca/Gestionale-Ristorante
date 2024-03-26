package it.sciancaleporevincenzo.gestRest.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "ordine")
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idProdottoCustom;
    private Long idProdottoOriginale;
    private Long idTavolo;
    private int quantita;
    private Timestamp dataInserimento;

    private int numPortata;

    @ManyToOne
    @JoinColumn(name = "idTavolo", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    private Tavolo tavolo;

    @ManyToOne
    @JoinColumn(name = "idProdottoCustom", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    private ProdottoCustom prodottoCustom;

    @ManyToOne
    @JoinColumn(name = "idProdottoOriginale", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    private ProdottoOriginale prodottoOriginale;

    public String getIntestazione() {
        if (prodottoCustom != null)
            return prodottoCustom.getProdottoOriginale().getNome() + this.getAggiunte() + this.getEliminazioni();
        else return prodottoOriginale.getNome();
    }

    public String getNome() {
        if (prodottoCustom != null) return prodottoCustom.getProdottoOriginale().getNome();
        else return prodottoOriginale.getNome();
    }

    public String getEliminazioni() {
        if (prodottoCustom != null && prodottoCustom.getEliminazioni() != null && prodottoCustom.getEliminazioni().trim().length() != 0)
            return " - " + prodottoCustom.getEliminazioni();
        else return "";
    }

    public String getNote() {
        if (prodottoCustom != null && prodottoCustom.getDescrizione() != null && prodottoCustom.getDescrizione().trim().length() != 0)
            return " NOTE: " + prodottoCustom.getDescrizione();
        else return "";
    }

    public String getAggiunte() {
        if (prodottoCustom != null && prodottoCustom.getAggiunte() != null && prodottoCustom.getAggiunte().trim().length() != 0)
            return " + " + prodottoCustom.getAggiunte();
        else return "";
    }

    public String getCifra() {
        if (prodottoCustom != null) return prodottoCustom.getPrezzo() + "";
        else return prodottoOriginale.getPrezzo() + "";
    }

    public Categoria getCategoria() {
        if (prodottoCustom != null) return prodottoCustom.getProdottoOriginale().getCategoria();
        else return prodottoOriginale.getCategoria();
    }

    public BigDecimal getCifraBigDecimal() {
        if (prodottoCustom != null) return new BigDecimal(prodottoCustom.getPrezzo());
        else return new BigDecimal(prodottoOriginale.getPrezzo());
    }
}
