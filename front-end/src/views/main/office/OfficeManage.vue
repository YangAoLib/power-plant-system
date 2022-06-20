<template>
<div id="office-manage">
  <!-- 科室添加 -->
  <el-form ref="officeAddForm" :model="officeAddFormInfo" :rules="officeAddFormRules" inline>
    <el-form-item label="科室名称" prop="name">
      <el-input v-model.trim="officeAddFormInfo.name" placeholder="请输入科室名称" maxlength="20"  clearable show-word-limit/>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="addOffice">添加</el-button>
    </el-form-item>
  </el-form>

  <!-- 科室的显示与操作 -->
  <el-table :data="officeTableData" style="width: 100%" height="480px">
    <el-table-column type="index" :index="index => index + 1" fixed="left"/>
    <el-table-column label="科室名称" prop="name"/>
    <el-table-column label="操作" fixed="right" width="140px">
      <template #default="{row}">
        <el-button type="primary" size="mini" @click="showUpdateOfficeDialog(row)">编辑</el-button>
        <el-popconfirm
          title="确定删除吗？"
          @confirm="deleteOffice(row.id)"
        >
          <el-button slot="reference" type="danger" size="mini">删除</el-button>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>

  <!-- 科室信息更新弹窗 -->
  <el-dialog title="更新科室信息" :visible.sync="updateOfficeDialogVisible">
    <el-form ref="officeUpdateForm" :model="officeUpdateFormInfo" :rules="officeAddFormRules" inline>
      <el-form-item label="科室名称" prop="name">
        <el-input v-model.trim="officeUpdateFormInfo.name" placeholder="请输入科室名称" maxlength="20"  clearable show-word-limit/>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="updateOfficeDialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="updateOffice">更 新</el-button>
    </div>
  </el-dialog>
</div>
</template>

<script>
export default {
  name: 'OfficeManage',
  data () {
    return {
      officeAddFormInfo: {
        name: ''
      },
      officeAddFormRules: {
        name: [{
          required: true,
          message: '请输入科室名称',
          trigger: 'blur'
        }, {
          max: 20,
          message: '科室名称长度不能超过20个字符',
          trigger: 'blur'
        }]
      },
      officeUpdateFormInfo: {
        id: 0,
        name: ''
      },
      updateOfficeDialogVisible: false,
      officeTableData: []
    }
  },
  created () {
    this.getAllOffice()
  },
  methods: {
    /**
     * 添加科室信息
     */
    addOffice () {
      this.axios.post('/office/save', this.officeAddFormInfo).then(res => {
        if (res.status === 200) {
          this.$message.success('添加成功')
          this.getAllOffice()
        } else {
          this.$message.error(`添加失败, 原因是${res.data}`)
        }
      })
    },
    /**
     * 获取科室信息
     */
    getAllOffice () {
      this.axios.get('/office/query-all').then(res => {
        if (res.status === 200) {
          this.officeTableData = res.data
        } else {
          this.$message.error(`数据获取异常, ${res.message}`)
        }
      })
    },
    /**
     * 打开更新科室信息弹窗
     * @param row 要更新的科室信息
     */
    showUpdateOfficeDialog (row) {
      const { id, name } = row
      this.officeUpdateFormInfo = { id, name }
      this.updateOfficeDialogVisible = true
    },
    /**
     * 更新科室信息
     */
    updateOffice () {
      this.axios.post('/office/update', this.officeUpdateFormInfo).then(res => {
        if (res.status === 200) {
          this.$message.success('更新成功')
          this.getAllOffice()
        } else {
          this.$message.error(`更新失败, 原因是${res.message}`)
        }
        // 关闭弹窗
        this.updateOfficeDialogVisible = false
      })
    },
    /**
     * 根据id删除科室信息
     * @param id 要删除的科室id
     */
    deleteOffice (id) {
      this.axios.delete(`/office/delete/${id}`).then(res => {
        if (res.status === 200) {
          this.$message.success('删除成功')
          this.getAllOffice()
        } else {
          this.$message.error(`更新失败, 原因是${res.data}`)
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
