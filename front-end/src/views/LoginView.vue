<template>
  <div id="login-view">
    <el-row v-if="loginOrRegister===1">
      <el-col :span="8" :offset="7" class="padding-card">
        <el-card class="box-card" shadow="hover">
          <template #header>
            <div class="card-header">
              <span class="head-text">电厂平台登录</span>
            </div>
          </template>
          <div>
            <!-- 表单的登录-->
            <el-form ref="form" :model="form" :rules="rules">
              <el-form-item label="登录账号" prop="name" aria-placeholder="请输入用户名" class="text">
                <el-input v-model="form.name" class="input-bg"/>
              </el-form-item>
              <el-form-item label="登录密码" prop="pass" class="text">
                <el-input v-model="form.pass" show-password class="input-bg"/>
              </el-form-item>
              <el-row>
                <el-col :span="18" :offset="4">
                  <el-form-item>
                    <el-button type="primary" @click="onSubmit" style="margin-left: 35%;font-family: '华文楷体',serif">登录
                    </el-button>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'LoginView',
  data () {
    return {
      loginOrRegister: 1,
      form: {
        name: '',
        pass: '',
        email: ''
      },
      rules: {
        name: [{
          required: true, message: '请输入用户名', trigger: 'blur'
        }],
        password: [{
          required: true, message: '请输入密码', trigger: 'blur'
        }],
        email: [{
          required: true, message: '请输入邮箱', trigger: 'blur'
        }]
      }
    }
  },
  methods: {
    onSubmit () {
      const _this = this
      if (this.form.name.length === 0 || this.form.pass.length === 0) {
        _this.$message({ type: 'error', message: '用户名或密码不能为空' })
      }
      this.$refs.form.validate(validate => {
        // 验证通过 进行请求
        if (validate) {
          const data = { phone: this.form.name, password: this.form.pass }
          this.axios.post('/user/login', data, {
            headers: {
              noToken: true
            }
          }).then(res => {
            if (res.status === 200) {
              // 存储token
              localStorage.setItem('token', res.data.token)
              localStorage.setItem('userId', res.data.userId)
              // 页面跳转
              this.$router.push('/main')
            } else {
              console.log(res.message)
              console.log(res.data)
              this.$message.error('登录失败')
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>
#login-view {
  background-image: url("../assets/loginBg.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  position: fixed;
  height: 100%;
  width: 100%;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
  -webkit-text-fill-color: slateblue;
  color: deeppink;
  font-family: 楷体,serif;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 480px;
  background: beige;
  opacity: 0.7;
  border-radius: 50px
}

.padding-card {
  padding-top: 200px;
}

.input-bg {
  background-color: transparent;
  border: 0;
}

.head-text {
  -webkit-text-fill-color: deeppink;
  font-family: 华文行楷,serif;
  font-size: 20px;
  margin-left: 35%;
}
</style>
