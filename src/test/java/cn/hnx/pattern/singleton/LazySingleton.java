package cn.hnx.pattern.singleton;

/**
 * Created by viruser on 2019/9/24.
 * 懒汉式，线程不安全
 */
public class LazySingleton {

    private static LazySingleton lazySingleton;

    public LazySingleton() {
    }

    public static LazySingleton getInstance(){
        if(lazySingleton == null){
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }

    public void show(){
        System.out.println("懒汉式，线程不安全");
    }
}
