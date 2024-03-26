<template>
  <div class="row">
    <div class="col-md-12">
    <div class="row" style="padding-bottom: 15px;">
      <div class="col-md-12">
        <b-button block variant="primary" @click="aggiungi()">Aggiungi Categoria</b-button>
      </div>
    </div>
    <div class="row" style="padding-bottom: 15px;flex: auto;">
      <div class="col">
        <card
          :title="'Categorie'" :subTitle="'Lista Categorie'">
          <div class="row">
          <div class="col-md-12">
            <b-table-simple
              striped
              bordered
              responsive
              head-variant="light"
              small>
              <b-tr class="table-active">
                <b-th scope="col">Id</b-th>
                <b-th scope="col">Descrizione</b-th>
                <b-th scope="col">Menu</b-th>
              </b-tr>
              <b-tr v-for="prodotto in tableData" @click="apriCategoria(prodotto.id)">
                <b-td>{{prodotto.id}}</b-td>
                <b-td>{{prodotto.descrizione}}</b-td>
                <b-td>{{prodotto.menu}}</b-td>
              </b-tr>
            </b-table-simple>
          </div>
          </div>
        </card>
      </div>
    </div>
    </div>
  </div>
</template>
<script>

import CategoriaService from "@/services/CategoriaService";

export default {
  mounted(){
    this.caricaListaCategorie();
  },
  data() {
    return {
      tableData: []
    }
  },
  methods:{
    caricaListaCategorie(){
      this.$store.state.isLoading = true;
      CategoriaService.findAll()
        .then((response) => {
          this.$store.state.isLoading = false;
          this.tableData = response;
        })
    },
    aggiungi(){
      this.$router.push("/aggiungi-categoria");
    },
    apriCategoria(idCategoria){
      this.$router.push("/categoria/"+idCategoria);
    },
  }
};
</script>
<style>
tr:hover {background-color: lightgray;}
</style>
