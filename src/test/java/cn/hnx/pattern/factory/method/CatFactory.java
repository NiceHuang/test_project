package cn.hnx.pattern.factory.method;

/**
 * Created by viruser on 2019/9/23.
 */
public class CatFactory implements AnimalFactory {
    @Override
    public Animal create() {

        System.out.println("创建猫工厂类");
        return new Cat();
    }
}
