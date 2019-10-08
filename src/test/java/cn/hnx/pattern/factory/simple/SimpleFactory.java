package cn.hnx.pattern.factory.simple;

/**
 * Created by viruser on 2019/9/23.
 */
public class SimpleFactory {

    public static final String CAT = "cat";
    public static final String DOG = "dog";


    public static Animal getAnimal(String type){
        switch (type){
            case CAT:
                return new Cat();
            case DOG:
                return new Dog();
            default:
                throw new IllegalArgumentException();
        }
    }

}
