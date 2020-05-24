package com.teradata.FourRefer;

import com.teradata.collection.Animal;
import com.teradata.collection.Cat;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by HE31 on 2020/4/13.
 */
public class FourReferTest {

   public static void main(String[] args){
       //强引用，等所有对象引用失效后，new Object()的内存才会被回收
       Object o1 = new Object();
       Object o2 =o1;

       //弱引用  第二次GC后才会被清除，清除对象为标识为回收垃圾的对象
       Cat cat = new Cat();
       cat.setFourFeet("123");
       WeakReference<Cat> wf = new WeakReference<>(cat);
      System.out.println(wf.get());

      //软引用,会实现类似缓存效果，避免在复杂的真实来源查询数据，在内存抛出OOM时，GC回收
       Cat cat1 = new Cat();
       cat1.setFourFeet("456");
       SoftReference<Cat> sr = new SoftReference<>(cat1);
       System.out.println(sr.get());

       //虚引用,判断对象是否被回收，get()方法得到为null，亦被称为幽灵引用，用于判断对象是否被回收
       Cat cat2 = new Cat();
       cat2.setFourFeet("789");
       PhantomReference<Cat> pr = new PhantomReference<>(cat2,new ReferenceQueue<>());
       System.out.println(pr.get());
       System.out.println(pr.isEnqueued());


   }
}
