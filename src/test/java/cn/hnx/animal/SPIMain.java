package cn.hnx.animal;

import java.util.ServiceLoader;

/**
 * Created by viruser on 2019/9/4.
 */
public class SPIMain {

    public static void main(String[] args) {
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);
        for (IShout s : shouts) {
            s.shout();
        }
    }
}
