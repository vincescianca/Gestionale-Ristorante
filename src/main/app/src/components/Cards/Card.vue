<template>
  <div class="card">
    <div class="card-image" v-if="$slots.image">
      <slot name="image"></slot>
    </div>
    <div class="card-header" v-if="$slots.header || title">
      <slot name="header">
        <div class="row" v-if="idTavolo!=null">
          <div class="col" >
            <h4 class="card-title">{{title}}</h4>
          </div>
          <div class="col-auto">
            <b-button block variant="primary" size="sm" @click="modifica()">M</b-button>
          </div>
          <div class="col-auto">
            <b-button block variant="danger" size="sm" @click="chiudi()">X</b-button>
          </div>
        </div>
        <h4 class="card-title" v-if="idTavolo==null">{{title}}</h4>
        <p class="card-category" v-if="subTitle">{{subTitle}}</p>
      </slot>
    </div>
    <div class="card-body" v-if="$slots.default">
      <slot></slot>
    </div>
    <slot name="raw-content"></slot>
    <div class="card-footer" v-if="$slots.footer">
      <hr>
      <slot name="footer"></slot>
    </div>
  </div>
</template>
<script>
import TavoloService from "@/services/TavoloService";

export default {
  name: "card",
  props: {
    title: String,
    subTitle: String,
    idTavolo: null,
  },
  methods:{
    chiudi(){
      this.$store.state.isLoading = true;
      TavoloService.chiudiTavolo(this.idTavolo)
        .then((response) => {
          this.$router.push("/tavoli");
          this.$alert("Tavolo chiuso con successo.");
        }).catch(err =>{
        this.$store.state.isLoading = false;
      })
    },
    modifica(){
      this.$router.push("/modifica-tavolo/"+this.idTavolo);
    },
  }
};
</script>
<style>
</style>
