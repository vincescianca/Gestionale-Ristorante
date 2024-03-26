<template>
  <div class="row">
    <div class="col-md-12">
      <div class="row" style="padding-bottom: 15px;  flex: auto;">
        <div class="col" v-if="categoria!=null">
          <card
            :title="categoria.descrizione" >
            <div class="row">
              <div class="col-md-12">
                <b>ID:</b> {{categoria.id}}€<br/>
                <b>Menu:</b> {{categoria.menu}}<br/>
                <b>Tipologia:</b> {{categoria.tipologia}}<br/>
                <b>Data Inserimento:</b> {{categoria.ultimaModifica}}<br/>
                <b>Portata Default:</b> {{categoria.numPortataStandard}}
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

import CategoriaService from "@/services/CategoriaService";

export default {
  props: {idCategoria: String},
  mounted(){
    this.caricaCategoria();
  },
  data() {
    return {
      categoria: null
    }
  },
  methods:{
    caricaCategoria(){
      CategoriaService.findCategoria(this.idCategoria)
        .then((response) => {
          this.categoria = response;
        })
    },
    cancella(idCategoria){
      this.$confirm("L’operazione comporta la cancellazione. Conferma ?").then(() => {
        CategoriaService.deleteCategoria(idCategoria)
              .then((response) => {
          this.caricaListaCategorie();
        }).catch((err)=>{
          this.$alert("ERRORE: Impossibile eliminare categoria. Verifica che non ci siano prodotti allegati a questa categoria.");
        })
      });
    },
    modifica(){
      this.$router.push("/modifica-categoria/"+this.idCategoria);
    },
    indietro(){
      this.$router.push("/categorie/");
    },
  }
};
</script>
<style>
tr:hover {background-color: lightgray;}
</style>
