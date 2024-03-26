<template>
  <div class="row">
    <div class="row" style="padding-bottom: 15px; width: 100%; flex: auto;">
      <div class="col-md-12" style="text-align: center">
        Seleziona un menu
        <b-form-select v-model="menuSelezionato" :options="listaMenu"></b-form-select>
      </div>
    </div>
    <div class="row" style="padding-bottom: 15px; width: 100%; flex: auto;" v-if="menuSelezionato>0">
      <div class="col-md-12" style="text-align: center" @click="caricaListaCategorieByMenu(menuSelezionato)">
          Seleziona una categoria
          <b-form-select v-model="categoriaSelezionata" :options="listaCategorie"></b-form-select>
      </div>
    </div>
    <div class="row" style="padding-bottom: 15px; width: 100%; flex: auto;" v-if="categoriaSelezionata>0">
      <div class="col-md-12" style="text-align: center">
        Nome
        <b-form-input v-model="nomeProdotto" placeholder="Inserisci il nome del prodotto"></b-form-input>
      </div>
    </div>
    <div class="row" style="padding-bottom: 15px; width: 100%; flex: auto;" v-if="categoriaSelezionata>0">
      <div class="col-md-12" style="text-align: center">
        Descrizione (Ingredienti)
        <b-form-input v-model="descrizioneProdotto" placeholder="Inserisci la descrizione del prodotto"></b-form-input>
      </div>
    </div>
    <div class="row" style="padding-bottom: 15px; width: 100%; flex: auto;" v-if="categoriaSelezionata>0">
      <div class="col-md-12" style="text-align: center">
        Prezzo
        <b-form-input v-model="prezzoProdotto" type="number" placeholder="Inserisci il prezzo del prodotto"></b-form-input>
      </div>
    </div>
    <div class="row" style="padding-bottom: 15px; width: 100%; flex: auto;"
         v-if="categoriaSelezionata>0">
      <div class="col-md-12">
        <b-button block variant="primary" @click="aggiungi()">Aggiungi Prodotto</b-button>
      </div>
    </div>
  </div>
</template>
<script>

import MenuService from "@/services/MenuService";
import CategoriaService from "@/services/CategoriaService";
import ProdottoService from "@/services/ProdottoService";

export default {
  mounted(){
    this.caricaListaMenu();
  },
  data() {
    return {
      categoriaSelezionata: 0,
      menuSelezionato: 0,
      listaMenu: [],
      listaCategorie: [],
      nomeProdotto: "",
      descrizioneProdotto: "",
      prezzoProdotto: null
    }
  },
  methods:{
    caricaListaMenu(){
      MenuService.findAllBFormSelect()
        .then((response) => {
          this.listaMenu = response;
        })
    },
    caricaListaCategorieByMenu(idMenu){
      CategoriaService.findByIdMenuBFormSelect(idMenu)
        .then((response) => {
          this.listaCategorie = response;
        })
    },
    aggiungi(){
      this.$store.state.isLoading = true;
      ProdottoService.addProdotto(
        {
          "nome":this.nomeProdotto,
          "descrizione": this.descrizioneProdotto,
          "prezzo": this.prezzoProdotto,
          "idCategoria": parseInt(this.categoriaSelezionata)
        })
        .then((response) => {
          this.$store.state.isLoading = false;
          this.$router.push("/prodotti");
        })
    }
  }
};
</script>
<style>
</style>
