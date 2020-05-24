package com.teradata.lambda;

import java.util.*;
import java.util.function.Predicate;

/**
 * Created by HE31 on 2019/12/1.
 */
public class ApplePredicate {


public static List<String> getAppleColors(List<Apple> apples, AppleFormatter appleFormatter){
    Set<String> set = new HashSet<>();
    for (Apple apple:apples){
        String s = appleFormatter.accept(apple);
        set.add(s);
    }
    return new ArrayList<>(set);
}

    public static void main(String[] args){
    List<Apple> appleList = Arrays.asList(new Apple("green",120),new Apple("red",150),
            new Apple("green",145));
        List<String> appleColors = getAppleColors(appleList, apple ->"color:"+ apple.getColor());
        System.out.println(appleColors);

        List<Apple> appleList1 = appleList;
        appleList1.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(appleList1);

        ColorPredicate colorPredicate = new ColorPredicate();
        Predicate<Apple> colorApple =
                colorPredicate.and(a -> a.getWeight() > 150)
                        .or(a -> "green".equals(a.getColor()));

    }
}



@FunctionalInterface
interface  AppleFormatter{
    String accept(Apple apple);
}

class ColorPredicate implements Predicate<Apple>{

    @Override
    public boolean test(Apple apple) {
        return false;
    }
}


