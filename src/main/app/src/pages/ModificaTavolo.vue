<template>
  <div class="row">
    <div class="col-md-12">
      <div class="row" style="padding-bottom: 15px; ">
        <div class="col-md-12" style="text-align: center">
          Numero
          <b-form-input v-model="numerotavolo" type="number" placeholder="Numero del tavolo"></b-form-input>
        </div>
      </div>
      <div class="row" style="padding-bottom: 15px; ">
        <div class="col-md-12" style="text-align: center">
          Descrizione (Facoltativo)
          <b-form-input v-model="descrizioneTavolo" placeholder="Inserisci una descrizione al tavolo"></b-form-input>
        </div>
      </div>
      <div class="row" style="padding-bottom: 15px; ">
        <div class="col" style="text-align: center">
          Coperti
          <b-form-input v-model="numeroCoperti" type="number" placeholder="Inserisci il numero dei coperti"></b-form-input>
        </div>
        <div class="col" style="text-align: center">
          Tipologia
          <b-form-select v-model="tipoCoperto" :options="listaTipiCoperti"></b-form-select>
        </div>
      </div>
    <div class="row" style="padding-bottom: 15px; ">
      <div class="col-md-12">
        <b-button block variant="primary" @click="salva()">Salva</b-button>
      </div>
    </div>
    </div>
  </div>
</template>
<script>

import TavoloService from "@/services/TavoloService";

export default {
  props: {idTavolo: String},
  mounted(){
    this.findTavolo(this.idTavolo);
  },
  data() {
    return {
      numerotavolo: null,
      listaTipiCoperti :
        [
          {
            text: "Coperto normale - 1,50€/pers",
            value: "1"
          },
          {
            text: "Nessun coperto - 0€/pers",
            value: "0"
          }
        ]
      ,
      numeroCoperti: 0,
      tipoCoperto: 1,
      descrizioneTavolo: null
    }
  },
  methods:{
    findTavolo(idTavolo) {
      this.$store.state.isLoading = true;
      TavoloService.findTavolo(idTavolo)
        .then((response) => {
          this.$store.state.isLoading = false;
          this.numerotavolo = response.numero;
          this.tipoCoperto = response.tipoCoperto;
          this.numeroCoperti = response.numCoperti;
          this.descrizioneTavolo = response.descrizione;
        })
    },
    salva(){
      this.$store.state.isLoading = true;
        TavoloService.editTavolo(
          {
            "descrizione": this.descrizioneTavolo,
            "numero": parseInt(this.numerotavolo),
            "dataInserimento": (new Date()).getTime(),
            "id": this.idTavolo,
            "aperto": true,
            "numCoperti": parseInt(this.numeroCoperti),
            "tipoCoperto" : this.tipoCoperto
          }).then((response) => {
          this.$store.state.isLoading = false;
          this.$router.push("/tavoli");
        })
    }
  }
};
</script>
<style>
</style>
