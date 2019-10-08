package cn.hnx.pattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by viruser on 2019/9/24.
 */
public class Manager implements Employee {
    private String name;
    private String salary;

    private List<Employee> employees = new ArrayList<>(16);

    public Manager(String name, String salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public void add(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void remove(Employee employee) {
        employees.remove(employee);
    }

    @Override
    public void print() {
        System.out.println("-------------");
        System.out.print("Name = " + this.name);
        System.out.println("\t\tSalary = " + this.salary);
        System.out.println("-------------");

        for (Employee employee : employees) {
            employee.print();
        }
    }
}
