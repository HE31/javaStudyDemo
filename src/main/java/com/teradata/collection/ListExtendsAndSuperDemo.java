package com.teradata.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HE31 on 2020/4/3.
 */
public class ListExtendsAndSuperDemo {
    public static void main(String[] args){
        List<Animal> animal = new ArrayList<>(10);
        List<Cat> cat = new ArrayList<>(10);
        List<Garfield> garfields = new ArrayList<>(10);
        animal.add(new Animal());
        cat.add(new Cat());
        garfields.add(new Garfield());

        //第二段：测试赋值操作
        //下行测试报错，只能赋值cat或cat子类
       // List<? extends Cat> extendCat = animal;
        List<? super Cat> superCatAnimal = animal;
        List<? extends Cat> extendsCatCat = cat;
        List<? super Cat> superCatCat = cat;
        List<? extends Cat> extendsCatGar = garfields;
        //下行报错，只能为cat或其父类
       // List<? super Cat> superCatGar = garfields;


        //第三段:测试add方法
        //下面三行均编译出错，无法进行add操作
        //extendsCatCat.add(new Animal());
        //extendsCatCat.add(new Cat());
        //extendsCatCat.add(new Garfield());

        //下行编译出错，只能add cat或其子类
        //superCatCat.add(new Animal());
        superCatCat.add(new Cat());
        superCatCat.add(new Garfield());

        //第四段：测试get方法
        Cat cat1 = extendsCatCat.get(0);
        Object cat2 = extendsCatCat.get(0);

        //下行编译出错，虽然cat集合从gar赋值而来，但是类型擦除后，是不知道的
        //Garfield garfield = extendsCatGar.get(0);

        //所有super都能返回元素，但是翻新丢失
        Object object = superCatAnimal.get(0);
        Object object1 = superCatCat.get(0);
    }

}
