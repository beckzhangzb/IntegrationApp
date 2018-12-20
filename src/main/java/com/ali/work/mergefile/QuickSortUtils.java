package com.ali.work.mergefile;

/**
 * @author zzb
 */
public class QuickSortUtils {

    /**
     * 快速排序
     * @param a  待排数组
     * @param low  地位
     * @param high 高位
     */
    public static void quickSort(int[] a, int low, int high) {
        int start = low;
        int end = high;
        int base = a[low];
        int tempIndex = low;
        while (low < high) {
            //没有找到,高位向前移动一位
            while (low < high && base <= a[high]) {
                high--;
            }

            //找到时,把找到的数赋值给低位所在位置
            a[tempIndex] = a[high];
            tempIndex = high;

            //没有找到，低位向后移动一位
            while(low < high && base >= a[low]) {
                low++;
            }

            //找到后赋值给高位所在位置
            a[tempIndex] = a[low];
            tempIndex = low;

            //直到循环结束  -->低位和高位重叠
            a[tempIndex] = base;
        }

        //对上面前后两个部分数列分别递归
        if (low - start > 1) {
            quickSort(a, start, tempIndex - 1);
        }
        if (end - high > 1) {
            quickSort(a, tempIndex + 1, end);
        }
    }
}
