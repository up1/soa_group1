import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import RecentProduct from '@/components/RecentProduct'
import AllProduct from '@/components/AllProduct'
import ProductDetail from '@/components/ProductDetail'

Vue.use(Router)

export default new Router({
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
      path: '/recentProduct',
      name: 'recentProduct',
      component: RecentProduct
    },
    {
      path: '/allProduct',
      name: 'allProduct',
      component: AllProduct
    },
    {
      path: '/productDetail',
      name: 'productDetail',
      component: ProductDetail
    }
  ]
})
