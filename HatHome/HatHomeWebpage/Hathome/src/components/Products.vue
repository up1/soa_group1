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
              <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="600" data-slider-step="5"
                     data-slider-value="[250,450]" id="sl2"><br/>
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
                        <div class="col-sm-7" align="right" style="border: 1px; margin-right: -5px">
                          <a href="" class="btn btn-default custom-button" v-on:click="addToCart(item.id, item.name)"><i
                            class="fa fa-shopping-cart"></i></a>
                        </div>
                        <div v-if="wishlists_id.indexOf( item.id ) < 0"  class="col-sm-5" style="border: 1px; margin-left: -25px;"  align="left">
                          <a href="" class="btn btn-default custom-button" v-on:click="addToWishlist(item.id, item.name)">
                            <i class="fa fa-star"></i>
                          </a>
                        </div>
                        <div v-else  class="col-sm-5" style="border: 1px; margin-left: -25px;"  align="left">
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
            }
              this.wishlists_id.push(this.wishlists[i].product_id);
          })
          .catch(function (error) {
            console.log(error)
          })
      },
      addToWishlist (id, name) {
        wishlist.addToWishlist(id, name, this.$auth.user().id);
      },

      deleteFromWishlist (productId){
        wishlist.deleteFromWishlist(productId, this.$auth.user().id)
          .then(() => {
            this.wishlists = []
            this.wishlist()
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
