package it.sciancaleporevincenzo.gestRest.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idMenu;

    @ManyToOne
    @JoinColumn(name = "idMenu", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    private Menu menu;

    private String descrizione;
    private String tipologia;
    private Date ultimaModifica;

    private int numPortataStandard;

}
