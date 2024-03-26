<template>
    <div class="row">
      <div class="col-md-12">
      <div class="row" style="padding-bottom: 15px;">
        <div class="col-md-12">
          <b-button block variant="warning" @click="aggiungi()">Aggiungi Tavolo</b-button>
        </div>
      </div>
      <div class="row" style="padding-bottom: 15px; ">
        <div v-for="table in tavoli" :key="table.id" class="col-md-12">
          <card
            :title="table.titolo" :subTitle="table.sottotitolo" :idTavolo="table.id">
            <div class="row">
              <div class="col-md-12">
                <table class="table
                        table-bordered
                        table-sm
                        table-striped">
                  <tr class="table-active">
                    <th scope="col">Descrizione</th>
                    <th scope="col">Pz.</th>
                    <th scope="col">Prezzo</th>
                  </tr>
                  <tr v-for="ordine in table.data"><td>{{ordine.ordine}}</td><td>{{ordine.quantita}}</td><td>{{ordine.prezzo}}€</td></tr>
                </table>
              </div>
            </div>
            <div class="row" style="padding-bottom: 15px;" >
              <div class="col">
                <b-button block variant="primary" @click="stampa(table.id)">Stampa Ricevuta</b-button>
              </div>
              <!--div class="col" v-if="table.aperto">
                <b-button block variant="danger" @click="chiudi(table.id)">Chiudi Tavolo</b-button>
              </div>
              <div class="col" v-if="!table.aperto">
                <b-button block variant="danger" @click="apri(table.id)">Apri Tavolo</b-button>
              </div-->
              <div class="col" v-if="table.aperto">
                <b-button block variant="success" @click="aggiungiOrdine(table.id)">Aggiungi Ordine</b-button>
              </div>
            </div>
            <div class="row" style="padding-bottom: 15px;" >

            </div>
          </card>
        </div>
      </div>
        <div class="row" style="padding-bottom: 15px;">
          <div class="col-md-12">
            <b-button block variant="warning" @click="aggiungi()">Aggiungi Tavolo</b-button>
          </div>
        </div>
        <div class="row" style="padding-bottom: 15px;">
          <div class="col-md-12">
            <b-button block variant="info" @click="tuttiTavoli()">Tavoli Archiviati</b-button>
          </div>
        </div>
      </div>
    </div>
</template>
<script>
import TavoloService from "@/services/TavoloService";

export default {
  mounted() {
    this.$store.state.isLoading = true;
      this.getListaTavoli();
      this.interval = window.setInterval(() => {
        this.getListaTavoli();
      }, 5000);
      for (let i = 1; i < this.interval; i++) {
        window.clearInterval(i);
      }
  },
  components: {
  },
  data() {
    return {
      interval : null,
      tavoli : [],
    };
  },
  methods : {
    getListaTavoliAll(){
      this.$store.state.isLoading = true;
      TavoloService.findAll()
        .then((response) => {
          this.$store.state.isLoading = false;
          this.tavoli = response;
        }).catch(err =>{
        this.$store.state.isLoading = false;
      })
    },
    myStopFunction() {
      clearInterval(this.interval);
    },
    getListaTavoli(){
      TavoloService.findAllAttivi()
        .then((response) => {
          this.$store.state.isLoading = false;
          this.tavoli = response;
        }).catch(err =>{
        this.$store.state.isLoading = false;
      })
    },
    tuttiTavoli(){
      this.$router.push("/tavoli-all");
    },
    aggiungi(){
      clearInterval(this.interval);
      this.$router.push("/aggiungi-tavolo");
    },
    aggiungiOrdine(idTavolo){
      this.$router.push("/aggiungi-ordine/"+idTavolo);
    },
    apri(idTavolo){
      this.$store.state.isLoading = true;
      TavoloService.apriTavolo(idTavolo)
        .then((response) => {
          this.$store.state.isLoading = false;
          this.$router.push("/tavoli");
          this.getListaTavoli();
          this.$alert("Tavolo aperto con successo.");
        }).catch(err =>{
        this.$store.state.isLoading = false;
      })
    },
    chiudi(idTavolo){
      this.$store.state.isLoading = true;
      TavoloService.chiudiTavolo(idTavolo)
        .then((response) => {
          this.$store.state.isLoading = false;
          this.$router.push("/tavoli");
          this.getListaTavoli();
          //this.$alert("Tavolo chiuso con successo.");
        }).catch(err =>{
          this.$store.state.isLoading = false;
      })
    },
    stampa(idTavolo){
      this.$store.state.isLoading = true;
      TavoloService.stampaNonFiscale(idTavolo)
        .then((response) => {
          this.$store.state.isLoading = false;
          this.$alert("Stampa inviata con successo.");
          this.chiudi(idTavolo);
        }).catch(err =>{
          this.$store.state.isLoading = false;
      })
    },
  }
};
</script>
<style>
</style>
