<template>
  <div class="row">
    <div class="col-md-12">
    <div class="row" style="padding-bottom: 15px; ">
      <div class="col-md-12" style="text-align: center">
        Nome Menu
        <b-form-input v-model="nomeMenu" placeholder="Inserisci un nome del menu"></b-form-input>
      </div>
    </div>
      <div class="row" style="padding-bottom: 15px; " >
        <div class="col-md-12">
          <b-button block variant="primary" @click="aggiungi()">Aggiungi Menu</b-button>
        </div>
      </div>
  </div>
  </div>
</template>
<script>

import MenuService from "@/services/MenuService";
import CategoriaService from "@/services/CategoriaService";

export default {
  mounted(){
  },
  data() {
    return {
      nomeMenu: "",
    }
  },
  methods:{
    aggiungi(){
      this.$store.state.isLoading = true;
      MenuService.addMenu(
        {
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
