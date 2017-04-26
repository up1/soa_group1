<template>
  <section id="cart_items">
    <div class="container">
      <h2 class="title text-center" style="margin-top:20px;">MY CART</h2>
      <div class="table-responsive cart_info">
        <div v-if="this.products[0]">
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
                <a href="">
                  <router-link :to="{ name: 'productDetail', params: { id: product.product.id}}">
                    <img
                      v-bind:src="'https://storage.googleapis.com/hathome01/products/' + product.product.id +'.jpg'"/>
                  </router-link>
                </a>
              </td>
              <td class="cart_description">
                <h4><a href="">
                  <router-link :to="{ name: 'productDetail', params: { id: product.product.id}}">
                    {{product.product.name}}
                  </router-link>
                </a>
                </h4>
              </td>
              <td class="cart_price">
                <p>{{product.product.price}} ฿</p>
              </td>
              <td class="cart_quantity">
                <div class="btn-group">
                  <input type="number" class="form-control" min="1" max="50" v-model="product.amount">
                </div>
              </td>
              <td class="cart_total">
                <p class="cart_total_price">{{product.amount * product.product.price}} ฿</p>
              </td>
              <td class="cart_delete">
                <a class="cart_quantity_delete" v-on:click="remove(product.id, product.product.name)"><i
                  class="fa fa-times"></i></a>
              </td>
            </tr>
            </tbody>
          </table>
          <a v-on:click="openModal" class="btn btn-default check_out">Check out your cart</a>
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
        <h3 slot="header" class="modal-title">
          Modal title
        </h3>
        <div slot="body" class="modal-body">
          <h4>{{s}}</h4>
          <h5>Please check your address.</h5>
          <textarea v-model="address" placeholder="your address"></textarea>
        </div>
        <div slot="footer">
          <button type="button" class="btn btn-outline-info" @click="closeModal()"> Close </button>
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
        address: '',
        s: 'Do you want to check out your cart?'
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
        axios.get('http://localhost:9001/user/' + this.$auth.user().id, {})
          .then(
            (response) => {
              this.username = response.data.emmail;
              this.address = response.data.address;
            }
          )
          .catch(
            (error) => {
              console.log(error);
            });
      },
      billing() {
        axios.post('http://localhost:9006/bill', {
          user_id: this.$auth.user().id,
          username: this.email,
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
