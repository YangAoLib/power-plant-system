<template>
  <div>
    <template v-if="menuItem.children != null && menuItem.children.length > 0">
      <el-submenu :index="menuItem.path">
        <template slot="title">
          <i :class="menuItem.icon"></i>
          <span>{{menuItem.title}}</span>
        </template>
        <my-menu-item v-for="(childMenuItem, index) in menuItem.children" :key="index" :item="childMenuItem"></my-menu-item>
      </el-submenu>
    </template>
    <template v-else>
      <el-menu-item :index="menuItem.path">
        <i :class="menuItem.icon"></i>
        <span slot="title">{{ menuItem.title }}</span>
      </el-menu-item>
    </template>
  </div>
</template>

<script>

import * as Path from 'path'
import clone from 'clone'

export default {
  name: 'MyMenuItem',
  props: {
    item: {
      type: Object,
      require: true
    },
    parentPath: {
      type: String,
      default: '/'
    }
  },
  data () {
    return {
      menuItem: clone(this.item),
      prefixPath: this.parentPath
    }
  },
  created () {
    this.menuItem.path = Path.join(this.prefixPath, this.menuItem.path)
    const children = this.menuItem.children
    if (children) {
      children.forEach(value => {
        value.path = Path.join(this.menuItem.path, value.path)
      })
    }
  }
}
</script>

<style scoped>

</style>
