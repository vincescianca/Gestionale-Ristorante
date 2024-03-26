
import Vue from "vue";
import App from "./App";
import router from "./router/index";
import VueSimpleAlert from "vue-simple-alert";
import axios from "axios";
import store from './store';


import PaperDashboard from "./plugins/paperDashboard";
import "vue-notifyjs/themes/default.css";
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import VueElementLoading from "vue-element-loading";

Vue.use(PaperDashboard);
Vue.use(BootstrapVue)
Vue.component("VueElementLoading", VueElementLoading);
Vue.use(IconsPlugin)

Vue.use(VueSimpleAlert);
/* eslint-disable no-new */

axios.defaults.withCredentials = true;

axios.interceptors.response.use((response) => Promise.resolve(response),
  (error) => {
    if (error.response.status === 401) {
      console.log('Unauthorized, logging out ...');
      store.dispatch('userSignOut');
      return Promise.reject(error);
    }
    return Promise.reject(error.response);
  });

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
