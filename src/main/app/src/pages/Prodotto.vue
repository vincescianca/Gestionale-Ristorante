<template>
  <div class="row">
    <div class="col-md-12">
    <div class="row" style="padding-bottom: 15px;  flex: auto;">
      <div class="col" v-if="prodotto != null">
        <card
          :title="prodotto.nome" :subTitle="prodotto.descrizione">
          <div class="row">
            <div class="col-md-12">
              <b>Prezzo:</b> {{prodotto.prezzo}}€<br/>
              <b>Categoria:</b> {{prodotto.categoria.descrizione}}<br/>
              <b>Menu:</b> {{prodotto.categoria.menu.descrizione}}<br/>
              <b>Data Inserimento:</b> {{prodotto.dataInserimento}}
            </div>
          </div>
        </card>
      </div>
    </div>
    <div class="row" style="padding-bottom: 15px;">
      <div class="col">
        <b-button block variant="danger" @click="cancella()">Cancella</b-button>
      </div>
      <div class="col">
        <b-button block variant="info" @click="modifica()">Modifica</b-button>
      </div>
    </div>
    <div class="row" style="padding-bottom: 15px;">
      <div class="col-md-12">
        <b-button block variant="primary" @click="indietro()">Indietro</b-button>
      </div>
    </div>
    </div>
  </div>
</template>
<script>

import ProdottoService from "@/services/ProdottoService";

export default {
  props: {idProdotto: String},
  mounted(){
    this.caricaProdotto(this.idProdotto);
  },
  data() {
    return {
      prodotto: null,
    }
  },
  methods:{
    caricaProdotto(idProdotto){
      this.$store.state.isLoading = true;
      ProdottoService.findProdotto(idProdotto)
        .then((response) => {
          this.$store.state.isLoading = false;
          this.prodotto = response;
        }).catch((err)=>{
        this.$store.state.isLoading = false;
        this.$alert("ERRORE: Prodotto non trovato.");
      })
    },
    cancella(){
      this.$confirm("L’operazione comporta la cancellazione. Conferma ?").then(() => {
        this.$store.state.isLoading = true;
      ProdottoService.deleteProdotto(this.idProdotto)
        .then((response) => {
          this.$store.state.isLoading = false;
          this.indietro();
        }).catch((err)=>{
        this.$store.state.isLoading = false;
        this.$alert("ERRORE: Impossibile eliminare Prodotto.");
      })
      });
    },
    modifica(){
      this.$router.push("/modifica-prodotto/"+this.idProdotto);
    },
    indietro(){
      this.$router.push("/prodotti/");
    },
  }
};
</script>
<style>
</style>
