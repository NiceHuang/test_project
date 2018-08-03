package cn.hnx.controller;

import cn.hnx.common.bean.User;
import cn.hnx.service.impl.TestThreadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hnx on 2018/7/30.
 */

@RestController
@RequestMapping("/thread")
public class TestThreadController {

    @Autowired
    private TestThreadServiceImpl testThreadService;


    @RequestMapping("/fetchUser")
    public List<User> fetchUser(){
        List<User> list = testThreadService.fetchUser();
        return list;
    }

    @RequestMapping("/fetchUserById")
    public User fetchUserById(int id){
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        User user = testThreadService.fetchUserById(params);
        return user;
    }

    @RequestMapping("/addUser")
    public String addUser(@RequestBody User user){
        if (user == null){
            return "error";
        }
        testThreadService.addUser(user);
        return "success";
    }

    @RequestMapping("/updateUser")
    public String updateUser(User user){
        if (user == null){
            return "error";
        }
        testThreadService.updateUser(user);
        return "success";
    }

    @RequestMapping("/testThread")
    public String testThread(){
        testThreadService.testThread();
        return "success";
    }
}
