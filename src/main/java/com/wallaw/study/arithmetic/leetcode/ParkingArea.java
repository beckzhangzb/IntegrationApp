package com.wallaw.study.arithmetic.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * enter 回车换行重新输入，控制台输入如下示例数据:
 * checkin -t=10 -n=AN1475
 * checkout -t=15 -n=AN1475
 * checkin -t=16 -n=AN1598
 * checkout -t=20 -n=AN1598
 * checkin -t=14 -n=AN2587
 * checkin -t=10 -n=AN1234
 * checkin -t=12 -n=AN1234
 * checkin -t=11 -n=AN2345
 * checkout -t=28 -n=AN2345
 * checkout -t=20 -n=AN2349
 * author: zzb
 * date: 2020-9-1 21:04:17
 */
public class ParkingArea {
    public static void main(String[] args) {
        // 从控制台接收数据
        Scanner scan = new Scanner(System.in);

        boolean circle = true;
        List<String> inputList = new ArrayList();
        System.out.println("输入end结束，请输入数据：");
        while (circle) {
            String inStr = scan.nextLine();
            System.out.println("输入为：" + inStr);

            if ("end".equals(inStr)) circle = false;
            inputList.add(inStr);
        }
        scan.close();

        List<String> records = buildOutRecords(inputList);

        if (records.size() > 0) {
            System.out.println("输出结果如下：");
            for (String record : records) {
                System.out.println(record);
            }
        }
    }

    private static List<String> buildOutRecords(List<String> inputList) {
        List<String> records = new ArrayList();
        if (inputList.size() > 0) {
            int count = 1;
            int checkInHour = 0;
            String checkInNumber = null;
            for (String str : inputList) {
                if (str.startsWith("checkin")) {
                    if (checkInNumber != null) { //说明前置输入为 驶入数据
                        records.add(fillStr(count, checkInNumber, checkInHour, 0));
                        count++;
                    }

                    String[] array = str.split(" ");
                    String inTemp = array[1].trim();
                    checkInHour = Integer.parseInt(inTemp.substring(3, inTemp.length()));
                    checkInNumber = array[2].trim().substring(3, array[2].trim().length());
                }

                if (str.startsWith("checkout")) {
                    if (checkInNumber == null) { // 说明没有前置 驶入记录，重置并继续
                        checkInHour = 0;
                        checkInNumber = null;
                        continue;
                    }

                    String[] array = str.split(" ");
                    String outTemp = array[1].trim();
                    int checkOutHour = Integer.parseInt(outTemp.substring(3, outTemp.length()));
                    String checkOutNumber = array[2].trim().substring(3, array[2].trim().length());

                    if (checkOutHour <= checkInHour || checkOutHour > 24 || !checkOutNumber.equals(checkInNumber)) {
                        // 时间不合法，车牌号不匹配，退出继续
                        checkInHour = 0;
                        checkInNumber = null;
                        continue;
                    }

                    records.add(fillStr(count, checkInNumber, checkInHour, checkOutHour));
                    checkInHour = 0;
                    checkInNumber = null;

                    count++;
                }
            }
        }
        return records;
    }

    private static String fillStr(int count, String number, int inHour, int outHour) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("record").append(count).append(":").append(number).append(" ").append(inHour).append(":00 in");

        if (outHour > 0) {
            buffer.append(" ").append(outHour).append(":00 out");
        }
        return buffer.toString();
    }
}
