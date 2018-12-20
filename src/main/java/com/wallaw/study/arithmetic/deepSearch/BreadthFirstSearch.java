package com.wallaw.study.arithmetic.deepSearch;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 宽度优先搜索算法
 * 思想：一层一层的计算，将满足条件的数据放入队列
 *
 广搜需要一个队列，用于一层一层扩展，一个hashset，用于判重，一棵树（只求长度时不需要），用于存储整棵树。
 对于队列，可以用queue，也可以把vector 当做队列使用。当求长度时，有两种做法：
 1. 只用一个队列，但在状态结构体state_t 里增加一个整数字段step，表示走到当前状态用 了多少步，当碰到目标状态，直接输出step 即可。这个方案，可以很方便的变成A* 算法，把队列换成优先队列即可。
 2. 用两个队列，current, next，分别表示当前层次和下一层，另设一个全局整数level，表示层数（也即路径长度），当碰到目标状态，输出level 即可。这个方案，状态可以少一个字段，节省内存。
 *
 * @author zhangzb
 * @since 2018/1/23 14:58
 */
public class BreadthFirstSearch {

    public static void main(String[] args){
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        bfs.BFS();
    }

    private void BFS(){
        int[][] a    = new int[51][51]; //行列最大不超过50
        int[][] book = new int[51][51];
        int[][] next = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        int head=0,tail=0;
        int i,j,n,m,startx,starty,p,q,tx,ty,flag;
        Scanner scan = new Scanner(System.in);
        System.out.println("输入行列数,逗号隔开：");
        String input = scan.next();
        String[] array = input.split(",");
        n=Integer.parseInt(array[0]);
        m=Integer.parseInt(array[1]);
        System.out.println("输入行列值,每行以逗号隔开：");
        for(i=0;i<n;i++){
            input = scan.next();
            String[] array2 = input.split(",");
            for (j=0;j<m;j++){
                a[i][j]=Integer.parseInt(array2[j]);
            }
        }
        System.out.println("输入目的点,以逗号隔开：");
        String input2 = scan.next();
        String[] array2 = input.split(",");
        p=Integer.parseInt(array2[0]);
        q=Integer.parseInt(array2[1]);

        /**
         * 用链表来实现队列
         */
        List<Note> que = new LinkedList<Note>();
        Note note = new Note(0,0,0,0, false);
        note.SB.append("("+0+"_"+0+")");
        que.add(note);
        book[0][0] = 1;
        flag = 0;//用来标记是否到达目标点，0表示暂时没有到达，1表示到达
        tail++;

        while (head <tail){
            for (int k=0;k<=3;k++){
                tx = que.get(head).x + next[k][0];
                ty = que.get(head).y + next[k][1];

                //判断是否越界
                if (tx <0 || tx >= n || ty <0 || ty >= m)
                {
                    continue;
                }
                //判断是否是障碍物或者已经在路径中
                if (a[tx][ty] == 0 && book[tx][ty] == 0){
                    //把这个点标记位已经走过
                    //注意宽度搜索每个点只入队一次，所以和深搜不同，不需要将book数组还原
                    book[tx][ty] = 1;
                    //插入新的点到队列中
                    Note newNode = new Note(tx,ty,que.get(head).f,que.get(head).s + 1, false);
                    newNode.SB.append(que.get(head).SB);
                    newNode.SB.append(",("+tx+"_"+ty+")");
                    que.add(newNode);
                    tail++;
                }
                //如果到目标点了，停止扩展，任务结束，退出循环
                if (tx==p && ty == q){
                    flag =1;
                    que.get(que.size() - 1).setEnd(true);
                    break;
                }
            }
            /*if (flag == 1){
                break;
            }*/
            head++; //当一个点扩展结束后，head++才能对后面的点再进行遍历扩展
        }

        for (Note note2 : que){
            if (note2.isEnd){
                System.out.println(note2.s);
                System.out.println(note2.SB.toString());
            }
        }
    }
}

class Note {
    int x;//横坐标
    int y;//众坐标
    int f;//父亲在队列中的编号
    int s;//步数
    boolean isEnd;//是否到达目的点
    StringBuffer SB = new StringBuffer(); //存入线路坐标点

    public Note(int x, int y, int f, int s, boolean isEnd) {
        this.x = x;
        this.y = y;
        this.f = f;
        this.s = s;
        this.isEnd = isEnd;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }
}
