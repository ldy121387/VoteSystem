<template>
  <div class="manage">
    <el-dialog
        title="Reminder"
        :visible.sync="dialogVisible"
        width="50%"
        :before-close="handleClose">
      <!-- 用户的表单信息 -->
      <el-form ref="form" :rules="rules" :inline="true" :model="form" label-width="130px">
        <el-form-item label="Name" prop="name">
          <el-input placeholder="enter the name" v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="Type" prop="type">
          <el-input placeholder="enter the type" v-model="form.type"></el-input>
        </el-form-item>
        <el-form-item label="Describe" prop="content">
          <el-input placeholder="enter the describe" v-model="form.content"></el-input>
        </el-form-item>

        <el-form-item label="Start Time" prop="startTime">
          <el-input placeholder="enter the start time" v-model="form.startTime"></el-input>
        </el-form-item>
        <el-form-item label="End Time" prop="endTime">
          <el-input placeholder="enter the end time" v-model="form.endTime"></el-input>
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
          <el-input placeholder="Please enter the name" v-model="userForm.name"></el-input>
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
            prop="name"
            label="Name">
        </el-table-column>
        <el-table-column
            prop="type"
            label="Type">
        </el-table-column>
        <el-table-column
            prop="content"
            label="Describe">
        </el-table-column>
        <el-table-column
            label="Manage">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.row)">Edit</el-button>
            <el-button type="danger" size="mini" @click="handleDelete(scope.row)">Delete</el-button>
            <el-button type="warning" size="mini" @click="handleEdit2(scope.row)">Enter Voting</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pager">
        <el-pagination
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
        name: '',
        content: '',
        type: '',
        startTime: '',
        endTime: ''
      },
      rules: {
        name: [
          { required: true, message: 'please enter the name' }
        ],
        content: [
          { required: true, message: 'please describe the vote' }
        ],
        type: [
          { required: true, message: 'please choose the vote type' }
        ],
        startTime: [
        { required: true, message: 'please choose the Start Time' }
        ],
        endTime: [
          { required: true, message: 'please choose the End Time' }
        ],
      },
      tableData: [],
      modalType: 0, // 0表示新增的弹窗， 1表示编辑
      total: 0, //当前的总条数
      pageData: {
        page: 1,
        limit: 10
      },
      current: 1,
      size: 10,
      userForm: {
        name: ''
      }
    }
  },
  methods: {
    handleEdit2(row) {
      this.modalType = 1
      let isTypeTrue = row.type !== 0 ? true : false;
      if(row.type==0){
        this.goToPage(`/vDetail?id=${row.id}`);
      } else {
        this.goToPage(`/vDetail2?id=${row.id}`);
      }

    },
    goToPage(path) {
      this.$router.push({ path: path });
    },
    // // 获取全部数据
    // getList() {
    //   http.get('/device/getDevice/').then( ({ data }) =>  {
    //     this.tableData = data.data
    //
    //   })
    // },
    //分页
    getList() {
      http.get('/vote/getVoteByPage',{params: {current:this.current,size:this.size,voteName:this.userForm.name}}).then(({ data }) =>{
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
            http.post('/vote/add',this.form).then(() => {
              this.getList()
            })
          }
          else {
            http.post('/vote/update',this.form).then(() => {
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
      this.$confirm('This action permanently deletes the vote, do you want to continue??', 'Reminder', {
        confirmButtonText: 'Continue',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        console.log({id : row.id})
        http.post(`/vote/delete/${row.id}`,).then(() => {
          this.$message({
            type: 'success',
            message: 'Successful Delete!',
          });
          // 重新获取列表的接口
          this.getList()
        })

      }).catch(() => {
        this.$message({
          type: 'info',
          message: 'Cancel'
        });
      });
    },
    // 列表的查询
    onSubmit() {
      // http.get('/device/selectByName', {params: {deviceName : this.userForm.name}})
      //     .then((res) => {
      //   this.tableData = res.data.data
      // })
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