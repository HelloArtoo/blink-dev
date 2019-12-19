package com.gobue.blink.algo.tree;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidBST {

    public static boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    public static boolean helper(TreeNode root, Integer lower, Integer upper) {
        if (root == null) {
            return true;
        }

        int val = root.val;
        if (lower != null && val <= lower) {
            return false;
        }

        if (upper != null && val >= upper) {
            return false;
        }

        if (!helper(root.left, lower, val)) {
            return false;
        }

        if (!helper(root.right, val, upper)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("aaa");

    }
}
