package com.wallaw.study.arithmetic.leetcode;

/**
 * @author: zhangzb
 * @date: 2020/2/25 11:36
 * @description: 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SumOfTwoList2 {

    public static void main(String[] args) {
        int[] arr1 = new int[]{5, 2, 3};
        ListNode l1 = createListNode(arr1);
        int[] arr2 = new int[]{1, 7, 6};
        ListNode l2 = createListNode(arr2);

        System.out.println(toPositiveOrder(l1));
        System.out.println(toPositiveOrder(l2));
        ListNode resultList = addTwoNumbers(l1, l2);
        System.out.println(toPositiveOrder(resultList));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Integer v1 = toPositiveOrder(l1);
        Integer v2 = toPositiveOrder(l2);

        int total = v1 + v2;

        ListNode head = null;
        ListNode current = null;
        while (total > 0) {
            ListNode node = new ListNode(total % 10);
            if (current == null) {
                head = node;
                current = node;
            } else {
                current.next = node;
                current = current.next;
            }
            total = total / 10;
        }
        return head;
    }

    public static Integer toPositiveOrder(ListNode node) {
        StringBuffer sb = new StringBuffer();

        while (node != null) {
            sb.append(node.val);
            if (node.next != null) {
                node = node.next;
            } else {
                break;
            }
        }

        sb.reverse();
        return Integer.parseInt(sb.toString());
    }

    public static ListNode createListNode(int[] array) {
        ListNode head = null;
        ListNode currentNode = null;
        if (array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                ListNode listNode = new ListNode(array[i]);
                if (i == 0) {
                    head = listNode;
                    currentNode = listNode;
                } else {
                    currentNode.next = listNode;
                    currentNode = currentNode.next;
                }
            }
        }
        System.out.println("LIST1:" + head);

        return head;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

/**
 * note：
 * Map的遍历问题， 构造list链表需要单独放头节点， 循环的时候一定要加上破循环的条件
 */
