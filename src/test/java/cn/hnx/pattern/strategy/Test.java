package cn.hnx.pattern.strategy;

/**
 * Created by viruser on 2019/9/23.
 * 策略模式
 * springMVC中DispatcherServlet使用的是策略模式，以及线程池中的拒绝策略也使用的是该模式
 *
 */
public class Test {


    public static void main(String[] args) {
        Nice nice = new Nice(new KuGouMusic());
        nice.listenSongs();
    }
}
