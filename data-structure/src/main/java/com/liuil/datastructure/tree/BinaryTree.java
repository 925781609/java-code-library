package com.liuil.datastructure.tree;

/**
 * 二叉树
 *
 * @param <T>
 */
public class BinaryTree<T> {
    T data;
    BinaryTree left;
    BinaryTree right;

    public BinaryTree(T data, BinaryTree left, BinaryTree right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
