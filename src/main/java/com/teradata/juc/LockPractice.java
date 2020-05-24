package com.teradata.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Created by HE31 on 2020/3/8.
 */

class Aircondiction{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition  = lock.newCondition();
    public void increment(){
        lock.lock();

        try {
            while (number != 0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+":"+number);
            condition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void decrement(){
        lock.lock();
        try {
           while (number == 0){
               condition.await();
           }
           number--;
           System.out.println(Thread.currentThread().getName()+":"+number);
           condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
public class LockPractice {

    public static void main(String[] args){

        Aircondiction aircondiction = new Aircondiction();
        new Thread(() ->{
            IntStream.range(0,10).forEach((i)->{
                aircondiction.increment();
            });
        },"A").start();

        new Thread(() ->{
            IntStream.range(0,10).forEach((i)->{
                aircondiction.decrement();
            });
        },"B").start();

    }
}
