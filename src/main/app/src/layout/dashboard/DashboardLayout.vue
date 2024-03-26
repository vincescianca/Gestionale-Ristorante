<template>
  <div class="wrapper">
    <side-bar>
      <template slot="links">
        <sidebar-link to="/dashboard" name="Dashboard" icon="ti-panel"/>
        <sidebar-link to="/tavoli" name="Tavoli" icon="ti-map"/>
        <sidebar-link to="/prodotti" name="Prodotti" icon="ti-archive"/>
        <sidebar-link to="/categorie" name="Categorie" icon="ti-gallery"/>
        <sidebar-link to="/menu" name="Menu" icon="ti-menu"/>
      </template>
    </side-bar>
    <div class="main-panel">

      <vue-element-loading :active="this.$store.state.isLoading" spinner="bar-fade-scale" color="red"/>
      <top-navbar></top-navbar>

      <dashboard-content @click.native="toggleSidebar">

      </dashboard-content>

      <content-footer></content-footer>
    </div>
  </div>
</template>
<style lang="scss">
</style>
<script>
import TopNavbar from "./TopNavbar.vue";
import ContentFooter from "./ContentFooter.vue";
import DashboardContent from "./Content.vue";
import MobileMenu from "./MobileMenu";
export default {
  components: {
    TopNavbar,
    ContentFooter,
    DashboardContent,
    MobileMenu
  },
  methods: {
    toggleSidebar() {
      if (this.$sidebar.showSidebar) {
        this.$sidebar.displaySidebar(false);
      }
    }
  }
};
</script>
