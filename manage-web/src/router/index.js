import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
//使用懒加载的方式来引入,只有路由被访问的时候才加载
import Main from '@/components/Main'
import User from '@/components/user/User'
import Log from '../components/log/Log'
import Index from '../components/Index'
import Managers from '../components/system/Managers'
import Permissions from '../components/system/Permissions'
import Roles from '../components/system/Roles'

const loginpage = resolve => require(['@/components/Login'], resolve)

Vue.use(Router)

var routes = [
    {
        path: '/',
        name: 'login',
        component: loginpage
    },
    {
        path: '/login',
        name: 'login',
        component: loginpage
    },
    {
        path: '/',
        name: '系统管理',
        component: Main,
        iconCls: 'fa fa-cog fa-fw',
        children: [
            {
                path: '/home',
                component: Index,
                name: '首页',
                iconCls: 'fa fa-user-o fa-fw'
            },
            {
                path: '/system/managers',
                component: Managers,
                name: '管理员管理',
                iconCls: 'fa fa-user-o fa-fw'
            },
            {
                path: '/system/roles',
                component: Roles,
                name: '角色管理',
                iconCls: 'fa fa-file-text-o fa-fw'
            },
            {
                path: '/system/permissions',
                component: Permissions,
                name: '权限管理',
                iconCls: 'fa fa-file-text-o fa-fw'
            },

        ]
    }
]

let router = new Router({
    mode: 'history',
    routes: routes
})

//对每次访问之前都要先看是否已经登录
router.beforeEach((to, from, next) => {
    if (to.path.startsWith('/login')) {
        window.sessionStorage.removeItem('access-token')
        next()
    } else {
        let token = window.sessionStorage.getItem('access-token')
        if (!token) {
            //未登录  跳回主页面
            next({path: '/login'})
        } else {
            next()
        }
    }
});

export default router
