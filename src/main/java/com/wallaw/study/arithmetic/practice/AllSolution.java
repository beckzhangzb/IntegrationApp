package com.wallaw.study.arithmetic.practice;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author: zhangzb
 * @date: 2020/6/9 10:40
 * @description:
 */
public class AllSolution {

    ArrayList<String> res = new ArrayList<String>();

    public static void main(String[] agrs) {
        AllSolution allSolution = new AllSolution();

        ArrayList<String> result = allSolution.permutation("abcd");

        System.out.println(JSONObject.toJSONString(result));
    }

    public ArrayList<String> permutation(String str) {
        if(str == null)
            return res;
        permutationHelper(str.toCharArray(), 0);
        Collections.sort(res);

        return res;
    }
    public void permutationHelper(char[] str, int i){
        if(i == str.length - 1){
            res.add(String.valueOf(str));
        }else{
            for(int j = i; j < str.length; j++){
                if(j!=i && str[i] == str[j])
                    continue;
                char temp = str[i];
                str[i] = str[j];
                str[j] = temp;

                permutationHelper(str, i+1);

                // 交换之后，还原数组
                char temp2 = str[i];
                str[i] = str[j];
                str[j] = temp2;
            }
        }
    }
    public void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }
}
