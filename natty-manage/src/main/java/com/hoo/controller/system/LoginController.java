package com.hoo.controller.system;

import cn.hutool.crypto.SecureUtil;
import com.hoo.bean.Result;
import com.hoo.service.system.ManagerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;

/**
 * @author : natty
 * create at:  2019-08-24  10:44
 * @description:
 */
@RestController
public class LoginController {

    @Resource
    private ManagerService managerService;

    @PostMapping("login")
    public Result login(String username, String pwd) {
        try {
            //        Manager manager = managerService.getOne(new QueryWrapper<Manager>()
//                .eq("username", username)
//                .eq("password", SecureUtil.sha1(SecureUtil.md5(pwd)))
//                .eq("is_del", 1)
//                .eq("is_job", 1));
//
//        if (ObjectUtil.isNull(manager)){
//            return Result.failure(Error._200106);
//        }

            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username,SecureUtil.sha1(SecureUtil.md5(pwd)));
            subject.login(token);
            String sessionId = (String)subject.getSession().getId();
            return Result.success(new HashMap<String,String>(){{put("token",sessionId);}});
        }catch (AuthenticationException ae){
            System.out.println("111"+ae);
            return Result.fail(ae.getMessage());
        }catch (Exception e){
            System.out.println("2222"+e);
            return Result.fail(e.getMessage());
        }


    }

    public static void main(String[] args) {
        String hushuai = SecureUtil.sha1(SecureUtil.md5("123456"));
        System.out.println(hushuai);

    }
}
