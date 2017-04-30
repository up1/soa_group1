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
              <div class="col-sm-3" v-for="item in wishlists">
                <div class="product-image-wrapper">
                  <div class="single-products">
                    <div class="productinfo text-center">
                        <router-link :to="{ name: 'productDetail', params: { id: item.id}}" style="text-decoration: none;">
                          <img v-bind:src="'https://storage.googleapis.com/hathome01/products/' + item.id + '.jpg'"/>
                          <h4>{{ item.name }}</h4>
                        </router-link>
                      <p>{{ item.price }} Baht</p>
                      <button href="#" class="btn btn-default custom-button" v-on:click="addToCart(item.id, item.name)">
                        <i class="fa fa-shopping-cart"></i></button>
                      <button href="#" class="btn btn-default custom-button" v-on:click="addToWishlist(item.id, item.name)">
                        <i class="fa fa-star"></i></button>
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
          <router-link :to="{ name: 'products', params: {page: 1 }}"><button type="submit" class="btn btn-default view-all-btn"> View All Product</button></router-link>
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
      wishlists: []
    }
  },
  mounted: function() {
    this.product()
  },
  methods: {
    product: function() {
      axios.get('http://localhost:9004/recentproducts', {})
        .then((response) => {
          this.list = response.data
          this.wishlists = response.data
        })
        .catch(function(error) {
          console.log(error)
        })
    },
    scrollBehavior: function(to, from, savedPosition) {
      return {x: 0,  y: 0}

    },
    addToWishlist (id, name) {
      wishlist.addToWishlist(id, name, this.$auth.user().id);
    },
    deleteFromWishlist (id){
      console.log(`DELETED`);
      wishlist.deleteFromWishlist(id)
        .then(() => {
          this.wishlists = []
          this.wishlist()
        })
    },
    addToCart (id, name) {
      cart.addToCart(id, name, this.$auth.user().id);
    }
  }
}

</script>
