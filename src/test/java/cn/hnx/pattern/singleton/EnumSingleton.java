package cn.hnx.pattern.singleton;

/**
 * Created by viruser on 2019/9/24.
 * 枚举特性（懒汉），线程安全
 */
public class EnumSingleton {

    enum Lazy{
        INSTANCE;
        private EnumSingleton enumSingleton;

        Lazy() {
            enumSingleton = new EnumSingleton();
        }

        public EnumSingleton getInstance(){
            return enumSingleton;
        }
    }


    public static EnumSingleton getInstance(){
        return Lazy.INSTANCE.getInstance();
    }

    public void show(){
        System.out.println("枚举特性（懒汉），线程安全");
    }
}
