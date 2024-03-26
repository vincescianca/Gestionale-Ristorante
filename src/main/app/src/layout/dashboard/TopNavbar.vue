<template>
  <!--nav class="navbar navbar-expand-lg navbar-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">{{routeName}}</a>
      <b-icon style="cursor: pointer;" icon="list" aria-hidden="true" variant="danger" font-scale="3" @click="apri()"></b-icon>
    </div>
  </nav-->
  <div class="col">
      <div class="row">
        <div class="col-md-12">
          <div class="row" style="padding-bottom: 15px;padding-top:15px">
            <div class="col">
              <a class="navbar-brand" >{{routeName}}</a>
           </div>
           <div class="col" align="right">
             <b-icon style="cursor: pointer;" icon="list" aria-hidden="true" variant="danger" font-scale="3" @click="apri()"></b-icon>
           </div>
         </div>
        </div>
      </div>
  </div>
  <!--div class="row">
    <div class="col-md-12">
      <div class="row" style="padding-bottom: 15px;">
        <div class="col">
          <a class="navbar-brand" href="#">{{routeName}}</a>
        </div>
        <div class="col" align="right">
          <a class="navbar-brand" href="#">{{routeName}}</a>
        </div>
      </div>
    </div>
  </div-->
</template>
<script>
export default {
  computed: {
    routeName() {
      const { name } = this.$route;
      return this.capitalizeFirstLetter(name);
    }
  },
  data() {
    return {
      activeNotifications: false
    };
  },
  methods: {
    apri(){
      this.$sidebar.displaySidebar(true);
    },
    capitalizeFirstLetter(string) {
      return string.charAt(0).toUpperCase() + string.slice(1);
    },
    toggleNotificationDropDown() {
      this.activeNotifications = !this.activeNotifications;
    },
    closeDropDown() {
      this.activeNotifications = false;
    },
    toggleSidebar() {
      this.$sidebar.displaySidebar(!this.$sidebar.showSidebar);
    },
    hideSidebar() {
      this.$sidebar.displaySidebar(false);
    }
  }
};
</script>
<style>
</style>
