package cn.hnx;

/**
 * Created by viruser on 2019/8/9.
 */
public class TestNiceHashMap {


    public static void main(String[] args) {

        NiceHashMap map = new NiceHashMap();
        map.put(1, "test1");

        map.put(2, "test2");
        map.put(3, "test3");
        map.put(4, "test4");

        System.out.println(map.get(3));

        map.put(3, "test31");

        System.out.println(map.get(3));
        System.out.println("11");
    }
}
