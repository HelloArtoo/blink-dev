package com.gobue.blink.algo.tree;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 * <p>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <p>
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SymmetricTree {

    /**
     * 递归判断
     *
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        return isMirrorTree(root, root);
    }

    private static boolean isMirrorTree(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        }

        if (left.val != right.val) {
            return false;
        }

        return isMirrorTree(left.left, right.right) && isMirrorTree(left.right, right.left);
    }

    public static void main(String[] args) {

    }
}
