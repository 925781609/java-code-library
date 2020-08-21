package com.liuil.datastructure.linkedlist;

/**
 * 求两个链表交叉节点
 */
public class IntersectionNode {


    /**
     * 求两个链表相交节点
     * 定义两个节点A、B，A从first遍历之后再从second开始，B从second遍历之后再从first开始
     * 最终A、B会在相交节点相遇
     *
     * @param first
     * @param second
     * @return
     */
    private static Node getIntersectionNode(Node first, Node second) {

        if (first == null || second == null) {
            return null;
        }

        //  因为等会还要重新使用first和second，所以暂时缓存起来
        Node A = first;
        Node B = second;

        while (A != B) {
            if (A.next != null) {
                A = A.next;
            } else {
                A = second;
            }

            if (B.next != null) {
                B = B.next;
            } else {
                B = first;
            }

        }
        return A;

    }


    public static void main(String[] args) {

        // 相交节点， 8 -> 4 -> 5
        Node node1 = new Node(5, null);
        Node node2 = new Node(4, node1);
        Node intersection = new Node(8, node2);

        // 第一个链表      4 -> 1 -> 8 -> 4 -> 5
        Node node4 = new Node(1, intersection);
        Node first = new Node(4, node4);

        // 第二个链表 5 -> 0 -> 1 -> 8 -> 4 -> 5
        Node node5 = new Node(1, intersection);
        Node node6 = new Node(0, node5);
        Node second = new Node(5, node6);

        Node node = getIntersectionNode(first, second);

        System.out.println(node);


    }


}
