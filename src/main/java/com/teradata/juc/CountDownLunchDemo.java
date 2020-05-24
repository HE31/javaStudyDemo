package com.teradata.juc;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * 使一个线程等待其他线程执行完后再执行
 * Created by HE31 on 2020/3/31.
 */
public class CountDownLunchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        IntStream.range(1,6).forEach(
                (i)->{
                    new Thread(()->{

                        System.out.println("线程"+i+"执行完毕");
                        countDownLatch.countDown();
                    },String.valueOf(i)).start();
                }
        );
        countDownLatch.await();
        System.out.println("main线程执行完毕");
    }
}
