package cn.hnx.pattern.adapter.clazz;

/**
 * Created by viruser on 2019/9/24.
 * 类适配器
 */
public class ClassAdapter extends Adaptee implements Target {
    @Override
    public void request() {
        System.out.println("类适配器调用");
        super.adapteeRequest();
    }
}
