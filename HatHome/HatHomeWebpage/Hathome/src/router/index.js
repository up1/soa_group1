import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Billing from '@/components/Billing'

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
      path: '/billing',
      name: 'Billing',
      component: Billing
    }
  ]
})
