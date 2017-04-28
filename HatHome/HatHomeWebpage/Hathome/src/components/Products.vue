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
      <div class="all-product">
        <div class="tab-content">
          <div class="tab-pane fade active in">
            <!-- item1 -->
            <div class="col-sm-4" v-for="item in list">
              <div class="product-image-wrapper">
                <div class="single-products">
                  <div class="productinfo text-center">
                    <router-link :to="{ name: 'productDetail', params: { id: item.id}}">
                      <img v-bind:src="'https://storage.googleapis.com/hathome01/products/' + item.id + '.jpg'" />
                      <h4>{{ item.name }}</h4>
                    </router-link>
                    <p>{{ item.price }} Baht</p> {{item.id}}
                    <a href="#" class="btn btn-default custom-button" v-on:click="addToCart(item.id, item.name)"><i class="fa fa-shopping-cart"></i></a>


                    {{wishlists_id}}
                    {{ item.id }}
                    {{wishlists_id.indexOf( item.id )}}

                    <div v-if="wishlists_id.indexOf( item.id ) < 0">
                      <a href="" class="btn btn-default wlclicked-button" v-on:click="addToWishlist(item.id, item.name)">
                        <i class="fa fa-star"></i>
                      </a>
                    </div>
                    <div v-else>
                      <a href="" class="btn btn-danger custom-button" v-on:click="addToWishlist(item.id, item.name)">
                        <i class="fa fa-star"></i>
                      </a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!--/all product-->
    </div>
    <div class="btn-group">
      <a href="" @click.prevent="setPage(n)" v-for="n in numOfPages" class="btn btn-default">{{n}}</a>
    </div>
  </div>
</div>
</template>

<script>
import axios from 'axios'
import wishlist from '../services/wishlist'
import cart from '../services/cart'

export default {
  name: 'recentProduct',
  data() {
    return {
      name: '',
      item: '',
      image: '',
      list: [],
      currentPage: 1,
      perPage: 9,
      wishlists: '',
      wishlists_id: [],
    }
  },
  mounted: function() {
    this.product();
    this.getWishlist();
  },
  methods: {
    product: function() {
      axios.get('http://localhost:9004/products', {})
        .then((response) => {
          console.log('gade>> ' + response)
          this.list = response.data
        })
        .catch(function(error) {
          console.log(error)
        })
    },

    getWishlist: function () {
      axios.get('http://localhost:9005/wishlist/user/'+ this.$auth.user().id, {})
        .then((response) => {
          console.log('>>>>>' + response.data)
          this.wishlists = response.data
          console.log(this.wishlists.length)


          var i = 0;
          for ( i=0 ; i < this.wishlists.length ; i ++){
            console.log('wishlists >' + this.wishlists);
            console.log('wishlists[i]>>' + this.wishlists[i]);
            console.log('wishlists[i].product_id>>>' + this.wishlists[i].product_id);
            console.log('-------------------------');
            this.wishlists_id.push(this.wishlists[i].product_id);
          }

        })
        .catch(function (error) {
          console.log(error)
        })
    }

    },

    setPage(n) {
      this.currentPage = n;
    },

    addToWishlist (id, name) {
      wishlist.addToWishlist(id, name, this.$auth.user().id);
    },

    deleteFromWishlist (id){
      console.log(`DELETED`);
      wishlist.deleteFromWishlist(id)
        .then(() => {
          this.list = null
          this.wishlist()
        })
    },

    addToCart (id, name) {
      cart.addToCart(id, name, this.$auth.user().id);
    },

  computed: {
    offset: function() {
      return ((this.currentPage - 1) * this.perPage);
    },
    limit: function() {
      return (this.offset + this.perPage);
    },
    numOfPages: function() {
      return Math.ceil(this.list.length / this.perPage);
    },
    computedProducts: function() {
      if (this.offset > this.list.length) {
        this.currentPage = this.numOfPages;
      }
      return this.list.slice(this.offset, this.limit);
    }

  }
}

</script>
