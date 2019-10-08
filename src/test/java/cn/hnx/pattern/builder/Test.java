package cn.hnx.pattern.builder;

/**
 * Created by viruser on 2019/9/24.
 * 建造者模式（Builder Pattern）属于创建型模式的一种，将多个简单对象构建成一个复杂的对象，
 * 构建过程抽象化，不同实现方法可以构造出不同表现（属性）的对象，还提供了一种更加优雅构建对象的方式…
 * 有时候构建一个复杂的对象，需要经过好几步的处理，比如常用的StringBuffer、StringBuilder、
 * 以及Swagger（一种接口文档），都是以这种模式构建对象的
 *
 * 优点：
 *  建造者模式比较独立，将对象本身与构建过程解耦
 *  精准控制构建出的对象和内容，构造层和显示层是分离的
 *  写法上更加优雅
 * 缺点：
 *  范围受限，不适合差异较大的对象
 *  内部复杂多变，构造类相对会多
 * 适用场景：
 *  构建具有共同特性的复杂对象
 *
 *  抽象工厂模式与建造者模式相似，因为它也可以创建复杂对象。主要的区别是建造者模式着重于一步步得构造出复杂对象。
 *  而抽象工厂模式着重于多个系列的产品对象（简单的或是复杂的）。
 *  建造者是在最后的一步返回对象，而对于抽象工厂来说，对象是立即返回的。
 */
public class Test {

    public static void main(String[] args) {
        Fruit fruit = new Fruit.FruitBuilder().name("苹果")
                .color("red")
                .price(5.5).weight(0.56).build();
        System.out.println(fruit.toString());
    }
}
