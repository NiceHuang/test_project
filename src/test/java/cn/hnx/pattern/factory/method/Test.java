package cn.hnx.pattern.factory.method;

/**
 * Created by viruser on 2019/9/23.
 * 工厂方法
 * 优点： 屏蔽了客户端实例化对象的细节，用户只需要关心自己使用的工厂即可。
         加入新的产品（图片读取器），无需更改现有代码，提高系统扩展性，符合开闭原则
         具备多态性，又被称为多态工厂模式
 * 缺点： 每次需要编写新的对象和对象工厂类，随业务发展，一定程度上增加了系统复杂度
 */
public class Test {

    public static void main(String[] args) {
        AnimalFactory catFactory = new CatFactory();

        Animal cat = catFactory.create();
        cat.eat();

        AnimalFactory dogFactory = new DogFactory();

        Animal dog = dogFactory.create();
        dog.eat();
    }

}
