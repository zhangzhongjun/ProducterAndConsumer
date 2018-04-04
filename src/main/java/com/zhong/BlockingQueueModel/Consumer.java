package com.zhong.BlockingQueueModel;

public interface Consumer {
    void consume() throws InterruptedException;
}
