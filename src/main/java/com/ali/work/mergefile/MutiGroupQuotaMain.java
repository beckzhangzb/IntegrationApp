package com.ali.work.mergefile;

import com.ali.work.mergefile.ThreadPoolFactory.ComsumeThreadPoolFactory;
import com.ali.work.mergefile.ThreadPoolFactory.PoolFactory;
import com.ali.work.mergefile.ThreadPoolFactory.ProduceThreadPoolFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程并发分组求值
 * @author zzb
 */
public class MutiGroupQuotaMain {
    /**
     * 阻塞队列，存放待分组的分组对象
     */
    private static BlockingQueue<GroupQuota> taskQueue = new LinkedBlockingQueue<GroupQuota>();

    /**
     * 分组list
     */
    private static volatile Map<String, List<GroupQuota>> groupListMap = new ConcurrentHashMap();

    /**
     * 分组id map
     */
    private static volatile Map<String, String> groupIdMap = new ConcurrentHashMap();

    /**
     * 最小quota map
     */
    private static volatile Map<String, GroupQuota> groupQuotaMap = new ConcurrentHashMap();

    /**
     * 记录读取文件结果数的统计
     */
    private static volatile ProduceResult produceResult = new ProduceResult();

    /**
     * 生产及消费的计数
     */
    private volatile static AtomicInteger produceCount = new AtomicInteger(0);

    /**
     * 线程大小
     */
    private static final int PRODUCE_POOL_SIZE = 10;
    private static final int COMSUME_POOL_SIZE = 20;
    private static ExecutorService produceExecutor;
    private static ExecutorService comsumeExecutor;

    public static void main(String[] args) throws InterruptedException {
        final MutiGroupQuotaMain quotaOperate = new MutiGroupQuotaMain();

        PoolFactory produceThreadPoolFactory = new ProduceThreadPoolFactory();
        produceExecutor = produceThreadPoolFactory.buildExecutePool(PRODUCE_POOL_SIZE);
        PoolFactory comsumeThreadPoolFactory = new ComsumeThreadPoolFactory();
        comsumeExecutor = comsumeThreadPoolFactory.buildExecutePool(COMSUME_POOL_SIZE);

        /**
         * 文件存放路径
         */
        String path = "D:\\file\\work\\";
        final List<String> filepathArray = quotaOperate.readfile(path);
        produceResult.setFileNum(filepathArray.size());

        /**
         * 线程1读取文件数据入queue
         */
        if (filepathArray != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        readDataIntoQueue(filepathArray);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        /**
         * 线程2执行queue里的任务
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                quotaOperate.executeTask();
            }
        }).start();

        while (true) {
            if (produceResult.getTotalCount().intValue() == produceResult.getFileNum() && taskQueue.size() == 0 &&
                    produceCount.intValue() == 0) {
                // 等待消费线程执行完
                Thread.sleep(500);

                // 根据groupId 排序
                int[] a = sortGroupIds();

                System.out.println("=============== 合并以分组id升序打印 ===============");
                printGroupList(a);

                System.out.println("================= 分组中的最小quota ===============");
                printGroupQuota(a);

                break;
            }
        }
    }

    private static void printGroupQuota(int[] a) {
        for (int groupId : a) {
            if (groupQuotaMap.containsKey(String.valueOf(groupId))) {
                GroupQuota groupQuota = groupQuotaMap.get(String.valueOf(groupId));
                StringBuilder sb = new StringBuilder();
                sb.append(groupQuota.getId()).append(",")
                        .append(groupQuota.getGroupId()).append(",")
                        .append(groupQuota.getQuota());
                System.out.println(sb.toString());
            }
        }
    }

    private static int[] sortGroupIds() {
        int i = 0;
        int a[] = new int[groupIdMap.size()];
        for (Entry entry : groupIdMap.entrySet()) {
            a[i++] = Integer.parseInt((String) entry.getKey());
        }
        QuickSortUtils.quickSort(a, 0, a.length - 1);
        return a;
    }

    private static void printGroupList(int[] a) {
        for (int groupId : a) {
            if (groupListMap.containsKey(String.valueOf(groupId))) {
                List<GroupQuota> groupQuotaList = groupListMap.get(String.valueOf(groupId));
                Iterator it = groupQuotaList.iterator();
                while (it.hasNext()) {
                    GroupQuota groupQuotaElement = (GroupQuota) it.next();
                    StringBuilder sb = new StringBuilder();
                    sb.append(groupQuotaElement.getId()).append(",")
                            .append(groupQuotaElement.getGroupId()).append(",")
                            .append(groupQuotaElement.getQuota());
                    System.out.println(sb.toString());
                }
            }
        }
    }

    private static void readDataIntoQueue(List<String> filepathArray) throws InterruptedException, ExecutionException {
        for (final String filepath : filepathArray) {
            MyCallable myCallable = new MyCallable(taskQueue, produceCount, filepath);
            Future future = produceExecutor.submit(myCallable);

            if (future.get() != null) {
                produceResult.getTotalCount().getAndIncrement();
            }
        }
    }

    /**
     * 线程并发消费执行
     */
    private void executeTask(){
        try {
            while (true) {
                final GroupQuota groupQuota = taskQueue.poll();
                if (groupQuota != null) {
                    comsumeExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            if (groupListMap.containsKey(groupQuota.getGroupId())) {
                                groupListMap.get(groupQuota.getGroupId()).add(groupQuota);
                            } else {
                                List<GroupQuota> groupQuotaList = new ArrayList<GroupQuota>();
                                groupQuotaList.add(groupQuota);
                                groupListMap.put(groupQuota.getGroupId(), groupQuotaList);
                            }

                            // 分组id 放入 map 中
                            groupIdMap.put(groupQuota.getGroupId(), groupQuota.getGroupId());

                            if (groupQuotaMap.containsKey(groupQuota.getGroupId())) {
                                if (groupQuotaMap.get(groupQuota.getGroupId()).getQuota() > groupQuota.getQuota()) {
                                    groupQuotaMap.put(groupQuota.getGroupId(), groupQuota);
                                }
                            } else {
                                groupQuotaMap.put(groupQuota.getGroupId(), groupQuota);
                            }
                        }
                    });

                    produceCount.getAndDecrement();
                }

                if (produceResult.getTotalCount().intValue() == produceResult.getFileNum() && taskQueue.size() == 0 ) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> readfile(String filepath) {
        List<String> pathList = new ArrayList<String>();
        File file = new File(filepath);
        if (file.isDirectory()) {
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                File readfile = new File(filepath + "\\" + filelist[i]);
                if (!readfile.isDirectory()) {
                    pathList.add(readfile.getAbsolutePath());
                }
            }
        }

        return pathList;
    }
}
