package com.wallaw.study.arithmetic.leetcode;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @author: beckzzb
 * @date: 2020/2/25 10:40
 * @description:
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class SumOfTwoNumber1 {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 1,8, 11,12};
        twoSum(nums, 13);
    }

    public static int[] twoSum(int[] nums, int target) {
        List<Integer> array = new ArrayList();
        if (nums.length > 1) {
            Map<Integer, Integer> numMap = new HashMap<Integer, Integer>(nums.length);
            for (int i=0 ; i<nums.length; i++) {
                numMap.put(i, nums[i]);
            }

            while (numMap.size() > 1) {
                Iterator it = numMap.keySet().iterator();
                Integer key = -1, value = -1;
                while (it.hasNext()) {
                    Integer mapKey = (Integer) it.next();
                    if (key == -1) {
                        key = (Integer) mapKey;
                        value = (Integer) numMap.get(mapKey);
                    } else {
                        int temp = value + (Integer) numMap.get(mapKey);
                        if (temp == target) {
                            array.add(key);
                            array.add(mapKey);
                            numMap.remove(key);
                            numMap.remove(mapKey);
                            break;
                        }
                    }
                }
                numMap.remove(key);
            }
        }
        int[] sums = new int[array.size()];
        int i=0;
        for (Integer value: array) {
            sums[i++] = value;
        }
        System.out.println("result:" + JSONObject.toJSONString(sums));
        return sums;
    }
}
