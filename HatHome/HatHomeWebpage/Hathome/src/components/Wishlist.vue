<template>
  <section id="cart_items">
    <div class="container">
      <h2 class="title text-center" style="margin-top:20px;">{{header}}</h2>
      <div class="col-sm-1"></div>
      <div class="col-sm-10">
        <div class="wishlist">
          <div class="table-responsive cart_info">

            <table class="table table-condensed" style="width: 100%;">
              <thead>
              <tr class="cart_menu">
                <td class="no">Item</td>
                <td class="image"></td>
                <td class="description">Product</td>
                <td class="price">Price</td>
                <td></td>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(item, index) in list">
                <td>{{index+1}}</td>
                <td><img v-bind:src="'https://storage.googleapis.com/hathome01/products/' + item.product_id + '.jpg'" style="width: 120px; height: 120px;" /></td>
                <td>{{item.product.name}}</td>
                <td>
                  <div id="add-cart">
                    <button v-on:click="addToCart(item.product_id, item.product.name)" class="btn btn-default check_out">Add to Cart</button>
                  </div>
                </td>
                <td class="cart_delete">
                  <a class="cart_quantity_delete" href=""><i class="fa fa-times"></i></a>
                </td>
              </tr>
              </tbody>
            </table>

          </div>
        </div>
      </div>
      <div class="col-sm-1"></div>
    </div>
  </section>
</template>

<script>
  import axios from 'axios'
  import cart from '../services/cart'
  export default {
    name: 'wishlist',
    data () {
      return {
        header: 'My Wishlist',
        product_id: '',
        list : []
      }
    },
    mounted: function () {
      this.wishlist()
      this.getallproduct()
    },
    methods: {
      wishlist() {
        axios.get('http://localhost:9005/wishlist/user/' + this.$auth.user().id, {
//          product_id: this.product_id
        })
          .then(
            (response) => {
//              this.$router.push({path: '/'})
              //return all data
              console.log('in then',this.$route.params.userid);
              this.list = response.data;
              console.log(list);
            }
          )
          .catch(
            (error) => {
              console.log('in catch', error);
            }
          )
      },
      getallproduct(){
        axios.get('http://localhost:9004/home',{
        })
          .then(
          (response) => {
            this.products = response.data;
          }
        )
            .catch(
              (error) => {
                  console.log('in catch', error);
              }
            )
      },
      addToCart(id, name){
        cart.addToCart(id, name, this.$auth.user().id);
      }
    }
  }
//  new Vue({
//    el: '#add-cart',
//    data:{
//
//    }
//  })
</script>
