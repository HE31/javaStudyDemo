package com.teradata;

import com.teradata.lambda.Apple;
import com.teradata.lambda.Dish;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by HE31 on 2019/9/8.
 */
public class AppleTest {


    @Test
    public void test1(){
        int[] a = {1,2,3,4};
        int[] array = Arrays.stream(a).filter(b -> b > 3).toArray();
        Arrays.stream(array).forEach(c ->System.out.println(c));
    }

    @Test
    public void test2(){
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
        List<String> collect = menu.stream().filter(dish -> dish.getCalories() > 500).map(Dish::getName).collect(Collectors.toList());
        collect.stream().forEach(dish ->System.out.println(dish));

        double asDouble = menu.stream().mapToInt(Dish::getCalories).average().getAsDouble();
        System.out.println(asDouble);

        Collection<Dish> emps=menu.stream().collect(Collectors.toCollection(ArrayList::new));

        IntSummaryStatistics collect1 = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));

        Map<Dish.Type, List<Dish>> collect2 = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(collect2);

        Map<Boolean, List<Dish>> collect3 = menu.stream().collect(Collectors.partitioningBy(dish -> dish.getCalories() > 300));
        System.out.println(collect3);
    }

    @Test
    public void test3(){
        String s = opTest(null);
        System.out.println(s);
    }

    public static String opTest(Apple apple){
        Optional<Apple> op = Optional.ofNullable(apple);
        Apple apple1 = op.orElse(new Apple("red", 12));
        return apple1.getColor();
    }

    @Test
    public  void test() throws IOException {
        File file  = new File("D:"+File.separator+"javademo"+File.separator+"test.txt");
        if (file.getParentFile().exists()){
            file.delete();
        }
        OutputStream outputStream =new FileOutputStream(file,true);
        String content = "hello world";
        outputStream.write(content.getBytes());
        outputStream.close();
    }

    @Test
    public void test4(){
        Map<String,String> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.forEach((k,v) ->{
           System.out.println(k);
           System.out.println(v);


        });

        List<Integer> list = Arrays.asList(1,2,3,4,5);
        Integer integer = list.stream().reduce(0, (x, y) -> Integer.sum(x, y));
        System.out.println(integer);
        List<String> collect = list.stream().map(x -> x + "x").collect(Collectors.toList());
        System.out.println(collect);
    }


}