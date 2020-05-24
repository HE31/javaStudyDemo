package com.teradata.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by HE31 on 2019/9/7.
 */
public class SimpleStream {

    public static void main(String[] args){

        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
                System.out.println(lowCalFood(menu));
    }

    public static List<String> lowCalFood(List<Dish> dishes){
        List<String> collect = dishes.stream().filter(dish -> dish.getCalories() < 400).sorted(Comparator.comparing(Dish::getCalories).reversed())
                .map(Dish::getName).parallel().collect(Collectors.toList());
        return collect;
    }

}
