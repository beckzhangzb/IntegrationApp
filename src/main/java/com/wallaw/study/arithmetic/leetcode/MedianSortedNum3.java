package com.wallaw.study.arithmetic.leetcode;

/**
 * @author: zhangzb
 * @date: 2020/2/26 17:09
 * @description:
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MedianSortedNum3 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int oddNum1 = nums1.length % 2;
        int oddNum2 = nums1.length % 2;

        int num1Length = nums1.length;
        int num2Length = nums1.length;
        int num1Mid = num1Length / 2, num2Mid = num2Length / 2;
        int index1 = num1Mid, index2 = num2Mid;

        if (nums1[index1] < nums2[index2]) {

        } else {

        }

        return 0;
    }
}
