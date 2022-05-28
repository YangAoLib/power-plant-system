import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import clone from '@/utils/clone'
import MenuList from '@/store/types/menu-list'

Vue.use(Vuex)

const menu = {
  state: () => ({
    menuList: [],
    menuPrefix: '',
    menuParentName: ''
  }),
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

  }
}

export default new Vuex.Store({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    menu
  },
  plugins: [
    createPersistedState({
      storage: window.sessionStorage
    })
  ]
})
