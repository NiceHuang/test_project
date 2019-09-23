package cn.hnx.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by viruser on 2019/9/23.
 */
public class MyThreadPoolExecutor {

    //直接丢弃策略
    private static ThreadFactory discardPolicyFactory = new ThreadFactoryBuilder()
            .setNameFormat("discard-policy-%d").build();

    private static ThreadPoolExecutor discardPolicyExecutor =
            new ThreadPoolExecutor(2, 3, 1,
                    TimeUnit.SECONDS, new LinkedBlockingDeque<>(10),
                    discardPolicyFactory, new ThreadPoolExecutor.DiscardPolicy());

    //丢弃队列中最老的任务
    private static ThreadFactory discardOldestPolicyFactory = new ThreadFactoryBuilder()
            .setNameFormat("discard-old-policy-%d").build();

    private static ThreadPoolExecutor oldPoolExecutor =
            new ThreadPoolExecutor(2, 3, 1,
                    TimeUnit.SECONDS, new LinkedBlockingDeque<>(10),
                    discardOldestPolicyFactory, new ThreadPoolExecutor.DiscardOldestPolicy());

    //抛异常
    private static ThreadFactory abortPolicyFactory = new ThreadFactoryBuilder()
            .setNameFormat("abort-policy-%d").build();

    private static ThreadPoolExecutor abortPolicyExecutor =
            new ThreadPoolExecutor(2, 3, 1,
                    TimeUnit.SECONDS, new LinkedBlockingDeque<>(10),
                    abortPolicyFactory, new ThreadPoolExecutor.AbortPolicy());

    //将任务分给调用线程来执行
    private static ThreadFactory callerRunsPolicyFactory = new ThreadFactoryBuilder()
            .setNameFormat("caller-runs-policy-%d").build();

    private static ThreadPoolExecutor callerRunsPolicyExecutor =
            new ThreadPoolExecutor(2, 3, 1,
                    TimeUnit.SECONDS, new LinkedBlockingDeque<>(10),
                    callerRunsPolicyFactory, new ThreadPoolExecutor.CallerRunsPolicy());


    public static void main(String[] args) {
        int i = 50;

        for (int i1 = 0; i1 < i; i1++) {
            MyTask task = new MyTask(String.format("hello %s", i1));
            callerRunsPolicyExecutor.execute(task);
        }
//        while (true){
//            try {
//                System.out.println("isShutdown: " + discardPolicyExecutor.isShutdown());
//                System.out.println("isTerminated: " + discardPolicyExecutor.isTerminated());
//                System.out.println("isTerminating: " + discardPolicyExecutor.isTerminating());
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }




    static class MyTask implements Runnable{

        private String value;

        public MyTask(String value) {
            this.value = value;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "=================" + this.value);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }







}
