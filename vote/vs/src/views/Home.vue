<template>
  <div class="container">
    
    <div class="page1">
      <div class = "C1">
        <div class = "user">
          <img class="p1" src="../assets/picture1.jpg" alt="">
        
        <div class="userInfo">
          <p class="name">Mike</p>
          <p class="access">Welcome To Voting System !</p>
        </div>
      

        </div>
      


        <p class="text">THE VOTE SYSTEM BASED ON BLOCK CHAIN </p>
        <div class = "button">
            <el-button @click="goToPage('/vote')" type="primary" round>Start New Vote</el-button>
          </div>
        
        
        <div class="login-info">
          <p>ID:<span>ldy</span></p>
          <p>Account Access:<span>NORMAL</span></p>
        </div>

      </div>


    
    </div>


    <div class="page2" >
      <img class="p3" src="../assets/picture3.png" alt="">
      
      <el-card class="r1">
        <h3 class="title" >Ongoing Voting</h3>
        <el-row class ="info":gutter="20">
          <el-col :span="6">
            <div>
              <el-statistic
                :value="vote_name"
                :title="title1"
              >
                
              </el-statistic>
            </div>
          </el-col>
          
          <el-col :span="6">
            <div>
              <el-statistic
                group-separator=","
                decimal-separator="."
                :value="value1"
                :title="title2"
              >
                <template slot="prefix">
                  <i class="el-icon-s-flag" style="color: red"></i>
                </template>
                <template slot="suffix">
                  <i class="el-icon-s-flag" style="color: blue"></i>
                </template>
              </el-statistic>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-statistic :value="like ? 521 : 520" title="Number of new votes">
                <template slot="suffix">
                  <span @click="like = !like" class="like">
                    <i
                      class="el-icon-star-on"
                      style="color:red"
                      v-show="!!like"
                    ></i>
                    <i class="el-icon-star-off" v-show="!like"></i>
                  </span>
                </template>
              </el-statistic>
            </div>
          </el-col>
          <el-col :span="6">
            <div>
              <el-statistic
              format="DD：HH：mm"
              :value="deadline"
              time-indices
              title="🚩Vote Deadline:"
            >
            </el-statistic>
            </div>
          </el-col>
        </el-row>

      </el-card>
    </div>

    <div class="page3" >
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
      <img class="p4" src="../assets/picture2.png" alt="">
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
      like: true,
      value1: 400,
      vote_name: 1,
      title1: "Vote Name",
      title2: "Number of participants",
      deadline: new Date("2024-06-06")
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

      // })
      // http.get('/user/getUser/',{params: {current:this.current,size:this.size,userName:this.userForm.name}}).then(({ data }) =>{
      //   console.log('test',data.data)
      //   this.userData = data.data.records
      //   this.total = data.data.total || 0
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
  width: 1200px;
  margin-left: 10%;
  margin-right: 10%;
  background-color: #BBE1FA;
  margin: 0  auto;

  .page1 {
    background-image:url('../assets/picture5.jpg');
    background-repeat:no-repeat;
    background-position: right;

    // background: linear-gradient(to bottom, #3282B8, #BBE1FA);
    height: 700px;
    display: flex;
    flex-direction: column;;
 
    .C1{
      margin-top: 100px;
      width: 200%;
      margin-left: 30px;

      .user{
        display: flex;
        flex-direction: row;
        .p1 {
          margin-right: 40px;
          width: 100px;
          height: 100px;
          border-radius: 50%;

        }

        .userInfo {

          
          .name {
            font-family: Arial, Helvetica, sans-serif;
            font-size: 25px;
            margin-bottom: 20px;
            color:white; /* Set a dark text color */
            text-shadow:2px 2px 8px #BBE1FA;
          }

          .access {
            font-family: Arial, Helvetica, sans-serif;
            font-size: 25px;
            margin-bottom: 20px;
            color:black; /* Set a dark text color */
            text-shadow:2px 2px 8px #BBE1FA;
          }
        }
      }
      .text{
        margin-top: 80px;
        padding-left: 300px;
        font-family: Arial, Helvetica, sans-serif;
        font-style: oblique;
          font-size: 25px;
          margin-bottom: 20px;
          color:hsl(0, 0%, 90%);/* Set a dark text color */
          text-shadow:2px 2px 8px #EEEEEE;

      }
      .button{
        margin-top: 80px;
        margin-left: 200px;
        padding-left: 300px;

      }
  
      .login-info {
        margin-top:200px;

        p {
          margin-top: 10px;
          line-height: 28px;
          font-size: 18px;
          color: white;
          span {
            margin-left: 40px;
            color: white;
          }
        }
      }
    }
    .C2{
      margin-top: 10px;
      margin-right: 40px;
      width: 200%;

      .p2{
        width: 500px;
        height: 500px;

      }
      
      
    }
  }
    
  

  .page2 {
    height: 700px;
    display: flex;
    .p3{
      width: 300px;
      height: 300px;
      margin-left: 40px;
      margin-top: 120px;
    }

    .r1 {
      margin-top: 80px;
      height: 80%;
      width: 800px;
      margin-right: 20px;

    }
    .info{
      margin-top:20px;
    }
  }
  .page3{
    height: 800px;
    display: flex;
    .r2 {
      margin-left: 40px;
      height: 100%;
      width:600px;
     }
    .p4{
      width: 400px;
      height: 400px;
      margin: 0 auto;

    }

  } 
  
}

</style>
<!-- </style>
<!-- <style lang="less" scoped>

.container {
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: row;
  .left {
    margin-top: 100px;
    margin-bottom: 100px;
    width: 40%;
    .user-info {
      padding-bottom: 20px;
      margin-bottom: 20px;
      margin-top: 20px;
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
          color: #1189d8;
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
      height: 60%;

      .card {
        display: flex;
        flex-direction: column;
        align-items: center;
  
        // .row1{
        //   margin-top: 70px;
        // }
        // .row2 {
        //   margin-top: 30px;
        // }
      }
    }
    .r2 {
      overflow-y: auto;
      margin-top: 20px;
      height: 40%;
    }
  }
}

</style> --> -->