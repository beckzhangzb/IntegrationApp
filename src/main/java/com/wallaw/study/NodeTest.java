package com.wallaw.study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: zhangzb
 * @date: 2020/10/26 11:40
 * @description:  node 上存的value 是大于0 小于10的正整数
 */
public class NodeTest {
    public static void main(String[] args) {

        //4>5>6
        Node node1 = new Node(4);
        Node n2 = new Node(5);
        Node n3 = new Node(6);
        node1.setNext(n2);
        n2.setNext(n3);


        //4>6>7>9
        Node node2 = new Node(4);
        Node no2 = new Node(6);
        Node no3 = new Node(7);
        Node no4 = new Node(9);
        node2.setNext(no2);
        no2.setNext(no3);
        no3.setNext(no4);


        //结果5>1>3>5
        Node node3 = add(node1, node2);
        myPrint(node3);
    }

    private static void myPrint(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.value);
        myPrint(node.next);
    }

    public static Node add(Node node1, Node node2) {
        if (node1 == null) return node2;
        if (node2 == null) return node1;

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        chageToList(list1, node1);
        chageToList(list2, node2);
        Collections.reverse(list1);
        Collections.reverse(list2);

        boolean isList1 = true;
        if (list1.size() < list2.size()) {
            isList1 = false;
        }

        List<Integer> resultList = new ArrayList();
        if (isList1) {
            mergeValueList(list1, list2, resultList);
        } else {
            mergeValueList(list2, list1, resultList);
        }

        Node node3 = chageToNote(resultList);
        return node3;
    }

    private static Node chageToNote(List<Integer> resultList) {
        Node start = new Node();
        Node head = null;
        for (int i = resultList.size() - 1; i>=0 ; i--) {
            if (i == resultList.size() - 1) {
                start = new Node();
                start.setValue(resultList.get(i));
                head = start;
            } else {
                Node node = new Node();
                node.setValue(resultList.get(i));
                start.next = node;
                start = node;
            }
        }
        return head;
    }

    /**
     * node 上存的value 是大于0 小于10的正整数
     */
    private static void mergeValueList(List<Integer> list1, List<Integer> list2, List<Integer> resultList) {
        int flag = 0; // 进位标识
        int current = 0;
        for (int i = 0; i < list1.size(); i++) {

            if (i < list2.size()) {
                current = list1.get(i) + list2.get(i);
                if (current >= 10) {
                    current = current % 10;

                    resultList.add(current + flag);
                    flag = 1;
                } else {
                    resultList.add(current + flag);
                    flag = 0;
                }

            } else {
                if (list1.get(i) + flag >= 10) {
                    current = current % 10;
                    resultList.add(current);
                    flag = 1;
                } else {
                    resultList.add(list1.get(i) + flag);
                    flag = 0;
                }
            }
        }
    }

    private static List chageToList(List list, Node node) {
        list.add(node.value);
        while (node.next != null) {
            node = node.next;
            list.add(node.value);
        }
        return list;
    }
}

class Node {
    public Integer value;
    public Node next;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node() {
    }

    public Node(Integer value) {
        this.value = value;
    }
}

