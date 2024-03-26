package it.sciancaleporevincenzo.gestRest.enums;


import lombok.Getter;

import java.util.Arrays;

public enum Coperto {
    NESSUNO(0, 0),
    NORMALE(1, 1.50),
    SPECIALE(2, 2);

    @Getter
    private int tipoCoperto;
    @Getter
    private double prezzo;

    Coperto(int numero, double prezzo) {
        this.tipoCoperto = numero;
        this.prezzo = prezzo;
    }

    public static Coperto fromtipoCoperto(int tipoCoperto) {
        return Arrays.asList(Coperto.values()).stream()
                .filter(coperto -> coperto.getTipoCoperto() == tipoCoperto)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Valore Coperto non trovato:" + tipoCoperto));
    }
}
