package cn.hnx.thread;

import java.util.Date;

/**
 * Created by viruser on 2019/9/17.
 */
public class ThreadJoin {

    public static void main(String[] args) throws InterruptedException {

//        Test test = new Test("hello");
//        Test test2 = new Test("2hello");
//
//        new Thread(() -> {
//            test.showName();
//        }).start();
//
//        new Thread(() -> {
//            test2.showName2();
//        }).start();

//        System.out.println("===================================================");
//
//        new Thread(Bank1::transferAccount, "北京银行").start();
//        new Thread(Bank1::debit, "上海银行").start();

        System.out.println("===================================================");

        DeadLockFixed fixed = new DeadLockFixed();
        new Thread(fixed::method1, "method1").start();
        new Thread(fixed::method2, "method2").start();
    }
}

class CaptureRunnable implements Runnable{
    private String machineName;//采集任务名
    private Long spendTime;//采集工作消耗时长
    public CaptureRunnable(String machineName, Long spendTime) {
        this.machineName = machineName;
        this.spendTime = spendTime;
    }

    @Override
    public void run() {
        try {
            System.out.println(machineName + "开始采集");
            Thread.sleep(spendTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Test {
    private String name;

    public Test(String name) {
        this.name = name;
    }

    public synchronized void showName(){
        try {
            Thread.sleep(10000);
            System.out.println("showName: " + new Date(System.currentTimeMillis()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void showName2(){
        try {
            Thread.sleep(10000);
            System.out.println("showName2: " + new Date(System.currentTimeMillis()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Bank1 {
    synchronized static void transferAccount() {
        System.out.println("开始转账：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("转账完毕");
    }

    synchronized static void debit() {
        System.out.println("开始扣款：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("扣款完毕");
    }
}

class DeadLockFixed {

    public DeadLockFixed() {
    }

    void method1() {
        while (true) {
            synchronized (Integer.class) {
                System.out.println("获得 Integer.class 对象锁");
                synchronized (String.class) {
                    System.out.println("获得 String.class 对象锁");
                }
            }
        }
    }
    void method2() {
        while (true) {
            synchronized (String.class) {
                System.out.println("获得 String.class 对象锁");
                synchronized (Integer.class) {
                    System.out.println("获得 Integer.class 对象锁");
                }
            }
        }
    }
}