package cn.hnx.pattern.adapter.clazz;

/**
 * Created by viruser on 2019/9/24.
 * 适配器模式（Adapter Pattern）属于结构型模式的一种，
 * 把一个类的接口变成客户端所期待的另一种接口，从而使原本接口不匹配而无法一起工作的两个类能够在一起工作…
 *
 */
public class Test {

    public static void main(String[] args) {
        Target target = new ClassAdapter();
        target.request();
    }
}
