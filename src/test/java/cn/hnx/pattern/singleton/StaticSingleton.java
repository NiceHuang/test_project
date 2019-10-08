package cn.hnx.pattern.singleton;

/**
 * Created by viruser on 2019/9/24.
 * 登记式/静态内部类
 */
public class StaticSingleton {


    private static class SingletonHolder{
        private static final StaticSingleton INSTANCE = new StaticSingleton();
    }


    public StaticSingleton() {
    }

    public static StaticSingleton getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public void show(){
        System.out.println("静态内部类，线程安全");
    }
}
