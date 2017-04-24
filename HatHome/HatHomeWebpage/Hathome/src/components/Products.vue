<template>
<div class="container">
  <div class="row">
      <div class="col-sm-3">
        <div class="left-sidebar">



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
                    <a href="#" class="btn btn-default custom-button"><i class="fa fa-shopping-cart"></i></a>
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
</template>

<script>
import axios from 'axios'
import wishlist from '../services/wishlist'

export default {
  name: 'recentProduct',
  data() {
    return {
      name: '',
      item: '',
      image: '',
      list: [],
      currentPage: 1,
      perPage: 9
    }
  },
  mounted: function() {
    this.product()
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
      wishlist.addToWishlist(id, name);
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
