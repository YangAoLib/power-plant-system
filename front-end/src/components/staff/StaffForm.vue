<template>
  <!-- 基础的人员信息表单 -->
  <el-form ref="staffForm" :model="staffInfo" :rules="update ? updateRules : rules" label-width="100px" style="width: 600px" :inline="inline">
    <el-form-item label="姓名" prop="name" class="form-item">
      <el-input v-model.trim="staffInfo.name" placeholder="请输入姓名" :maxlength="30" show-word-limit style="width: 240px"/>
    </el-form-item>
    <el-form-item label="密码" prop="password" class="form-item">
      <el-input v-model.trim="staffInfo.password" type="password" placeholder="请输入密码(最多20个字)" show-password
                :maxlength="20" show-word-limit style="width: 240px"/>
    </el-form-item>
    <el-form-item label="确认密码" prop="checkPassword" class="form-item">
      <el-input v-model.trim="staffInfo.checkPassword" type="password" placeholder="请输入确认密码" show-password
                :maxlength="20" show-word-limit style="width: 240px"/>
    </el-form-item>
    <el-form-item label="祖籍" prop="originalHome" class="form-item">
      <el-input v-model="staffInfo.originalHome" placeholder="请输入祖籍" :maxlength="30" show-word-limit
                style="width: 240px"/>
    </el-form-item>
    <el-form-item label="性别" prop="sex" class="form-item">
      <el-radio-group v-model="staffInfo.sex">
        <el-radio-button v-for="(item, index) in sex" :key="index" :label="item.value">{{ item.desc }}
        </el-radio-button>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="在职状态" prop="status" class="form-item">
      <el-radio-group v-model="staffInfo.status">
        <el-radio-button v-for="(item, index) in status" :key="index" :label="item.value">{{ item.desc }}
        </el-radio-button>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="联系电话" prop="phone" class="form-item">
      <el-input v-model.trim="staffInfo.phone" placeholder="请输入联系电话" :maxlength="13" show-word-limit
                style="width: 240px"/>
    </el-form-item>
    <el-form-item label="身份证号" prop="cardId" class="form-item">
      <el-input v-model="staffInfo.cardId" placeholder="请输入身份证号" :maxlength="18" show-word-limit style="width: 240px"/>
    </el-form-item>

    <el-form-item label="出生日期" prop="birthDate" class="form-item">
      <el-date-picker
        v-model="staffInfo.birthDate"
        type="date"
        style="width: 100%"
        placeholder="选择出生日期"/>
    </el-form-item>

    <el-form-item label="职务" prop="dutyIdList" class="form-item">
      <el-select v-model="dutyIdListArray" placeholder="请选择职务" multiple filterable clearable style="width: 240px">
        <el-option v-for="(item, index) in duties" :key="index" :label="item.name" :value="item.id"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="科室" prop="officeIdList" class="form-item">
      <el-select v-model="officeIdListArray" placeholder="请选择科室" multiple filterable clearable style="width: 240px">
        <el-option v-for="(item, index) in offices" :key="index" :label="item.name" :value="item.id"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item class="form-item" :id="inline ? 'form-item-option-inline' : 'form-item-option'">
      <el-button type="primary" @click="onSubmit"><span v-if="update">更新</span><span v-else>上传</span></el-button>
      <el-button @click="$refs.staffForm.resetFields()">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import clone from 'clone'
import { CARD_ID_REGEXP, PHONE_REGEXP } from '@/utils/regexp-const'
import { SERVER_ERROR_MESSAGE } from '@/utils/string-utils'
import { commonArrayEmptyNull } from '@/utils/common-computed'

/**
 * 可用于添加和更新操作
 */
export default {
  name: 'StaffForm',
  props: {
    // 是否用于更新
    update: {
      type: Boolean,
      default: false
    },
    // 前置人员信息
    info: {
      type: Object,
      // 默认都为空
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
          status: 0,
          dutyIdList: [],
          officeIdList: []
        }
      }
    },
    inline: {
      type: Boolean,
      default: false
    }
  },
  emits: ['submit', 'update'],
  data () {
    return {
      // 人员信息, 从传递进来的数据进行克隆
      staffInfo: clone(this.info),
      // 表单验证规则
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
          required: true,
          message: '请输入姓名',
          trigger: 'blur'
        }, {
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
          required: true,
          message: '请输入手机号',
          trigger: 'blur'
        }, {
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
      status: [],
      duties: [],
      offices: []
    }
  },
  created () {
    // 获取性别和在职状态的枚举类
    this.getSexEnums()
    this.getStatusEnums()
    // 获取职务信息
    this.getDuties()
    // 获取科室信息
    this.getOffices()
    // 确认是使用 创建者id 还是 更新者id
    if (this.update) {
      this.staffInfo.updateId = JSON.parse(localStorage.getItem('userId'))
    } else {
      this.staffInfo.createId = JSON.parse(localStorage.getItem('userId'))
    }
  },
  activated () {
    console.log('active')
    this.staffInfo = clone(this.info)
  },
  methods: {
    /**
     * 验证密码
     */
    validatePassword (rule, value, callback) {
      if (value === null || value === '') {
        callback(new Error('请输入密码'))
      } else {
        // 长度不能超过20个字符
        if (value.length > 20) callback(new Error('密码长度不能超过20个字符'))
        // 如果确认密码不为空, 验证确认密码
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
     * 更新, 验证密码
     */
    validatePasswordUpdate (rule, value, callback) {
      if (value !== '' && this.staffInfo.checkPassword !== '') {
        this.$refs.staffForm.validateField('checkPassword')
        callback()
      } else if (value.length > 20) {
        // 长度不能超过20个字符
        callback(new Error('密码长度不能超过20个字符'))
      } else {
        callback()
      }
    },
    /**
     * 更新, 验证确认密码
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
     * 更新, 验证手机号
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
     * 更新, 验证身份证号
     */
    validateCardIdUpdate (rule, value, callback) {
      if (value === null || value === '') {
        callback(new Error('请输入身份证号'))
      } else if (!CARD_ID_REGEXP.test(this.staffInfo.cardId)) {
        // 使用统一的全局正则进行验证
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
          return false
        }
      })
    },
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
     * 获取职务信息
     */
    getDuties () {
      this.axios.get('/duty/query-all').then(res => {
        if (res.status === 200) {
          // 获取全部的职务信息, 用于选项
          this.duties = res.data
        } else {
          // 错误提示
          console.log(res)
          this.$message.error(SERVER_ERROR_MESSAGE)
        }
      }).catch(err => {
        console.log(err)
        this.$message.error(SERVER_ERROR_MESSAGE)
      })
    },
    /**
     * 获取科室信息
     */
    getOffices () {
      this.axios.get('/office/query-all').then(res => {
        if (res.status === 200) {
          this.offices = res.data
        } else {
          console.log(res)
          this.$message.error(SERVER_ERROR_MESSAGE)
        }
      }).catch(err => {
        console.log(err)
        this.$message.error(SERVER_ERROR_MESSAGE)
      })
    }
  },
  computed: {
    dutyIdListArray: commonArrayEmptyNull('dutyIdList', 'staffInfo'),
    officeIdListArray: commonArrayEmptyNull('officeIdList', 'staffInfo')
  }
}
</script>

<style scoped>
.form-item {
  width: 340px !important;
}

#form-item-option-inline {
  display: block;
  width: 100% !important;
  text-align: center;
}

#form-item-option {
  width: 100% !important;
  text-align: right;
}
</style>
