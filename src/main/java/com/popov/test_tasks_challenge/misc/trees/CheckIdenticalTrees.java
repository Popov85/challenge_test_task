package com.popov.test_tasks_challenge.misc.trees;

/**
 * Check if two trees are identical, have the same structure and values in nodes.
 * @see <a href ="https://www.geeksforgeeks.org/write-c-code-to-determine-if-two-trees-are-identical/">Link</a>
 */
public class CheckIdenticalTrees {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);

        TreeNode left1 = new TreeNode(2);

        TreeNode right1 = new TreeNode(3);

        root1.left = left1;
        root1.right = right1;

        TreeNode root2 = new TreeNode(1);

        TreeNode left2 = new TreeNode(2);

        TreeNode right2 = new TreeNode(4);

        root2.left = left2;
        root2.right = right2;

        boolean result = new CheckIdenticalTrees().identicalTrees(root1, root2);

        System.out.println("Is identical ="+result);

    }
    private static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }

    public boolean identicalTrees(TreeNode a, TreeNode b) {
        /*1. both empty */
        if (a == null && b == null)
            return true;

        /* 2. both non-empty -> compare them */
        if (a != null && b != null)
            return (a.val == b.val
                    && identicalTrees(a.left, b.left)
                    && identicalTrees(a.right, b.right));

        /* 3. one empty, one not -> false */
        return false;
    }
}
