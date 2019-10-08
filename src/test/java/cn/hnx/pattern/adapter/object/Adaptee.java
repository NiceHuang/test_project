package cn.hnx.pattern.adapter.object;

/**
 * Created by viruser on 2019/9/24.
 * 已存在的将被适配的类
 */
public class Adaptee {

    public void adapteeRequest() {
        System.out.println("被适配者的方法");
    }
}
