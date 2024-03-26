<template>
  <div class="row">
    <div class="col-md-12">
      <div class="row" style="padding-bottom: 15px;  flex: auto;">
        <div class="col" v-if="tavolo!=null">
          <card
            :title="tavolo.descrizione" >
            <div class="row">
              <div class="col-md-12">
                <b>Numero:</b> {{tavolo.numero}}<br/>
                <b>Coperti:</b> {{tavolo.numCoperti}}€<br/>
                <b>Tipo Coperto:</b> {{tavolo.tipoCoperto}}<br/>
                <b>Data:</b> {{tavolo.dataInserimento}}
              </div>
            </div>
            <div class="row" style="padding-top: 15px">
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
                  <tr v-for="ordine in ordini"><td>{{ordine.ordine}}</td><td>{{ordine.quantita}}</td><td>{{ordine.prezzo}}€</td></tr>
                </table>
              </div>
            </div>
          </card>
        </div>
      </div>
      <div class="row" style="padding-bottom: 15px;">
        <div class="col" v-if="tavolo!=null && tavolo.aperto">
          <b-button block variant="danger" @click="chiudi()">Chiudi</b-button>
        </div>
        <div class="col" v-if="tavolo!=null && !tavolo.aperto">
          <b-button block variant="danger" @click="apri()">Apri</b-button>
        </div>
        <div class="col">
          <b-button block variant="info" @click="stampa()">Stampa Ricevuta</b-button>
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

import TavoloService from "@/services/TavoloService";
import OrdineService from "@/services/OrdineService";

export default {
  props: {idTavolo: String},
  mounted(){
    this.caricaTavolo(this.idTavolo);
    this.caricaOrdini(this.idTavolo);
  },
  data() {
    return {
      tavolo: null,
      ordini: null
    }
  },
  methods:{
    caricaTavolo(idTavolo){
      TavoloService.findTavolo(idTavolo)
        .then((response) => {
          this.tavolo = response;
        }).catch((err)=>{
        this.$alert("ERRORE: Tavolo non trovato.");
      })
    },
    caricaOrdini(idTavolo){
      this.$store.state.isLoading = true;
      OrdineService.findByTableCustom(idTavolo)
        .then((response) => {
          this.ordini = response;
          this.$store.state.isLoading = false;
        }).catch((err)=>{
        this.$alert("ERRORE: Ordini non trovati.");
        this.$store.state.isLoading = false;
      })
    },
    indietro(){
      this.$router.push("/tavoli-all");
    },
    chiudi(){
      this.$store.state.isLoading = true;
      TavoloService.chiudiTavolo(this.idTavolo)
        .then((response) => {
          this.$store.state.isLoading = false;
          this.$router.push("/tavoli");
          this.$alert("Tavolo chiuso con successo.");
        }).catch(err =>{
        this.$store.state.isLoading = false;
      })
    },
    apri(){
      this.$store.state.isLoading = true;
      TavoloService.apriTavolo(this.idTavolo)
        .then((response) => {
          this.$store.state.isLoading = false;
          this.$router.push("/tavoli");
          this.$alert("Tavolo aperto con successo.");
        }).catch(err =>{
        this.$store.state.isLoading = false;
      })
    },
    stampa(idTavolo){
      this.$store.state.isLoading = true;
      TavoloService.stampaNonFiscale(this.idTavolo)
        .then((response) => {
          this.$store.state.isLoading = false;
          this.$alert("Stampa inviata con successo.");
        }).catch(err =>{
        this.$store.state.isLoading = false;
      })
    },
  }
};
</script>
<style>
</style>
