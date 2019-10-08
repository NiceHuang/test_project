package cn.hnx.pattern.singleton;

/**
 * Created by viruser on 2019/9/24.
 * 双检锁/双重校验锁（DCL，即 double-checked locking）
 * 注意volatile关键字
 */
public class DCLSafeSingleton {

    private volatile static DCLSafeSingleton lazySingleton;

    public DCLSafeSingleton() {
    }

    public static DCLSafeSingleton getInstance(){
        if(lazySingleton == null){
            synchronized (DCLSafeSingleton.class){
                if(lazySingleton == null){
                    lazySingleton = new DCLSafeSingleton();
                }
            }
        }
        return lazySingleton;
    }

    public void show(){
        System.out.println("双检锁/双重校验锁，线程安全");
    }
}
