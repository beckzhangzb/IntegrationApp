package com.aa.work.mergefile;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 读取文件
 */
public class MyCallable implements Callable {
    private static final String SEPERATOR_REGEX = ",";
    private static final int ID_INDEX = 0;
    private static final int GROUP_ID_INDEX = 1;
    private static final int QUOTA_INDEX = 2;

    private BlockingQueue<GroupQuota> taskQueue;
    private AtomicInteger produceCount;
    private String filepath;

    public MyCallable(BlockingQueue<GroupQuota> taskQueue, AtomicInteger produceCount, String filepath) {
        this.taskQueue = taskQueue;
        this.produceCount = produceCount;
        this.filepath = filepath;
    }

    @Override
    public Object call() throws Exception {
        // 执行完一次即为计数一次
        readFromFile(filepath);
        return 1;
    }

    private void readFromFile(String absolutepath) throws IOException {
        File inputFile = new File(absolutepath);
        InputStream is = null;
        Scanner scanner = null;
        try {
            is = new FileInputStream(inputFile);
            scanner = new Scanner(is);
            while (scanner.hasNextLine()) {
                String value = scanner.next();
                if (value != null || !"".equals(value.trim())) {
                    String[] array = value.split(SEPERATOR_REGEX);

                    GroupQuota groupQuota = new GroupQuota();
                    groupQuota.setId(array[ID_INDEX]);
                    groupQuota.setGroupId(array[GROUP_ID_INDEX]);
                    groupQuota.setQuota(Float.parseFloat(array[QUOTA_INDEX]));

                    taskQueue.add(groupQuota);
                    produceCount.getAndIncrement();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
