import Vue from 'vue'
import VueRouter from 'vue-router'
import { addDynamicRoute } from '@/utils/my-router-utils'
import store from '@/store'

Vue.use(VueRouter)

const routes = [{
  name: 'main',
  path: '/main',
  component: () => import('@/views/main/MainView')
}, {
  name: 'login',
  path: '/login',
  component: () => import('@/views/LoginView')
}]

function createRouter () {
  return new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
  })
}

const router = createRouter()

export function resetRouter () {
  router.matcher = createRouter().matcher
}

router.beforeEach((to, from, next) => {
  // 有可以进入的路由则直接进入
  if (router.getMatchedComponents(to).length > 0) next()
  else { // 没有则进行路由的动态添加
    const menuList = store.state.menu.menuList
    const menuParentName = 'main'
    // 重置路由并动态添加路由
    addDynamicRoute(menuParentName, menuList)
    router.addRoute({
      path: '*',
      name: 'notFound',
      component: () => import('@/views/NotFound')
    })
    next(to)
  }
})

export default router
