package cn.hnx.service;

import cn.hnx.common.bean.User;

import java.util.List;
import java.util.Map;

/**
 * Created by hnx on 2018/7/30.
 */
public interface TestThreadService {

    void testThread();

    List<User> fetchUser();

    User fetchUserById(Map<String, Object> params);

    void addUser(User user);

    void updateUser(User user);
}
