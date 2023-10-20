import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

// 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单跳转当前页面报错问题
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  { path: '/', name: 'Manager', component: () => import('../components/Manager'), redirect: '/home',
    children: [
      { path: 'home', name: 'Home', component: () => import('../views/manager/Home') },
      { path: 'user', name: 'User', component: () => import('../views/manager/User')},
      { path: 'person', name: 'Person', component: () => import('../views/manager/Person')},
      { path: 'goods', name: 'Goods', component: () => import('../views/manager/Goods')},
      { path: 'stash', name: 'Stash', component: () => import('../views/manager/Stash')},
      { path: 'storein', name: 'Storein', component: () => import('../views/manager/Storein')},
      { path: 'storeout', name: 'Storeout', component: () => import('../views/manager/Storeout')},
      { path: 'store', name: 'Store', component: () => import('../views/manager/Store')},
    ]
  },

  { path: '/login', name: 'Login', component: () => import('../views/Login')},
  { path: '*', component: () => import('../views/404') }
]

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes
})

export default router
