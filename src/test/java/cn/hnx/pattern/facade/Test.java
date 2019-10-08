package cn.hnx.pattern.facade;

/**
 * Created by viruser on 2019/9/25.
 * 外观模式（Facade Pattern）属于结构型模式的一种，
 * 为子系统中的一组接口提供一个统一的入口，它通过引入一个外观角色来简化客户端与子系统之间的交互…
 *
 * 优点：
 *  降低了客户类与子系统类的耦合度，实现了子系统与客户之间的松耦合关系
 *  外观模式对客户屏蔽了子系统组件，从而简化了接口，减少了客户处理的对象数目并使子系统的使用更加简单。
 *  客户程序与抽象类的实现部分之间存在着很大的依赖性，引入Facade将这个子系统与客户以及其他的子系统分离，
 *  可以提高子系统的独立性和可移植性。
 * 缺点：
 *  在不引入抽象外观类的情况下，增加新的子系统可能需要修改外观类或客户端的源代码，违背了开闭原则
 *
 */
public class Test {

    public static void main(String[] args) {
        FacadeOrder facadeOrder = new FacadeOrder();

        facadeOrder.buyPhone();
    }
}
