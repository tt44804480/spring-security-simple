package com.liuliuliu.conf;

import org.junit.Test;

/**
 * 对类的描述
 *
 * @author liutianyang
 * @since 1.0
 */
public class test2 {

    @Test
    public void test1(){
        String A = "A";
        String B = "B";
            Thread t1 = new Thread(new Runnable(){
                @Override
                public void run(){
                    synchronized (A){
                        try {
                            System.out.println("sleep start");
                            Thread.currentThread().sleep(5000);
                            System.out.println("sleep end");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized (B){
                            System.out.println("thread1 running");
                        }
                    }
                }
            });
            Thread t2 = new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B){
                        synchronized (A){
                            System.out.println("thread2 running");
                        }
                    }
                }
            });
            t1.setName("AAAAAAAAAAAAAAAAA");
            t2.setName("BBBBBBBBBBBBBBBBB");
            t1.start();
            t2.start();
        }
}
