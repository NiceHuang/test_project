package cn.hnx.controller;

import cn.hnx.common.bean.DataPortralUser;
import cn.hnx.common.utils.ResponseMessage;
import cn.hnx.common.utils.ResultMessage;
import cn.hnx.common.utils.ResultMessageBuilder;
import cn.hnx.common.utils.TokenUtil;
import cn.hnx.service.impl.DataPortralUserServiceImpl;
import cn.hnx.service.redis.DistributedLockUtil;
import cn.hnx.service.redis.IDistributedLock;
import cn.hnx.service.redis.RedisLock;
import cn.hnx.service.redis.TestThread;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by viruser on 2018/8/6.
 */
@RestController
public class UserController {

    @Autowired
    private DataPortralUserServiceImpl dataPortralUserService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/fetchUser")
    public ResultMessage fetchUser(){
        Map<String, Object> params = new HashMap<>();
        List<DataPortralUser> list = dataPortralUserService.fetchUser(params);
        return ResultMessageBuilder.build(list);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultMessage login(@RequestBody() DataPortralUser user){
        if (user == null || StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getPassword())){
            return ResultMessageBuilder.build(ResponseMessage.INVALID_PASSWORD.getCode(), ResponseMessage.INVALID_PASSWORD.getMessage());
        }
        DataPortralUser dbUser = dataPortralUserService.fetchUserByEmail(user.getEmail());
        if (dbUser == null || !dbUser.getPassword().equals(user.getPassword())){
            return ResultMessageBuilder.build(ResponseMessage.INVALID_PASSWORD.getCode(), ResponseMessage.INVALID_PASSWORD.getMessage());
        }
        String token = TokenUtil.createTokenForUser(dbUser);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return ResultMessageBuilder.build(map);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResultMessage register(@RequestBody() DataPortralUser user){
        dataPortralUserService.addDataPortralUser(user);
        return ResultMessageBuilder.build();
    }


    @RequestMapping(value = "/redis")
    public ResultMessage redis(){
        RedisLock lock = new RedisLock();
        lock.setRedisTemplate(redisTemplate);
//        lock.acquire();
        Thread thread1 = new Thread(new TestThread("线程1", redisTemplate));
        Thread thread2 = new Thread(new TestThread("线程2", redisTemplate));

        thread1.start();

        thread2.start();
        return ResultMessageBuilder.build();
    }
}
