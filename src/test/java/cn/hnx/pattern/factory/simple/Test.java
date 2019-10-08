package cn.hnx.pattern.factory.simple;

/**
 * Created by viruser on 2019/9/23.
 *
 * 简单工厂方法：
 *  优点： 方便
 *  缺点： 存在耦合，且违反了开闭原则，即当新增一个类型的时候，工厂类也需要一起修改，
 *  所有对象实例化都需要依赖它，一旦出问题，影响的会是整个系统
 *
 *
 */
public class Test {

    public static void main(String[] args) {
        Animal animal = SimpleFactory.getAnimal(SimpleFactory.CAT);
        animal.eat();
    }
}
