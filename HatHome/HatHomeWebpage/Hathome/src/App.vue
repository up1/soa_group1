<template>
  <div id="app">
    <nav class="navbar navbar-fixed-top" style="background-color: white;">
      <div class="container" style="margin-top: 8px">
        <div class="row">
          <div class="col-sm-3">
            <div class="logo pull-left">
              <router-link to="/recentproducts">
                <img src="./assets/images/logo.png" width="108" height="30" alt=""/>
              </router-link>
            </div>
          </div>

          <div class="col-sm-3">
            <div class="input-group stylish-input-group">
              <input type="text" class="search_box" placeholder="Search"/>
              <button type="submit">
                  <i class="fa fa-search"></i>
              </button>
            </div>
          </div>

          <div class="col-sm-6">
            <div class="shop-menu pull-right">
              <ul class="nav navbar-nav">
                <li v-if="$auth.check()">
                  <router-link :to="{ name: 'wishlist', params: { userid: this.$auth.user().id}}">
                    <i class="fa fa-star"></i> Wishlist
                  </router-link>
                </li>
                <li v-if="$auth.check()">
                  <router-link :to="{ name: 'cart', params: { userId: this.$auth.user().id}}">
                    <a href="cart.html"><i class="fa fa-shopping-cart"></i> Cart</a>
                  </router-link>
                </li>
                <li v-if="$auth.check()">
                  <router-link :to="{ name: 'userProfile', params: { userId: this.$auth.user().id}}">
                    <i class="fa fa-user"></i> Account
                  </router-link>
                </li>
                <li v-if="$auth.check()">
                  <a href="" @click.prevent="logout()"><i class="fa fa-lock"></i> Logout</a>
                </li>
                <li v-if="!$auth.check()">
                  <router-link :to="{ name: 'login'}">
                    <i class="fa fa-lock"></i> Sign in
                  </router-link>
                </li>
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
            <div class="single-widget" align="left">
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
    name: 'app',
    data () {
      return {}
    },
    methods: {
      logout () {
        this.$auth.logout({
          success () {
            console.log('success ' + this.context)
          },
          error () {
            console.log('error ' + this.context)
          }
        })
      }
    }
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
  .stylish-input-group .input-group-addon{
    background: #e9f0ea;
    border: medium none;
    height: 30px;
    border-radius: 0px ;
    outline: medium none;
  }
</style>
