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
                <td></td>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(item, index) in wishlists">
                <td style="vertical-align: middle;">{{index+1}}</td>
                <td><img v-bind:src="'https://storage.googleapis.com/hathome01/products/' + item.product_id + '.jpg'" style="width: 120px; height: 120px;" /></td>
                <td style="vertical-align: middle;">{{item.product.name}}</td>
                <td style="vertical-align: middle;">{{item.product.price}}</td>
                <td style="vertical-align: middle;">
                  <div id="add-cart">
                    <button v-on:click="addToCart(item.product_id, item.product.name)" class="btn btn-default check_out">Add to Cart</button>
                  </div>
                </td>
                <td class="cart_delete" style="vertical-align: middle;">
                  <a class="cart_quantity_delete" v-on:click="deleteFromWishlist(item.product_id, item.user_id)"><i class="fa fa-times"></i></a>
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
  import wishlist from '../services/wishlist'

  export default {
    name: 'wishlist',
    data () {
      return {
        header: 'My Wishlist',
        product_id: '',
        wishlists : [],
        product: ''
      }
    },
    mounted: function () {
      this.wishlist()
    },
    methods: {
      wishlist() {
        axios.get('http://localhost:9005/wishlist/user/' + this.$auth.user().id, {
        })
          .then(
            (response) => {
              this.wishlists = response.data;
            }
          )
          .catch(
            (error) => {
              console.log('in catch'+ this.context , error);
            }
          )
      },
      deleteFromWishlist (pdId, userId){
        console.log(`DELETED`);
        wishlist.deleteFromWishlist(pdId, userId)
        .then(() => {
          this.wishlists = []
          this.wishlist()
        })
      },
      addToCart(id, name){
        cart.addToCart(id, name, this.$auth.user().id);
      }
    }
  }
</script>
