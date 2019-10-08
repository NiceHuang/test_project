package cn.hnx.pattern.factory.simple;

/**
 * Created by viruser on 2019/9/23.
 */
public class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("啃骨头");
    }
}
