<template>
  <el-form ref="form" label-width="70px" :inline="true" class="login-container" :model="form" :rules="rules">
    <h3 class="login_title">系统注册</h3>
    <el-form-item label="用户名" prop="account">
      <el-input  v-model="form.account" placeholder="请输入账号"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input type="password"  placeholder="请输入密码" v-model="form.password" ></el-input>
    </el-form-item>
    <el-form-item label="昵称" prop="nickName">
      <el-input placeholder="请输入昵称" v-model="form.nickName"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input placeholder="请输入邮箱" v-model="form.email"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button @click="submit" style="margin-left: 105px;margin-top: 10px;" type="primary">注册</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import http from "@/utils/request";
export default {
  data() {
    return {
      form: {
        account: '',
        password: '',
        nickName: '',
        email: '',
        role: 'normal'
      },
      rules: {
        account: [
          { required: true, trigger: 'blur', message: '请输入用户名' }
        ],
        password: [
          { required: true, trigger: 'blur', message: '请输入密码' }
        ]
      },
    }
  },
  methods: {
    //提交用户表单
    submit() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          http.post('/user/add', this.form).then(({ data }) => {
            if (data.code === "200") {
              this.$message.success("注册成功");
              this.$router.push('/login')
            }
            else {
              this.$message.error("注册失败");
            }
          })
        }

        // 清空表单的数据
        this.$refs.form.resetFields()
        // 关闭弹窗
        this.dialogVisible = false
      })
    },

  }
}
</script>

<style lang="less" scoped>
.login-container {
  width: 350px;
  border: 1px solid #eaeaea;
  margin: 180px auto;
  padding: 35px 35px 15px 35px;
  background-color: #fff;
  border-radius: 15px;
  box-shadow: 0 0 25px #cac6c6;
  box-sizing: border-box;
  .login_title {
    text-align: center;
    margin-bottom: 40px;
    color: #505458;
  }
  .el-input {
    width: 198px;
  }
}
</style>