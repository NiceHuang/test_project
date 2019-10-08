package cn.hnx.pattern.composite;

/**
 * Created by viruser on 2019/9/24.
 */
public interface Employee {

    void add(Employee employee);

    void remove(Employee employee);

    void print();
}
