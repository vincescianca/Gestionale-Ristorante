<template>
  <div class="row">
    <div class="col-md-12">
    <div class="row" style="padding-bottom: 15px; ">
      <div class="col-md-12">
        <b-button block variant="primary" @click="aggiungi()">Aggiungi Prodotto</b-button>
      </div>
    </div>
    <div class="row" style="padding-bottom: 15px;  ">
      <div class="col">
        <card
          :title="'Prodotti'" :subTitle="'Lista Prodotti'">
          <div class="row">
            <div  class="col-md-12">
            <b-table-simple
              striped
              bordered
              responsive
              head-variant="light"
              small>
              <b-tr class="table-active">
                <b-th scope="col">Id</b-th>
                <b-th scope="col">Nome</b-th>
                <b-th scope="col">Prezzo</b-th>
              </b-tr>
              <b-tr v-for="prodotto in tableData" @click="apriProdotto(prodotto.id)">
                <b-td>{{prodotto.id}}</b-td>
                <b-td>{{prodotto.nome}}</b-td>
                <b-td>{{prodotto.prezzo}}€</b-td>
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

import ProdottoService from "@/services/ProdottoService";

export default {
  mounted(){
    this.caricaListaProdotti();
  },
  data() {
    return {
      tableData: []
    }
  },
  methods:{
    caricaListaProdotti(){
      this.$store.state.isLoading = true;
      ProdottoService.findAll()
        .then((response) => {
          this.$store.state.isLoading = false;
          this.tableData = response;
        })
    },
    aggiungi(){
      this.$router.push("/aggiungi-prodotto");
    },
    apriProdotto(prodottoId){
      this.$router.push("/prodotto/"+prodottoId);
    },
  }
};
</script>
<style>
  tr:hover {background-color: lightgray;}
</style>
