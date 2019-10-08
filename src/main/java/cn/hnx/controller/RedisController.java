package cn.hnx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by viruser on 2019/9/4.
 */

@RestController
@RequestMapping("/redis")
public class RedisController {


    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("/get")
    public void getCache(){
        Object myKey = redisTemplate.opsForValue().get("myKey");
        System.out.println(myKey);
    }
}
