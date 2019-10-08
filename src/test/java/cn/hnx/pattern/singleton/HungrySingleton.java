package cn.hnx.pattern.singleton;

/**
 * Created by viruser on 2019/9/24.
 * 饿汉式，线程安全
 */
public class HungrySingleton {

    private static final HungrySingleton instance = new HungrySingleton();

    public HungrySingleton() {
    }

    public static HungrySingleton getInstance(){
        return instance;
    }

    public void show(){
        System.out.println("饿汉式，线程安全");
    }
}
