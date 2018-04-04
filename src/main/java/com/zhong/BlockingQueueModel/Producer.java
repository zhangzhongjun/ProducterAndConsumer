package com.zhong.BlockingQueueModel;

public interface Producer {
    void produce() throws InterruptedException;
}
