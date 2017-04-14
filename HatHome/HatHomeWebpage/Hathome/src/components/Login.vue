<template xmlns:v-on="http://www.w3.org/1999/xhtml">
  <div class="container">
    <div class="row">
      <div class="col-md-4"></div>
      <div class="col-md-4">
        <div class="login-form">
          <!--login form-->
          <div class="loginForm">
          <h2>{{header}}</h2>
          <form method="POST" v-on:submit.prevent="login()">
            <input type="email" placeholder="email" id="email" v-model="email"/>
            <input type="password" placeholder="password" id="password" v-model="password" />
            <div class="col-md-6">
                                <span>
                                <input type="checkbox" class="checkbox" id="checkbox" v-model="checked" > Keep me signed in
                              </span>
            </div>
            <div class="col-md-6">
                                <span>
                                <a href="register.html"><p>Not a member yet?</p></a>
                                </span>
            </div>

            <div class="row">
              <div class="col-md-4"></div>
              <div class="col-md-4"><button type="submit" class="btn btn-default" v-on:click="login">Login</button></div>
              <div class="col-md-4"></div>
            </div>
          </form>
          </div>
        </div>
        <!--/login form-->
      </div>
      <div class="col-md-4"></div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    name: 'loginForm',
    data () {
      return {
        email: '',
        password: '',
        header: 'Login to your account',
        checked:'',
      }
    },
    methods: {
      login: function (e) {
//          e.preventDefault();
        axios.post('http://localhost:9002/login', {
        email: this.email,
        password: this.password
          })
          .then(function (response) {
            this.$router.push({ path: '/' })
              console.log(response);
        })
          .catch(function (error) {
            console.log(error);
          });
      }

    }
  }
</script>
