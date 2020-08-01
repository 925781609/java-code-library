package com.liuil.datastructure.linkedlist;

/**
 * 单链表的节点
 */
public class Node<T> {
    /**
     * 数据
     */
    public T data;
    /**
     * 指向下一个节点的指针
     */
    public Node next;

    public Node(T data, Node next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }
}