<template xmlns:v-blind="http://www.w3.org/1999/xhtml">
<div class="recentproduct">
  <section id="slider">
    <!--  slider-->
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-12">
          <div id="slider-carousel" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
              <li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
              <li data-target="#slider-carousel" data-slide-to="1"></li>
              <li data-target="#slider-carousel" data-slide-to="2"></li>
            </ol>

            <div class="carousel-inner">
              <div class="item active">
                <img src="http://i.imgur.com/q9b2ElC.jpg" style="width: 100%; height: 100%" alt="First Slide">
              </div>
              <div class="item">
                <img src="http://i.imgur.com/cUWQDqy.jpg" style="width: 100%; height: 100%" alt="Second Slide">
              </div>
              <div class="item">
                <img src="http://i.imgur.com/5gfRxw2.jpg" style="width: 100%; height: 100%" alt="Third Slide">
              </div>
            </div>

            <a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
              <i class="fa fa-angle-left"></i>
            </a>
            <a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
              <i class="fa fa-angle-right"></i>
            </a>
          </div>
          <!-- carousel-->

        </div>
      </div>
    </div>
  </section>
  <!--/slider-->
  <section>
    <div class="container">
      <div class="row">
        <div class="category-tab">
          <!--category-tab-->
          <h2 class="title text-center">RECENT PRODUCTS</h2>
          <div class="tab-content">
            <div class="tab-pane fade active in">
              <!-- item -->
              <div class="col-sm-3" v-for="item in lists">
                <div class="product-image-wrapper">
                  <div class="single-products">
                    <div class="productinfo text-center">
                        <router-link :to="{ name: 'productDetail', params: { id: item.id}}" style="text-decoration: none;">
                          <img v-bind:src="'https://storage.googleapis.com/hathome01/products/' + item.id + '.jpg'"/>
                          <h4>{{ item.name }}</h4>
                        </router-link>
                      <p>{{ item.price }} Baht</p>

                      <!-- ''' signin ''' -->
                      <div v-if="$auth.check()" class="row">
                      <div class="col-sm-8" style="margin-left: 35px;">
                        <button href="#" class="btn btn-default custom-button" v-on:click="addToCart(item.id, item.name)">
                          <i class="fa fa-shopping-cart"></i>
                        </button>
                      </div>
                      <div v-if="wishlists_id.indexOf(item.id) < 0"  class="col-sm-4" style="margin-left: -110px;"  align="left">
                        <button type="button" class="btn btn-default custom-button" v-on:click="addToWishlist(item.id)">
                          <i class="fa fa-star"></i>
                        </button>
                      </div>
                      <div v-else class="col-sm-4" style="border: 1px; margin-left: -110px;"  align="left">
                        <button type="button" class="btn wlclicked-button" v-on:click="deleteFromWishlist(item.id)">
                          <i class="fa fa-star"></i>
                        </button>
                      </div>
                      </div>
                      <!-- ''' end of sign in ''' -->

                      <!-- ''' not signin ''' -->
                      <div v-if="!$auth.check()" class="row">
                        <div class="col-sm-8" style="margin-left: 35px;">
                          <router-link :to="{ name: 'login'}">
                          <button href="#" class="btn btn-default custom-button">
                            <i class="fa fa-shopping-cart" style="color: black"></i>
                          </button>
                          </router-link>
                        </div>
                        <div v-if="wishlists_id.indexOf(item.id) < 0"  class="col-sm-4" style="margin-left: -110px;"  align="left">
                          <router-link :to="{ name: 'login'}">
                          <button type="button" class="btn btn-default custom-button" v-on:click="addToWishlist(item.id)">
                            <i class="fa fa-star" style="color: black"></i>
                          </button>
                          </router-link>
                        </div>
                        <div v-else class="col-sm-4" style="border: 1px; margin-left: -110px;"  align="left">
                          <button type="button" class="btn wlclicked-button" v-on:click="deleteFromWishlist(item.id)">
                            <i class="fa fa-star"></i>
                          </button>
                        </div>
                      </div>
                      <!-- ''' end of not sign in ''' -->



                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <center>
          <router-link :to="{ name: 'products', params: {page: 1 }}">
            <button type="submit" class="btn btn-default view-button">
              View All Product
            </button>
          </router-link>
        </center>
      </div>
    </div>
  </section>
</div>
</template>

<script>
import axios from 'axios'
import wishlist from '../services/wishlist'
import cart from '../services/cart'

export default {
  name: 'recentProducts',
  data() {
    return {
      name: '',
      item: '',
      image: '',
      id: '',
      lists: [],
      wishlists: null,
      wishlists_id: []
    }
  },
  mounted: function() {
    this.product()
    this.checkUser()
  },
  methods: {
    checkUser: function () {
      if (this.$auth.user().id > 0){
        this.getWishlist();
      }
      else {}
    },
    product: function() {
      axios.get('http://localhost:9004/recentproducts', {})
        .then((response) => {
          this.lists = response.data
        })
        .catch(function(error) {
          console.log(error)
        })
    },
    scrollBehavior: function(to, from, savedPosition) {
      return {x: 0,  y: 0}
    },
    getWishlist: function () {
      axios.get('http://localhost:9005/wishlist/user/' + this.$auth.user().id)
        .then((response) => {
          this.wishlists = response.data
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
        this.wishlists_id = []
        this.getWishlist()
        window.location.reload()
      })
    },
    deleteFromWishlist (id){
      wishlist.deleteFromWishlist(id, this.$auth.user().id)
        .then((response) => {
          this.wishlists_id = []
          this.getWishlist()
          window.location.reload()
        })
    },
    addToCart (id, name) {
      cart.addToCart(id, name, this.$auth.user().id);
    }
  }
}

</script>


<style>
.view-button {
  background:#FE980F;
  border: 0 none;
  border-radius: 0;
  color: #FFFFFF;
  font-family: 'Roboto', sans-serif;
  font-size: 15px;
  margin-bottom: 25px;
}

.view-button:hover {
  background: #e6e6e6;
  border: 0 none;
  border-radius: 0;
  color: #696763;
}
</style>
