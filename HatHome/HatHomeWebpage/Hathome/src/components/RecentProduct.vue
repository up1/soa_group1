<template>
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
                    <h2 class="title text-center">NEW PRODUCTS</h2>
                    <div class="tab-content">
                        <div class="tab-pane fade active in">
                            <!-- item1 -->
                            <div class="col-sm-3">
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
                            <div class="col-sm-3">
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
                            <div class="col-sm-3">
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
                            <!-- item4 -->
                            <div class="col-sm-3">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <img src="http://i.imgur.com/NjNhKl1.jpg" alt="" />
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
            </div>
            <div class="row">
                <center>
                    <a href="all-product.html">
                        <button type="submit" class="btn btn-default view-all-btn">View All Product</button>
                    </a>
                </center>
            </div>
        </div>
    </section>

  </div>
</template>

<script>
export default {
  name: 'recentproduct',
  data () {
    return {
      msg: 'test'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
</style>
