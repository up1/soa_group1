<template xmlns:v-on="http://www.w3.org/1999/xhtml">
  <div class="container">
    <h2>{{header}}</h2>
    <div class="userProfile">
      <form method="POST" v-on:submit.prevent="updateProfile()">
      <div class="row">
        <div class="col-sm-1 left">email: </div>
        <div class="col-sm-4">
          <input class="col-sm-12" type="email" v-model="email">
        </div>
      </div>
      <br>
      <div class="row">
        <div class="col-sm-1">address:</div>
        <div class="col-sm-4"><textarea name="address" v-model="address" rows="8"></textarea></div>
      </div>
      <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
          <button type="submit" class="btn btn-default">save</button>
        </div>
        <div class="col-md-2"></div>
      </div>
      </form>
    </div> <!--/Profile form-->

    <div id="wrapper" class="container">
      <modal v-if="showModal" id="myModal">
        <p slot="header" class="modal-title">
          {{modalTitle}}
        </p>
        <div slot="body" class="modal-body">
          <i class="fa fa-lock"></i>
        </div>
      </modal>
    </div>

  </div>
</template>

<script>
  import axios from 'axios'
  import Modal from '@/components/Modal'
  export default {
    name: 'userProfile',
    components: {
      Modal
    },
    data () {
      return {
        header: 'Your profile',
        email: '',
        address: '',
        showModal: '',
        modalTitle: 'Update success'
      }
    },
    mounted: function () {
      this.showProfile()
    },
    methods: {
      showProfile() {
        axios.get('http://localhost:9007/user/' + this.$route.params.userId, {})
          .then(
            (response) => {
              console.log('in then', response.data);
              this.email = response.data.email
              this.address = response.data.address
              this.showModal = false;
            }
          )
          .catch(
            (error) => {
              console.log('in catch', error);
            }
          )
      },
      updateProfile(){
        axios.put('http://localhost:9007/user/' + this.$route.params.userId , {email: this.email, address: this.address})
          .then(
            (response) => {
              axios.get('http://localhost:9007/user/' + this.$route.params.userId, {})
              this.showModal = true;
              setTimeout(function(){
                $("#myModal").hide();
              }, 3000);
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
  .modal-title{
    font-size: 20px;
    text-align: center;
  }
  .modal-text{
    font-size: 17px;
    text-align: center;
    margin-top: -20px;
  }
</style>
