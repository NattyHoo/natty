<template>

  <!--<div id="login">

      <h1> {{msg}}</h1>
  </div>-->
  <el-form ref='AccountFrom' :model='account' :rules='rules' lable-position='left' lable-width='0px' class='demo-ruleForm login-container'>
    <h3 class="title">登录系统首页</h3>
    <el-form-item prop="username">
      <el-input type="text" v-model="account.username" auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="pwd">
      <el-input type="password" v-model="account.pwd" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>
    <el-form-item style="width:100%;">
      <el-button type="primary" style="width:100%;" @click.native.prevent='handleLogin' :loading= 'logining'>登录</el-button>
    </el-form-item>

  </el-form>

</template>

<script>
  //引入api.js  好调用里面的接口
  import {requestLogin} from '../axios/axios';

  export default {
    name: 'login',
    data() {
      return {
        logining: false,
        account: {
          username: '',
          pwd: ''
        },
        rules: {
          username: [{
            required: true,
            message: '请输入账号',
            trigger: 'blur'
          }],
          pwd: [{
            required: true,
            message: '请输入密码',
            trigger: 'blur'
          }]
        },
        checked: true
      }
    },
    methods:{
      handleLogin(){
        this.$refs.AccountFrom.validate((valid)=>{

          if(valid){
            //验证通过 可以提交
            this.logining = true;
            //将提交的数据进行封装
            var loginParams = {username : this.account.username,pwd:this.account.pwd};

            //调用函数  传递参数 获取结果
            requestLogin(loginParams).then(data=>{

              console.log(data)
              this.logining = false;

              if(data.status == '1'){
                  console.log(data.token)
                //登录成功
                sessionStorage.setItem('access-token',data.data.token);
                //用vue路由跳转到后台主界面
                this.$router.push({path:'/home'});
              }else{
                this.$message({
                  message:data.errorMsg,
                  type:'error'
                });
              }
            })

          }else{
            console.log('error submit');
            return false;
          }
        });
      }
    }
  }
</script>

<style>
  body {
    background: #ffffff;
  }

  .login-container {
    width: 350px;
    margin-left: 35%;
  }
</style>
