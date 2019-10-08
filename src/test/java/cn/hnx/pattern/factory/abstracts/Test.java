package cn.hnx.pattern.factory.abstracts;

/**
 * Created by viruser on 2019/9/23.
 */
public class Test {

    public static void main(String[] args) {

        AbstractFactory men = new MenFactory();
        men.getAnimal().eat();
        men.getFruit().time();

        AbstractFactory women = new WomenFactory();
        women.getAnimal().eat();
        women.getFruit().time();
    }
}
