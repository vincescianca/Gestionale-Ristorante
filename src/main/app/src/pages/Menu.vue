<template>
  <div class="row">
    <div class="col-md-12">
      <div class="row" style="padding-bottom: 15px; ">
        <div class="col-md-12">
          <b-button block variant="primary" @click="aggiungi()">Aggiungi Menu</b-button>
      </div>
    </div>
    <div class="row" style="padding-bottom: 15px;  ">
      <div class="col">
        <card
        :title="'Menu'" :subTitle="'Lista Menu'">
        <div class="row">
          <div  class="col-md-12">
            <b-table-simple
              striped
              bordered
              responsive
              head-variant="light"
              small>
              <b-tr class="table-active">
                <b-th scope="col">Descrizione</b-th>
                <b-th scope="col">Data</b-th>
                <b-th scope="col">Attivo</b-th>
              </b-tr>
              <b-tr v-for="data in tableData" @click="apriMenu(data.id)">
              <b-td>{{data.descrizione}}</b-td>
              <b-td>{{data.dataCreazione}}</b-td>
              <b-td>
                {{data.attivo}}
                </b-td>
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

import MenuService from "@/services/MenuService";

export default {
  mounted(){
    this.caricaListaMenu();
  },
  data() {
    return {
      tableData: []
    }
  },
  methods:{
    caricaListaMenu(){
      this.$store.state.isLoading = true;
      MenuService.findAll()
        .then((response) => {
          this.tableData = response;
          this.$store.state.isLoading = false;
        }).catch(err =>{
        this.$store.state.isLoading = false;
      })
    },
    cambia(idMenu,attivo){
      var message;
      if(attivo=='Attivo') message = "ATTENZIONE: stai per disattivare un menu. L'operazione non comporta la cancellazione. Confermi?";
      else message = "ATTENZIONE: per cambiare lo stato da 'NON ATTIVO' a 'ATTIVO' devi assicurarti che non ci siano menu attivi. Confermi ?";this.$confirm(message).then(() => {
        this.$store.state.isLoading = true;
        MenuService.cambiaStato(idMenu)
          .then((response) => {
            this.caricaListaMenu();
            this.$store.state.isLoading = false;
          }).catch((err)=>{
          this.$store.state.isLoading = false;
          this.$alert("ERRORE: Impossibile cambiare stato al menu. Verifica lo stato degli altri menu.");
        })
      });
    },
    aggiungi(){
      this.$router.push("/aggiungi-menu");
    },
    cancella(idMenu){
      this.$confirm("L’operazione comporta la cancellazione. Conferma ?").then(() => {
        this.$store.state.isLoading = true;
        MenuService.deleteMenu(idMenu)
              .then((response) => {
                this.$store.state.isLoading = false;
          this.caricaListaMenu();
        }).catch((err)=>{
          this.$store.state.isLoading = false;
          this.$alert("ERRORE: Impossibile eliminare menu. Verifica che non ci siano categorie allegate a questa categoria.");
        })
      });
    },
    modifica(idMenu){
      this.$router.push("/modifica-menu/"+idMenu);
    },
    apriMenu(idMenu){
      this.$router.push("/menu/"+idMenu);
    }
  }
};
</script>
<style>
tr:hover {background-color: lightgray;}
</style>
