package cn.hnx.pattern.composite;

/**
 * Created by viruser on 2019/9/24.
 */
public class Developer implements Employee {

    private String name;
    private String salary;

    public Developer(String name, String salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public void add(Employee employee) {

    }

    @Override
    public void remove(Employee employee) {

    }

    @Override
    public void print() {
        System.out.println("-------------");
        System.out.print("Name = " + this.name);
        System.out.println("\t\tSalary = " + this.salary);
        System.out.println("-------------");
    }
}
