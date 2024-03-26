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
        <b-button block variant="primary" @click="avanti()">Avanti</b-button>
      </div>
    </div>
    </div>
  </div>
</template>
<script>

import TavoloService from "@/services/TavoloService";

export default {
  mounted(){
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
    avanti(){
      TavoloService.addTavolo(
        {
          "descrizione": this.descrizioneTavolo,
          "numero": parseInt(this.numerotavolo),
          "numCoperti": parseInt(this.numeroCoperti),
          "tipoCoperto" : this.tipoCoperto
        })
        .then((response) => {
          this.$router.push("/aggiungi-ordine/"+response);
        })
    }
  }
};
</script>
<style>
</style>
