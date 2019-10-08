package cn.hnx.pattern.factory.method;

/**
 * Created by viruser on 2019/9/23.
 */
public class Dog implements Animal {

    public Dog() {
    }

    @Override
    public void eat() {
        System.out.println("狗啃骨头");
    }
}
