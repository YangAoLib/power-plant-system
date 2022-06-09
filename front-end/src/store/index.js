import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import clone from 'clone'
import MenuList from '@/store/types/menu-list'
import MenuBreadcrumb from '@/store/types/menu-breadcrumb'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    menuList: [{
      path: 'staff',
      name: 'staff',
      meta: {
        title: '人员管理',
        icon: 'el-icon-user-solid'
      },
      children: [{
        path: 'add',
        name: 'staffAdd',
        component: '/main/staff/StaffAdd',
        meta: {
          title: '人员添加',
          icon: 'el-icon-plus'
        }
      }, {
        path: 'find',
        name: 'staffFind',
        component: '/main/staff/StaffFind',
        meta: {
          title: '人员查看',
          icon: 'el-icon-s-grid'
        }
      }]
    }],
    menuPrefix: '/main',
    menuParentName: 'main',
    menuBreadcrumb: []
  },
  getters: {
  },
  mutations: {
    [MenuList.SET_MENU_LIST] (state, menuList) {
      state.menuList = clone(menuList)
    },
    [MenuList.RESET_MENU_LIST] (state) {
      state.menuList = []
    },
    [MenuList.SET_MENU_PREFIX] (state, menuPrefix) {
      state.menuPrefix = menuPrefix
    },
    [MenuList.SET_MENU_PARENT_NAME] (state, menuParentName) {
      state.menuParentName = menuParentName
    },
    [MenuBreadcrumb.ADD_MENU_BREADCRUMB] (state, breadcrumb) {
      // 查找是否有相同的
      const indexExist = state.menuBreadcrumb.findIndex(item => item.name === breadcrumb.name)
      if (indexExist !== -1) {
        // 存在则先删除
        state.menuBreadcrumb.splice(indexExist, 1)
      }
      // 添加操作
      state.menuBreadcrumb.push(breadcrumb)
    },
    [MenuBreadcrumb.RESET_MENU_BREADCRUMB] (state) {
      state.menuBreadcrumb = []
    }
  },
  actions: {

  },
  plugins: [
    createPersistedState({
      storage: window.sessionStorage
    })
  ]
})
