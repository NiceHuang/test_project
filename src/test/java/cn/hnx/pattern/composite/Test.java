package cn.hnx.pattern.composite;

/**
 * Created by viruser on 2019/9/24.
 * 组合模式（Composite Pattern）属于结构型模式的一种，
 * 组合多个对象形成树形结构来表示部分 - 整体的结构层次，对单个对象（叶子对象）和组合对象（容器对象）的使用具有一致性
 *
 * 分为透明方式和安全方式
 * 透明方式：接口暴露所有方法
 *
 * 安全方式:接口只暴露公共方法
 *
 */
public class Test {

    public static void main(String[] args) {
        Employee emp1 = new Developer("员工 - 小李", "10000");
        Employee emp2 = new Developer("员工 - 小王", "15000");
        Employee manager1 = new Manager("经理 - 邱东", "25000");
        manager1.add(emp1);
        manager1.add(emp2);
        Employee emp3 = new Developer("员工 - 小唐", "20000");
        Manager generalManager = new Manager("总经理 - 覃飞", "50000");
        generalManager.add(emp3);
        generalManager.add(manager1);
        generalManager.print();
    }
}
