<template>
  <div id="duty-manage">
    <!-- 职务添加 -->
    <div>
      <el-form ref="dutyAddForm" :model="dutyAddFormInfo" :rules="dutyAddFormRules" inline>
        <el-form-item label="职务名称" prop="name">
          <el-input v-model.trim="dutyAddFormInfo.name" placeholder="请输入职务名称" maxlength="20"  clearable show-word-limit/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addDuty">添加</el-button>
        </el-form-item>
      </el-form>
    </div>

    <!-- 职务的显示与操作 -->
    <el-table :data="dutyTableData" style="width: 100%" height="480px">
      <el-table-column type="index" :index="index => index + 1" fixed="left"/>
      <el-table-column label="职务名称" prop="name"/>
      <el-table-column label="操作" fixed="right" width="140px">
        <template #default="{row}">
          <el-button type="primary" size="mini" @click="showUpdateDutyDialog(row)">编辑</el-button>
          <el-popconfirm
            title="确定删除吗？"
            @confirm="deleteDuty(row.id)"
          >
            <el-button slot="reference" type="danger" size="mini">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!-- 更新职务弹窗 -->
    <el-dialog title="更新职务名称" :visible.sync="updateDutyDialogVisible">
      <el-form ref="dutyUpdateForm" :model="dutyUpdateFormInfo" :rules="dutyAddFormRules" inline>
        <el-form-item label="职务名称" prop="name">
          <el-input v-model.trim="dutyUpdateFormInfo.name" maxlength="20" placeholder="请输入职务名称" clearable
                    show-word-limit/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updateDutyDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateDuty">更 新</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

export default {
  name: 'DutyManage',
  data () {
    return {
      dutyAddFormInfo: {
        name: ''
      },
      dutyAddFormRules: {
        name: [{
          required: true,
          message: '请输入职务名称',
          trigger: 'blur'
        }, {
          max: 20,
          message: '职务名称长度不能超过20个字符',
          trigger: 'blur'
        }]
      },
      dutyUpdateFormInfo: {
        id: 0,
        name: ''
      },
      updateDutyDialogVisible: false,
      dutyTableData: []
    }
  },
  created () {
    this.getAllDuty()
  },
  methods: {
    /**
     * 添加职务
     */
    addDuty () {
      this.axios.post('/duty/save', this.dutyAddFormInfo).then(res => {
        if (res.status === 200) {
          this.$message.success('添加成功')
          this.getAllDuty()
        } else {
          this.$message.error(`添加失败, 原因是${res.message}`)
        }
      })
    },
    /**
     * 获取全部职务信息, 并显示到页面
     */
    getAllDuty () {
      this.axios.get('/duty/query-all').then(res => {
        if (res.status === 200) {
          this.dutyTableData = res.data
        } else {
          this.$message.error(`数据获取失败, ${res.message}`)
        }
      })
    },
    /**
     * 显示更新职务名称弹窗
     * @param row 要更新的职务数据
     */
    showUpdateDutyDialog (row) {
      const { id, name } = row
      this.dutyUpdateFormInfo = { id, name }
      this.updateDutyDialogVisible = true
    },
    /**
     * 更新职务信息
     */
    updateDuty () {
      this.axios.post('/duty/update', this.dutyUpdateFormInfo).then(res => {
        if (res.status === 200) {
          this.$message.success('更新成功')
          this.getAllDuty()
        } else {
          this.$message.error(`更新失败, 原因是${res.data}`)
        }
        // 关闭弹窗
        this.updateDutyDialogVisible = false
      })
    },
    /**
     * 根据id删除职务信息
     * @param id 职务id
     */
    deleteDuty (id) {
      this.axios.delete(`/duty/delete/${id}`).then(res => {
        if (res.status === 200) {
          this.$message.success('删除成功')
          this.getAllDuty()
        } else {
          this.$message.error(`删除失败, 原因是${res.data}`)
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
