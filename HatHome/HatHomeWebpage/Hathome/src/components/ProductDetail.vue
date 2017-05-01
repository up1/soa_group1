<template xmlns:v-blind="http://www.w3.org/1999/xhtml">
  <div class="container">
              <div class="row">

                  <div class="col-sm-12 padding-right">
                      <div class="product-details">
                          <!--product-details-->
                          <div class="col-sm-5">
                              <div class="view-product">
                                <img v-bind:src="'https://storage.googleapis.com/hathome01/products/' + id + '.jpg'"/>
                              </div>


                          </div>
                          <div class="col-sm-7">
                              <div class="product-information">
                                  <!--/product-information-->
                                  <h2>{{name}}</h2>
                                  <p>{{detail}}</p>

                                  <p class="price-info"><b> {{price}} Baht</b></p>

                                <!-- ''' signin ''' -->
                                <div v-if="$auth.check()" class="row">
                                  <div class="col-sm-4" style="horiz-align: center; text-align: left;">
                                    <button type="button" class="btn btn-default cart" v-on:click="addToCart">
                                      <i class="fa fa-shopping-cart"></i>
                                      Add to cart
                                    </button>
                                  </div>
                                  <div v-if="wishlists_id.indexOf(id) < 0" class="col-sm-4" style="horiz-align: center; text-align: left;">
                                    <button type="button" class="btn btn-default custom-button" v-on:click="addToWishlist(id)">
                                      <i class="fa fa-star"></i>
                                      Add To Wishlist
                                    </button>
                                  </div>
                                  <div v-else class="col-sm-4" style="horiz-align: center; text-align: left;">
                                    <button type="button" class="btn wlclicked-button custom-button" v-on:click="deleteFromWishlist(id)">
                                      <i class="fa fa-star"></i>
                                      Delete To Wishlist
                                    </button>
                                  </div>
                                </div>
                                <!-- ''' end of sign in ''' -->


                                <!-- ''' not signin ''' -->
                                <div v-if="!$auth.check()" class="row">
                                  <div class="col-sm-4" style="horiz-align: center; text-align: left;">
                                    <router-link :to="{ name: 'login'}">
                                      <button type="button" class="btn btn-default cart">
                                      <a class="fa fa-shopping-cart" style="color: black"></a>
                                      Add to cart
                                    </button>
                                    </router-link>
                                  </div>
                                  <div v-if="wishlists_id.indexOf(id) < 0" class="col-sm-4" style="horiz-align: center; text-align: left;">
                                    <router-link :to="{ name: 'login'}">
                                    <button type="button" class="btn btn-default custom-button">
                                      <a class="fa fa-star" style="color: black"></a>
                                      Add To Wishlist
                                    </button>
                                    </router-link>
                                  </div>
                                  <div v-else class="col-sm-4" style="horiz-align: center; text-align: left;">
                                    <button type="button" class="btn wlclicked-button custom-button" v-on:click="deleteFromWishlist(id)">
                                      <i class="fa fa-star"></i>
                                      Delete To Wishlist
                                    </button>
                                  </div>
                                </div>
                                <!-- ''' end of not sign in ''' -->

                                <div style="text-align: left;">
                                  <p class="product-amount"><b>Amount:</b> {{amount}}</p>
                                  <p class="product-brand"><b>Brand:</b> {{brand}}</p>
                                  <p class="product-color"><b>Color:</b> {{color}}</p>
                                  <p class="product-cat"><b>Category:</b> {{type}}</p>
                                </div>
                              <!--/product-information-->
                          </div>
                      </div>
                      <!--/product-details-->
                  </div>
              </div>
          </div>
        </div>
</template>

<script>
  import axios from 'axios'
  import cart from '../services/cart'
  import wishlist from '../services/wishlist'

  export default {
    name: 'productDetail',
    data () {
      return {
        name: '',
        detail: '',
        image: '',
        amount: '',
        price: '',
        brand: '',
        color: '',
        type: '',
        id: '',
        wishlists: [],
        wishlists_id: []
      }
    },
    mounted: function () {
      this.product();
      this.checkUser();
    },
    methods: {
      checkUser: function () {
        if (this.$auth.user().id > 0) {
          this.getWishlist();
        }
      },
      product: function () {
        axios.get('http://localhost:9004/products/' + this.$route.params.id, {
        })
          .then((response) => {
            this.name = response.data.name
            this.detail = response.data.detail
            this.image = response.data.image
            this.amount = response.data.amount
            this.price = response.data.price
            this.brand = response.data.brand
            this.color = response.data.color
            this.type = response.data.type
            this.id = response.data.id
          })
          .catch(function (error) {
            console.log(error)
          })
      },
      getWishlist: function () {
        axios.get('http://localhost:9005/wishlist/user/' + this.$auth.user().id, {})
          .then((response) => {
            this.wishlists = response.data;
            var i = 0;
            for (i = 0; i < this.wishlists.length; i++) {
              this.wishlists_id.push(this.wishlists[i].productId);
            }
          })
          .catch(function (error) {
            console.log(error)
          })
      },
      addToWishlist (id) {
        wishlist.addToWishlist(id, this.$auth.user().id)
          .then(() => {
            this.wishlists = []
            this.getWishlist()
            window.location.reload()
          })
      },
      deleteFromWishlist (productId){
        console.log(`DELETED`);
        wishlist.deleteFromWishlist(productId, this.$auth.user().id)
          .then(() => {
            this.wishlists = []
            this.getWishlist()
            window.location.reload()
          })
      },
      addToCart (){
        cart.addToCart(this.id, this.name, this.$auth.user().id);
      }
    }
  }
</script>
