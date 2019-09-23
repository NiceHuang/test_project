package cn.hnx.model.factory.abstracts;

/**
 * Created by viruser on 2019/9/23.
 */
public class Cat implements Animal {
    @Override
    public void eat() {
        System.out.println("吃鱼");
    }
}
