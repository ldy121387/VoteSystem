import Vue from 'vue'
import VueRouter from "vue-router";
import Home from "@/views/Home.vue";
import User from "@/views/User.vue";
import Main from "@/views/Main.vue";
import Login from "@/views/Login.vue";
import Register from "@/views/Register.vue";
import Test from "@/views/test.vue";
import Vote from "@/views/Vote.vue";
import vDetail from "@/views/VoteDetail.vue";
import vDetail2 from "@/views/VoteDetail2.vue";
Vue.use(VueRouter)

const routes = [
    //主路由
    {
        path: '/',
        component: Main,
        redirect: '/home',
        children:[
            { path: 'home', component: Home },
            { path: 'user', component: User },
            { path: 'test', component:Test  },
            { path: 'vote', component:Vote  },
            { path: 'vDetail', component:vDetail  },
            { path: 'vDetail2', component:vDetail2  },
        ]
    },
    {
        path: '/login',
        name: 'login',
        component: Login
    },
    {
        path: '/register',
        name: 'register',
        component: Register
    }

]

const router = new VueRouter({
    routes // (缩写) 相当于 routes: routes
})

export default router


