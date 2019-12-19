package com.gobue.blink.algo.tree;


import java.util.*;


public class LevelOrderTraversal {


    /**
     * 题目1：
     * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
     * <p>
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回其层次遍历结果：
     * <p>
     * [
     * [3],
     * [9,20],
     * [15,7]
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * <p>
     * BFS遍历
     */
    public static List<List<Integer>> levelOrderBFS(TreeNode root) {
        List<List<Integer>> rst = new ArrayList<>();
        if (root == null) return rst;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        //visted set can mark the visted node
        while (queue.size() != 0) {
            List<Integer> levelList = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                levelList.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            rst.add(levelList);
        }
        return rst;
    }

    /**
     * 深度优先遍历，DFS(递归)
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderDFS(TreeNode root) {
        List<List<Integer>> rst = new ArrayList<>();
        if (root == null) {
            return rst;
        }
        helper(root, rst, 0);
        return rst;
    }

    private static void helper(TreeNode node, List<List<Integer>> result, int level) {
        if (node == null) {
            return;
        }

        if (result.size() < level + 1) {
            result.add(new ArrayList<Integer>());
        }
        result.get(level).add(node.val);

        helper(node.left, result, level + 1);
        helper(node.right, result, level + 1);
    }


    //变种，如果要求列表顺序逆序

    /**
     * 题目二：
     * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     * <p>
     * 例如：
     * 给定二叉树 [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回其自底向上的层次遍历为：
     * <p>
     * [
     * [15,7],
     * [9,20],
     * [3]
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        //TODO也可以直接用ArrayList
        LinkedList<List<Integer>> lst = new LinkedList<>();
        if (root != null) {
            helperBottom(root, lst, 0);
        }
        return lst;
    }

    private static void helperBottom(TreeNode node, LinkedList<List<Integer>> lst, int level) {
        if (node == null) {
            return;
        }

        if (lst.size() < level + 1) {
            //如果用ArrayList用 add(index, object)的方法
            lst.addFirst(new ArrayList<Integer>());
        }

        lst.get(lst.size() - level - 1).add(node.val);
        helperBottom(node.left, lst, level + 1);
        helperBottom(node.right, lst, level + 1);
    }


    public static void main(String[] args) {
        List<List<Integer>> rst = new ArrayList<>();
        rst.add(0, Arrays.asList(1, 2));
        rst.add(0, Arrays.asList(3, 4));
        System.out.println(rst);


        LinkedList<Integer> lst = new LinkedList<Integer>();
        lst.addFirst(1);
        lst.addFirst(2);
        lst.addFirst(3);
        lst.addLast(4);
        lst.addLast(5);
        System.out.println(lst);
        List<List<Integer>> newLst = (List) lst;
        System.out.println(newLst);
    }


}
