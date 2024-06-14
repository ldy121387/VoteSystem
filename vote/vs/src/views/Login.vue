<template>
  <el-form ref="form" label-width="70px" :inline="true" class="login-container" :model="form" :rules="rules">
    <h3 class="login_title">LOGIN</h3>
    <el-form-item label="Account" prop="username">
      <el-input v-model="form.account" placeholder="Please enter your account number"></el-input>
    </el-form-item>
    <el-form-item label="Password" prop="password">
      <el-input type="password" v-model="form.password" placeholder="Please enter your password"></el-input>
    </el-form-item>
    <el-form>
      <el-form-item>
        <el-button @click="submit" style="margin-top: 10px;" type="primary">Login</el-button>
      </el-form-item>
      <el-form-item>
        <el-button @click="register" style="margin-top: 10px;" type="primary">Register</el-button>
      </el-form-item>
    </el-form>
  </el-form>
</template>

<script>
import Cookie from 'js-cookie'
import http from "@/utils/request";

export default {
  data() {
    return {
      form: {
        account: '',
        password: ''
      },
      rules: {
        account: [
          { required: true, trigger: 'blur', message: '请输入用户名' }
        ],
        password: [
          { required: true, trigger: 'blur', message: '请输入密码' }
        ]
      },
      normalMenu: [
        {
          path: '/home',
          name: 'home',
          label: '首页',
          icon: 's-home',
          url: 'Home.vue'
        },
        
      ],
      adminMenu: [
        {
          path: '/home',
          name: 'home',
          label: '首页',
          icon: 's-home',
          url: 'Home.vue'
        },
        {
          path: '/user',
          name: 'user',
          label: '用户管理',
          icon: 'user',
          url: 'User.vue'
        },
        {
          path:'/vote',
          name:'vote',
          label:'投票管理',
          icon:'message',
          url:'Vote.vue'
        },
      ]
    }
  },
  methods: {
    // 登录
    submit() {
      // 校验通过
      this.$refs.form.validate((valid) => {
        if (valid) {
          http.post('/user/login',this.form).then(({ data }) => {
            if (data.code === "200") {
              // token信息存入cookie用于不同页面间的通信
              Cookie.set('token', data.data.token)

              // 获取菜单的数据，存入store中
              if (data.data.role==="admin"){
                this.$store.commit('setMenu', this.adminMenu)
                this.$store.commit('addMenu', this.$router)
              }
              if (data.data.role==="normal"){
                console.log('menu',this.normalMenu)
                this.$store.commit('setMenu', this.normalMenu)
                this.$store.commit('addMenu', this.$router)
              }

              // 跳转到首页
              this.$router.push('/home')
            } else {
              this.$message.error(data.data.msg);
            }
          })
        }
      })
    },
    register() {
      this.$router.push('register')
    }
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
  .el-form{
    display: flex;
    padding-left: 50px;
  }
}
</style>