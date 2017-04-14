<template>
  <div class="container">
    <div class="row">
      <div class="col-md-4"></div>
      <div class="col-sm-4">
        <div class="regisForm">
          <!--sign up form-->
          <h2>{{header}}</h2>
          <div class="form-regis">
          <form action="#">
            <input type="email" placeholder="Email Address" />
            <input type="password" placeholder="Password" />
            <textarea name="address" placeholder="Address" rows="8"></textarea>
            <div class="col-md-4"></div>
            <div class="col-md-4"><button type="submit" class="btn btn-default">Signup</button></div>
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
      }
    },
    methods: {
//      login() {
//        axios.post('http://localhost:9002/login', {
//          email: this.email,
//          password: this.password
//        })
//          .then(
//            (response) => {
//              this.$router.push({path: '/'})
//              //return all data
//              console.log('in then', response.data);
//
//              //return token
//              console.log('in then', response.data.access_token);
//
//              //return email
//              console.log('in then', response.data.user.email);
//            }
//          )
//          .catch(
//            (error) => {
//              console.log('in catch', error);
//              alert('Please try login again');
//            }
//          )
//      }
    }
  }
</script>
