<template xmlns:v-blind="http://www.w3.org/1999/xhtml">
  <div class="container">
              <div class="row">
                  <div class="col-sm-3">
                      <div class="left-sidebar">
                        <h2>Category</h2>
                        <div class="panel-group category-products">
                          <!--category-products-->
                          <div class="cat-product checkbox">
                            <label><input type="checkbox" id="caps" value="Caps" v-model="checkedCat">Caps</label>
                          </div>
                          <div class="cat-product checkbox">
                            <label><input type="checkbox" id="hats" value="Hats" v-model="checkedCat">Hats</label>
                          </div>
                          <div class="cat-product checkbox">
                            <label><input type="checkbox" id="beanies" value="Beanies" v-model="checkedCat">Beanies</label>
                          </div>

                        </div>
                        <!--/category-products-->

                        <div class="brands_products">
                          <!--brands_products-->
                          <h2>Brands</h2>
                          <div class="brands-name">
                            <div class="cat-product checkbox">
                              <label><input type="checkbox" id="newEra" value="newEra" v-model="checkedCat">New Era</label>
                            </div>
                            <div class="cat-product checkbox">
                              <label><input type="checkbox" id="converse" value="converse" v-model="checkedCat">Converse</label>
                            </div>
                            <div class="cat-product checkbox">
                              <label><input type="checkbox" id="jaxon" value="jaxon" v-model="checkedBrand">Jaxon</label>
                            </div>
                          </div>
                        </div>
                        <!--/brands_products-->

                        <div class="color_products">
                          <!--color-->
                          <h2>Color</h2>
                          <div class="brands-name">
                            <div class="cat-product checkbox">
                              <label><input type="checkbox" id="black" value="black" v-model="checkedColor">black</label>
                            </div>
                            <div class="cat-product checkbox">
                              <label><input type="checkbox" id="white" value="white" v-model="checkedColor">white</label>
                            </div>
                            <div class="cat-product checkbox">
                              <label><input type="checkbox" id="red" value="red" v-model="checkedColor">red</label>
                            </div>
                          </div>
                        </div>
                        <!--/color-->


                        <div class="price-range">
                          <!--price-range-->
                          <h2>Price Range</h2>
                          <div class="price-slider">
                            <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="600" data-slider-step="5" data-slider-value="[250,450]" id="sl2"><br />
                            <b>฿ 0</b> <b class="pull-right">฿ 600</b>
                          </div>
                        </div>
                        <!--/price-range-->


                      </div>
                  </div>

                  <div class="col-sm-9 padding-right">
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
                                      <!--Add to cart-->
                                    </button>
                                  </div>
                                  <div v-if="wishlists_id.indexOf(id) < 0" class="col-sm-4" style="horiz-align: center; text-align: left;">
                                    <button type="button" class="btn btn-default custom-button" v-on:click="addToWishlist(id)">
                                      <i class="fa fa-star"></i>
                                      <!--Add To Wishlist-->
                                    </button>
                                  </div>
                                  <div v-else class="col-sm-4" style="horiz-align: center; text-align: left;">
                                    <button type="button" class="btn wlclicked-button" v-on:click="deleteFromWishlist(id)">
                                      <i class="fa fa-star"></i>
                                      <!--Delete To Wishlist-->
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
                                      <!--Add to cart-->
                                    </button>
                                    </router-link>
                                  </div>
                                  <div v-if="wishlists_id.indexOf(id) < 0" class="col-sm-4" style="horiz-align: center; text-align: left;">
                                    <router-link :to="{ name: 'login'}">
                                    <button type="button" class="btn btn-default custom-button">
                                      <a class="fa fa-star" style="color: black"></a>
                                      <!--Add To Wishlist-->
                                    </button>
                                    </router-link>
                                  </div>
                                  <div v-else class="col-sm-4" style="horiz-align: center; text-align: left;">
                                    <button type="button" class="btn wlclicked-button" v-on:click="deleteFromWishlist(id)">
                                      <i class="fa fa-star"></i>
                                      <!--Delete To Wishlist-->
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
            console.log(response)
            console.log(this.$route.params.id)
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
              this.wishlists_id.push(this.wishlists[i].product_id);
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
