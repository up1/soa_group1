<template xmlns:v-on="http://www.w3.org/1999/xhtml">
<div class="container">
  <h2>{{header}}</h2>
  <div class="userProfile">
    <form method="POST" v-on:submit.prevent="updateProfile()">
      <div class="row">

        <div class="col-sm-4" style="text-align:right; margin-top:10px;">
          <div>Email: </div>
          <div style="margin-top:25px;">Address:</div>

        </div>

        <div class="col-sm-4">
          <input class="input" type="email" v-model="email" id="email">

          <textarea name="address" v-model="address" rows="8" id="address"></textarea>
          <div class="col-md-2"></div>
        </div>
        <div class="col-sm-2"></div>
      </div>
      <div class="row" style="margin-top:20px;">
          <button type="submit" class="btn btn-default" style="vertical-align:center">save</button>

      </div>

    </form>
  </div>
  <!--/Profile form-->

  <div id="wrapper" class="container">
    <modal v-if="showModal" id="myModal">
      <p slot="header" class="modal-title">
        {{modalTitle}}
      </p>
      <div slot="body" class="modal-body">
        <i class="fa fa-check-circle"></i>
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
  data() {
    return {
      header: 'Your profile',
      email: '',
      address: '',
      showModal: '',
      modalTitle: 'Update success'
    }
  },
  mounted: function() {
    this.showProfile()
  },
  methods: {
    showProfile() {
      axios.get('http://128.199.64.78:9007/user/' + this.$route.params.userId, {})
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
    updateProfile() {
      axios.put('http://128.199.64.78:9007/user/' + this.$route.params.userId, {
          email: this.email,
          address: this.address
        })
        .then(
          (response) => {
            axios.get('http://128.199.64.78:9007/user/' + this.$route.params.userId, {})
            this.showModal = true;
            setTimeout(function() {
              $("#myModal").hide();
            }, 1000);
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
.modal-title {
  font-size: 20px;
  text-align: center;
}

.modal-text {
  font-size: 17px;
  text-align: center;
  margin-top: -20px;
}
.input{
  background: #F0F0E9;
  border: medium none;
  color: #696763;
  display: block;
  font-family: 'Roboto', sans-serif;
  font-size: 14px;
  font-weight: 300;
  height: 40px;
  margin-bottom: 10px;
  outline: medium none;
  padding-left: 10px;
  width: 100%;
}
</style>
