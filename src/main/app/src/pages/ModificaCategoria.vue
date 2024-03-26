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
    <div class="row" style="padding-bottom: 15px; width: 100%; flex: auto; " v-if="menuSelezionato>0">
      <div class="col-md-12" style="text-align: center">
        Portata Default
        <VSelect :options="listPortate" v-model="numPortata" searchable />
      </div>
    </div>
    <div class="row" style="padding-bottom: 15px; width: 100%; flex: auto;" v-if="menuSelezionato>0 && tipologiaCategoria!=null">
      <div class="col-md-12">
        <b-button block variant="primary" @click="modifica()">Modifica Categoria</b-button>
      </div>
    </div>
  </div>
</template>
<script>

import MenuService from "@/services/MenuService";
import CategoriaService from "@/services/CategoriaService";
import VSelect from '@alfsnd/vue-bootstrap-select'
import OrdineService from "@/services/OrdineService";

export default {
  props: {idCategoria: String},
  components:{VSelect},
  mounted(){
    this.caricaListaMenu();
    this.caricaCategoria(this.idCategoria);
    this.caricaListaTipoologie();
  },
  data() {
    return {
      listPortate: [],
      categoria: null,
      tipologiaCategoria: null,
      descrizioneCategoria: null,
      listaTipologie: [],
      numPortata: null,
      menuSelezionato: 0,
      listaMenu: [],
    }
  },
  methods:{
    caricaListaMenu(){
      MenuService.findAllBFormSelect()
        .then((response) => {
          this.listaMenu = response;
        })
    },
    caricaMenuByDesc(desc){
      MenuService.findMenuByDesc()
        .then((response) => {
          this.menuSelezionato = response.id;
        })
    },
    caricaListaTipoologie(){
      CategoriaService.caricaListaTipoologie()
        .then((response) => {
          this.listaTipologie = response;
        })
    },
    caricaListaCategorieByMenu(idMenu){
      CategoriaService.findByIdMenuBFormSelect(idMenu)
        .then((response) => {
          this.listaCategorie = response;
        })
    },
    caricaPortate(){
      OrdineService.getPortate().then(response=>{
        this.listPortate = response;
      });
    },
    caricaCategoria(idCategoria){
      CategoriaService.findCategoria(idCategoria)
        .then((response) => {
          this.categoria = response;
          this.menuSelezionato = this.categoria.idMenu;
          this.tipologiaCategoria = this.categoria.tipologiaSigla;
          this.caricaListaCategorieByMenu(this.menuSelezionato);
          this.descrizioneCategoria = this.categoria.descrizione;

          this.caricaPortate();
          OrdineService.getPortata(this.categoria.numPortataStandard).then(response=>{
            this.numPortata = response;
          });

        })
    },
    modifica(){
      CategoriaService.editCategoria(
        {
          "id": this.categoria.id,
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
