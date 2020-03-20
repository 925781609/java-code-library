package com.liuil.datastructure;


/**
 * 反转链表
 */
public class ReverseLinkedList {


    /**
     * 定义链表的节点
     * 节点由数据和指向下一个节点的指针组成
     */
    static class Node {
        /**
         * 数据
         */
        public int data;
        /**
         * 指向下一个节点的指针
         */
        public Node next;

        Node() {
        }
    }

    static Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node pre = null;
        Node next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;

    }

    public static void main(String[] args) {
        Node three = new Node();
        three.data = 3;

        Node two = new Node();
        two.data = 2;
        two.next = three;

        Node one = new Node();
        one.data = 1;
        one.next = two;

        Node head = new Node();
        head.data = 0;
        head.next = one;

        Node newhead = reverse(head);

        Node tmp = newhead;
        while (tmp != null) {
            System.out.println(tmp.data);
            tmp = tmp.next;


        }

    }


}
