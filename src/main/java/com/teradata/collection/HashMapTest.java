package com.teradata.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HE31 on 2020/5/21.
 */
public class HashMapTest {
    public static void main(String[] args){
        Map<Integer,String> map = new HashMap<>();
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 1000_0000 ; i++) {
            map.put(i,"17826800154");
        }
        long l2 = System.currentTimeMillis();
        long l = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("占用内存大小："+(l/1024/1024));
        System.out.println("耗时："+(l2-l1)+"毫秒，map大小："+map.size());

    }
}
