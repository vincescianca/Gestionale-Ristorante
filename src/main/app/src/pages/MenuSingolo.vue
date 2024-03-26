<template>
  <div class="row">
    <div class="col-md-12">
      <div class="row" style="padding-bottom: 15px;  flex: auto;">
        <div class="col" v-if="menu!=null">
          <card
            :title="menu.descrizione">
            <div class="row">
              <div class="col-md-12">
                <b>ID:</b> {{menu.id}}<br/>
                <b v-if="menu.attivo">Attivo</b> <b v-if="!menu.attivo">Non Attivo</b> <br/>
                <b>Data Inserimento:</b> {{menu.dataCreazione}}
              </div>
            </div>
          </card>
        </div>
      </div>
      <div class="row" style="padding-bottom: 15px;">
        <div class="col">
          <b-button block variant="info" @click="cambia(menu.id, menu.attivo)">Cambia Stato</b-button>
        </div>
        <div class="col">
          <b-button block variant="info" @click="modifica()">Modifica</b-button>
        </div>
      </div>
      <div class="row" style="padding-bottom: 15px;">
        <div class="col">
          <b-button block variant="danger" @click="cancella()">Cancella</b-button>
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
import MenuService from "@/services/MenuService";

export default {
  props: {idMenu: String},
  mounted(){
    this.caricaMenu(this.idMenu);
  },
  data() {
    return {
      menu : null,
    }
  },
  methods:{
    caricaMenu(idMenu){
      this.$store.state.isLoading = true;
      MenuService.findMenu(idMenu)
        .then((response) => {
          this.$store.state.isLoading = false;
          this.menu = response;
        }).catch((err)=>{
        this.$store.state.isLoading = false;
      })
    },
    cambia(idMenu,attivo){
      var message;
      if(attivo) message = "ATTENZIONE: stai per disattivare un menu. L'operazione non comporta la cancellazione. Confermi?";
      else message = "ATTENZIONE: per cambiare lo stato da 'NON ATTIVO' a 'ATTIVO' devi assicurarti che non ci siano menu attivi. Confermi ?";this.$confirm(message).then(() => {
        this.$store.state.isLoading = true;
        MenuService.cambiaStato(idMenu)
          .then((response) => {
            this.$store.state.isLoading = false;
            this.caricaMenu(idMenu);
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
    indietro(){
      this.$router.push("/menu/");
    },
  }
};
</script>
<style>
tr:hover {background-color: lightgray;}
</style>
