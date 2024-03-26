<template>
  <div class="row">
    <div class="row" style="padding-bottom: 15px; width: 100%; flex: auto;">
      <div class="col-md-12" style="text-align: center">
        Seleziona un menu
        <b-form-select v-model="menuSelezionato" :options="listaMenu"></b-form-select>
      </div>
    </div>
    <div class="row" style="padding-bottom: 15px; width: 100%; flex: auto;" v-if="menuSelezionato>0">
      <div class="col-md-12" style="text-align: center">
        Descrizione
        <b-form-input v-model="descrizioneCategoria" placeholder="Inserisci una descrizione della categoria"></b-form-input>
      </div>
    </div>
    <div class="row" style="padding-bottom: 15px; width: 100%; flex: auto;" v-if="menuSelezionato>0">
      <div class="col-md-12" style="text-align: center">
        Tipologia
        <b-form-select v-model="tipologiaCategoria" :options="listaTipologie"></b-form-select>
      </div>
    </div>
    <div class="row" style="padding-bottom: 15px; width: 100%; flex: auto;" v-if="menuSelezionato>0">
      <div class="col-md-12" style="text-align: center">
        Portata Default
        <b-form-input v-model="numPortata" type="number" placeholder="Inserisci la portata di default associata a questa categoria"></b-form-input>
      </div>
    </div>
    <div class="row" style="padding-bottom: 15px; width: 100%; flex: auto;" v-if="menuSelezionato>0 && tipologiaCategoria!=null">
      <div class="col-md-12">
        <b-button block variant="primary" @click="aggiungi()">Aggiungi Categoria</b-button>
      </div>
    </div>
  </div>
</template>
<script>

import MenuService from "@/services/MenuService";
import CategoriaService from "@/services/CategoriaService";

export default {
  mounted(){
    this.caricaListaMenu();
    this.caricaListaTipoologie()
  },
  data() {
    return {
      menuSelezionato: 0,
      listaMenu: [],
      numPortata: 1,
      listaTipologie: [],
      tipologiaCategoria: null,
      descrizioneCategoria: "",
    }
  },
  methods:{
    caricaListaMenu(){
      MenuService.findAllBFormSelect()
        .then((response) => {
          this.listaMenu = response;
        })
    },
    caricaListaTipoologie(){
      CategoriaService.caricaListaTipoologie()
        .then((response) => {
          this.listaTipologie = response;
        })
    },
    aggiungi(){
      CategoriaService.addCategoria(
        {
          "descrizione": this.descrizioneCategoria,
          "idMenu": parseInt(this.menuSelezionato),
          "numPortataStandard": parseInt(this.numPortata.value),
          "tipologia": this.tipologiaCategoria
        })
        .then((response) => {
          this.$router.push("/categorie");
        })
    }
  }
};
</script>
<style>
</style>
