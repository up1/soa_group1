<template>
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
                                <img v-bind:src="image" />
                              </div>


                          </div>
                          <div class="col-sm-7">
                              <div class="product-information">
                                  <!--/product-information-->
                                  <h2>{{name}}</h2>
                                  <p>{{detail}}</p>

                                  <p class="price-info"><b> {{price}} Baht</b></p>

                                  <label>Quantity:</label>
                                  <input type="text" value="1" />

                                  <button type="button" class="btn btn-fefault cart" v-on:click="addToCart">
                                    <i class="fa fa-shopping-cart"></i>
                  										Add to cart
                  									</button>
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
</template>

<script>
  import axios from 'axios'
  import cart from '../services/cart'
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
        id: ''
      }
    },
    mounted: function () {
      this.product()
    },
    methods: {
      product () {
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
      addToCart () {
        cart.addToCart(this.id, this.name);
      }
    }
  }
</script>
