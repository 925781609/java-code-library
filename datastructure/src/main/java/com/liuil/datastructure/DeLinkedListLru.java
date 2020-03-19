package com.liuil.datastructure;


import java.util.HashMap;
import java.util.Map;

class DoublyLinkedListLru {
    int capacity;
    DoublyLinkedList sequence;
    private Map<Integer, Node> cache;


    public DoublyLinkedListLru(int capacity) {
        this.capacity = capacity;
        this.sequence = new DoublyLinkedList();
        this.cache = new HashMap<>();
    }

    void put(int key, int value) {
        if (!cache.containsKey(key)) {
            Node node = new Node(key, value);
            cache.put(key, node);
            sequence.addToTail(node);

            if (cache.size() > capacity) {
                Node rmv = sequence.removeHead();
                cache.remove(rmv.key);
            }
        } else {
            Node n = cache.get(key);
            n.value = value;
            sequence.moveToTail(n);
        }
    }

    public int get(int key) {

        if (!cache.containsKey(key)) {
            return -1;
        }

        Node node = cache.get(key);
        sequence.moveToTail(node);
        return node.value;

    }


    class DoublyLinkedList {
        Node head;
        Node tail;

        DoublyLinkedList() {
        }


        void addToTail(Node node) {
            if (node == null) {
                throw new IllegalArgumentException();
            }
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.pre = tail;
                tail = node;
            }
        }

        void moveToTail(Node node) {
            if (node == null) {
                throw new IllegalArgumentException();
            }
            if (tail == node) {
                return;
            }
            if (head == node) {
                head = head.next;
                head.pre = null;
            } else {
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }
            tail.next = node;
            node.pre = tail;
            node.next = null;
            tail = tail.next;
        }

        Node removeHead() {
            Node node = head;
            if (head == null) {
                return node;
            }
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.pre = null;
            }
            return node;
        }


    }

    class Node {
        public int key;
        public int value;
        public Node pre;
        public Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }


}


public class DeLinkedListLru {

    public static void main(String[] args) {
        DoublyLinkedListLru lru = new DoublyLinkedListLru(3);
        lru.put(1, 1);
        lru.put(2, 2);
        lru.put(1, 1);
        lru.put(3, 3);
        lru.put(4, 4);
        System.out.println(lru.get(1));
        System.out.println(lru.get(2));
        System.out.println(lru.get(3));
        System.out.println(lru.get(4));

    }


}
