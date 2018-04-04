package com.zhong.BlockingQueueModel;

/**
 * @author 张中俊
 * @create 2018-03-30 11:58
 **/
abstract class AbstractMaterial implements Material,Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                material();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
