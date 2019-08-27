<template>
    <section>
        <el-container class="container">
            <!--展开菜单-->
            <el-button @click="showMenu()" style="position: fixed;top: 60px;z-index: 1" v-show="isCollapse"
                       icon="el-icon-arrow-right"></el-button>
            <!--左边-->
            <el-aside :width="isCollapse ? '0px' : '200px'">
                <el-container>
                    <el-header>
                        <img src="../assets/logo.png" class="logoImg">
                    </el-header>
                    <el-button @click="showMenu()" style="width: 100%;position: sticky;top: 60px"
                               icon="el-icon-arrow-left"></el-button>
                    <el-main>
                        <el-menu :default-active="$route.path" :collapse="isCollapse">
                            <template v-for="(item,index) in menus">
                                <el-submenu :index="index+''" v-if="!item.leaf">
                                    <template slot="title">
                                        <i :class="item.iconCls" style="margin-left: -20px;margin-right: 10px;"></i>
                                        <span>{{item.name}}</span>
                                    </template>
                                    <!-- @click="$router.push(child.path)"-->
                                    <el-menu-item
                                        v-for="child in item.children"
                                        :index="child.path"
                                        :key="child.path"

                                        @click="menuItemClick(child.name,child.path)">
                                        <i :class="child.iconCls" style="margin-left: -20px;margin-right: 10px;"></i>{{child.name}}
                                    </el-menu-item>
                                </el-submenu>
                                <el-menu-item v-if="item.leaf&&item.children.length>0" :index="item.children[0].path">
                                    <i :class="item.iconCls" style="margin-left: -20px;margin-right: 10px;"></i>{{item.children[0].name}}
                                </el-menu-item>
                            </template>

                        </el-menu>
                    </el-main>
                </el-container>
            </el-aside>
            <!--内容-->
            <el-container>
                <!--页眉-->
                <el-header class="header">
                    <el-row>
                        <el-col :span="20" class="header-title">
                            <span class="system-name">{{systemName}}</span>

                        </el-col>
                        <el-col :span="2"><span class="el-dropdown-link userinfo-inner">你好：{{userName}}</span></el-col>
                        <el-col :span="1"><span class="el-dropdown-link userinfo-inner">|</span></el-col>
                        <el-col :span="1"><span class="el-dropdown-link userinfo-inner">退出</span></el-col>
                    </el-row>
                </el-header>
                <!-- 面包屑导航 -->
                <el-tabs type="card" v-model="currentTab" @tab-click="tabClick" @tab-remove="tabRemove">
                    <el-tab-pane v-for="(tab,index) in tabs" :index="tab.path" :closable="tab.path != '/home'"
                                 :key="index" :name="index+''"
                                 :label="tab.name"></el-tab-pane>
                </el-tabs>

                <!--中间-->
                <el-main class="main">
                    <transition name="fade" mode="out-in">
                        <keep-alive>
                            <router-view></router-view>
                        </keep-alive>

                    </transition>
                </el-main>

                <!--<el-main class="main">
                    <div v-for="(item,index) in menus" :index="index+''" :key="index">
                        <el-tabs type="card" editable @tab-click="tabClick">
                            <el-tab-pane v-if="!item.leaf" v-for="child in item.children" :index="child.path"
                                         :key="child.path" :name="child.path"
                                         :label="child.name">
                                <transition name="fade" mode="out-in">
                                    <router-view></router-view>
                                </transition>
                            </el-tab-pane>
                        </el-tabs>
                    </div>
                </el-main>-->


            </el-container>
        </el-container>
    </section>
</template>

<script>
    let data = () => {
        return {
            collapsed: false,
            systemName: 'XXX管理系统',
            userName: 'admin',
            menus: [],
            isCollapse: false,
            tabs: [
                {
                    name: '首页',
                    path: '/home'
                }
            ],
            currentTab: '0'
        }
    }



    let menuItemClick = function (name, path) {
        // console.log(name, path)
        //跳转路由
        this.$router.push(path)

        //添加顶部tab
        let tabs = this.tabs;
        for (let i in tabs) {
            if (tabs[i].path == path) {
                //已经存在了，就不在添加
                this.currentTab = i+''
                return
            }
        }
        tabs.push({'name': name, 'path': path})
        this.currentTab = tabs.length-1+''
    }

    let tabClick = function (tab) {
        // console.log(tab)
        this.$router.push(this.tabs[tab.name].path)
    }

    let tabRemove = function (index) {
        this.tabs.splice(parseInt(index), 1)
        if (this.currentTab == index) {
            this.currentTab = parseInt(this.currentTab) - 1+''
        }
    }

    let getMenu = function () {
        this.$api.post('/permission/menu', {}, res => {
            console.log("menu",res.data)
            let result = res.data;
            if (result.status === '1') {
                this.initMenu(result.data)
            }
        })
    }

    //这个方法就是变量菜单的方法，然后放入menus这边集合当中
    let initMenu = function (data) {
        // this.getMenu()
        // console.log("routes",data)
        for (let i in data) {
            let root = data[i]
            if (root.hidden)
                continue
            let children = []
            for (let j in root.children) {
                let item = root.children[j]
                if (item.hidden)
                    continue
                children.push(item)
            }

            if (children.length < 1)
                continue

            this.menus.push(root)
            root.children = children
        }
        this.getCurrentRouter()
    }
    let getCurrentRouter = function () {
        let router = this.$route;
        if (router.path == '/home') {
            return
        }
        this.tabs.push({name:router.name,path:router.path})
        this.currentTab = '1'
        console.log('router',router)
    }

    // let initMenu = function() {
    //     // this.getMenu()
    //     console.log("routes",this.$router.options.routes)
    //     for (let i in this.$router.options.routes) {
    //         let root = this.$router.options.routes[i]
    //         if (root.hidden)
    //             continue
    //         let children = []
    //         for (let j in root.children) {
    //             let item = root.children[j]
    //             if (item.hidden)
    //                 continue
    //             children.push(item)
    //         }
    //
    //         if (children.length < 1)
    //             continue
    //
    //         this.menus.push(root)
    //         root.children = children
    //     }
    // }
    //显示/隐藏菜单
    let showMenu = function () {
        this.isCollapse = !this.isCollapse
    }


    export default {
        data: data,
        methods: {
            getCurrentRouter,
            initMenu,
            showMenu,
            getMenu,
            menuItemClick,
            tabClick,
            tabRemove
        },
        // created:function(){
        //     this.getMenu()
        // },
        mounted: function () {
            this.getMenu()
        }
    }
</script>

<style scoped="scoped" lang="scss">


    $width: 100%;
    $height: 100%;
    $background-color: #E3BA63;
    $header-color: #fff;
    $header-height: 60px;

    .container {
        position: absolute;
        top: 0;
        bottom: 0;
        width: 100%;

        .el-aside {
            .el-header {
                line-height: $header-height;
                background-color: $background-color;
                color: $header-color;
                text-align: center;
            }

            .el-container {
                height: $height;

                .el-main {
                    padding: 0;
                }
            }
        }

        .main {
            width: $width;
            height: $height;
            background-color: #ffffff;
        }

        .menu-button {
            width: 14px;
            cursor: pointer;
        }

        .userinfo-inner {
            cursor: pointer;
        }

        .el-menu {
            height: $height;
        }

        .header {
            background-color: $background-color;
            color: $header-color;
            text-align: center;
            line-height: $header-height;
            padding: 0;

            .header-title {
                text-align: left;

                span {
                    padding: 0 20px;
                }
            }
        }

        .system-name {
            font-size: large;
            font-weight: bold;
        }
    }

    .logoImg {
        width: 50px;
        height: 50px;
        border-radius: 30px;
        margin-top: 5px;
    }
</style>

