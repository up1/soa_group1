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
              <input type="email" placeholder="email" id="email" v-model="body.email"/>
              <input type="password" placeholder="password" id="password" v-model="body.password"/>
              <div class="col-md-6">
                                <span>
                                <input type="checkbox" class="checkbox" id="checkbox" v-model="checked"> Keep me signed in
                              </span>
              </div>
              <div class="col-md-6">
                                <span>
                                  <router-link :to="{ name: 'userRegister'}"> <a href="register.html"><p>Not a member yet?</p></a></router-link>
                                </span>
              </div>

              <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                  <button type="submit" class="btn btn-default">Login</button>
                </div>
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
  export default {
    name: 'loginForm',
    data () {
      return {
        context: 'login context',
        body: {
          email: '1',
          password: '2'
        },
        header: 'Login to your account',
        checked: '',
      }
    },
    methods: {
      login() {
        var redirect = this.$auth.redirect()
        this.$auth.login({
          body: this.body,
          redirect: {name: redirect ? redirect.from.name : 'products'},
          success(){
            console.log('success ' + this.context)
          },
          error(response) {
            console.log('in catch', response);
            alert('Please try login again');
          }
        })
      }
    }
  }

</script>
