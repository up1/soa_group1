<template>
<div id="app">
  <nav class="navbar navbar-fixed-top" style="background-color: white;">
    <div class="container" style="margin-top: 8px">
      <div class="row">
        <div class="col-sm-3">
          <div class="logo pull-left">
            <router-link to="/recentproducts">
              <img src="./assets/images/logo.png" width="108" height="30" alt="" />
            </router-link>
          </div>
        </div>

        <div class="col-sm-3">
          <div class="input-group stylish-input-group">
            <input type="text" class="search_box" placeholder="Search">
            <span class="input-group-addon ">
                            <button type="submit">
                                <span class="glyphicon glyphicon-search"></span>
            </button>
            </span>
          </div>
        </div>

        <div class="col-sm-6">
          <div class="shop-menu pull-right">
            <ul class="nav navbar-nav">
              <li><a href="wishlist.html"><i class="fa fa-star"></i> Wishlist</a></li>
              <li><a href="cart.html"><i class="fa fa-shopping-cart"></i> Cart</a></li>
              <li><a href="#"><i class="fa fa-user"></i> Account</a></li>
              <li><a href="login.html"><i class="fa fa-lock"></i> Login</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </nav>
  <!--/navbar-->
  <router-view></router-view>

  <footer id="footer">
       <!--Footer-->
       <div class="container">
           <div class="row">
               <div class="col-sm-12">
                   <div class="single-widget">
                       <h2>Member</h2>
                       <ul class="nav nav-pills nav-stacked">
                           <li>57070041 Traisak Traisenee</li>
                           <li>57070049 Thanapa Thamrongthanyawong</li>
                           <li>57070054 Tharathip Malaimarn</li>
                           <li>57070068 Pawarisa Thong-ngoen</li>
                           <li>57070145 Amita Mongkhonpreedarchai</li>

                       </ul>
                   </div>
               </div>
           </div>
       </div>
   </footer>
</div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
