package it.sciancaleporevincenzo.gestRest.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descrizione;
    private boolean attivo;
    private Date dataCreazione;

}
