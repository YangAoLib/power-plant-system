<template>
  <div id="staff-add">
    <staff-form style="width: 100%" @submit="onSubmit" inline></staff-form>
  </div>
</template>

<script>

import StaffForm from '@/components/staff/StaffForm'

export default {
  name: 'StaffAdd',
  components: { StaffForm },
  methods: {
    onSubmit ({ info, form }) {
      this.axios.post('/staff/save-one', info).then(res => {
        console.log(res)
        if (res.status === 200) {
          this.$message.success('人员信息添加成功')
          form.resetFields()
        } else {
          this.$message.error(`人员信息添加添加失败, 原因是${res.message}`)
        }
      }).catch(err => {
        console.log(err)
        this.$message.error('服务器错误')
      })
    }
  }
}
</script>

<style scoped>
#staff-add {
  width: 100%;
}

</style>
