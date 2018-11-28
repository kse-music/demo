package com.hiekn.demo.test.java.tree;

/**
 * @Author: DingHao
 * @Date: 2018/11/28 18:30
 */
public class TreeNode{

    private TreeNode leftTreeNode;
    private TreeNode rightTreeNode;
    private int value;

    public TreeNode(TreeNode leftTreeNode, TreeNode rightTreeNode, int value) {
        this.leftTreeNode = leftTreeNode;
        this.rightTreeNode = rightTreeNode;
        this.value = value;
    }

    public TreeNode getLeftTreeNode() {
        return leftTreeNode;
    }

    public void setLeftTreeNode(TreeNode leftTreeNode) {
        this.leftTreeNode = leftTreeNode;
    }

    public TreeNode getRightTreeNode() {
        return rightTreeNode;
    }

    public void setRightTreeNode(TreeNode rightTreeNode) {
        this.rightTreeNode = rightTreeNode;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}