package cn.hnx.pattern.factory.abstracts;

/**
 * Created by viruser on 2019/9/23.
 * 女生喜欢猫和苹果
 */
public class WomenFactory extends AbstractFactory {

    @Override
    public Animal getAnimal() {
        return new Cat();
    }

    @Override
    public Fruit getFruit() {
        return new Apple();
    }
}
