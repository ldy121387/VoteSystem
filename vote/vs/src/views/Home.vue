<template>
  <div class="container">
    <el-card class="left">
      <div class="user-info">
        <img class="p1" src="../assets/picture1.jpg" alt="">
        <div class="userInfo">
          <p class="name">李丁宇</p>
          <p class="access">欢迎使用EMS</p>
        </div>
      </div>
      <div class="login-info">
        <p>账号名:<span>ldy</span></p>
        <p>账户权限:<span>普通员工</span></p>
      </div>
    </el-card>


    <div class="right" >
      <el-card class="r1">
        <h3 class="title">功能导航门户</h3>
        <div class="card">
          <el-row class="row1">
<!--            <el-button @click="goToPage('/user')" type="primary" round>用户管理</el-button>-->
<!--            <el-button @click="goToPage('/device')" type="primary" round>设备库存</el-button>-->
            <el-button @click="goToPage('/mall')" type="primary" round>采购管理</el-button>
            <el-button @click="goToPage('/scrap')" type="primary" round>报废管理</el-button>
          </el-row>
          <el-row class="row2">
            <el-button @click="goToPage('/borrow')" type="primary" round>借出管理</el-button>
            <el-button @click="goToPage('/email')" type="primary" round>待审提醒</el-button>
<!--            <el-button @click="goToPage('/view')" type="primary" round>数据视图</el-button>-->
            <el-button @click="handleClick" type="primary" round>退出</el-button>
          </el-row>
        </div>
      </el-card>
      <el-card class="r2">
        <h3>管理员列表</h3>
        <el-table
            stripe
            height="450px"
            :data="userData"
            style="width: 100%">
          <el-table-column
              prop="nickName"
              label="昵称">
          </el-table-column>
          <el-table-column
              prop="email"
              label="联系邮箱">
          </el-table-column>

        </el-table>
      </el-card>
    </div>

  </div>
</template>

<script>

import Cookie from "js-cookie";
import http from "@/utils/request";

export default {
  data() {
    return {
      userData: [],
    }
  },
  methods: {
    goToPage(path) {
      this.$router.push({ path: path });
    },
    handleClick() {
        Cookie.remove('token')
        Cookie.remove('menu')
        this.$router.push('/login')
    },
    getList() {
      http.get('/user/getAdmin').then(({ data }) => {
        this.userData = data.data
        console.log("User",data.data)
      })

    }

  },
  mounted() {
    this.getList()
  }
}
</script>

<style lang="less" scoped>

.container {
  height: 100%;
  display: flex;
  flex-direction: row;
  .left {
    margin-top: 100px;
    margin-bottom: 100px;
    width: 40%;
    .user-info {
      padding-bottom: 20px;
      margin-bottom: 20px;
      margin-top: 100px;
      border-bottom: 1px solid #ccc;
      display: flex;
      align-items: center;
      .p1 {
        margin-right: 40px;
        width: 150px;
        height: 150px;
        border-radius: 50%;
      }
      .userInfo {
        .name {
          font-size: 32px;
          margin-bottom: 20px;
        }
        .access {
          color: #33bdbd;
          font-size: 30px;
        }
      }
    }
    .login-info {
      margin-top: 100px;
      p {

        margin-top: 10px;
        line-height: 28px;
        font-size: 18px;
        color: #999;
        span {
          margin-left: 40px;
          color: #666666;
        }
      }

    }
  }
  .right {
    width: 60%;
    margin-left: 20px;
    .r1 {
      margin-top: 75px;
      height: 40%;
      .card {
        display: flex;
        flex-direction: column;
        align-items: center;
        .row1{
          margin-top: 70px;
        }
        .row2 {
          margin-top: 30px;
        }
      }
    }
    .r2 {
      overflow-y: auto;
      margin-top: 20px;
      height: 40%;
    }
  }
}

</style>