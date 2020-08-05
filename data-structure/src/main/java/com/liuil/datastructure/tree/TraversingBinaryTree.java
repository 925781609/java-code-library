package com.liuil.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 遍历二叉树
 */
public class TraversingBinaryTree {


    /**
     * 深度优先遍历二叉树(基于递归)
     * 前序遍历（根节点在前，中、左、右）
     * 中序遍历（根节点在中间，左、中、右）
     * 后续遍历（根节点在最后，左、右、中）
     *
     * @param root
     */
    private static void depthFirstSearch(BinaryTree root) {
        if (root == null) {
            return;
        }

        // 遍历左边节点
        if (root.left != null) {
            depthFirstSearch(root.left);
        }

        // 访问右边节点
        if (root.right != null) {
            depthFirstSearch(root.right);
        }

        // 访问中间节点
        System.out.print(root.data);
        System.out.print(' ');
    }

    /**
     * 广度优先遍历
     *
     * @param root
     */
    private static void breadthFirstSearch(BinaryTree root) {
        Queue<BinaryTree> queue = new LinkedList();
        // 根节点进入队列
        queue.add(root);
        while (!queue.isEmpty()) {
            // 根节点出队列
            BinaryTree data = queue.poll();
            System.out.print(data.data);
            System.out.print(' ');

            // 如果根节点左节点不为空，左节点进队列
            if (data.left != null) {
                queue.add(data.left);
            }

            // 如果根节点右节点不为空，右节点进队列
            if (data.right != null) {
                queue.add(data.right);
            }
        }


    }

    /**
     * 构建二叉树
     *
     * @return
     */
    private static BinaryTree build() {
        BinaryTree one = new BinaryTree(1, null, null);
        BinaryTree three = new BinaryTree(3, null, null);
        BinaryTree two = new BinaryTree(2, one, three);
        BinaryTree six = new BinaryTree(6, null, null);
        BinaryTree seven = new BinaryTree(7, six, null);
        BinaryTree four = new BinaryTree(4, two, seven);
        return four;

    }

    public static void main(String[] args) {
        BinaryTree root = build();
        breadthFirstSearch(root);
    }
}
