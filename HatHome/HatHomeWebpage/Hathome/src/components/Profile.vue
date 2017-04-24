<template xmlns:v-on="http://www.w3.org/1999/xhtml">
  <div class="container">
    <div class="row">
      <div class="col-md-4"></div>
      <div class="col-sm-4">
          <!--Profile form-->
          <h2>{{header}}</h2>
          <div class="userProfile">
            <!--<form method="POST" v-on:submit.prevent="register()">-->
              email: <input type="email" v-model="email">
              address: <textarea name="address" v-model="address" rows="8"></textarea>
              <div class="col-md-4"></div>
              <!--<div class="col-md-4"><button type="submit" class="btn btn-default">Signup</button></div>-->
              <div class="col-md-4"></div>
            <!--</form>-->
          </div>
        <!--/Profile form-->
      </div>
      <div class="col-md-4"></div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: 'userProfile',
    data () {
      return {
        header: 'Your profile',
        email: '',
        address:'',
      }
    },
    mounted: function () {
      this.showProfile()
    },
    methods: {
      showProfile() {
        axios.get('http://localhost:9001/user/' + this.$route.params.userId, {
        })
          .then(
            (response) => {
              console.log('in then', response.data);
              this.email = response.data.email
              this.address = response.data.address
            }
          )
          .catch(
            (error) => {
              console.log('in catch', error);
//              alert('Please try login again');
            }
          )
      }
    }
  }
</script>
