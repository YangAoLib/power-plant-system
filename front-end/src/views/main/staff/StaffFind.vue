<template>
  <div id="staff-find">
    <!-- 条件查询列表-->
    <div>
      <el-form ref="conditionForm" :model="condition" :rules="conditionRules" :style="{width: '100%', display: staffAllConditionForm ? null : 'inline'}" :label-width="staffAllConditionForm ? '120px' : '70px'" inline>
        <el-form-item label="姓名" prop="name" :class="{'form-item': staffAllConditionForm}">
          <el-input v-model.trim="nameStringNull" :maxlength="30" show-word-limit style="width: 240px"/>
        </el-form-item>
        <el-form-item label="性别" prop="sex" :class="{'form-item': staffAllConditionForm}">
          <el-radio-group v-model="condition.sex">
            <el-radio-button v-for="(item, index) in sex" :key="index" :label="item.value">{{
                item.desc
              }}
            </el-radio-button>
          </el-radio-group>
        </el-form-item >
        <el-form-item label="联系电话" prop="phone" :class="{'form-item': staffAllConditionForm}">
          <el-input v-model.trim="phoneStringNull" :maxlength="13" show-word-limit style="width: 240px"/>
        </el-form-item>
        <!-- 平时隐藏的查询条件 -->
        <template v-if="staffAllConditionForm">
          <el-form-item label="祖籍" prop="originalHome" :class="{'form-item': staffAllConditionForm}">
            <el-input v-model="condition.originalHome" :maxlength="30" show-word-limit style="width: 240px"/>
          </el-form-item>
          <el-form-item label="身份证号" prop="cardId" :class="{'form-item': staffAllConditionForm}">
            <el-input v-model="cardIdStringNull" :maxlength="18" show-word-limit style="width: 240px"/>
          </el-form-item>
          <el-form-item label="在职状态" prop="status" :class="{'form-item': staffAllConditionForm}">
            <el-radio-group v-model="condition.status">
              <el-radio-button v-for="(item, index) in status" :key="index" :label="item.value">{{ item.desc }}
              </el-radio-button>
            </el-radio-group>
          </el-form-item >
          <el-form-item label="出生日期" prop="birthDateRange" :class="{'form-item': staffAllConditionForm}">
            <el-date-picker
              v-model="birthDateRange"
              type="daterange"
              style="width: 240px"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="创建时间" prop="createTimeRange" :class="{'form-item': staffAllConditionForm}">
            <el-date-picker
              v-model="createTimeRange"
              type="datetimerange"
              style="width: 240px"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="更新时间" prop="updateTimeRange" :class="{'form-item': staffAllConditionForm}">
            <el-date-picker
              v-model="updateTimeRange"
              type="datetimerange"
              style="width: 240px"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="创建者身份证号" prop="creatorCardId" :class="{'form-item': staffAllConditionForm}">
            <el-input v-model="creatorCardIdStringNull" :maxlength="18" show-word-limit style="width: 240px"/>
          </el-form-item>
          <el-form-item label="更新者身份证号" prop="updaterCardId" :class="{'form-item': staffAllConditionForm}">
            <el-input v-model="updaterCardIdStringNull" :maxlength="18" show-word-limit style="width: 240px"/>
          </el-form-item>
          <el-form-item :id="staffAllConditionForm ? 'form-item-option-inline' : 'form-item-option'">
            <el-button type="primary" @click="conditionOnSubmit">查询</el-button>
            <el-button @click="$refs.conditionForm.resetFields()">清空表单</el-button>
            <el-button @click="staffAllConditionForm = false">收起</el-button>
          </el-form-item>
        </template>
      </el-form>
      <!-- 显示部分条件时 需要显示的内容-->
      <template v-if="!staffAllConditionForm">
        <el-button type="primary" @click="conditionOnSubmit">查询</el-button>
        <el-button @click="staffAllConditionForm = true" >展开查询</el-button>
      </template>
    </div>
    <!-- 数据展示表格-->
    <el-table
      :data="tableData"
      style="width: 100%" height="480px" stripe border>
      <el-table-column type="index" fixed="left" :index="(index) => 1 + index + (condition.currentPage - 1) * condition.pageSize"/>
      <el-table-column prop="name" label="姓名" fixed="left"/>
      <el-table-column prop="dutyList" label="职务" :formatter="cellDutyOfficeArrayFormat" />
      <el-table-column prop="officeList" label="科室" :formatter="cellDutyOfficeArrayFormat" />
      <el-table-column prop="sex.desc" label="性别" width="50px"/>
      <el-table-column prop="phone" label="联系电话" width="120px"/>
      <el-table-column prop="cardId" label="身份证号" width="200px"/>
      <el-table-column prop="originalHome" label="祖籍"/>
      <el-table-column prop="birthDate" label="出生日期" :formatter="cellDateFormate" width="100px"/>
      <el-table-column prop="status.desc" label="在职状态"/>
      <el-table-column prop="createTime" label="创建时间" :formatter="cellDateTimeFormate" width="100px"/>
      <el-table-column prop="creator" label="创建者">
        <template #default="{row}">
          <template v-if="row.creator">
            <el-popover title="创建者信息" placement="left" width="200px" trigger="hover">
              <template #reference>
                <el-button type="text" size="medium">{{ row.creator.name }}</el-button>
              </template>
              <span>联系电话: {{ row.creator.phone }} <br/>
            身份证号: {{ row.creator.cardId }}</span>
            </el-popover>
          </template>
          <template v-else>
            创建人不详
          </template>
        </template>
      </el-table-column>
      <el-table-column prop="updateTime" label="更新时间" :formatter="cellDateTimeFormate" width="100px"/>
      <el-table-column prop="updater" label="更新者" width="100px">
        <template #default="{row}">
          <template v-if="row.updater">
            <el-popover title="更新者信息" placement="left" width="200px" trigger="hover">
              <template #reference>
                <el-button type="text" size="medium">{{ row.updater.name }}</el-button>
              </template>
              <span>联系电话: {{ row.updater.phone }} <br/>
            身份证号: {{ row.updater.cardId }}</span>
            </el-popover>
          </template>
          <template v-else>
            尚未被更新
          </template>
        </template>
      </el-table-column>
      <el-table-column label="操作" fixed="right" width="140px" style="display: flex;justify-content: space-between">
        <template #default="{row}" >
          <el-button type="primary" size="mini" @click="showUpdateStaff(row)">编辑</el-button>
          <el-popconfirm
            title="确定删除吗？"
            @confirm="deleteStaffInfoById(row.id)"
          >
            <el-button slot="reference" type="danger" size="mini">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
      @size-change="sizeChange"
      @current-change="currentChange"
      :current-page="condition.currentPage"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="condition.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="page.total">
    </el-pagination>
    <!-- 多条件查询弹窗 -->
    <el-dialog title="多条件查询操作" :visible.sync="dialogStaffConditionVisible" @close="conditionDialogClose" >
      <el-form ref="conditionForm" :model="condition" :rules="conditionRules" style="width: 85%;" label-width="120px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model.trim="nameStringNull" :maxlength="255" show-word-limit/>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="condition.sex">
            <el-radio-button v-for="(item, index) in sex" :key="index" :label="item.value">{{
                item.desc
              }}
            </el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model.trim="phoneStringNull" :maxlength="13" show-word-limit/>
        </el-form-item>
        <el-form-item label="身份证号" prop="cardId">
          <el-input v-model="cardIdStringNull" :maxlength="18" show-word-limit/>
        </el-form-item>
        <el-form-item label="祖籍" prop="originalHome">
          <el-input v-model="condition.originalHome" :maxlength="255" show-word-limit/>
        </el-form-item>
        <el-form-item label="在职状态" prop="status">
          <el-radio-group v-model="condition.status">
            <el-radio-button v-for="(item, index) in status" :key="index" :label="item.value">{{ item.desc }}
            </el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="出生日期" prop="birthDateRange">
          <el-date-picker
            v-model="birthDateRange"
            type="daterange"
            style="width: 100%"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="创建时间" prop="createTimeRange">
          <el-date-picker
            v-model="createTimeRange"
            type="datetimerange"
            style="width: 100%"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新时间" prop="updateTimeRange">
          <el-date-picker
            v-model="updateTimeRange"
            type="datetimerange"
            style="width: 100%"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="创建者身份证号" prop="creatorCardId">
          <el-input v-model="creatorCardIdStringNull" :maxlength="18" show-word-limit/>
        </el-form-item>
        <el-form-item label="更新者身份证号" prop="updaterCardId">
          <el-input v-model="updaterCardIdStringNull" :maxlength="18" show-word-limit/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="conditionOnSubmit">查询</el-button>
          <el-button @click="$refs.conditionForm.resetFields()">清空表单</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <!-- 人员信息更新弹窗-->
    <el-dialog title="更新人员信息" :visible.sync="dialogStaffUpdateVisible">
      <staff-form update :info="updateForm" @submit="updateStaff" inline style="width: 100%" v-if="dialogStaffUpdateVisible"></staff-form>
    </el-dialog>
  </div>
</template>

<script>
import moment from 'moment'
import clone from 'clone'
import StaffForm from '@/components/staff/StaffForm'
import { CARD_ID_REGEXP, PHONE_REGEXP } from '@/utils/regexp-const'
import { isBlank } from '@/utils/string-utils'
import { isEmpty } from '@/utils/array-utils'

// 格式化 dateRange
const commonDateRange = (prop) => ({
  get () {
    return this.commonDateRangeGet(prop)
  },
  set (value) {
    this.commonDateRangeSet(value, prop)
  }
})

// 格式化 String, 针对null值进行处理
const commonStringNull = (prop) => ({
  get () {
    return this.commonStringNullGet(prop)
  },
  set (value) {
    this.commonStringNullSet(value, prop)
  }
})

export default {
  name: 'StaffFind',
  components: { StaffForm },
  data () {
    return {
      tableData: [],
      condition: {
        id: null,
        name: null,
        phone: null,
        cardId: null,
        status: null,
        sex: null,
        originalHome: '',
        birthDateStart: '',
        birthDateEnd: '',
        createTimeStart: '',
        createTimeEnd: '',
        updateTimeStart: '',
        updateTimeEnd: '',
        createId: null,
        creatorCardId: null,
        updateId: null,
        updaterCardId: null,
        currentPage: 1,
        pageSize: 10
      },
      conditionRules: {
        name: [{
          max: 255,
          message: '人员姓名长度不能超过255个字符',
          trigger: 'blur'
        }],
        phone: [{
          validator: this.validatePhone,
          trigger: 'blur'
        }],
        cardId: [{
          validator: this.validateCardId,
          trigger: 'blur'
        }],
        originalHome: [{
          max: 255,
          message: '祖籍的长度不能超过255个字符',
          trigger: 'blur'
        }],
        creatorCardId: [{
          validator: this.validateCardId,
          trigger: 'blur'
        }],
        updaterCardId: [{
          validator: this.validateCardId,
          trigger: 'blur'
        }]
      },
      page: {
        total: 0
      },
      // 人员信息更新弹窗控制
      dialogStaffUpdateVisible: false,
      // 多条件查询弹窗控制
      dialogStaffConditionVisible: false,
      // 多条件的全部信息显示控制
      staffAllConditionForm: false,
      updateForm: {},
      sex: [],
      status: [],
      // 条件缓存, 当点击查询按钮时, 与真实条件同步
      conditionTemp: null
    }
  },
  created () {
    // 创建条件缓存, 让翻页更符合逻辑
    this.conditionTemp = clone(this.condition)
    // 获取数据
    this.getStaffInfoList()
    // 获取性别与在职状态枚举信息
    this.getSexEnums()
    this.getStatusEnums()
  },
  methods: {
    /**
     * 获取信息
     */
    getStaffInfoList () {
      this.axios.post('/staff/query', this.conditionTemp).then(res => {
        if (res.status === 200) {
          this.tableData = res.data.records
          this.page.total = res.data.total
        } else {
          this.$message.error('数据请求失败')
        }
      }).catch(err => {
        console.log(err)
        this.$message.error('数据请求失败')
      })
    },
    /**
     * 格式化表格内的日期
     * @param row 行
     * @param column 列
     * @param cellValue 单元格的值
     * @param index 索引
     * @returns {string} 格式化后的字符串
     */
    cellDateFormate (row, column, cellValue, index) {
      if (cellValue) {
        return moment(cellValue).format('yyyy-MM-DD')
      } else {
        return '时间不详'
      }
    },
    /**
     * 格式化表格内的日期时间
     * @param row 行
     * @param column 列
     * @param cellValue 单元格的值
     * @param index 索引
     * @returns {string} 格式化后的字符串
     */
    cellDateTimeFormate (row, column, cellValue, index) {
      if (cellValue) {
        return moment(cellValue).format('yyyy-MM-DD HH:mm:ss')
      } else {
        return '时间不详'
      }
    },
    /**
     * 格式化表格内职务和科室数据信息
     * @param row
     * @param column
     * @param cellValue 单元格内的数据
     * @param index
     */
    cellDutyOfficeArrayFormat (row, column, cellValue, index) {
      if (isEmpty(cellValue)) {
        return `${column.label}暂无`
      }
      // 根据数组内容生成字符串
      let temp = ''
      for (let i = 0; i < cellValue.length; i++) {
        temp += cellValue[i].name
        if (i !== cellValue.length - 1) temp += '、'
      }
      return temp
    },
    /**
     * 页面大小变化时, 触发的事件
     * @param value 新的页面大小
     */
    sizeChange (value) {
      // 两个条件的页面大小同步更新
      this.condition.pageSize = value
      this.conditionTemp.pageSize = value
      this.getStaffInfoList()
    },
    /**
     * 当前页码变化时, 触发的事件
     * @param value 新的页码
     */
    currentChange (value) {
      // 两个条件的当前页数同步更新
      this.condition.currentPage = value
      this.conditionTemp.currentPage = value
      this.getStaffInfoList()
    },
    /**
     * 删除人员信息
     * @param id 人员id
     */
    deleteStaffInfoById (id) {
      this.axios.delete(`/staff/delete-one/${id}`).then(res => {
        if (res.status === 200) {
          this.$message.success('删除成功')
          this.getStaffInfoList()
        } else {
          this.$message.error(`删除失败, 原因是${res.msg}`)
        }
      }).catch(err => {
        this.$message.error('服务器异常')
        console.log(err)
      })
    },
    /**
     * 弹出更新窗格
     * @param info 要更新的信息
     */
    showUpdateStaff (info) {
      const temp = clone(info)
      temp.sex = temp.sex.value
      temp.status = temp.status.value
      temp.dutyIdList = temp.dutyList.map(item => item.id)
      temp.officeIdList = temp.officeList.map(item => item.id)
      this.updateForm = temp
      console.log(this.updateForm)
      this.dialogStaffUpdateVisible = true
    },
    /**
     * 执行更新操作
     * @param info 需要更新的信息
     * @param form 更新信息对应的表单实体
     */
    updateStaff ({
      info,
      form
    }) {
      this.axios.post('/staff/update-one', info).then(res => {
        if (res.status === 200) {
          this.$message.success('更新成功')
          this.getStaffInfoList()
          this.dialogStaffUpdateVisible = false
        } else {
          this.$message.error(`更新失败, 原因是${res.desc}`)
        }
      }).catch(err => {
        this.$message.error('服务器异常')
        console.log(err)
      })
    },
    /**
     * 获取性别枚举信息
     */
    getSexEnums () {
      this.axios.get('/enums/sex').then(res => {
        this.sex = res.data
        this.enumsAddAll(this.sex)
      })
    },
    /**
     * 获取在职状态枚举信息
     */
    getStatusEnums () {
      this.axios.get('/enums/status').then(res => {
        this.status = res.data
        this.enumsAddAll(this.status)
      })
    },
    /**
     * 给枚举添加全选选项
     * @param enums 枚举数组
     */
    enumsAddAll (enums) {
      enums.push({
        value: null,
        desc: '全选'
      })
    },
    /**
     * 日期范围选择通用设置
     * @param value 设置值
     * @param prop 属性名前缀
     */
    commonDateRangeSet (value, prop) {
      // 默认值为空
      let start = ''
      let end = ''
      // 从传进来的参数中获取值
      if (value !== null) {
        [start, end] = value
      }
      // 赋值
      this.condition[`${prop}Start`] = start
      this.condition[`${prop}End`] = end
    },
    /**
     * 日期范围选择通用获取
     * @param prop 属性名前缀
     * @returns {*[]} 对应的日期范围数组
     */
    commonDateRangeGet (prop) {
      return [this.condition[`${prop}Start`], this.condition[`${prop}End`]]
    },
    /**
     * 字符串通用设置, 当字符串为空时则将其值设为null
     * @param value 更新的值
     * @param prop 字符串在表单对象中的属性名
     */
    commonStringNullSet (value, prop) {
      this.condition[prop] = isBlank(value) ? null : value
    },
    /**
     * 字符串通用获取, 根据字符串是否为空返回不同的值
     * @param prop 字符串在表单对象中的属性名
     * @returns {string|*} 返回的内容
     */
    commonStringNullGet (prop) {
      // 要返回的数据
      const temp = this.condition[prop]
      // 判断是否为空, 再进行返回
      return isBlank(temp) ? '' : temp
    },
    /**
     * 条件克隆, 并进行查询
     */
    conditionOnSubmit () {
      // 复制条件进行查询
      this.conditionTemp = clone(this.condition)
      this.getStaffInfoList()
      // 关闭弹窗
      this.dialogStaffConditionVisible = false
      // 回缩展开的查询条件
      if (this.staffAllConditionForm) this.staffAllConditionForm = false
    },
    /**
     * 验证身份证号
     */
    validateCardId (rule, value, callback) {
      console.log(value)
      if (value === null || value === '') {
        callback()
      } else if (!CARD_ID_REGEXP.test(value)) {
        callback(new Error('身份证号格式错误'))
      } else {
        callback()
      }
    },
    /**
     * 验证手机号
     */
    validatePhone (rule, value, callback) {
      if (value === null || value === '') {
        callback()
      } else if (!PHONE_REGEXP.test(this.condition.phone)) {
        callback(new Error('手机号格式错误'))
      } else {
        callback()
      }
    },
    /**
     * 多条件查询的弹窗关闭
     */
    conditionDialogClose () {
      // 表单中的条件与实际用于查询的条件进行同步
      this.condition = clone(this.conditionTemp)
    }
  },
  computed: {
    birthDateRange: commonDateRange('birthDate'),
    createTimeRange: commonDateRange('createTime'),
    updateTimeRange: commonDateRange('updateTime'),
    nameStringNull: commonStringNull('name'),
    phoneStringNull: commonStringNull('phone'),
    cardIdStringNull: commonStringNull('cardId'),
    creatorCardIdStringNull: commonStringNull('creatorCardId'),
    updaterCardIdStringNull: commonStringNull('updaterCardId')
  }
}
</script>

<style scoped>
#staff-find {
  height: 100%;
  width: 100%;
}

.form-item {
  width: 380px !important;
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
