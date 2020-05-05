package com.redisStudy.baseStudy;

import com.alibaba.fastjson.JSON;
import com.redisStudy.client.CommonRedissionClient;
import com.redisStudy.model.User;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

/**
 * 测试{@link User}的redis读写
 *
 * @author chenwu on 2020.5.5
 */
public class UserReadAndWrite {

    private CommonRedissionClient commonRedissionClient = CommonRedissionClient.getInstance("redis/local_redis.properties");

    public void writePeopleInJson(String key,User user){
        if(user==null){
            return;
        }
        String jsonString = JSON.toJSONString(user);
        commonRedissionClient.setSingleKey(key,jsonString,10l, TimeUnit.MINUTES,String.class);
    }

    public User readPeopleFromJson(String key){
        if(StringUtils.isBlank(key)){
            return null;
        }
        String value = commonRedissionClient.getSingleKey(key,String.class);
        if(StringUtils.isBlank(value)){
            return null;
        }
        User user = JSON.parseObject(value, User.class);
        return user;
    }

    public void writeUserInHash(String key,User user){
        if(StringUtils.isBlank(key) || user==null){
            return;
        }
        Field[] declaredFields = user.getClass().getDeclaredFields();
        for(Field field:declaredFields){
            field.setAccessible(true);
            try{
                Object object = field.get(user);
                commonRedissionClient.hsetSingleKey(key,field.getName(),object,10,TimeUnit.MINUTES);
            }catch(Exception e){
                throw new RuntimeException(e);
            }
        }

    }

    public <T> T readUserInHash(String key,String field,Class<T> valueClass){
        if(StringUtils.isBlank(key) || StringUtils.isBlank(field)){
            return null;
        }
        T t = commonRedissionClient.hgetSingleKey(key, field, valueClass);
        return t;
    }

    public static void main(String[] args) {
        UserReadAndWrite userReadAndWrite = new UserReadAndWrite();
        //String key = "testUser";
        //字典的方式
        String key = "testHash";
        User user = new User();
        user.setId(11);
        user.setName("sweet");
        //以json 的方式来实现
//        userReadAndWrite.writePeopleInJson(key,user);
//        User userFromRedis = userReadAndWrite.readPeopleFromJson(key);
//        Assert.assertEquals(11,userFromRedis.getId().intValue());
//        Assert.assertEquals("sweet",userFromRedis.getName());
        userReadAndWrite.writeUserInHash(key,user);
        Integer id = userReadAndWrite.readUserInHash(key, "id", Integer.class);
        String name = userReadAndWrite.readUserInHash(key, "name", String.class);
        Assert.assertEquals(11,id.intValue());
        Assert.assertEquals("sweet",name);
        userReadAndWrite.commonRedissionClient.close();
    }
}
