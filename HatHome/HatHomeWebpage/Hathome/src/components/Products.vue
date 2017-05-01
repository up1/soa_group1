<template xmlns:v-blind="http://www.w3.org/1999/xhtml">
  <div class="container">
    <div class="row">
      <div class="col-sm-3">
        <div class="left-sidebar">

          <h2>Category</h2>
          <div class="panel-group category-products">
            <!--category-products-->
            <div class="cat-product checkbox">
              <label><input type="checkbox" id="caps" value="Caps" v-model="checkedHat">Caps</label>
            </div>
            <div class="cat-product checkbox">
              <label><input type="checkbox" id="hats" value="Hats" v-model="checkedHat">Hats</label>
            </div>
            <div class="cat-product checkbox">
              <label><input type="checkbox" id="beanies" value="Beanies" v-model="checkedHat">Beanies</label>
            </div>

          </div>
          <!--/category-products-->

          <div class="brands_products">
            <!--brands_products-->
            <h2>Brand</h2>
            <div class="brands-name">
              <div class="cat-product checkbox">
                <label><input type="checkbox" id="newEra" value="New Era" v-model="checkedBrand">New Era</label>
              </div>
              <div class="cat-product checkbox">
                <label><input type="checkbox" id="converse" value="Converse" v-model="checkedBrand">Converse</label>
              </div>
              <div class="cat-product checkbox">
                <label><input type="checkbox" id="jaxon" value="Jaxon" v-model="checkedBrand">Jaxon</label>
              </div>
              <div class="cat-product checkbox">
                <label><input type="checkbox" id="feltManishHat" value="Felt Manish Hat" v-model="checkedBrand">Felt Manish Hat</label>
              </div>
              <div class="cat-product checkbox">
                <label><input type="checkbox" id="bartsHats" value="Barts Hats" v-model="checkedBrand">Barts Hats</label>
              </div>
              <div class="cat-product checkbox">
                <label><input type="checkbox" id="goorinBros" value="Goorin Bros" v-model="checkedBrand">Goorin Bros</label>
              </div>
            </div>
          </div>
          <!--/brands_products-->

          <div class="color_products">
            <!--color-->
            <h2>Color</h2>
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
            <h2>Price</h2>
            <div class="price-name">
              <div class="cat-product radio">
                <label><input type="radio" id="price0" value="0-10000" v-model="checkedPrice" checked>all price</label>
              </div>
              <div class="cat-product radio">
                <label><input type="radio" id="price1" value="0-200" v-model="checkedPrice">less than 200</label>
              </div>
              <div class="cat-product radio">
                <label><input type="radio" id="price2" value="200-400" v-model="checkedPrice">200 - 400</label>
              </div>
              <div class="cat-product radio">
                <label><input type="radio" id="price3" value="400-600" v-model="checkedPrice">400 - 600</label>
              </div>
              <div class="cat-product radio">
                <label><input type="radio" id="price4" value="600-800" v-model="checkedPrice">600 - 800</label>
              </div>
              <div class="cat-product radio">
                <label><input type="radio" id="price5" value="800-10000" v-model="checkedPrice">more than 800</label>
              </div>
            </div>
          </div>
          <!--/price-range-->
        </div>
      </div>


      <div v-if="computedProducts.length===0" class="col-sm-9 padding-right">
        <h5 style="margin-top: 200px">Sorry, we didn't find any results matching this search.</h5>
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
                      <router-link :to="{ name: 'productDetail', params: { id: item.id}}" style="text-decoration: none;">
                        <img v-bind:src="'https://storage.googleapis.com/hathome01/products/' + item.id + '.jpg'"/>
                        <h4 id="productId">{{ item.name }}</h4>
                      </router-link>
                      <p>{{ item.price }} Baht</p>


                      <!-- ''' signin ''' -->
                      <div v-if="$auth.check()" class="row">
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
                      <!-- ''' end of sign in ''' -->


                      <!-- ''' not signin ''' -->
                      <div v-if="!$auth.check()" class="row">
                        <div class="col-sm-7" align="right" style="margin-right: -5px">
                          <router-link :to="{ name: 'login'}">
                            <a href="" class="btn btn-default custom-button"><i
                              class="fa fa-shopping-cart"></i></a>
                          </router-link>
                        </div>
                        <div v-if="wishlists_id.indexOf( item.id ) < 0"  class="col-sm-5" style="margin-left: -25px;"  align="left">
                          <router-link :to="{ name: 'login'}">
                            <a href="" class="btn btn-default custom-button">
                              <i class="fa fa-star"></i>
                            </a>
                          </router-link>
                        </div>
                        <div v-else class="col-sm-5" style="border: 1px; margin-left: -25px;"  align="left">
                          <a href="" class="btn wlclicked-button" v-on:click="deleteFromWishlist(item.id)">
                            <i class="fa fa-star"></i>
                          </a>
                        </div>
                        <div class="col-sm-2"></div>
                      </div>
                      <!-- ''' end of not sign in ''' -->


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
    <div class="page">
      <button id="previousBtn" class="btn btn-default" v-bind="checkDisable()" @click="goToPrevious" style="vertical-align: middle;">
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
    props: ['keyword'],
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
        wishlists_id: [],
        keyword: '',
        checkedType: [],
        checkedColor: [],
        checkedBrand:[],
        checkedHat: [],
        checkedPrice: "0-10000"
      }
    },
    watch: {
      'keyword': function (v) {
        this.search()
        if(this.keyword===''){
          console.log("keywordssssss" + this.keyword)
          this.$router.push({ name: 'products', params: { page: 1} });
        }else {
          console.log("keyword" + this.keyword)
          this.search()
        }
      },
      '$route.params.page'(newpage, oldpage) {
        this.product(newpage)
      }
    },
    mounted: function() {
      this.product(this.$route.params.page)
      this.checkUser();
    },
    methods: {
      checkUser: function () {
        if (this.$auth.user().id > 0){
          this.getWishlist();
        }
        else {}
      },
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
          document.getElementById("previousBtn").disabled = false;
          document.getElementById("nextBtn").disabled = false;
        }
        else if (this.currentPage==1) {
          document.getElementById("previousBtn").disabled = true;
          document.getElementById("nextBtn").disabled = false;
        }
        else if(this.lastPage==this.$route.params.page){
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
        wishlist.deleteFromWishlist(productId, this.$auth.user().id)
          .then(() => {
            this.wishlists = []
            this.getWishlist()
          })
      },
      addToCart (id, name) {
        cart.addToCart(id, name, this.$auth.user().id);
      },
      search: function() {
        console.log(this.keyword)
        axios.get('http://localhost:9004/search?keyword='+this.keyword, {})
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
      computedProducts: function() {
        return this.colorFilter.slice(this.offset, this.limit);
      },
      colorFilter: function(){
        if(this.checkedColor.length > 0){
          return this.brandFilter.filter(product => this.checkedColor.indexOf(product.color)!==-1);
        }else{
          return this.brandFilter
        }
      },
      brandFilter: function(){
        if(this.checkedBrand.length > 0){
          return this.hatFilter.filter(product => this.checkedBrand.indexOf(product.brand)!==-1);
        }else{
          return this.hatFilter
        }
      },
      hatFilter: function(){
        if(this.checkedHat.length > 0){
          return this.priceFilter.filter(product => this.checkedHat.indexOf(product.type)!==-1);
        }else{
          return this.priceFilter
        }
      },
      priceFilter: function(){
        let price_str = this.checkedPrice.split("-")
        return this.list.filter(product => price_str[0] < product.price && product.price < price_str[1]);
      }
    }
  }
</script>
<style>
  .page{
    margin-bottom: 20px;
  }
  .page-text{
    display:inline;
    font-family: 'roboto';
    font-size: 15px;
    font-weight: 300;
    margin-left: 10px;
    margin-right: 10px;
    vertical-align: top;

  }
</style>
