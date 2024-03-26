package it.sciancaleporevincenzo.gestRest.model;

import it.sciancaleporevincenzo.gestRest.entity.Tavolo;
import it.sciancaleporevincenzo.gestRest.enums.Coperto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;


@Data
@AllArgsConstructor
public class TavoloModel {
    private Long id;
    private String descrizione;
    private int numero;
    private String dataInserimentoStr;
    private String dataInserimentoStr2;
    private Timestamp dataInserimento;
    private boolean aperto;
    private String titolo;
    private String sottotitolo;
    private String azione;
    private BigDecimal contoTotale;
    private List<OrdineTableCustomModel> data;

    public TavoloModel(Tavolo po) {
        this.id = po.getId();
        this.descrizione = po.getDescrizione();
        this.numero = po.getNumero();
        this.dataInserimentoStr = new SimpleDateFormat("HH:mm").format(po.getDataInserimento());
        this.dataInserimento = po.getDataInserimento();
        this.aperto = po.isAperto();
        this.titolo = "Tavolo " + this.numero + " - " + ((aperto) ? "IN CORSO" : "CHIUSO") + " - " + this.dataInserimentoStr;
        this.sottotitolo = this.descrizione;
    }

    public TavoloModel(Tavolo po, List<OrdineTableCustomModel> data) {
        this.id = po.getId();
        this.descrizione = po.getDescrizione();
        this.numero = po.getNumero();
        this.dataInserimentoStr = new SimpleDateFormat("HH:mm").format(po.getDataInserimento());
        this.dataInserimentoStr2 = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(po.getDataInserimento());
        this.dataInserimento = po.getDataInserimento();
        this.aperto = po.isAperto();
        this.titolo = "Tavolo " + this.numero + " - " + ((aperto) ? "IN CORSO" : "CHIUSO") + " - " + this.dataInserimentoStr;
        this.sottotitolo = this.descrizione;
        this.data = data;

        this.contoTotale = new BigDecimal(0);
        data.stream().forEach(x -> {
            BigDecimal singlePrice = new BigDecimal(x.getPrezzo());
            this.contoTotale = this.contoTotale.add(singlePrice);
        });
        BigDecimal valCoperto = new BigDecimal(Coperto.fromtipoCoperto(po.getTipoCoperto()).getPrezzo());
        this.contoTotale = this.contoTotale.add(valCoperto.multiply(new BigDecimal(po.getNumCoperti())));

    }

}
