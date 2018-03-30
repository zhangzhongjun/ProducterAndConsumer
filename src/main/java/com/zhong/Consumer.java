package com.zhong;

import java.util.List;

/**
 * @author 张中俊
 * @create 2018-03-29 20:35
 **/
public class Consumer implements Runnable {
    private List<PCData> queue;

    public Consumer(List<PCData> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted())
                    break;
                PCData data = null;
                synchronized (queue) {
                    if (queue.size() == 0) {
                        queue.wait();
                        queue.notifyAll();
                    }
                    data = queue.remove(0);
                }
                System.out.println(
                        Thread.currentThread().getName() + " 消费了:" + data.get() + " result:" + (data.get() * data.get()));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}