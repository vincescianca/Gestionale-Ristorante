<template>
  <div class="row">
    <div class="col-md-12">
      <div class="row" style="padding-bottom: 15px;  flex: auto;">
        <div class="col">
          <card>
            <div class="row" style="padding-top: 15px">
              <div class="col-md-12">
                <table class="table
                        table-bordered
                        table-sm
                        table-striped">
                  <tr class="table-active">
                    <th scope="col"></th>
                    <th scope="col">Pezzi da stampare</th>
                    <th scope="col">Descrizione</th>
                    <th scope="col">Orario</th>
                  </tr>
                  <tr v-for="ordine in ordini">
                    <td style="vertical-align: middle;">
                      <b-form-checkbox
                        v-model="ordine.selected"></b-form-checkbox>
                    </td>
                    <td>
                      <b-form-input v-if="ordine.quantita>1"
                        v-model="ordine.quantitaDaStampare" type="number"
                      ></b-form-input>
                    </td>
                    <td style="white-space: nowrap;vertical-align: middle;">{{ordine.quantita}}x {{ordine.ordine}}</td><td style="vertical-align: middle;">{{ordine.dataInserimento}}</td></tr>
                </table>
              </div>
            </div>
          </card>
        </div>
      </div>
      <div class="row" style="padding-bottom: 15px;">
        <div class="col">
          <b-button block variant="info" @click="stampa()">Stampa Selezionati</b-button>
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
    this.caricaOrdini(this.idTavolo);
  },
  data() {
    return {
      tavolo: null,
      selezionati: [],
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
      })
    },
    indietro(){
      this.$router.push("/tavoli");
    },
    stampa() {
      this.$store.state.isLoading = true;
      if (this.checkOrdini()) {
        TavoloService.stampaParziale(this.idTavolo, this.ordini)
          .then((response) => {
            this.$store.state.isLoading = false;
            this.$alert("Stampa inviata con successo.");
            this.tavolo = response;
          }).catch((err) => {
          this.$alert("ERRORE: Stampa non possibile.");
        });
      } else {
        this.$store.state.isLoading = false;
      }
    },
    checkOrdini(){
      for(const x of this.ordini) {
        if (x.selected && (x.quantitaDaStampare > x.quantita)) {
          this.$alert("ATTENZIONE: stai provando a stampare più pezzi di quanti realmente ordinati");
          return false;
        }
      }
      return true;
    }
  }
};
</script>
<style>
</style>
