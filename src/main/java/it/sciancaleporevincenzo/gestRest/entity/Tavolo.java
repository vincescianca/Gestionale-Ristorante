package it.sciancaleporevincenzo.gestRest.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "tavolo")
public class Tavolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;
    private String descrizione;
    private boolean aperto;
    private int numCoperti;
    private int tipoCoperto;
    private Timestamp dataInserimento;
}
