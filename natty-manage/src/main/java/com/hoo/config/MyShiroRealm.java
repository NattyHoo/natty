package com.hoo.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hoo.service.system.ManagerService;
import com.hoo.system.bean.Manager;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
* create by hushuai at 2019-08-24 09:50
* description: ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm，负责用户的认证和权限的处理
*/
public class MyShiroRealm extends AuthorizingRealm {

    //注入用户service
    @Resource
    private ManagerService managerService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("##################执行Shiro权限认证##################");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        System.out.println("token:"+token);

        /**
         * 获取输入的用户名和密码
         */
        String userName = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        System.out.println(userName);
        System.out.println(password);
        Manager user = managerService.getOne(new QueryWrapper<Manager>().lambda().eq(Manager::getUsername, userName));
        if (user == null) {
            throw new UnknownAccountException("用户名不存在!");
        }
        if (!user.getIsDel()){
            throw new UnknownAccountException("该用户不存在!");
        }
        if (!user.getIsJob()){
            throw new UnknownAccountException("该用户已经离职!");
        }
        System.out.println("user:"+user);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        System.out.println("info:"+info);
        return info;
    }

//    @PostConstruct
//    public void initCredentialsMatcher(){
//        setCredentialsMatcher(new CredentialsMatcher());
//    }
}
