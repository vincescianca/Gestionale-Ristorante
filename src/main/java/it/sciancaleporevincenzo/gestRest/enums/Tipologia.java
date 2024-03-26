package it.sciancaleporevincenzo.gestRest.enums;

import it.sciancaleporevincenzo.gestRest.model.BFormSelectModel;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Tipologia {
    FOOD("F"),
    DRINK("D"),
    ALTRO("A");

    @Getter
    private String sigla;

    Tipologia(String sigla) {
        this.sigla = sigla;
    }

    public static List<BFormSelectModel> getListaTipologie() {
        return Arrays.asList(Tipologia.values()).stream().map(x -> new BFormSelectModel(x.sigla, x.name())).collect(Collectors.toList());
    }

    public static Tipologia fromCodTipologia(String codTipologia) {
        return Arrays.asList(Tipologia.values()).stream()
                .filter(tipo -> tipo.getSigla().equals(codTipologia))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Valore Tipologia non trovato:" + codTipologia));
    }

    public static Tipologia fromName(String name) {
        return Arrays.asList(Tipologia.values()).stream()
                .filter(tipo -> tipo.name().equals(name))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Nome Tipologia non trovato:" + name));
    }
}
