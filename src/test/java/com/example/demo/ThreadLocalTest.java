package com.example.demo;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {

    @Test
    public void testThreadLocalSetAndGet() {
        ThreadLocal threadLocal = new ThreadLocal<>();
        new Thread(() -> {
            threadLocal.set("xiaoyan");
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        }, "blue").start();
        new Thread(() -> {
            threadLocal.set("yaochen");
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        }, "green").start();
    }
}
