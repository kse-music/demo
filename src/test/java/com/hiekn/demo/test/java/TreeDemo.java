package com.hiekn.demo.test.java;

/**
 * 二叉树就是这么简单
 * @since 2018-10-12 14:05:46
 */
public class TreeDemo {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(null,null,15);
        TreeNode node2 = new TreeNode(null,null,35);
        TreeNode node3 = new TreeNode(node1,node2,20);
        TreeNode node4 = new TreeNode(null,null,9);
        TreeNode rootTreeNode = new TreeNode(node4,node3,10);

        preTraverseBtree(rootTreeNode);

        System.out.println("-----");
        inTraverseBtree(rootTreeNode);

        System.out.println("-----");
        nextTraverseBtree(rootTreeNode);

    }

    /**
     * 先序遍历 ：根 -> 左 -> 右
     * @param rootTreeNode
     */
    public static void preTraverseBtree(TreeNode rootTreeNode){
        if(rootTreeNode != null){
            System.out.println(rootTreeNode.getValue());
            preTraverseBtree(rootTreeNode.getLeftTreeNode());
            preTraverseBtree(rootTreeNode.getRightTreeNode());
        }
    }

    /**
     * 中序遍历 ：左 -> 根 -> 右
     * @param rootTreeNode
     */
    public static void inTraverseBtree(TreeNode rootTreeNode){
        if(rootTreeNode != null){
            inTraverseBtree(rootTreeNode.getLeftTreeNode());
            System.out.println(rootTreeNode.getValue());
            inTraverseBtree(rootTreeNode.getRightTreeNode());
        }
    }

    /**
     * 后序遍历 ：左 -> 右 -> 根
     * @param rootTreeNode
     */
    public static void nextTraverseBtree(TreeNode rootTreeNode){
        if(rootTreeNode != null){
            nextTraverseBtree(rootTreeNode.getLeftTreeNode());
            nextTraverseBtree(rootTreeNode.getRightTreeNode());
            System.out.println(rootTreeNode.getValue());
        }
    }
}

class TreeNode{

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
