package it.sciancaleporevincenzo.gestRest.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "prodotto_originale")
public class ProdottoOriginale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descrizione;
    private double prezzo;
    private long idCategoria;

    @ManyToOne
    @JoinColumn(name = "idCategoria", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    Categoria categoria;

    private Date dataInserimento;

}
