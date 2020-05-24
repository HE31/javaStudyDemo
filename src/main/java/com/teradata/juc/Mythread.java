package com.teradata.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by HE31 on 2020/3/26.
 */
public class Mythread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new MyCallable());
    new Thread(futureTask,"test callable").start();
        Object o = futureTask.get();
        System.out.println(o);
    }
}
