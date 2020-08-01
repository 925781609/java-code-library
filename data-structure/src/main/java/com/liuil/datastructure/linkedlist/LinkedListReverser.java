package com.liuil.datastructure.linkedlist;


/**
 * 反转链表
 */
public class LinkedListReverser {


    static Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node pre = null;
        Node next = null;

        while (head != null) {
            // 保存下一节点
            next = head.next;
            // 当前节点指向前一节
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;

    }

    static void print(Node head) {
        while (head != null) {
            System.out.print(head.data);
            System.out.print(' ');
            head = head.next;
        }
        System.out.print('\n');

    }


    public static void main(String[] args) {


        Node three = new Node(3, null);

        Node two = new Node(2, three);

        Node one = new Node(1, two);

        Node head = new Node(0, one);

        print(head);

        Node newHead = reverse(head);

        print(newHead);


    }
}


