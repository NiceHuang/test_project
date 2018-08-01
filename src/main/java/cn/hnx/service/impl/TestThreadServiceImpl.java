package cn.hnx.service.impl;

import cn.hnx.common.bean.User;
import cn.hnx.common.utils.UserStatus;
import cn.hnx.dao.TestThreadDao;
import cn.hnx.service.TestThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by hnx on 2018/7/30.
 */

@CacheConfig(cacheNames = "map")
@Service
public class TestThreadServiceImpl implements TestThreadService, ApplicationListener<ContextRefreshedEvent> {

    private LinkedBlockingQueue<User> queueList = new LinkedBlockingQueue<User>();

    @Autowired
    private TestThreadDao testThreadDao;

    @Override
    public void testThread() {

    }

    @Override
    public List<User> fetchUser() {
        Map<String, Object> params = new HashMap<>();
        return testThreadDao.fetchUser(params);
    }

    @Cacheable()
    @Override
    public User fetchUserById(Map<String, Object> params) {
        return testThreadDao.fetchUserById(params);
    }

    int i = 0;

    @Override
    public void addUser(User user) {
        try {
            Thread.sleep(10000);
            System.out.println(user.toString());
            System.out.println("当前序号：" + i);
            testThreadDao.addUser(user);
            i++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        testThreadDao.updateUser(user);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //root application context 没有parent
        if(event.getApplicationContext().getParent() == null)
        {
            //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
            Thread thread = new Thread(new TestThread("test_thread"));
            thread.start();
        }
    }

    class TestThread implements Runnable{

        private String threadName;

        public TestThread(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            while (true){
                System.out.println("线程【" + threadName + "】");
                try {
                    Map<String, Object> params = new HashMap<>();
                    params.put("status", UserStatus.WAITING.getStatus());
                    List<User> list = testThreadDao.fetchUser(params);
                    if (list != null){
                        queueList = new LinkedBlockingQueue<User>(list);
                    }
                    while (true){
                        User user = queueList.poll();
                        if (user == null){
                            break;
                        }
                        System.out.println(user.toString());
                        user.setStatus(UserStatus.ONLINE.getStatus());
                        testThreadDao.updateUser(user);

                        Thread.sleep(10000);
                    }
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

