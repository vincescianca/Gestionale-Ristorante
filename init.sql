CREATE TABLE menu
(
    id             serial  NOT NULL,
    descrizione    varchar NULL,
    data_creazione date    NULL,
    attivo         bool    NULL,
    CONSTRAINT menu_pk PRIMARY KEY (id),
    CONSTRAINT menu_un UNIQUE (id)
);
CREATE INDEX menu_id_idx ON menu USING btree (id);

CREATE TABLE categoria
(
    id              serial  NOT NULL,
    id_menu         int4    NOT NULL,
    descrizione     varchar NULL,
    ultima_modifica date    NULL,
    tipologia       varchar NULL,
    CONSTRAINT categorie_pk PRIMARY KEY (id)
);
CREATE INDEX categorie_id_idx ON categoria USING btree (id, id_menu);
ALTER TABLE categoria
    ADD CONSTRAINT
        categorie_fk FOREIGN KEY (id_menu) REFERENCES menu (id);

CREATE TABLE prodotto_originale
(
    id               serial  NOT NULL,
    nome             varchar NULL,
    descrizione      varchar NULL,
    prezzo           float8  NULL,
    id_categoria     int4    NOT NULL,
    data_inserimento date    NOT NULL,
    CONSTRAINT prodotti_pk PRIMARY KEY (id)
);
CREATE INDEX prodotti_id_idx ON prodotto_originale USING btree (id);
ALTER TABLE prodotto_originale
    ADD CONSTRAINT prodotti_fk FOREIGN KEY (id_categoria) REFERENCES categoria (id);

CREATE TABLE prodotto_custom
(
    id                    serial  NOT NULL,
    descrizione           varchar NULL,
    id_prodotto_originale int4    NULL,
    prezzo                float8  NULL,
    eliminazioni          varchar NULL,
    aggiunte              varchar NULL,
    nome                  varchar NULL,
    CONSTRAINT prodottiprenotati_pk PRIMARY KEY (id)
);
ALTER TABLE prodotto_custom
    ADD CONSTRAINT prodottiprenotati_fk FOREIGN KEY (id_prodotto_originale) REFERENCES
        prodotto_originale (id);

CREATE TABLE tavolo
(
    id               serial       NOT NULL,
    numero           int4         NOT NULL,
    descrizione      varchar      NULL,
    aperto           bool         NOT NULL DEFAULT true,
    data_inserimento timestamp(0) NOT NULL,
    num_coperti      int4         NULL,
    tipo_coperto     int4         NULL,
    CONSTRAINT tavoli_pk PRIMARY KEY (id)
);
CREATE INDEX tavoli_id_idx ON tavolo USING btree (id);

CREATE TABLE ordine
(
    id                    serial    NOT NULL,
    id_tavolo             int4      NOT NULL,
    id_prodotto_custom    int4      NULL,
    data_inserimento      timestamp NOT NULL,
    id_prodotto_originale int4      NULL,
    quantita              int4      NULL,
    CONSTRAINT ordini_pk PRIMARY KEY (id)
);
CREATE INDEX ordine_prodotto_custom_idx ON ordine USING btree (id_prodotto_originale);
CREATE INDEX
    ordine_prodotto_originale_idx ON ordine USING btree (id_prodotto_custom);
ALTER TABLE ordine
    ADD CONSTRAINT
        ordine_fk FOREIGN KEY (id_prodotto_custom) REFERENCES prodotto_custom (id);
ALTER TABLE ordine
    ADD CONSTRAINT
        ordine_fk_1 FOREIGN KEY (id_prodotto_originale) REFERENCES prodotto_originale (id);
ALTER TABLE ordine
    ADD
        CONSTRAINT ordini_fk_1 FOREIGN KEY (id_tavolo) REFERENCES tavolo (id);


ALTER TABLE ordine
    ADD num_portata int4 NOT NULL DEFAULT 1;

ALTER TABLE categoria
    ADD num_portata_standard int NOT NULL DEFAULT 1;

