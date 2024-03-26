<template>
  <form @submit.prevent="userSignIn">
  <div class="simple-login-container">
    <h2>Login Form</h2>
    <div class="row">
      <div class="col-md-12 form-group">
        <input type="text" v-model="username" class="form-control" placeholder="Username">
      </div>
    </div>
    <div class="row">
      <div class="col-md-12 form-group">
        <input type="password" v-model="password" placeholder="Enter your Password" class="form-control">
      </div>
    </div>
    <div class="row">
      <div class="col-md-12 form-group">
        <input type="submit" class="btn btn-block btn-login" placeholder="Enter your Password" >
      </div>
    </div>
  </div>
  </form>
</template>

<script>
import GeneralService from "@/services/GeneralService";
import router from "@/router";

export default {
  mounted(){
    this.checkLogged();
  },
  data() {
    return {
      interval : null,
      username: '',
      password: ''
    };
  },
  computed: {
  },
  methods: {
    checkLogged(){
      GeneralService.logged().then((res)=>{
        if(res === 'SI')
          router.push('/dashboard');
      });
    },
    userSignIn() {
      this.$store.dispatch('userSignIn', { username: this.username, password: this.password });
    },
  },
};
</script>
<style>
body{
  background-color:#5286F3;
  font-size:14px;
  color:#fff;
}
.simple-login-container{
  width:300px;
  max-width:100%;
  margin:50px auto;
}
.simple-login-container h2{
  text-align:center;
  font-size:20px;
}

.simple-login-container .btn-login{
  background-color:#FF5964;
  color:#fff;
}
a{
  color:#fff;
}
</style>
