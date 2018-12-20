package com.ali.work.mergefile;

import java.util.concurrent.atomic.AtomicInteger;

public class ProduceResult {

    /**
     * 生产线程任务的计数
     */
    private volatile AtomicInteger totalCount = new AtomicInteger();

    /**
     * 文件个数
     */
    private int fileNum;

    public AtomicInteger getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(AtomicInteger totalCount) {
        this.totalCount = totalCount;
    }

    public int getFileNum() {
        return fileNum;
    }

    public void setFileNum(int fileNum) {
        this.fileNum = fileNum;
    }
}
