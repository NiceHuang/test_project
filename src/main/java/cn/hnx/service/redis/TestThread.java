package cn.hnx.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Created by viruser on 2019/8/9.
 */
public class TestThread implements Runnable {

    private StringRedisTemplate redisTemplate;
    private String name;

    private final static String lockKey = "test";

    public TestThread(String name, StringRedisTemplate redisTemplate) {
        this.name = name;
        this.redisTemplate = redisTemplate;
        this.setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        RedisLock lock = (RedisLock) DistributedLockUtil.getDistributedLock(lockKey);
        lock.setRedisTemplate(redisTemplate);
        if(lock.acquire()){
            System.out.println(Thread.currentThread().getName() + "线程获取到了锁");
//            try {
//                System.out.println(Thread.currentThread().getName() + "线程获取到了锁");
//                Thread.currentThread().sleep(40000);
//                System.out.println(Thread.currentThread().getName() + "线程要释放锁了");
//                lock.release();
//                System.out.println(Thread.currentThread().getName() + "线程成功的释放了锁");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        } else {
            System.out.println(Thread.currentThread().getName() + "线程未获取到锁");
            while (true){
                if(lock.acquire()){
                    try {
                        System.out.println(Thread.currentThread().getName() + "线程获取到了锁");
                        Thread.currentThread().sleep(30000);
                        System.out.println(Thread.currentThread().getName() + "线程要释放锁了");
                        lock.release();
                        System.out.println(Thread.currentThread().getName() + "线程成功的释放了锁");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        break;
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + "线程尝试重新获取");
                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
