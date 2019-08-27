package com.hoo.config;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

/**
* create by hushuai at 2019-08-24 09:52
* description: 过滤器MyPassThruAuthenticationFilter
 * 在ShiroConfig配置类的shiroFilter方法中配置使用MyPassThruAuthenticationFilter过滤器
 * 系统重定向会默认把请求头清空，通过在MyPassThruAuthenticationFilter的onAccessDenied方法中重新设置请求头，
 * 解决跨域问题 。当Shiro判定当前请求为非法请求（未登录）时，会调用该onAccessDenied方法，
 * 若当前请求地址是设定的loginUrl则放行继续执行之后的方法，在我们的配置中是执行/unauth请求的方法，
 * 返回json数据。若当前请求不是loginUrl方法则重定向到loginUrl方法。
*/
public class MyPassThruAuthenticationFilter extends PassThruAuthenticationFilter {

    private Logger log = LoggerFactory.getLogger(this.getClass());

        //获取请求方法，若为OPTIONS请求直接返回True放行
    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        log.info("进入MyPassThruAuthenticationFilter");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
            log.info("OPTIONS方法直接返回True");
            return true;
        }
        return super.onPreHandle(request, response, mappedValue);
    }
    
    
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResp = WebUtils.toHttp(response);
        HttpServletRequest httpReq = WebUtils.toHttp(request);

        /** 系统重定向会默认把请求头清空，这里通过拦截器重新设置请求头，解决跨域问题 */
        httpResp.addHeader("Access-Control-Allow-Origin", httpReq.getHeader("Origin"));
        httpResp.addHeader("Access-Control-Allow-Headers", "*");
        httpResp.addHeader("Access-Control-Allow-Methods", "*");
        httpResp.addHeader("Access-Control-Allow-Credentials", "true");

        if (isLoginRequest(request, response)) {
            return true;
        } else {
            saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }
}
