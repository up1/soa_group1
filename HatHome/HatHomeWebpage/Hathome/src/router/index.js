import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import RecentProducts from '@/components/RecentProducts'
import Products from '@/components/Products'
import ProductsPage from '@/components/Products'
import ProductDetail from '@/components/ProductDetail'

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
      path: '/products/page/:page',
      name: 'productPage',
      component: Products
    }
  ],
  scrollBehavior (to, from, savedPosition) {
    return { x: 0, y: 0 }
  }
})
Vue.router = router
Vue.use(Router)
export default router
