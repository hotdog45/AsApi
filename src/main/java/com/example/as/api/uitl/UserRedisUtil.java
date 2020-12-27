package com.example.as.api.uitl;

import com.example.as.api.entity.UserEntity;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserRedisUtil {
    public static final String BOARDING_PASS = "boarding-pass";
    /**
     * 添加
     * @param redisTemplate
     * @param session
     * @param userEntity
     */
    public static void  addUser(StringRedisTemplate redisTemplate, HttpSession session,
                                UserEntity userEntity){
        //用户session写入redis
        redisTemplate.opsForValue().set(getKey(session),JsonUtil.toJsonString(userEntity));
    }

    /**
     * 移除
     * @param redisTemplate
     * @param session
     */
    public static void  removeUser(StringRedisTemplate redisTemplate, HttpSession session){

        session.invalidate();;
        redisTemplate.delete(getKey(session));

    }

    /**
     * 判断用户是否为空
     * @param redisTemplate
     * @param request
     * @return
     */
    public static boolean checkUser(StringRedisTemplate redisTemplate,HttpServletRequest request){
        String val = redisTemplate.opsForValue().get(getBoardingPass(request));
        return val !=null;
    }

    /**
     * 读取
     * @param redisTemplate
     */
    public static UserEntity  getUser(StringRedisTemplate redisTemplate, HttpServletRequest request){


        String val = redisTemplate.opsForValue().get(getBoardingPass(request));
        if (val !=null){
            return  JsonUtil.fromJson(val,UserEntity.class);
        }

        return  null;
    }

    /**
     * 获取redis 存储的key
     * @param session
     * @return
     */
    public static String getKey(HttpSession session){
        return session.getId();
    }
    /**
     * 获取是否登录的凭证
     *
     * @param request
     * @return
     */
    public static String getBoardingPass(HttpServletRequest request){

        String pass = request.getHeader(BOARDING_PASS);
        return pass != null ? pass : "no-pass";
    }

}
