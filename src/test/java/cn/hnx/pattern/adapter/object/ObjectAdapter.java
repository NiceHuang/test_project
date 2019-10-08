package cn.hnx.pattern.adapter.object;

/**
 * Created by viruser on 2019/9/24.
 */
public class ObjectAdapter implements Target {

    public ObjectAdapter() {
    }

    private Adaptee adaptee = new Adaptee();

    @Override
    public void request() {
        System.out.println("对象适配器");
        adaptee.adapteeRequest();
    }
}
