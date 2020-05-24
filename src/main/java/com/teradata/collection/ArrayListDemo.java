package com.teradata.collection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HE31 on 2020/4/3.
 */
public class ArrayListDemo {
    public static void main(String[] args) {
        //泛型出现之前的集合定义方式
    List a1 = new ArrayList();
    a1.add(new Object());
    a1.add(new Integer(111));
    a1.add(new java.lang.String("a1a1"));

    //第二段:a1引用赋值给a2，增加泛型限制
        List<Object> a2 =a1;
        a2.add(new Object());
        a2.add(new Integer(222));
        a2.add(new String("a2a2"));

        //第三段：a1引用给a3,增加泛型<Integer>
        //打印如下：[java.lang.Object@14ae5a5, 111, a1a1, java.lang.Object@7f31245a, 222, a2a2, 333]
        List<Integer> a3 = a1;
        a3.add(new Integer(333));
        System.out.println(a3);

        //第四段：把a1引用赋值给a4，增加通配符
        List<?> a4 = a1;
      //  a1.removeIf(i -> "a2a2".equals(i));
        System.out.println(a1);
        a4.removeIf(i ->"a2a2".equals(i));
        System.out.println(a4);
        //a4.add(new Object());

    }
}
