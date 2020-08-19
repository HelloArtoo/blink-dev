package com.gobue.blink.algo.tree;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BuildTree {

    //递归的方式1
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length < 1) {
            return null;
        }

        int root = preorder[0];
        int i = 0;
        for (; i < inorder.length; i++) {
            if (inorder[i] == root) {
                break;
            }
        }

        int[] leftInOrder = new int[i];
        int[] rightInOrder = new int[inorder.length - i - 1];
        System.arraycopy(inorder, 0, leftInOrder, 0, i);
        System.arraycopy(inorder, i + 1, rightInOrder, 0, inorder.length - i - 1);

        int[] leftPreOrder = new int[i];
        int[] rightPreOrder = new int[preorder.length - i - 1];
        System.arraycopy(preorder, 1, leftPreOrder, 0, i);
        System.arraycopy(preorder, 1 + i, rightPreOrder, 0, preorder.length - i - 1);

        TreeNode rootNode = new TreeNode(root);
        rootNode.left = buildTree(leftPreOrder, leftInOrder);
        rootNode.right = buildTree(rightPreOrder, rightInOrder);
        return rootNode;
    }


    static int pre = 0, in = 0;
    //递归方式2
    public static TreeNode buildTree2(int[] preorder, int[] inorder) {
        //Minvalue 避免匹配上
        return recursive(preorder, inorder, Integer.MIN_VALUE);
    }

    /**
     *
     * @param preorder
     * @param inorder
     * @param stop 截止节点(根节点)，用于在中序遍历中区分左右子树
     * @return
     */
    public static TreeNode recursive(int[] preorder, int[] inorder, int stop) {
        if(pre >= preorder.length) {
            return null;
        }

        if(inorder[in] == stop) {
            //找到分割点
            in++;
            return null;
        }

        int rootVal = preorder[pre++];
        TreeNode rootNode = new TreeNode(rootVal);
        rootNode.left = recursive(preorder, inorder, rootVal);
        rootNode.right = recursive(preorder, inorder, stop);
        return rootNode;
    }



    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        System.out.println(buildTree2(preorder, inorder));
    }

}
