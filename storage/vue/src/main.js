import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import '@/assets/main.css'
import request from "@/utils/request";
import MyMessage from '@/utils/message'

Vue.config.productionTip = false

Vue.prototype.request = request
Vue.prototype.$baseUrl = process.env.VUE_APP_BASEURL
Vue.prototype.$message = MyMessage

Vue.use(ElementUI, {size: "mini"});

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')
