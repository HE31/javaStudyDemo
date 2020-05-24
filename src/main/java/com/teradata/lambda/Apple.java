package com.teradata.lambda;

/**
 * Created by HE31 on 2019/9/7.
 */
public class Apple {
    private String color;
    private long weight;

    public Apple(String color, long weight) {
        this.color = color;
        this.weight = weight;
    }

    public Apple() {
    }

    public String getColor() {
        return color;
    }

    public Apple setColor(String color) {
        this.color = color;
        return this;
    }

    public long getWeight() {
        return weight;
    }

    public Apple setWeight(long weight) {
        this.weight = weight;
        return this;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
