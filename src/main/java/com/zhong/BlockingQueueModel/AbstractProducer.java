package com.zhong.BlockingQueueModel;

/**
 * @author 张中俊
 * @create 2018-03-30 11:11
 **/
abstract class AbstractProducer implements Producer, Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

