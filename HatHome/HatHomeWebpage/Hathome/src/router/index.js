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

Vue.use(Router)

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
    }
  ]
})
Vue.router = router
Vue.use(Router)
export default router
