// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import './assets/css/animate.css'

/*
import './assets/css/bootstrap.min.css'
import './assets/css/font-awesome.min.css'

import './assets/css/price-range.css'
import './assets/css/prettyPhoto.css'
import './assets/css/responsive.css'

*/
/*
import './assets/js/bootstrap.min.js'
import './assets/js/html5shiv.js'
import './assets/js/jquery.js'
import './assets/js/main.js'
import './assets/js/price-range.js'
*/

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
