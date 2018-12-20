package com.wallaw.study.arithmetic.deepSearch;

/**
 * desc: 深度搜索实现全排序
 * 思想: 解决当前一步该如何做，再解决下一步如何做（下一步跟当前这一步方式相同，采用递归）;
 *       而每一步可能尝试多种数据，可以用循环来遍历。
 *       首先访问一个未被访问过的顶点作为起点，沿该顶点的边访问，当未访问到其他关联顶点，则回溯到上一个顶点，看是否还有其他分支点。
 * @author zhangzb
 * @since 2018/1/23 11:36
 */
public class TotalOrderByDepthFirstSearch {
    /**
     * 待全排列的数
     */
    static int   num   = 5;
    /**
     * 已处理的标记
     */
    static int[] book  = new int[num + 1];
    /**
     * 每一步存入的数据
     */
    static int[] array = new int[num + 1];

    public static void main(String[] args) {
        depthFirstSearch(1);
    }

    private static void depthFirstSearch(int step) {
        if (step == num + 1) {
            StringBuffer sb = new StringBuffer();
            for (int i = 1; i <= num; i++) {
                sb.append(array[i]);
            }
            System.out.println(sb.toString());
        }

        for (int j = 1; j <= num; j++) {
            if (book[j] == 0) {
                array[step] = j;
                book[j] = 1;
                depthFirstSearch(step + 1); // 处理下一步，递归调用，可以确保对应计数不丢失
                book[j] = 0; //完整一次递归结束，也得到结果，需要将对应标记位清零，方便重排
            }
        }
        return;
    }
}
