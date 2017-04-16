<template>
  <div id="app">
      <nav class="navbar navbar-fixed-top" style="background-color: white;">
        <div class="container" style="margin-top: 8px">
          <div class="row">
            <div class="col-sm-3">
              <div class="logo pull-left">
                <a href="index.html"><img src="./assets/images/logo.png" width="108" height="30" alt="" /></a>
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
    <router-view></router-view>
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
