<template>
  <el-form ref="staffForm" :model="staffInfo" :rules="update ? updateRules : rules" label-width="100px"
           style="width: 600px">
    <el-form-item label="姓名" prop="name">
      <el-input v-model.trim="staffInfo.name"/>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model.trim="staffInfo.password" type="password"/>
    </el-form-item>
    <el-form-item label="确认密码" prop="checkPassword">
      <el-input v-model.trim="staffInfo.checkPassword" type="password"/>
    </el-form-item>
    <el-form-item label="性别" prop="sex">
      <el-radio-group v-model="staffInfo.sex">
        <el-radio-button v-for="(item, index) in sex" :key="index" :label="item.value">{{ item.desc }}
        </el-radio-button>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="联系电话" prop="phone">
      <el-input v-model.trim="staffInfo.phone"/>
    </el-form-item>
    <el-form-item label="身份证号" prop="cardId">
      <el-input v-model="staffInfo.cardId"/>
    </el-form-item>
    <el-form-item label="祖籍" prop="originalHome">
      <el-input v-model="staffInfo.originalHome"/>
    </el-form-item>
    <el-form-item label="出生日期" prop="birthDate">
      <el-date-picker
        v-model="staffInfo.birthDate"
        type="date"
        placeholder="选择出生日期"/>
    </el-form-item>
    <el-form-item label="在职状态" prop="status">
      <el-radio-group v-model="staffInfo.status">
        <el-radio-button v-for="(item, index) in status" :key="index" :label="item.value">{{ item.desc }}
        </el-radio-button>
      </el-radio-group>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit"><span v-if="update">更新</span><span v-else>添加</span>人员信息</el-button>
      <el-button @click="$refs.staffForm.resetFields()">清空表单</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import clone from 'clone'
import { CARD_ID_REGEXP, PHONE_REGEXP } from '@/utils/regexp-const'

export default {
  name: 'StaffForm',
  props: {
    update: {
      type: Boolean,
      default: false
    },
    info: {
      type: Object,
      default: () => {
        return {
          name: '',
          password: '',
          checkPassword: '',
          sex: 0,
          phone: '',
          cardId: '',
          originalHome: '',
          birthDate: '',
          status: 0
        }
      }
    }
  },
  emits: ['submit', 'update'],
  data () {
    return {
      staffInfo: clone(this.info),
      rules: {
        name: [{
          required: true,
          message: '请输入人员姓名',
          trigger: 'blur'
        }, {
          max: 255,
          message: '人员姓名长度不能超过255个字符',
          trigger: 'blur'
        }],
        password: [{
          required: true,
          message: '请输入密码',
          trigger: 'blur'
        }, {
          validator: this.validatePassword,
          trigger: 'blur'
        }],
        checkPassword: [{
          required: true,
          message: '请输入确认密码',
          trigger: 'blur'
        }, {
          validator: this.validateCheckPassword,
          trigger: 'blur'
        }],
        sex: [{
          required: true,
          message: '请选择性别',
          trigger: 'blur'
        }],
        phone: [{
          required: true,
          message: '请输入手机号',
          trigger: 'blur'
        }, {
          validator: this.validatePhone,
          trigger: 'blur'
        }],
        cardId: [{
          required: true,
          message: '请输入身份证号',
          trigger: 'blur'
        }, {
          validator: this.validateCardId,
          trigger: 'blur'
        }],
        originalHome: [{
          required: true,
          message: '请输入祖籍',
          trigger: 'blur'
        }, {
          max: 255,
          message: '祖籍的长度不能超过255个字符',
          trigger: 'blur'
        }],
        birthDate: [{
          required: true,
          message: '请选择人员的出生日期',
          trigger: 'blur'
        }],
        status: [{
          required: true,
          message: '请选择人员的在职状态',
          trigger: 'blur'
        }]
      },
      updateRules: {
        name: [{
          max: 255,
          message: '人员姓名长度不能超过255个字符',
          trigger: 'blur'
        }],
        password: [{
          validator: this.validatePasswordUpdate,
          trigger: 'blur'
        }],
        checkPassword: [{
          validator: this.validateCheckPasswordUpdate,
          trigger: 'blur'
        }],
        sex: [{
          required: true,
          message: '请选择性别',
          trigger: 'blur'
        }],
        phone: [{
          validator: this.validatePhoneUpdate,
          trigger: 'blur'
        }],
        cardId: [{
          required: true,
          message: '请输入身份证号',
          trigger: 'blur'
        }, {
          validator: this.validateCardIdUpdate,
          trigger: 'blur'
        }],
        originalHome: [{
          required: true,
          message: '请输入祖籍',
          trigger: 'blur'
        }, {
          max: 255,
          message: '祖籍的长度不能超过255个字符',
          trigger: 'blur'
        }],
        birthDate: [{
          required: true,
          message: '请选择人员的出生日期',
          trigger: 'blur'
        }],
        status: [{
          required: true,
          message: '请选择人员的在职状态',
          trigger: 'blur'
        }]
      },
      sex: [],
      status: []
    }
  },
  created () {
    this.getSexEnums()
    this.getStatusEnums()
    // 确认是使用 创建者id 还是 更新者id
    if (this.update) {
      this.staffInfo.updateId = JSON.parse(localStorage.getItem('userId'))
    } else {
      this.staffInfo.createId = JSON.parse(localStorage.getItem('userId'))
    }
    console.log(this.staffInfo)
  },
  methods: {
    /**
     * 获取性别枚举信息
     */
    getSexEnums () {
      this.axios.get('/enums/sex').then(res => {
        this.sex = res.data
      })
    },
    /**
     * 获取在职状态枚举信息
     */
    getStatusEnums () {
      this.axios.get('/enums/status').then(res => {
        this.status = res.data
      })
    },
    /**
     * 验证密码
     */
    validatePassword (rule, value, callback) {
      if (value === null || value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.staffInfo.checkPassword !== '') {
          this.$refs.staffForm.validateField('checkPassword')
        }
        callback()
      }
    },
    /**
     * 验证确认密码
     */
    validateCheckPassword (rule, value, callback) {
      if (value === null || value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.staffInfo.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    },
    /**
     * 验证手机号
     */
    validatePhone (rule, value, callback) {
      if (value === null || value === '') {
        callback(new Error('请输入手机号'))
      } else if (!PHONE_REGEXP.test(this.staffInfo.phone)) {
        callback(new Error('手机号格式错误'))
      } else {
        callback()
      }
    },
    /**
     * 验证身份证号
     */
    validateCardId (rule, value, callback) {
      if (value === null || value === '') {
        callback(new Error('请输入身份证号'))
      } else if (!CARD_ID_REGEXP.test(this.staffInfo.cardId)) {
        callback(new Error('身份证号格式错误'))
      } else {
        callback()
      }
    },

    /**
     * 更新验证密码
     */
    validatePasswordUpdate (rule, value, callback) {
      if (value !== '' && this.staffInfo.checkPassword !== '') {
        this.$refs.staffForm.validateField('checkPassword')
        callback()
      } else {
        callback()
      }
    },
    /**
     * 更新验证确认密码
     */
    validateCheckPasswordUpdate (rule, value, callback) {
      // 主密码不为空才进行判断
      if (this.staffInfo.password === undefined || this.staffInfo.password === null || this.staffInfo.password === '') {
        callback()
      } else if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.staffInfo.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    },
    /**
     * 更新验证手机号
     */
    validatePhoneUpdate (rule, value, callback) {
      if (value === null || value === '') {
        callback(new Error('请输入手机号'))
      } else if (!PHONE_REGEXP.test(this.staffInfo.phone)) {
        callback(new Error('手机号格式错误'))
      } else {
        callback()
      }
    },
    /**
     * 更新验证身份证号
     */
    validateCardIdUpdate (rule, value, callback) {
      if (value === null || value === '') {
        callback(new Error('请输入身份证号'))
      } else if (!CARD_ID_REGEXP.test(this.staffInfo.cardId)) {
        callback(new Error('身份证号格式错误'))
      } else {
        callback()
      }
    },
    /**
     * 提交表单
     */
    onSubmit () {
      this.$refs.staffForm.validate((valid) => {
        const event = 'submit'
        if (valid) {
          this.$emit(event, {
            info: this.staffInfo,
            form: this.$refs.staffForm
          })
        } else {
          this.$message.error('表单参数错误')
          return false
        }
      })
    }
  }
}
</script>

<style scoped>

</style>