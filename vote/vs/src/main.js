import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue'
import router from "./router";
import store from "./store";
import './api/mock'
import Cookie from "js-cookie";

Vue.config.productionTip = false
Vue.use(ElementUI);

//添加全局前置导航首位
router.beforeEach((to, from, next) => {
  const token = Cookie.get('token')

  if(!token && to.name !== 'login' && to.name!== 'register'){
    next({ name : 'login'})
  } else if (token && to.name === 'login'){
    next({name : 'home'})
  } else {
    next()
  }
})
new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
