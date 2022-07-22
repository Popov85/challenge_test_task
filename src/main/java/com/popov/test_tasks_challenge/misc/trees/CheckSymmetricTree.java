package com.popov.test_tasks_challenge.misc.trees;

/**
 * Check if a tree is symmetric
 * @see <a href="https://www.techiedelight.com/ru/check-given-binary-tree-symmetric-structure-not/">Link</a>
 */
public class CheckSymmetricTree {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        //Makes tree asymmetric!
        TreeNode rightRight = new TreeNode(4);
        root.left = left;
        root.right = right;
        //Makes tree asymmetric!
        right.right = rightRight;

        boolean treeSymmetric =
                new CheckSymmetricTree().isTreeSymmetric(root);
        System.out.println("Result = "+treeSymmetric);
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


    public boolean isTreeSymmetric(TreeNode root) {
        // базовый вариант
        if (root == null) return true;
        // возвращаем true, если левое и правое поддеревья отражают друг друга
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode X, TreeNode Y) {
        // базовый случай: если оба дерева пусты
        if (X == null && Y == null) {
            return true;
        }
        // вернуть истину, если
        // 1. Оба дерева непусты, и
        // 2. Левое поддерево является зеркалом правого поддерева, и
        // 3. Правое поддерево является зеркалом левого поддерева
        return (X != null && Y != null) &&
                isSymmetric(X.left, Y.right) &&
                isSymmetric(X.right, Y.left);
    }
}
