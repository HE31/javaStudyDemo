package com.teradata;

import com.teradata.lambda.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Created by HE31 on 2019/9/7.
 */
public class Test {

    public interface AppleFilter{
        boolean filter(Apple apple);
    }

    public List<Apple> findApple(List<Apple> apples,AppleFilter appleFilter){
        List<Apple> list = new ArrayList<>();
        for (Apple apple:apples){
            if (appleFilter.filter(apple)){
                list.add(apple);
            }
        }
        return list;
    }
    public static void main(String[] args) throws InterruptedException {
        List<Apple> list = Arrays.asList(new Apple("red",120),
                new Apple("green",150),
                new Apple("red",160));
        Test test = new Test();
    /*    List<Apple> appleList = test.findApple(list, apple -> "green".equals(apple.getColor()));
        System.out.println(appleList);

         new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("普通线程");
                System.out.println(Thread.currentThread().getName());
            }
        }).start();

         new Thread(() ->{
             Thread.currentThread().setName("lambda线程");
             System.out.println(Thread.currentThread().getName());
         }).start();*/
        List<Apple> yellow = filterApple(list, apple -> apple.setColor("yellow")
        );
        System.out.println(yellow);

    }

    public static List<Apple> filterApple(List<Apple> apples,Function<Apple,Apple> fun){
        List<Apple> list = new ArrayList<>();
        for (Apple apple:apples){
            fun.apply(apple);
            list.add(apple);
        }
        return list;
    }
   /* public static void test() throws IOException {
        File file  = new File("D:"+File.pathSeparator+"javademo"+File.pathSeparator+"test.txt");
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        OutputStream outputStream =new  FileOutputStream(file,true);
        String content = "hello world";
        outputStream.write(content.getBytes());
        outputStream.close();
    }*/

}
