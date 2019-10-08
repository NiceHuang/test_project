package cn.hnx.animal;

/**
 * Created by viruser on 2019/9/4.
 */
public class Dog implements IShout {
    @Override
    public void shout() {
        System.out.println("wang wang");
    }
}
