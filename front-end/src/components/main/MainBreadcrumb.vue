<template>
  <div id="main-breadcrumb">
    <div id="main-breadcrumb-body">
      <el-breadcrumb separator="|" style="height: 1rem;" v-if="breadcrumbList.length > 0">
        <template v-for="(item, index) in breadcrumbList">
          <template v-if="item.meta.title">
            <el-breadcrumb-item v-if="item.name !== $route.name" :key="index" :to="item">{{
                item.meta.title
              }}
            </el-breadcrumb-item>
            <el-breadcrumb-item v-else :key="index">{{ item.meta.title }}</el-breadcrumb-item>
          </template>
        </template>
      </el-breadcrumb>
      <template v-else>
        暂无记录
      </template>
      <el-button size="mini" @click="resetMenuBreadcrumb">清空</el-button>
    </div>
    <hr/>
  </div>
</template>

<script>
import MenuBreadcrumb from '@/store/types/menu-breadcrumb'

export default {
  name: 'MainBreadcrumb',
  methods: {
    // 重置面包屑记录
    resetMenuBreadcrumb () {
      this.$store.commit(MenuBreadcrumb.RESET_MENU_BREADCRUMB)
    }
  },
  watch: {
    $route (route) {
      const {
        name,
        path,
        meta
      } = route
      const temp = {
        name,
        path,
        meta
      }
      this.$store.commit(MenuBreadcrumb.ADD_MENU_BREADCRUMB, temp)
    }
  },
  computed: {
    breadcrumbList () {
      return this.$store.state.menuBreadcrumb
    }
  }
}
</script>

<style scoped>
#main-breadcrumb-body {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
