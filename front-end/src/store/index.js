import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import clone from 'clone'
import MenuList from '@/store/types/menu-list'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    menuList: [{
      path: 'staff',
      name: 'staff',
      title: '人员管理',
      icon: 'el-icon-user-solid',
      children: [{
        path: 'add',
        name: 'staffAdd',
        title: '人员添加',
        icon: 'el-icon-plus',
        component: '/main/staff/StaffAdd'
      }, {
        path: 'find',
        name: 'staffFind',
        title: '人员查看',
        icon: 'el-icon-s-grid',
        component: '/main/staff/StaffFind'
      }]
    }],
    menuPrefix: '/main',
    menuParentName: 'main'
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
