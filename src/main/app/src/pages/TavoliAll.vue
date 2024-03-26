<template>
    <div class="row">
      <div class="row" style="padding-bottom: 15px; width: 100%; flex: auto;">
        <div class="col-md-12">
          <table class="table
                          table-bordered
                          table-sm
                          table-striped">
            <tr class="table-active">
              <th scope="col">Numero</th>
              <th scope="col">Descrizione</th>
              <th scope="col">Data Apertura</th>
              <th scope="col">Stato</th>
              <th scope="col">Conto</th>
            </tr>
            <tr v-for="tavolo in tavoli" @click="apriTavolo(tavolo.id)">
              <td>{{tavolo.numero}}</td>
              <td>{{tavolo.descrizione}}</td>
              <td>{{tavolo.dataInserimentoStr2}}</td>
              <td v-if="tavolo.aperto">In Corso</td>
              <td v-if="!tavolo.aperto">Chiuso</td>
              <td>{{tavolo.contoTotale}} €</td>
            </tr>
          </table>
        </div>
      </div>
    </div>
</template>
<script>
import TavoloService from "@/services/TavoloService";

export default {
  mounted() {
      this.getListaTavoliAll();
  },
  components: {
  },
  data() {
    return {
      tavoli : [],
    };
  },
  methods : {
    getListaTavoliAll(){
      this.$store.state.isLoading = true;
      TavoloService.findAll()
        .then((response) => {
          this.tavoli = response;
          this.$store.state.isLoading = false;
        }).catch(err =>{
        this.$store.state.isLoading = false;
      })
    },
    apriTavolo(idTavolo){
      this.$router.push("/tavolo/"+idTavolo);
    }
  }
};
</script>
<style>
</style>
