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
                                  <img src="http://i.imgur.com/TL5ldBy.jpg" />
                              </div>


                          </div>
                          <div class="col-sm-7">
                              <div class="product-information">
                                  <!--/product-information-->
                                  <h2>Atlanta Braves Baseball Cap</h2>
                                  <p>This cap has the Atlanta Braves logo in white on a navy blue crown with a red coloured brim. The MLB logo is embroidered on the back of the cap in team colours. This cap is part of the MLB Authentic On-Field Collection.
                                      That means that there is no difference between the New Era 59FIFTY that MLB players wear on-field and the New Era 59FIFTY that fans wear in the stands. The hat offers NE TECH™ that provides sun protection and helps
                                      wick away sweat. With all of these features and over 70 years of craftsmanship and tradition in every stitch, this is the ultimate baseball cap.</p>

                                  <p class="price-info"><b>590 Baht</b></p>

                                  <label>Quantity:</label>
                                  <input type="text" value="3" />

                                  <button type="button" class="btn btn-fefault cart">
                                    <i class="fa fa-shopping-cart"></i>
                  										Add to cart
                  									</button>
                                  <p class="product-amount"><b>Amount:</b> 55</p>
                                  <p class="product-brand"><b>Brand:</b> New Era</p>
                                  <p class="product-color"><b>Color:</b> Black</p>
                                  <p class="product-cat"><b>Category:</b> Caps</p>
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
export default {
  name: 'allproduct',
  data () {
    return {
      msg: 'test'
    }
  }
}
</script>
