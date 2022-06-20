<template>
  <div id="mian-view">
    <el-container style="height: 100vh">
      <!--头部-->
      <el-header style="padding: 0; position: sticky"><main-header></main-header></el-header>
      <el-container>
        <!--侧边栏-->
        <el-aside style="width: 200px"><main-aside></main-aside></el-aside>
        <!--主要显示部分-->
        <el-main>
          <main-breadcrumb></main-breadcrumb>
          <div style="height: 92%">
            <router-view />
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import MainHeader from '@/components/main/MainHeader'
import MainAside from '@/components/main/MainAside'
import MainBreadcrumb from '@/components/main/MainBreadcrumb'

export default {
  name: 'MainView',
  components: { MainBreadcrumb, MainAside, MainHeader },
  created () {
  }
}
</script>

<style scoped>

</style>
