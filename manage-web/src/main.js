// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

//引入element-ui控件
import ElementUI from 'element-ui'
// import 'element-ui/lib/theme-chalk/index.css'
//引入font-awesome
import 'font-awesome/css/font-awesome.min.css'
//引入主题
import './assets/theme/element-#409EFF/index.css'
//引入axios
import Api from './axios/axios.js'



Vue.config.productionTip = false

//使用use命令后才起作用
Vue.use(ElementUI)
Vue.prototype.$api = Api

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
