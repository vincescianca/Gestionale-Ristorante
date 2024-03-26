package it.sciancaleporevincenzo.gestRest.model;

import it.sciancaleporevincenzo.gestRest.entity.Ordine;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.SimpleDateFormat;


@Data
@AllArgsConstructor
public class OrdineTableCustomModel {
    private String ordine;
    private int quantita;
    private int quantitaDaStampare;
    private double prezzo;
    private long id;
    private String dataInserimento;
    private boolean selected;

    public OrdineTableCustomModel(Ordine po) {
        this.dataInserimento = new SimpleDateFormat("HH:mm").format(po.getDataInserimento());
        this.id = po.getId();
        this.quantita = po.getQuantita();
        this.ordine = "";
        this.quantitaDaStampare = po.getQuantita();
        this.ordine = (po.getIdProdottoCustom() != null && po.getIdProdottoCustom() > 0) ?
                this.ordine.concat(po.getProdottoCustom().getNome()
                        + getEliminazioni(po.getProdottoCustom().getEliminazioni())
                        + getAggiunte(po.getProdottoCustom().getAggiunte()))
                        + getNote(po.getProdottoCustom().getDescrizione())
                : this.ordine.concat(po.getProdottoOriginale().getNome());
        this.prezzo = (po.getIdProdottoCustom() != null && po.getIdProdottoCustom() > 0) ?
                po.getProdottoCustom().getPrezzo() * this.quantita
                : po.getProdottoOriginale().getPrezzo() * this.quantita;
    }

    private String getNote(String descrizione) {
        return (descrizione.trim().length() > 0) ? "Note: " + descrizione + " " : "";
    }

    private String getAggiunte(String aggiunte) {
        return (aggiunte != null && aggiunte.trim().length() > 0) ? " Con: " + aggiunte + " " : "";
    }

    private static String getEliminazioni(String eliminazioni) {
        return (eliminazioni != null && eliminazioni.trim().length() > 0) ? " Senza: " + eliminazioni + " " : "";
    }
}
