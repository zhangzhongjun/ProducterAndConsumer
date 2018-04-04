package com.zhong.BlockingQueueModel;

import java.util.Collection;

/**
 * @author 张中俊
 * @create 2018-03-31 10:14
 **/
public class Task_material {
    private String keyword;
    private Collection<String> filenames;

    public Task_material(String keyword, Collection<String> filenames) {
        this.keyword = keyword;
        this.filenames = filenames;
    }

    public String getKeyword() {
        return keyword;
    }

    public Collection<String> getFilenames() {
        return filenames;
    }
}
