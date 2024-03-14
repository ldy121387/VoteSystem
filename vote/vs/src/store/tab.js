import Cookie from "js-cookie";
export default {
    state:{
        isCollapse:false, //控制菜单展开收起
        tabList: [
            {
                path: '/home',
                name: 'home',
                label: '首页',
                icon: 's-home',
                url: 'Home.vue'
            }
        ],
        menu: []
    },
    mutations: {
        //控制菜单展开收起的方法
        collapseMenu(state) {
            state.isCollapse = !state.isCollapse
        },
        //设置menu的数据
        setMenu(state, val){
            state.menu = val
            Cookie.set('menu',JSON.stringify(val))
        }
    }
}