package com.gobue.blink.algo.tree;

public class LeftLeavesSum {

    /**
     * 计算给定二叉树的所有左叶子之和。
     * <p>
     * 示例：
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * <p>
     * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sum-of-left-leaves
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        //叶子节点
        int leftSum = 0;
        TreeNode left = root.left;
        if (left != null && left.left == null && left.right == null) {
            leftSum = left.val;
        } else {
            leftSum = sumOfLeftLeaves(left);
        }

        return leftSum + sumOfLeftLeaves(root.right);
    }


}
