package com.teradata.juc;

import java.util.concurrent.Callable;

/**
 * Created by HE31 on 2020/3/26.
 */
public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 2*2;
    }
}
