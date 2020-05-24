package com.teradata.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

import static com.teradata.juc.ShareData.CNT_A;
import static com.teradata.juc.ShareData.CNT_B;
import static com.teradata.juc.ShareData.CNT_C;

/**
 * Created by HE31 on 2020/3/8.
 * 多线程之间按顺序调用，A->B->C
 * 三个线程启动，要求如下：
 * A打印5次，B10次，C15次
 * 来10轮
 */

class ShareData{
    /**
     *     1:A; 2:B; 3:C
     */
    private int flag = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    public final static int CNT_A = 5;
    public final static int CNT_B = 10;
    public final static int CNT_C = 15;

    public void printNum(int cnt){
        lock.lock();
        try {
       while (cnt == CNT_A){
           while (flag != 1){
               c1.await();
           }
           IntStream.range(1,cnt+1).forEach(i->{
               System.out.println(Thread.currentThread().getName()+":"+i);
           });
           flag = 2;
           c2.signal();
           break;
       }

            while (cnt == CNT_B) {
                while (flag != 2) {
                    c2.await();
                }
                IntStream.range(1, cnt + 1).forEach(i -> {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                });
                flag = 3;
                c3.signal();
                break;
            }
            while (cnt == CNT_C) {
                while (flag != 3) {
                    c3.await();
                }
                IntStream.range(1, cnt + 1).forEach(i -> {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                });
                flag = 1;
                c1.signal();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class MultiLockPractice {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            IntStream.range(1,11).forEach((i)->{
                shareData.printNum(CNT_A);
            });
        },"A").start();

        new Thread(() -> {
            IntStream.range(1,11).forEach((i)->{
                shareData.printNum(CNT_B);
            });
        },"B").start();

        new Thread(() -> {
            IntStream.range(1,11).forEach((i)->{
                shareData.printNum(CNT_C);
            });
        },"C").start();
    }

}
