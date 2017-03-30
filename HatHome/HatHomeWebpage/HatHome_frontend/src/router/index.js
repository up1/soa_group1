import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Mimi from '@/components/Mimi'

const router = new Router({
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
      path: '/mark',
      name: 'Mark',
      component: Mimi
    }
  ]
})

Vue.use(Router)
Vue.router = router
export default router
