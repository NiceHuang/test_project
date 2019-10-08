package cn.hnx.pattern.adapter.object;


/**
 * Created by viruser on 2019/9/24.
 *
 */
public class Test {

    public static void main(String[] args) {
        Target target = new ObjectAdapter();
        target.request();
    }
}
