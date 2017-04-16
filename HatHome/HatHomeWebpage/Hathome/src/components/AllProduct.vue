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
                    <div class="all-product">
                        <div class="tab-content">
                            <div class="tab-pane fade active in">
                                <!-- item1 -->
                                <div class="col-sm-4">
                                    <div class="product-image-wrapper">
                                        <div class="single-products">
                                            <div class="productinfo text-center">
                                                <a href="product-details.html"><img src="http://i.imgur.com/Ns4cgfm.jpg" />
                                                    <h4>Easy Polo Black Edition</h4></a>
                                                <p>56 Baht</p>
                                                <a href="#" class="btn btn-default custom-button"><i class="fa fa-shopping-cart"></i></a>
                                                <a href="#" class="btn btn-default custom-button"><i class="fa fa-star"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- item2 -->
                                <div class="col-sm-4">
                                    <div class="product-image-wrapper">
                                        <div class="single-products">
                                            <div class="productinfo text-center">
                                                <img src="http://i.imgur.com/hflVXay.jpg" alt="" />
                                                <h4>Easy Polo Black Edition</h4>
                                                <p>56 Baht</p>
                                                <a href="#" class="btn btn-default custom-button"><i class="fa fa-shopping-cart"></i></a>
                                                <a href="#" class="btn btn-default custom-button"><i class="fa fa-star"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- item3 -->
                                <div class="col-sm-4">
                                    <div class="product-image-wrapper">
                                        <div class="single-products">
                                            <div class="productinfo text-center">
                                                <img src="http://i.imgur.com/kj7tXU7.jpg" alt="" />
                                                <h4>Easy Polo Black Edition</h4>
                                                <p>56 Baht</p>
                                                <a href="#" class="btn btn-default custom-button"><i class="fa fa-shopping-cart"></i></a>
                                                <a href="#" class="btn btn-default custom-button"><i class="fa fa-star"></i></a>
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
