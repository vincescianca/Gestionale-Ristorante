package it.sciancaleporevincenzo.gestRest.model;

import it.sciancaleporevincenzo.gestRest.entity.Categoria;
import it.sciancaleporevincenzo.gestRest.entity.Ordine;
import it.sciancaleporevincenzo.gestRest.entity.Tavolo;
import it.sciancaleporevincenzo.gestRest.enums.Coperto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ElementoScontrino {
    private String descrizione;
    private String aggiunte;
    private Categoria categoria;
    private BigDecimal prezzo;
    private int quantita;
    private int portata;

    public ElementoScontrino(Ordine ordine) {
        this.descrizione = ordine.getNome();
        this.aggiunte = ordine.getAggiunte() + ordine.getEliminazioni() + ordine.getNote();
        this.prezzo = ordine.getCifraBigDecimal();
        this.quantita = ordine.getQuantita();
        this.categoria = ordine.getCategoria();
        this.portata = ordine.getNumPortata();
    }

    public ElementoScontrino(Tavolo tavolo) {
        if (tavolo.getTipoCoperto() == Coperto.NESSUNO.getTipoCoperto()) this.descrizione = "Coperto gratuito";
        if (tavolo.getTipoCoperto() == Coperto.NORMALE.getTipoCoperto()) this.descrizione = "Coperto normale";
        if (tavolo.getTipoCoperto() == Coperto.SPECIALE.getTipoCoperto()) this.descrizione = "Coperto speciale";
        this.prezzo = new BigDecimal(Coperto.fromtipoCoperto(tavolo.getTipoCoperto()).getPrezzo());
        this.quantita = tavolo.getNumCoperti();
    }
}
