package com.gobue.blink.algo.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeDepth {

    /**
     * 返回树的深度
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return left > right ? left + 1 : right + 1;
    }

    public static void main(String[] args) {


    }
}
