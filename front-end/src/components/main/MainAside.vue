<template>
  <div id="main-aside">
    <el-menu
      :default-active="$route.path"
      router
      style="height: 100%"
      >
      <my-menu-item v-for="(item,index) in items" :key="index" :item="item" :parent-path="parentPath"></my-menu-item>
    </el-menu>
  </div>
</template>

<script>
import MyMenuItem from '@/components/MyMenuItem'
import MyRouterUtils from '@/utils/my-router-utils'
import { resetRouter } from '@/router'
export default {
  name: 'MainAside',
  components: { MyMenuItem },
  data () {
    return {
      // 菜单项的前置路由名称
      parentName: '',
      // 菜单项的前置路径
      parentPath: '',
      /*
      path: 不能以 / 开头
      name: 路由名称
      title: 菜单标题
      icon: 菜单图标
      children: 子菜单
       */
      items: []
    }
  },
  created () {
    // 获取路由信息
    this.parentName = this.$store.state.menuParentName
    this.parentPath = this.$store.state.menuPrefix
    this.items = this.$store.state.menuList
    // 如果进入的是主页则对路由进行初始化操作
    if (this.$route.name === this.parentName) this.myAddRoute()
  },
  methods: {
    // 添加路由操作
    myAddRoute () {
      // TODO: 路由操作 需要更改到登录操作之后
      // 重置路由
      resetRouter()
      // 存储菜单列表
      // this.$store.commit(MenuList.SET_MENU_LIST, this.items)
      // this.$store.commit(MenuList.SET_MENU_PARENT_NAME, this.parentName)
      // this.$store.commit(MenuList.SET_MENU_PREFIX, this.parentPath)
      // 动态添加路由
      MyRouterUtils.addDynamicRoute(this.parentName, this.items)
      /* // 对当前的列表进行组件的生成
      this.items.forEach(item => MyRouterUtils.componentGet(item))
      // 将列表信息添加到路由中
      this.items.forEach(item => MyRouterUtils.addRoute(this.parentName, item)) */
    }
  }
}
</script>

<style scoped>
#main-aside {
  height: 100%;
}
</style>
