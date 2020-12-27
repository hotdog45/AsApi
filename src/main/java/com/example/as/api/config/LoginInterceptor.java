package com.example.as.api.config;

import com.example.as.api.uitl.UserRedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.handler.Handler;
import java.lang.reflect.Method;

@Component
public class LoginInterceptor  implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class<?>  clazz = handlerMethod.getBeanType();
        Method m = handlerMethod.getMethod();
        //需要登录才可以访问的接口
        if (!clazz.isAnnotationPresent(NeedLogin.class)&&!m.isAnnotationPresent(NeedLogin.class)){
            return  true;
        }
        if (UserRedisUtil.checkUser(redisTemplate,request)){
            return true;

        }else {
            response.setStatus(401);
            response.setContentType("text/html;charset=utf-8");//防止乱码
            response.getWriter().write("请先登录~");
        }
        return false;
    }
}
