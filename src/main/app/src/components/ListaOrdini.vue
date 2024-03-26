<template>
  <div class="row" style="padding-bottom: 15px">
    <div class="col-md-12" >
      <center>Lista {{tipologia}}</center>
      <table class="table
                      table-bordered
                      table-sm
                      table-striped">
        <tr class="table-active">
          <th scope="col">Descrizione</th>
          <th scope="col"></th>
        </tr>
        <tr v-for="ordine in ordini">
          <td>{{ordine.quantita}} x {{ordine.ordine}}</td>
          <td align="right">
            <b-icon style="cursor: pointer;"
                    icon="trash-fill"
                    aria-hidden="true"
                    variant="danger"
                    font-scale="2" @click="elimina(ordine.id)">
            </b-icon>
          </td>
        </tr>
      </table>
    </div>
      <div class="col-md-12">
        <b-button block variant="info" @click="stampa()">Stampa sezione {{tipologia}}</b-button>
      </div>
  </div>
</template>

<script>
import OrdineService from "@/services/OrdineService";
import TavoloService from "@/services/TavoloService";

export default {
  props: {ordini: [], tipologia: String, idTavolo: String},
  name: "ListaOrdini",
  methods:{
    elimina(idOrdine){
      this.$confirm("L’operazione comporta la cancellazione dell'ordine. Conferma ?").then(() => {
        this.$store.state.isLoading = true;
        OrdineService.deleteOrdine(idOrdine)
          .then((response) => {
            this.caricaOrdini();
            this.$store.state.isLoading = false;
          }).catch((err)=>{
          this.$alert("ERRORE: Impossibile eliminare ordine.");
          this.$store.state.isLoading = false;
          this.caricaOrdini();
        })
      });
    },
    stampa(){
      this.$store.state.isLoading = true;
      this.selezionaTuttiGliOrdini();
      TavoloService.stampaParziale(this.idTavolo, this.ordini)
        .then((response) => {
          this.$store.state.isLoading = false;
          this.$alert("Stampa inviata con successo.");
          this.tavolo = response;
        }).catch((err)=>{
        this.$alert("ERRORE: Stampa non possibile.");
      });
    },
    selezionaTuttiGliOrdini(){
      this.ordini.forEach(ordine=>{
          ordine.selected = true;
      });
    }
  }
}
</script>

<style scoped>

</style>
