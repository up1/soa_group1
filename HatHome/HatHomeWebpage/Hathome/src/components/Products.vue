<template>
  <div class="products">
    <div class="container">
      <div class="row">
          <div class="col-sm-3">
            <div class="left-sidebar">

              <h2>{{checkedHat}}</h2>
              <div class="panel-group category-products">
                <!--category-products-->
                <div class="cat-product checkbox">
                  <label><input type="checkbox" id="caps" value="Caps" v-model="checkedHat" @click="search()">Caps</label>
                </div>
                <div class="cat-product checkbox">
                  <label><input type="checkbox" id="hats" value="Hats" v-model="checkedHat" @click="search()">Hats</label>
                </div>
                <div class="cat-product checkbox">
                  <label><input type="checkbox" id="beanies" value="Beanies" v-model="checkedHat" @click="search()">Beanies</label>
                </div>

              </div>
              <!--/category-products-->

              <div class="brands_products">
                <!--brands_products-->
                <h2>{{checkedBrand}}</h2>
                <div class="brands-name">
                  <div class="cat-product checkbox">
                    <label><input type="checkbox" id="newEra" value="newEra" v-model="checkedBrand">New Era</label>
                  </div>
                  <div class="cat-product checkbox">
                    <label><input type="checkbox" id="converse" value="converse" v-model="checkedBrand">Converse</label>
                  </div>
                  <div class="cat-product checkbox">
                    <label><input type="checkbox" id="jaxon" value="jaxon" v-model="checkedBrand">Jaxon</label>
                  </div>
                  <div class="cat-product checkbox">
                    <label><input type="checkbox" id="feltManishHat" value="feltManishHat" v-model="checkedBrand">Felt Manish Hat</label>
                  </div>
                  <div class="cat-product checkbox">
                    <label><input type="checkbox" id="bartsHats" value="bartsHats" v-model="checkedBrand">Barts Hats</label>
                  </div>
                  <div class="cat-product checkbox">
                    <label><input type="checkbox" id="goorinBros" value="goorinBros" v-model="checkedBrand">Goorin Bros</label>
                  </div>
                </div>
              </div>
              <!--/brands_products-->

              <div class="color_products">
                <!--color-->
                <h2>{{checkedColor}}</h2>
                <div class="color-name">
                  <div class="cat-product checkbox">
                    <label><input type="checkbox" id="black" value="black" v-model="checkedColor">black</label>
                  </div>
                  <div class="cat-product checkbox">
                    <label><input type="checkbox" id="white" value="white" v-model="checkedColor">white</label>
                  </div>
                  <div class="cat-product checkbox">
                    <label><input type="checkbox" id="red" value="red" v-model="checkedColor">red</label>
                  </div>
                  <div class="cat-product checkbox">
                    <label><input type="checkbox" id="brown" value="brown" v-model="checkedColor">brown</label>
                  </div>
                  <div class="cat-product checkbox">
                    <label><input type="checkbox" id="blue" value="blue" v-model="checkedColor">blue</label>
                  </div>
                </div>
                <div class="cat-product checkbox">
                  <label><input type="checkbox" id="green" value="green" v-model="checkedColor">green</label>
                </div>
                <div class="cat-product checkbox">
                  <label><input type="checkbox" id="grey" value="grey" v-model="checkedColor">grey</label>
                </div>
              </div>
              <!--/color-->


              <div class="price-range">
                <!--price-range-->
                <h2>Price Range</h2>
                <div clanpmss="price-slider">
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
                <div class="col-sm-4" v-for="(item, index) in computedProducts">
                  <div class="product-image-wrapper">
                    <div class="single-products">
                      <div class="productinfo text-center">
                        <router-link :to="{ name: 'productDetail', params: { id: item.id}}">
                          <img v-bind:src="'https://storage.googleapis.com/hathome01/products/' + item.id + '.jpg'" />
                          <h4>{{ item.name }}</h4>
                        </router-link>
                        <p>{{ item.price }} Baht</p>
                        <a href="#" class="btn btn-default custom-button" v-on:click="addToCart(item.id, item.name)"><i class="fa fa-shopping-cart"></i></a>
                        <a href="" class="btn btn-default custom-button" v-on:click="addToWishlist(item.id, item.name)"><i class="fa fa-star"></i></a>
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
  </div>
 </template>

<script>
import axios from 'axios'
import wishlist from '../services/wishlist'
import cart from '../services/cart'

export default {
  name: 'recentProduct',
  props: ['keyword'],
  data() {
    return {
      name: '',
      item: '',
      image: '',
      list: [],
      currentPage: 1,
      perPage: 9,
      keyword: this.$route.query.keyword,
      checkedColor: [],
      checkedBrand:[],
      checkedHat: []
    }
  },
  watch: {
      'keyword': function (v) {
          this.search()
    }
  },
  mounted: function() {
    if(this.keyword===null){
      this.product()
    }else {
      this.search()
    }
  },
  methods: {
    product: function() {
      axios.get('http://localhost:9004/products', {})
        .then((response) => {
          console.log(response)
          this.list = response.data
        })
        .catch(function(error) {
          console.log(error)
        })
    },
    setPage(n) {
      this.currentPage = n;
    },
    addToWishlist (id, name) {
      wishlist.addToWishlist(id, name, this.$auth.user().id);
    },addToCart (id, name) {
      cart.addToCart(id, name, this.$auth.user().id);
    },
    search: function() {
        console.log(this.keyword)
      axios.get('http://localhost:9004/search?keyword='+this.keyword+'&type='+this.checkedHat, {})
        .then((response) => {
          console.log(response)
          this.list = response.data.results
        })
        .catch(function(error) {
          console.log(error)
        })
    }
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
