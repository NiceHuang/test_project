package cn.hnx.pattern.builder;

/**
 * Created by viruser on 2019/9/24.
 */
public class Fruit {

    private String name;

    private String color;

    private double price;

    private double weight;

    public Fruit() {
    }

    public Fruit(FruitBuilder builder) {
        this.name = builder.name;
        this.color = builder.color;
        this.price = builder.price;
        this.weight = builder.weight;
    }


    protected static class FruitBuilder{
        private String name;

        private String color;

        private double price;

        private double weight;


        protected FruitBuilder name(String name){
            this.name = name;
            return this;
        }

        protected FruitBuilder color(String color){
            this.color = color;
            return this;
        }

        protected FruitBuilder price(double price){
            this.price = price;
            return this;
        }

        protected FruitBuilder weight(double weight){
            this.weight = weight;
            return this;
        }

        protected Fruit build() {
            return new Fruit(this);
        }
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }
}
