<template>
  <div class="header-container">
    <img class="logo" src="../assets/blockchain.png" alt="">
    <p class="text">BLOCK CHAIN VOTING</p>
    <div class="l-content">
      
      <el-row class="row"></el-row>
        <el-button class = "button1" @click="goToPage('/home')" type="text" size ="medium" >Home</el-button>
        <el-button class = "button2" @click="goToPage('/vote')" type="text" size ="medium">Vote Manage</el-button>
        <el-button class = "button3" @click="goToPage('/user')" type="text" size ="medium">User Manage</el-button>
        <!-- <el-button @click="goToPage('/vote')" type="text" size ="medium">投票结果</el-button> -->
        <el-button class = "button4" @click="goToPage('/home')" type="text" size ="medium">Help</el-button>
        
        
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
    goToPage(path) {
      this.$router.push({ path: path });
    },
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
  height: 80px;
  display: flex;
  
  align-items: center;
  
  
  // &::after {
  //   content: "";
  //   position: absolute;
  //   bottom: 0;
  //   left: 350px;
  //   width: 50%;
  //   height: 1px;
     background-color: hsl(0, 0%, 90%);
  // }
  .logo{
      width: 45px;
      height: 45px;
      
     
}
  .text{padding-left: 10px;}
  .l-content{

    
    

    margin: 0 auto;



    .row{
      padding:  0 200px;
      


      height: 5px;


    }
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