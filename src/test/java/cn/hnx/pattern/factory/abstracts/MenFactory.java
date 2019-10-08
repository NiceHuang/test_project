package cn.hnx.pattern.factory.abstracts;

/**
 * Created by viruser on 2019/9/23.
 * 男生喜欢狗和香蕉
 */
public class MenFactory extends AbstractFactory {

    @Override
    public Animal getAnimal() {
        return new Dog();
    }

    @Override
    public Fruit getFruit() {
        return new Banana();
    }
}
