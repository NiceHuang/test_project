package controller;

import cn.hnx.AppApplication;
import cn.hnx.common.bean.User;
import cn.hnx.service.impl.TestThreadServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hnx on 2018/8/1.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//启动端口配置，随机生成
@ContextConfiguration(classes = AppApplication.class)//启动类配置
public class TestThreadControllerTest {

    @Autowired
    private TestThreadServiceImpl testThreadService;

    @Test
    public void addUserTest(){
        int count = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++){
            executorService.execute(new AddUserThread(cyclicBarrier));
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    class AddUserThread implements Runnable {

        private String name;
        private CyclicBarrier cyclicBarrier;

        public AddUserThread(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        public AddUserThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
                User user = new User();
                user.setName("测试" + ((int)(Math.random() * 100)));
                testThreadService.addUser(user);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public CyclicBarrier getCyclicBarrier() {
            return cyclicBarrier;
        }

        public void setCyclicBarrier(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }
    }
}
