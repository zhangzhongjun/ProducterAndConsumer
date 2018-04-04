package com.zhong.BlockingQueueModel;

/**
 * @author 张中俊
 * @create 2018-03-30 11:10
 **/
abstract class AbstractConsumer implements Consumer, Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}