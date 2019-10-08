package cn.hnx.pattern.singleton;

/**
 * Created by viruser on 2019/9/24.
 * 单例模式主要分三种：懒汉式单例、饿汉式单例、登记式单例三种。
 * 懒汉：非线程安全，需要用一定的风骚操作控制，装逼失败有可能导致看一周的海绵宝宝
 * 饿汉：天生线程安全，ClassLoad的时候就已经实例化好，该操作过于风骚会造成资源浪费
 * 单例注册表：Spring初始化Bean的时候，默认单例用的就是该方式
 *
 * 懒汉 -》需要使用的时候再初始化
 * 饿汉 -》项目启动时就初始化
 */
public class Test {

    public static void main(String[] args) {

        EnumSingleton singleton1 = EnumSingleton.getInstance();
        EnumSingleton singleton2 = EnumSingleton.getInstance();

        System.out.println(singleton1);
        System.out.println(singleton2);


        DCLSafeSingleton dclSafeSingleton = DCLSafeSingleton.getInstance();
        DCLSafeSingleton dclSafeSingleton2 = DCLSafeSingleton.getInstance();

        System.out.println(dclSafeSingleton);
        System.out.println(dclSafeSingleton2);

        HungrySingleton hungrySingleton = HungrySingleton.getInstance();
        HungrySingleton hungrySingleton2 = HungrySingleton.getInstance();

        System.out.println(hungrySingleton);
        System.out.println(hungrySingleton2);


        LazySafeSingleton lazySafeSingleton = LazySafeSingleton.getInstance();
        LazySafeSingleton lazySafeSingleton2 = LazySafeSingleton.getInstance();

        System.out.println(lazySafeSingleton);
        System.out.println(lazySafeSingleton2);

        LazySingleton lazySingleton = LazySingleton.getInstance();
        LazySingleton lazySingleton2 = LazySingleton.getInstance();

        System.out.println(lazySingleton);
        System.out.println(lazySingleton2);

        StaticSingleton staticSingleton = StaticSingleton.getInstance();
        StaticSingleton staticSingleton2 = StaticSingleton.getInstance();

        System.out.println(staticSingleton);
        System.out.println(staticSingleton2);
    }
}
