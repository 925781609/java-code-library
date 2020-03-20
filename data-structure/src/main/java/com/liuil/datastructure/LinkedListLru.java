package com.liuil.datastructure;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 基于链表的lru， 查找和添加的时间复杂度都是O(n)
 * 链表尾表示最近被访问的元素，越靠近链表头表示越早之前被访问的元素
 */
class Lru {
    private int capacity;
    LinkedList<Node> cache;

    Lru(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedList<>();
    }

    /**
     * 插入元素， 不满插到链表尾，满， 移除链表头元素再插入到链表尾
     *
     * @param key
     * @param value
     */
    void put(int key, int value) {

        cache.removeIf(e -> e.key == key);

        if (cache.size() == capacity) {
            cache.remove(0);
        }

        cache.add(new Node(key, value));
    }

    /**
     * 访问元素，从链表尾开始遍历，访问之后，将其从原位置删除，重新放入链表尾
     *
     * @param key
     * @return
     */
    int get(int key) {
        // 从后往前查找， 复合LRU的思想
        Iterator<Node> iterator = cache.descendingIterator();
        int result = -1;
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (node.key == key) {
                result = node.value;
                iterator.remove();
                cache.add(new Node(key, node.value));
                break;
            }
        }
        return result;
    }

    class Node {
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}

public class LinkedListLru {
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


