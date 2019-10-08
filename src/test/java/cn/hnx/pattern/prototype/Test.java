package cn.hnx.pattern.prototype;

import java.util.ArrayList;

/**
 * Created by viruser on 2019/9/24.
 * 原型模式（Prototype Pattern）是创建模式的一种，
 * 其作用是提高创建效率，减少计算机资源开销，与工厂模式类似的是，都屏蔽了对象实例化的过程…
 *
 * 优点：
 *  简化对象创建过程，通过拷贝的方式构建效率更高
 *  可运行时指定动态创建的对象
 * 缺点：
 *  需要实现 Cloneable接口，clone位于内部，不易扩展，容易违背开闭原则(程序扩展,不应该修改原有代码)
 *  默认的 clone 只是浅克隆，深度克隆需要额外编码(比如：统一实现Cloneable接口，或者序列化方式，
 *  还有org.apache.commons:commons-lang3.SerializationUtils.java)
 *
 * 适用场景：
 *  常用在初始化步骤繁琐，资源耗损严重的对象
 */
public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {

        Prototype prototype = new Prototype("hello",
                new Child("child"), new ArrayList<String>(2){{
                    add("1");
                    add("2");
        }});

        Prototype cloned = prototype.clone();
        System.out.println(prototype.toString());
        System.out.println(cloned.toString());
    }
}
