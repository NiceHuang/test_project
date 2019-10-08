package cn.hnx.pattern.factory.method;

/**
 * Created by viruser on 2019/9/23.
 */
public class Cat implements Animal {

    public Cat() {
    }

    @Override
    public void eat() {
        System.out.println("猫吃鱼");
    }
}
