package com.gobue.blink.algo.tree;

public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        System.out.println("前序：");
        preTraversal(this);
        System.out.println();

        System.out.println("中序：");
        midTraversal(this);
        System.out.println();

        //后序遍历
        System.out.println("后序：");
        postTraversal(this);
        System.out.println();
        return "";
    }

    private void preTraversal(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        System.out.print(treeNode.val + " ");
        preTraversal(treeNode.left);
        preTraversal(treeNode.right);
    }

    private void midTraversal(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        midTraversal(treeNode.left);
        System.out.print(treeNode.val + " ");
        midTraversal(treeNode.right);
    }

    private void postTraversal(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        postTraversal(treeNode.left);
        postTraversal(treeNode.right);
        System.out.print(treeNode.val + " ");
    }
}
