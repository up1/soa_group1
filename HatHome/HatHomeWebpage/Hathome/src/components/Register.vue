<template xmlns:v-on="http://www.w3.org/1999/xhtml">
  <div class="container">
    <div class="row">
      <div class="col-md-4"></div>
      <div class="col-sm-4">
        <div class="regisForm">
          <!--sign up form-->
          <h2 class="header">{{header}}</h2>
          <div class="form-regis">
            <form method="POST" v-on:submit.prevent="register()">
            <input class="form-input" type="email" placeholder="Email Address" v-model="email" id="email" />
            <input class="form-input" type="password" placeholder="Password" v-model="password" id="password"/>
            <textarea class="form-address" name="address" placeholder="Address" v-model="address" rows="8" id="address"></textarea>
            <div class="col-md-4"></div>
            <div class="col-md-4">
              <button type="submit" class="btn btn-primary signup-btn" style="margin-bottom:100px;">
                Sign up
              </button>
            </div>
            <div class="col-md-4"></div>
          </form>
          </div>
        </div>
        <!--/sign up form-->
      </div>
      <div class="col-md-4"></div>
    </div>
  </div>
</template>



<script>
  import axios from 'axios'

  export default {
    name: 'regisForm',
    data () {
      return {
        header: 'New User Signup!',
        email: '',
        password: '',
        address:'',
      }
    },
    methods: {
      register() {
        axios.post('http://localhost:9007/user', {
          email: this.email,
          password: this.password,
          address: this.address,
        })
          .then(
            (response) => {
              this.$router.push({path: '/login'})
              console.log('in then', response.data);
            }
          )
          .catch(
            (error) => {
              console.log('in catch', error);
            }
          )
      }
    }
  }
</script>
<style>
  .header{
    color: #696763;
    font-family: 'Roboto', sans-serif;
    font-size: 20px;
    font-weight: 300;
    margin-bottom: 30px;
    margin-top: 50px;
  }

   .form-input {
     background: #F0F0E9;
     border: medium none;
     color: #9d9d9d;
     font-family: 'roboto';
     font-size: 14px;
     font-weight: 300;
     height: 40px;
     margin-bottom: 10px;
     outline: medium none;
     padding-left: 10px;
     width: 100%;
   }

   .form-address{
     background: #F0F0E9;
     border: medium none;
     color: #696763;
     display: block;
     font-family: 'Roboto', sans-serif;
     font-size: 14px;
     font-weight: 300;
     margin-bottom: 10px;
     outline: medium none;
     padding-left: 10px;
     width: 100%;
   }

   .signup-btn{
     background:#FE980F;
     border: 0 none;
     border-radius: 0;
     color: #fff;
     font-family: 'Roboto', sans-serif;
     font-size: 15px;
     margin-bottom: 25px;
   }


</style>
