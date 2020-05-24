package com.teradata.collection;

/**
 * Created by HE31 on 2020/4/3.
 */
public class Cat extends Animal {
    private String fourFeet;

    public String getFourFeet() {
        return fourFeet;
    }

    public Cat setFourFeet(String fourFeet) {
        this.fourFeet = fourFeet;
        return this;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "fourFeet='" + fourFeet + '\'' +
                '}';
    }
}
