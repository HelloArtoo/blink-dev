package com.gobue.blink.algo.tree;

public class PathSum {

    /**
     * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例: 
     * 给定如下二叉树，以及目标和 sum = 22，
     * <p>
     * 5
     * / \
     * 4   8
     * /   / \
     * 11  13  4
     * /  \      \
     * 7    2      1
     * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/path-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        return helper(root, 0, sum);
    }

    private boolean helper(TreeNode node, int sum, int target) {
        if (node == null && sum == target) {
            return true;
        } else if (node == null) {
            return false;
        }

        if (node.left == null) {
            return helper(node.right, node.val + sum, target);
        }

        if (node.right == null) {
            return helper(node.left, node.val + sum, target);
        }

        return helper(node.left, node.val + sum, target) || helper(node.right, node.val + sum, target);
    }

    /**
     * 问题2
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null)
            return false;

        sum -= root.val;
        if ((root.left == null) && (root.right == null))
            return (sum == 0);
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

}
