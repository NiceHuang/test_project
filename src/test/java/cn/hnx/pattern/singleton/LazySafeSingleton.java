package cn.hnx.pattern.singleton;

/**
 * Created by viruser on 2019/9/24.
 * 懒汉式，线程安全
 */
public class LazySafeSingleton {

    private static LazySafeSingleton lazySingleton;

    public LazySafeSingleton() {
    }

    public synchronized static LazySafeSingleton getInstance(){
        if(lazySingleton == null){
            lazySingleton = new LazySafeSingleton();
        }
        return lazySingleton;
    }

    public void show(){
        System.out.println("懒汉式，线程安全");
    }
}
