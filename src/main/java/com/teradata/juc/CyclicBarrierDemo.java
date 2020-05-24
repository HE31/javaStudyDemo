package com.teradata.juc;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/**
 * 线程累加 等执行完成的线程达到一定量之后再执行其他线程
 * Created by HE31 on 2020/3/31.
 */
public class CyclicBarrierDemo {
    public static void main(String[] args){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{System.out.println("召唤神龙");});
        IntStream.range(1,8).forEach((i)->{

            new Thread(()->{
                System.out.println("收集到第"+i+"颗龙珠");

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        });
    }
}
