<template>
  <div class="row">
    <div class="row" style="padding-bottom: 15px; width: 100%; flex: auto;" >
      <div class="col-md-12" style="text-align: center">
        Nome del Menu
        <b-form-input v-model="nomeMenu" placeholder="Inserisci un nome al Menu"></b-form-input>
      </div>
    </div>
    <div class="row" style="padding-bottom: 15px; width: 100%; flex: auto;">
      <div class="col-md-12">
        <b-button block variant="primary" @click="modifica()">Modifica Menu</b-button>
      </div>
    </div>
  </div>
</template>
<script>

import MenuService from "@/services/MenuService";
import CategoriaService from "@/services/CategoriaService";
import ProdottoService from "@/services/ProdottoService";

export default {
  props: {idMenu: String},
  mounted(){
    this.caricaMenu(this.idMenu);
  },
  data() {
    return {
      nomeMenu: "",
      menu: null
    }
  },
  methods:{
    caricaMenu(idMenu){
      this.$store.state.isLoading = true;
      MenuService.findMenu(idMenu)
        .then((response) => {
          this.$store.state.isLoading = false;
          this.nomeMenu = response.descrizione;
          this.menu = response;
        })
    },
    modifica(){
      this.$store.state.isLoading = true;
      MenuService.editMenu(
        {
          "id" : this.menu.id,
          "descrizione": this.nomeMenu
        })
        .then((response) => {
          this.$store.state.isLoading = false;
          this.$router.push("/menu");
        })
    }
  }
};
</script>
<style>
</style>
