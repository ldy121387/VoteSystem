<template>
  <div class="header-container">
    <div class="l-content">
      <el-button @click="handleMenu" icon="el-icon-menu" size="mini"></el-button>
      <span class="text">首页</span>
    </div>
    <div class="r-content">
      <el-dropdown @command="handleClick">
        <span class="el-dropdown-link">
          <img class="user" src="../assets/picture1.jpg" alt="">
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>个人中心</el-dropdown-item>
          <el-dropdown-item command="cancel">退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import Cookie from "js-cookie";
export default {
  data(){
    return {}
  },
  methods: {
    handleMenu() {
      this.$store.commit('collapseMenu')
    },
    handleClick(command) {
      if (command === 'cancel') {
        console.log('登出')
        // 清除cookie中的token
        Cookie.remove('token')
        // 清除cookie中的menu
        Cookie.remove('menu')
        // 跳转到登录页
        this.$router.push('/login')
      }
    }
  }
}
</script>

<style lang="less" scoped>
.header-container {
  padding: 0 20px;
  background-color: #333;
  height: 60px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  .text{
    color: #fff;
    font-size: 14px;
    margin-left: 10px;
  }
  .r-content{
    .user{
      width: 40px;
      height: 40px;
      border-radius: 50%;
    }
  }
}
</style>