package cn.hnx.redis;

/**
 * Created by viruser on 2019/8/9.
 */
public class Test {

    public static void main(String[] args) {


//        Thread thread1 = new Thread(new TestThread("线程1"));
//        Thread thread2 = new Thread(new TestThread("线程2"));
//
//        thread1.start();
//
//        thread2.start();
        IDistributedLock lock = DistributedLockUtil.getDistributedLock("sss");

        lock.acquire();

    }


    public static class TestThread implements Runnable{

        private String name;

        private final static String lockKey = "test";

        public TestThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            IDistributedLock lock = DistributedLockUtil.getDistributedLock(lockKey);
            if(lock.acquire()){
                try {
                    System.out.println(Thread.currentThread().getName() + "线程获取到了锁");
                    Thread.currentThread().sleep(40000);
                    System.out.println(Thread.currentThread().getName() + "线程要释放锁了");
                    lock.release();
                    System.out.println(Thread.currentThread().getName() + "线程成功的释放了锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + "线程未获取到锁");
                while (true){
                    if(lock.acquire()){
                        try {
                            System.out.println(Thread.currentThread().getName() + "线程获取到了锁");
                            Thread.currentThread().sleep(40000);
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
                        } finally {
                            break;
                        }
                    }
                }
            }
        }
    }
}
