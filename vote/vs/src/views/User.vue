<template>
  <div class="manage">
    <el-dialog
        title="Reminder"
        :visible.sync="dialogVisible"
        width="50%"
        :before-close="handleClose">
      <!-- 用户的表单信息 -->
      <el-form ref="form" :rules="rules" :inline="true" :model="form" label-width="100px">
        <el-form-item label="Account" prop="account">
          <el-input placeholder="Please enter the account" v-model="form.account"></el-input>
        </el-form-item>
        <el-form-item label="password" prop="password">
          <el-input placeholder="Please enter the password" v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item label="email" prop="email">
          <el-input placeholder="Please enter the email" v-model="form.email"></el-input>
        </el-form-item>
        <el-form-item label="nickName" prop="nickName">
          <el-input placeholder="Please enter the nickName" v-model="form.nickName"></el-input>
        </el-form-item>
        <el-form-item label="Role" prop="role">
          <el-input placeholder="Please choose the role" v-model="form.role"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
                <el-button @click="cancel">Cancel</el-button>
                <el-button type="primary" @click="submit">Confirm</el-button>
            </span>
    </el-dialog>
    <div class="manage-header">
      <el-button @click="handleAdd" type="primary">
        + Add
      </el-button>
      <!-- form搜索区域 -->
      <el-form :inline="true" :model="userForm">
        <el-form-item>
          <el-input placeholder="Please enter the name" v-model="userForm.nickName"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">Search</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="common-table">
      <el-table
          stripe
          height="90%"
          :data="tableData"
          style="width: 100%">
        <el-table-column
            prop="nickName"
            label="nickName">
        </el-table-column>
        <el-table-column
            prop="email"
            label="Email">
        </el-table-column>
        <el-table-column
            prop="role"
            label="Role">
        </el-table-column>
        <el-table-column
            label="Manage">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.row)">Edit</el-button>
            <el-button type="danger" size="mini" @click="handleDelete(scope.row)">Delete</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pager">
        <el-pagination
            :pager-count="5"
            layout="prev, pager, next"
            :total="total"
            @current-change="handlePage">
        </el-pagination>
      </div>
    </div>
  </div>
</template>
<script>
import http from "@/utils/request";
export default {
  data() {
    return {
      dialogVisible: false,
      form: {
        account: '',
        password: '',
        email: '',
        nickName: '',
        role: ''
      },
      rules: {
        account: [
          { required: true, message: 'Please enter the Account' }
        ],
        password: [
          { required: true, message: 'Please enter the Password' }
        ],
        email: [
          { required: true, message: 'Please enter the email' }
        ],
        nickName: [
          { required: true, message: 'Please enter the nickName' }
        ],
        role: [
          { required: true, message: 'Please enter the role' }
        ]
      },
      tableData: [],
      modalType: 0, // 0表示新增的弹窗， 1表示编辑
      total: 0, //当前的总条数
      current: 1,
      size: 10,
      userForm: {
        name: ''
      }
    }
  },
  methods: {
    //分页
    getList() {
      http.get('/user/getUser/',{params: {current:this.current,size:this.size,userName:this.userForm.name}}).then(({ data }) =>{
        console.log('test',data.data)
        this.tableData = data.data.records
        this.total = data.data.total || 0
      })
    },
    // 选择页码的回调函数
    handlePage(val) {
      this.current = val
      this.getList()
    },
    //新增弹窗
    handleAdd() {
      this.modalType = 0
      this.dialogVisible = true
    },
    //关闭弹窗
    handleClose() {
      this.$refs.form.resetFields()
      this.dialogVisible = false
    },
    //取消弹窗
    cancel() {
      this.handleClose()
    },
    //提交用户表单
    submit() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.modalType === 0) {
            http.post('/user/add',this.form).then(() => {
              this.getList()
            })
          }
          else {
            http.post('/user/update',this.form).then(() => {
              // 重新获取列表的接口
              this.getList()
            })
          }
        }

        // 清空表单的数据
        this.$refs.form.resetFields()
        // 关闭弹窗
        this.dialogVisible = false
      })
    },
    handleEdit(row) {
      this.modalType = 1
      this.dialogVisible = true
      // 注意需要对当前行数据进行深拷贝，否则会出错
      this.form = JSON.parse(JSON.stringify(row))
    },
    handleDelete(row) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        console.log({id : row.id})
        http.post(`/user/delete/${row.id}`,).then(() => {
          this.$message({
            type: 'success',
            message: '删除成功!',
          });
          // 重新获取列表的接口
          this.getList()
        })

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    // 列表的查询
    onSubmit() {
      this.getList()
    }
  },
  mounted () {
    this.getList()
  }
}
</script>
<style lang="less" scoped>
.manage {
  height: 90%;
  .manage-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .common-table {
    position: relative;
    height: calc(100% - 62px);
    .pager {
      position: absolute;
      bottom: 0;
      right: 20px;
    }
  }
}
</style>