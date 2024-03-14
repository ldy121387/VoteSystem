<template>
  <div class="manage">
    <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="50%"
        :before-close="handleClose">
      <!-- 用户的表单信息 -->
      <el-form ref="form" :rules="rules" :inline="true" :model="form" label-width="80px">
        <el-form-item label="投票名称" prop="name">
          <el-input placeholder="请输入设备名称" v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="content">
          <el-input placeholder="请输入投票描述" v-model="form.content"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
                <el-button @click="cancel">取 消</el-button>
                <el-button type="primary" @click="submit">确 定</el-button>
            </span>
    </el-dialog>

    <div class="manage-header">
      <el-button @click="handleAdd" type="primary">
        + 新增
      </el-button>
      <!-- form搜索区域 -->
      <el-form :inline="true" :model="userForm">
        <el-form-item>
          <el-input placeholder="请输入设备名称" v-model="userForm.name"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
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
            label="候选名称">
        </el-table-column>
        <el-table-column
            prop="content"
            label="描述">
        </el-table-column>
        <el-table-column
            prop="number"
            label="票数">
        </el-table-column>
        <el-table-column
            label="管理">
          <template slot-scope="scope">
            <el-button type="danger" size="mini" @click="handleVote(scope.row)" :disabled="votedButtons.hasOwnProperty(scope.row.id)"  >投票</el-button>
            <!--            <el-button type="danger" size="mini" @click="handleVote(scope.row)" :disabled="hasVoted"  >投票</el-button>-->
            <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
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
      votedButtons: {},
      isMultipleSelection:'', // 是否是多选模式
      hasVoted: false, // 是否已经投票
      dialogVisible: false,
      form: {
        name: '',
        content: '',
        type: '',
        voteId:'',
        number:'',

      },
      rules: {
        name: [
          { required: true, message: '请输入候选名称' }
        ],
        content: [
          { required: true, message: '请输入描述' }
        ],
        // type: [
        //   { required: true, message: '请选择类别' }
        // ],
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
        name: '',
        voteId:''
      }
    }
  },
  methods: {

    //分页
    getList() {
      const id = this.$route.query.id; // 获取传递过来的id
      this.userForm.voteId = id;
      this.form.voteId = id;

      http.get('/vDetail/getVoteByPage',{params: {current:this.current,size:this.size,voteName:this.userForm.name,voteId:this.userForm.voteId}}).then(({ data }) =>{
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
            http.post('/vDetail/add',this.form).then(() => {
              this.getList()
            })
          }
          else {
            http.post('/vDetail/update',this.form).then(() => {
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
    handleVote(row) {

      const voteId = row.id;
      http.post('/vDetail/voteById',null,{params: {voteId:voteId}}).then(() => {

        this.$set(this.votedButtons, row.id, true);

        // 重新获取列表的接口
        this.getList()
      })

    },
    handleDelete(row) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        console.log({id : row.id})
        http.post(`/vDetail/delete/${row.id}`,).then(() => {
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