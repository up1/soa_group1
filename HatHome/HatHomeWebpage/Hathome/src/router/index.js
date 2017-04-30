import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import RecentProducts from '@/components/RecentProducts'
import Products from '@/components/Products'
import ProductDetail from '@/components/ProductDetail'
import Login from '@/components/Login'
import Register from '@/components/Register'
import Profile from '@/components/Profile'
import Cart from '@/components/Cart'
import Wishlist from '@/components/Wishlist'
import Billing from '@/components/Billing'
import VueAuth from '@websanova/vue-auth'
import VueResource from 'vue-resource'
import custom from '@/driver/custom'
var router = new Router({
  hashbang: false,
  linkActiveClass: 'active',
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/recentProducts',
      name: 'recentProducts',
      component: RecentProducts
    },
    {
      path: '/products',
      name: 'products',
      component: Products
    },
    {
      path: '/products/:id',
      name: 'productDetail',
      component: ProductDetail
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/user',
      name: 'userRegister',
      component: Register
    },
    {
      path: '/user/:userId',
      name: 'userProfile',
      component: Profile
    },
    {
      path: '/cart/:userId',
      name: 'cart',
      component: Cart
    },
    {
      path: '/wishlist/:userid',
      name: 'wishlist',
      component: Wishlist
    },
    {
      path: '/bill/:id',
      name: 'Billing',
      component: Billing
    },
    {
      path: '/logout',
      name: 'logout'
    }
  ],
  scrollBehavior (to, from, savedPosition) {
    return { x: 0, y: 0 }
  }
})

Vue.router = router
Vue.use(Router)
Vue.use(VueResource)
Vue.http.options.xhr = {withCredentials: true}

Vue.use(VueAuth, {
  auth: custom,
  http: require('@websanova/vue-auth/drivers/http/vue-resource.1.x.js'),
  router: require('@websanova/vue-auth/drivers/router/vue-router.2.x.js'),
  token: [{request: 'Authorization', response: 'Authorization', authType: 'bearer', foundIn: 'header'}, {request: 'token', response: 'token', authType: 'custom1', foundIn: 'response'}],
  refreshData: {
    enabled: false // true by default.
  },
  loginData: {
    url: 'http://localhost:9002/login'
  },
  fetchData: {
    url: 'http://localhost:9007/user/profile'
  },
  parseUserData: function (data) {
    return data
  },
  registerData: {
    url: 'http://localhost:9002/user'
  }
})

export default router
