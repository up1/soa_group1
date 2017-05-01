<template>
  <section id="cart_items">
    <div class="container">
      <h2 class="title text-center" style="margin-top:20px;">MY CART</h2>
      <div class="table-responsive cart_info">
        <div v-if="this.products !== null">
          <table class="table table-condensed cart_table">
            <thead>
            <tr class="cart_menu">
              <td class="image">Item</td>
              <td class="description"></td>
              <td class="price">Price</td>
              <td class="quantity">Quantity</td>
              <td class="total">Total</td>
              <td></td>
            </tr>
            </thead>
            <tbody id="cart_item">
            <tr v-for="product in products">
              <td class="cart_product">
                  <router-link :to="{ name: 'productDetail', params: { id: product.product.id}}">
                    <img v-bind:src="'https://storage.googleapis.com/hathome01/products/' + product.product.id +'.jpg'"/>
                  </router-link>
              </td>
              <td class="cart_description" style="vertical-align: middle;">
                <h4 style="vertical-align: middle;"><a href="">
                  <router-link :to="{ name: 'productDetail', params: { id: product.product.id}}">
                    {{product.product.name}}
                  </router-link>
                </a>
                </h4>
              </td>
              <td class="cart_price" style="vertical-align: middle;">
                <p>{{product.product.price}} ฿</p>
              </td>
              <td class="cart_quantity" style="vertical-align: middle; padding-top: 20px;">
                <div class="btn-group">
                  <input id="amount" type="number" class="form-control" min="1" max="50" v-model="product.amount">
                </div>
              </td>
              <td class="cart_total" style="vertical-align: middle; padding-top: 20px;">
                <p class="cart_total_price" style="text-align: center;">{{product.amount * product.product.price}} ฿</p>
              </td>
              <td class="cart_delete" style="padding-top: 60px;">
                <a class="cart_quantity_delete" v-on:click="remove(product.id, product.product.name)"><i
                   id="deleteItem" class="fa fa-times"></i></a>
              </td>
            </tr>
            </tbody>
          </table>
          <a v-on:click="openModal" class="btn btn-default checkout-button" id="checkOut">Check out your cart</a>
        </div>
        <div v-else>
          <div class="row">
            <div class="col-sm-12">
              <h4>This cart is empty</h4>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div id="wrapper" class="container">
      <modal v-if="showModal">
        <p slot="header" class="modal-title">
          {{modalTitle}}
        </p>
        <div slot="body" class="modal-body">
          <p class="modal-text">Please check your address.</p>
          <textarea v-model="address" placeholder="your address"></textarea>
        </div>
        <div slot="footer">
          <button type="button" class="btn btn-primary" @click="closeModal()"> Close </button>
          <button type="button" class="btn btn-primary" data-dismiss="modal" @click="billing()">
            Submit
          </button>
        </div>
      </modal>
    </div>
  </section>
</template>
<script>
  import axios from 'axios'
  import Modal from '@/components/Modal'
  import cart from '../services/cart'
  import Vue from  'vue'

  export default {
    name: 'cart_info',
    components: {
      Modal
    },
    data () {
      return {
        products: null,
        showModal: false,
        username: '',
        address: '',
        modalTitle: 'Do you want to check out your cart?'
      }
    },
    mounted: function () {
      this.getCartByUserId();
      this.get_address()
    },
    beforeDestroy: function () {
      this.updateAmount()
    },
    methods: {
      getCartByUserId(){
        axios.get('http://localhost:9003/cart/' + this.$auth.user().id)
          .then(
          (response) => {
            this.products = response.data;
          }
        ).catch(
          (error) => {
            console.log('Error occur : ', error);
          }
        )
      },
      get_address() {
        axios.get('http://localhost:9007/user/' + this.$auth.user().id, {})
          .then(
            (response) => {
              this.username = response.data.email;
              this.address = response.data.address;
            }
          )
          .catch(
            (error) => {
              console.log(error);
            });
      },
      billing() {
        console.log(this.username)
        axios.post('http://localhost:9006/bill', {
          userId: this.$auth.user().id,
          username: this.username,
          address: this.address
        })
          .then(
            (response) => {
              this.$router.push({
                name: 'Billing',
                params: {id: response.data.id}
              })
            }
          )
          .catch(
            (error) => {
              console.log(error);
            });
      },
      openModal(){
        this.showModal = true;
      },
      closeModal() {
        this.showModal = false;
      },
      submitAndClose() {
        this.s = 'Please wait...'
      },
      remove(itemId, name){
        cart.removeInCart(itemId, name)
          .then(() => {
            this.products = null
            this.getCartByUserId()
          })
      },
      updateAmount (){
        cart.updateItemInCart(this.products);
      }
    }
  }
</script>

<style>

.checkout-button {
  background:#FE980F;
  border: 0 none;
  border-radius: 0;
  color: #FFFFFF;
  font-family: 'Roboto', sans-serif;
  font-size: 15px;
  margin-bottom: 25px;
  float: right;
  margin-top: 50px;
}

.checkout-button:hover {
  background: #e6e6e6;
  border: 0 none;
  border-radius: 0;
  color: #696763;
}
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
