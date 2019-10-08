package cn.hnx.pattern.prototype;

import java.util.List;

/**
 * Created by viruser on 2019/9/24.
 */
public class Prototype implements Cloneable {

    private String name;

    private Child child;

    private List<String> list;


    public Prototype() {
    }

    public Prototype(String name, Child child, List<String> list) {
        this.name = name;
        this.child = child;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    protected Prototype clone() throws CloneNotSupportedException {
        return (Prototype) super.clone();
    }

    @Override
    public String toString() {
        return "Prototype{" +
                "name='" + name + '\'' +
                ", child=" + child +
                ", list=" + list +
                '}';
    }
}
