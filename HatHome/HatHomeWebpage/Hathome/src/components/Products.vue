<template xmlns:v-blind="http://www.w3.org/1999/xhtml">
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
              <div class="col-sm-4" v-for="(item, index) in list">
                <div class="product-image-wrapper">
                  <div class="single-products">
                    <div class="productinfo text-center">
                      <router-link :to="{ name: 'productDetail', params: { id: item.id}}">
                        <img v-bind:src="'https://storage.googleapis.com/hathome01/products/' + item.id + '.jpg'"/>
                        <h4>{{ item.name }}</h4>
                      </router-link>
                      <p>{{ item.price }} Baht</p>
                      <div>
                        <div class="col-sm-7" align="right" style="margin-right: -5px">
                          <a href="" class="btn btn-default custom-button" v-on:click="addToCart(item.id, item.name)"><i
                            class="fa fa-shopping-cart"></i></a>
                        </div>
                        <div v-if="wishlists_id.indexOf( item.id ) < 0"  class="col-sm-5" style="margin-left: -25px;"  align="left">
                          <a href="" class="btn btn-default custom-button" v-on:click="addToWishlist(item.id)">
                            <i class="fa fa-star"></i>
                          </a>
                        </div>
                        <div v-else class="col-sm-5" style="border: 1px; margin-left: -25px;"  align="left">
                          <a href="" class="btn wlclicked-button" v-on:click="deleteFromWishlist(item.id)">
                            <i class="fa fa-star"></i>
                          </a>
                        </div>
                        <div class="col-sm-2"></div>
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
    </div>
    <div>
      <button id="previousBtn" class="btn btn-default" v-bind="checkDisable()" @click="goToPrevious">
        Previous
      </button>
        Page {{currentPage}} of {{lastPage}}
      <button id="nextBtn" class="btn btn-default" v-bind="checkDisable()" @click="goToNext">
        Next
      </button>
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
      resource_url:'http://localhost:9004/products/pages/'+ this.$route.params.page,
      currentPage: '',
      lastPage: '',
      wishlists: [],
      wishlists_id: []
    }
  },
  mounted: function() {
    this.product(this.$route.params.page)
    this.getWishlist();
  },
  methods: {
    product: function(page) {
      return axios.get('http://localhost:9004/products/pages/'+ this.$route.params.page, {})
        .then((response) => {
          console.log(response)
          this.list = response.data.products
          this.currentPage = response.data.currentPage
          this.lastPage = response.data.lastPage
        })
        .catch(function(error) {
          console.log(error)
        })
    },

    updateResource(data){
      this.list = data
      this.lastPage = data.lastPage
      this.currentPage = data.currentPage
    },
    checkDisable() {
      if(this.currentPage==1 && this.$route.params.page!=1){
        console.log('el'+this.currentPage + ' ' + this.$route.params.page);
        document.getElementById("previousBtn").disabled = false;
        document.getElementById("nextBtn").disabled = false;
      }
      else if (this.currentPage==1) {
        console.log('pre'+this.currentPage + ' ' + this.$route.params.page);
        document.getElementById("previousBtn").disabled = true;
        document.getElementById("nextBtn").disabled = false;
      }
      else if(this.lastPage==this.$route.params.page){
        console.log('last'+this.currentPage + ' ' + this.$route.params.page);
        document.getElementById("nextBtn").disabled = true;
        document.getElementById("previousBtn").disabled = false;
      }
    },
    goToPrevious() {
    this.$router.push({ name: 'products', params: { page: this.currentPage - 1 } });
    },
    goToNext() {
    this.$router.push({ name: 'products', params: { page: this.currentPage + 1 } });
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
            console.log('fffffff'+error)
          })
    },
    addToWishlist (id) {
      wishlist.addToWishlist(id, this.$auth.user().id);
    },
    deleteFromWishlist (productId){
        console.log('adasasffasf');
      wishlist.deleteFromWishlist(productId, this.$auth.user().id)
        .then(() => {
          this.wishlists = []
          this.getWishlist()
        })
    },
    addToCart (id, name) {
      cart.addToCart(id, name, this.$auth.user().id);
    }
  },
  watch: {
    '$route.params.page'(newpage, oldpage) {
        this.product(newpage)
    }
  }
}
</script>
