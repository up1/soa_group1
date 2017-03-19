/**
 * Created by Mark on 3/19/2017.
 */
import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'

const router = new Router({
    hashbang: false,
    linkActiveClass: 'active',
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'Hello',
            component: Hello
        }
    ]
})

Vue.use(Router)
Vue.router = router
export default router