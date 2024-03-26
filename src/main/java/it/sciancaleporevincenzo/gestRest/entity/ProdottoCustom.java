package it.sciancaleporevincenzo.gestRest.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "prodotto_custom")
public class ProdottoCustom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descrizione;

    private Long idProdottoOriginale;

    @ManyToOne
    @JoinColumn(name = "idProdottoOriginale", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    private ProdottoOriginale prodottoOriginale;

    private double prezzo;

    private String eliminazioni;
    private String aggiunte;
    private String nome;

}
