<template>
  <div class="row">
    <div class="col-md-12">
    <div class="row" style="padding-bottom: 15px; ">
      <div class="col-md-12" style="text-align: center">
        Prodotto
        <VSelect :options="prodotti" v-model="prodottoSelezionato" searchable />
      </div>
    </div>
    <div class="row" style="padding-bottom: 15px; ">
      <div class="col-md-12" style="text-align: center">
        Quantità
        <b-form-input v-model="quantita" placeholder="Definisci una quantità"></b-form-input>
      </div>
    </div>


    <div class="row" style="padding-bottom: 15px; ">
      <div class="accordion col-md-12" role="tablist">
        <b-card no-body class="mb-1">
          <b-card-header header-tag="header" class="p-1" role="tab">
            <b-button block v-b-toggle.accordion-1 variant="secondary" @click="caricaPortata()">Più informazioni</b-button>
          </b-card-header>
          <b-collapse id="accordion-1" accordion="my-accordion" role="tabpanel">
            <b-card-body>
              <div class="row" style="padding-bottom: 15px; ">
                <div class="col-md-12" style="text-align: center">
                  Portata
                  <VSelect :options="listPortate" v-model="numPortata" searchable />
                </div>
              </div>
              <div class="row" style="padding-bottom: 15px; ">
                <div class="col-md-12" style="text-align: center">
                  Aggiunte <u>(a pagamento 1€)</u>
                  <b-form-tags
                    input-id="tags-separators"
                    v-model="aggiunte"
                    separator=" ,;"
                    placeholder="Scrivi qui le aggiunte"
                    no-add-on-enter
                  ></b-form-tags>
                </div>
                <div class="col-md-12" style="text-align: center">
                  Salse <u>(a pagamento 0,5€)</u>
                  <b-form-tags
                    input-id="tags-separators"
                    v-model="aggiunteSalse"
                    separator=" ,;"
                    placeholder="Scrivi qui le aggiunte di salse"
                    no-add-on-enter
                  ></b-form-tags>
                </div>
              </div>
              <div class="row" style="padding-bottom: 15px; ">
                <div class="col-md-12" style="text-align: center">
                  Eliminazioni
                  <b-form-tags
                    input-id="tags-separators"
                    v-model="eliminazioni"
                    separator=" ,;"
                    placeholder="Scrivi qui le eliminazioni"
                    no-add-on-enter
                  ></b-form-tags>
                </div>
              </div>
              <div class="row" style="padding-bottom: 15px; ">
                <div class="col-md-12" style="text-align: center">
                  Descrizione (Note)
                  <b-form-input v-model="descrizione" placeholder="Inserisci una descrizione al tavolo"></b-form-input>
                </div>
              </div>
            </b-card-body>
          </b-collapse>
        </b-card>
      </div>
    </div>


    <div class="row" style="padding-bottom: 15px; ">
      <div class="col-md-12">
        <b-button block variant="success" @click="aggiungi()">Aggiungi</b-button>
      </div>
    </div>
    <div class="row" style="padding-bottom: 15px; padding-top: 50px">
      <div class="col-md-12"  v-if="ordiniDrink != null && ordiniDrink.length != 0">
        <ListaOrdini :ordini="ordiniDrink" tipologia="DRINK" :id-tavolo="this.idTavolo"></ListaOrdini>
      </div>
      <div class="col-md-12"  v-if="ordiniFood != null && ordiniFood.length != 0">
        <ListaOrdini :ordini="ordiniFood" tipologia="FOOD" :id-tavolo="this.idTavolo"></ListaOrdini>
      </div>
      <div class="col-md-12"  v-if="ordiniAltro != null && ordiniAltro.length != 0">
        <ListaOrdini :ordini="ordiniAltro" tipologia="ALTRO" :id-tavolo="this.idTavolo"></ListaOrdini>
      </div>
    </div>
    <div class="row" style="padding-bottom: 15px; ">
      <div class="col">
        <b-button block variant="warning" @click="stampaParziale()">Stampa Parziale</b-button>
      </div>
      <div class="col">
        <b-button block variant="success" @click="stampaTutto()">Stampa Tutto</b-button>
      </div>

    </div>
    <div class="row" style="padding-bottom: 15px; ">
      <div class="col">
        <b-button block variant="primary" @click="torna()">Indietro</b-button>
      </div>
    </div>
  </div>
  </div>
</template>
<script>

import ProdottoService from "@/services/ProdottoService";
import OrdineService from "@/services/OrdineService";
import VSelect from '@alfsnd/vue-bootstrap-select'
import TavoloService from "@/services/TavoloService";
import ListaOrdini from "@/components/ListaOrdini";

export default {
  props: {idTavolo: String},
  components:{ListaOrdini, VSelect},
  mounted(){
    this.caricaListaProdotti();
    this.caricaOrdini();
    this.caricaPortate();
  },
  watch: {
    prodottoSelezionato(value) {
      this.caricaPortata();
    }
  },
  data() {
    return {
      quantita:1 ,
      listPortate: [],
      numPortata: null,
      prodotti: [],
      ordiniDrink: [],
      ordiniFood: [],
      ordiniAltro: [],
      aggiunte: [],
      aggiunteSalse: [],
      eliminazioni: [],
      descrizione: "",
      prodottoSelezionato: null
    }
  },
  methods:{
    caricaListaProdotti(){
      ProdottoService.findAllAttivi()
        .then((response) => {
          this.prodotti = response;
        })
    },
    getIdProdottoSelezionato(){
      return this.prodottoSelezionato.id;
    },
    aggiungi(){
      if(this.numPortata==null) this.caricaPortata();

      if(this.eliminazioni.length >0 || this.aggiunte.length >0|| this.aggiunteSalse.length >0|| this.descrizione.length >0) {
         this.aggiungiOrdineCustom()
      }
      else this.aggiungiOrdine(this.getIdProdottoSelezionato(), null);

    },
    caricaPortata(){
      OrdineService.getPortata(this.prodottoSelezionato.numPortataDefault).then(response=>{
        this.numPortata = response;
      });
    },
    caricaPortate(){
      OrdineService.getPortate().then(response=>{
        this.listPortate = response;
      });
    },
    caricaOrdini(){
      OrdineService.findByTableCustomTipologia(this.idTavolo, 'DRINK').then((response) => {
        this.ordiniDrink = response;
      })
      OrdineService.findByTableCustomTipologia(this.idTavolo, 'FOOD').then((response) => {
        this.ordiniFood = response;
      })
      OrdineService.findByTableCustomTipologia(this.idTavolo, 'ALTRO').then((response) => {
        this.ordiniAltro = response;
      })
    },
    aggiungiOrdineCustom(){
      ProdottoService.addProdottoCustom({
        "descrizione": this.descrizione,
        "nome": this.getNomeFromProdotto(this.getIdProdottoSelezionato()),
        "eliminazioni": this.getEliminazioni(),
        "idProdottoOriginale": this.getIdProdottoSelezionato(),
        "prezzo": this.getPrezzo(this.getIdProdottoSelezionato()),
        "aggiunte": this.getAggiunte(),

      })
        .then((response) => {
          this.aggiungiOrdine(null,response);
          return response;
        }).catch((err)=>{
          this.$alert("ERRORE: Impossibile inserire prodotto custom.");
      })
    },
    getPrezzo(idProdotto){
      var returnInt = 0;
      this.prodotti.forEach(prodotto => {
        if(prodotto.id == idProdotto){
          returnInt = prodotto.prezzo + (this.aggiunte.length) + (this.aggiunteSalse.length*(0.5)) ;
        }
      })
      return returnInt;
    },
    getAggiunte(){
      let stringaOut = "";
      this.aggiunte.forEach(stringa => {
        stringaOut = stringaOut + stringa+";";
      })
      this.aggiunteSalse.forEach(stringa => {
        stringaOut = stringaOut + stringa+";";
      })
      return stringaOut;
    },
    getEliminazioni(){
      let stringaOut = "";
      this.eliminazioni.forEach(stringa => {
        stringaOut = stringaOut + stringa+";";
      })
      return stringaOut;
    },
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
    getDescrizioneFromProdotto(idProdotto){
      var returnString = "";
      this.prodotti.forEach(prodotto => {
        if(prodotto.id == idProdotto) {
          returnString =  prodotto.descrizione;
        }
      })
      return returnString;
    },
    getNomeFromProdotto(idProdotto){
      var returnString = "";
      this.prodotti.forEach(prodotto => {
        if(prodotto.id == idProdotto)
          returnString = prodotto.nome;
      })
      return returnString;
    },
    aggiungiOrdine(prodottoOriginale, prodottoCustom){
      this.$store.state.isLoading = true;
      let payload;

      if(prodottoOriginale==null) {
        payload =
          {
            "quantita": this.quantita,
            "idTavolo": parseInt(this.idTavolo),
            "idProdottoCustom": prodottoCustom,
            "numPortata":this.getNumPortata()
          }
      }else {
        payload =
          {
            "quantita": this.quantita,
            "idTavolo": parseInt(this.idTavolo),
            "idProdottoOriginale": prodottoOriginale,
            "numPortata":this.getNumPortata()
          }
      }

      OrdineService.addOrdine(payload)
        .then((response) => {
          this.pulisci();
          this.caricaOrdini();
          this.$store.state.isLoading = false;
        }).catch((err)=>{
        this.$alert("ERRORE: Impossibile inserire l'ordine.");
        this.$store.state.isLoading = false;
      })
    },
    getNumPortata(){
      if(this.numPortata != null) return parseInt(this.numPortata.value);
      else return null;
    },
    pulisci(){
      this.quantita = 1;
      this.prodottoSelezionato = null;
      this.aggiunte= [];
      this.eliminazioni= [];
      this.descrizione= "";
      this.prodottoSelezionato= null;
      this.aggiunteSalse = [];
    },
    torna(){
      this.$router.push("/tavoli");
    },
    stampaTutto(){
      this.$confirm("Stampa tutto?").then(() => {
        TavoloService.stampaComanda(this.idTavolo)
          .then((response) => {
            this.$alert("Stampa inviata con successo.");
            this.torna();
          }).catch((err)=>{
          this.$alert("ERRORE: Impossibile inviare la stampa.");
          this.$store.state.isLoading = false;
        })
      });
    },
    stampaParziale(){
      this.$router.push("/stampa-parziale/"+this.idTavolo);
    },
  }
};
</script>
<style>
</style>
