package com.zhong;

/**
 * 基本数据类型。生成和消费的产品
 *
 * @author 张中俊
 * @create 2018-03-29 20:34
 **/
public class PCData {
    private long value;
    public void set(long value){
        this.value = value;

    }
    public long get(){
        return value;
    }
}
