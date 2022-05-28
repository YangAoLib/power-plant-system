<template>
  <div id="main-header">
    <div class="left">电厂信息业务支撑平台</div>
    <div class="right">
      <el-dropdown placement="bottom" @command="dropdownCommand">
        <el-button type="primary">
          个人中心<i class="el-icon-arrow-down el-icon--right"></i>
        </el-button>
        <el-dropdown-menu slot="dropdown"><!--style="text-align: center"-->
          <el-dropdown-item icon="el-icon-user">个人信息</el-dropdown-item>
          <el-dropdown-item icon="el-icon-setting">设置</el-dropdown-item>
          <el-dropdown-item icon="el-icon-switch-button" command="logout">退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MainHeader',
  methods: {
    // 执行下拉菜单中菜单项命令
    dropdownCommand (command) {
      this[command]()
    },
    // todo: 退出方法
    logout () {
      console.log('test')
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
#main-header {
  width: 100%;
  height: 100%;
  background-color: #409EFF;
  color: #FFFFFF;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 50px 0 30px;
  box-sizing: border-box;
}

.left {
  font-weight: bold;
  font-size: 1.5rem;
}

.right {
  color: #FFFFFF;
}
</style>
