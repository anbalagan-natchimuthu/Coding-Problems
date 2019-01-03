package interview.tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {

    public TreeNode left;

    public TreeNode right;

    public int data;

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

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TreeNode(int data) {
        this.data = data;
    }

    public static void prettyPrintTree(TreeNode root) {
        if (root == null) {
            return;
        }
        int[] size = new int[1];
        size[0] = 0;
        int height = height(root, size) - 1;
        int total = 2 * (int) Math.pow(2, height) - 1;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        for (int i = 0; i <= height; i++) {
            int front = (int) (Math.pow(2, (height - i))) - 1;
            int num = (int) (Math.pow(2, i));
            int interval = num > 1 ? (total - front * 2 - num) / (num - 1) : 0;

            for (int j = 0; j < num; j++) {
                TreeNode peek = queue.poll();

                if (j == 0) {
                    print(front, size, peek);
                } else {
                    print(interval, size, peek);
                }

                if (peek == null) {
                    queue.offer(null);
                    queue.offer(null);
                } else {
                    queue.offer(peek.left);
                    queue.offer(peek.right);
                }
            }

            System.out.println();
        }
    }

    private static int height(TreeNode root, int[] size) {
        if (root == null) {
            return 0;
        }

        size[0] = Math.max(size[0], Integer.toString(root.data).length());
        return Math.max(height(root.left, size), height(root.right, size)) + 1;
    }

    private static void print(int count, int[] size, TreeNode root) {
        for (int i = 0; i < count * size[0]; i++) {
            System.out.print(" ");
        }

        if (root != null) {
            for (int j = 0; j < size[0] - Integer.toString(root.data).length(); j++) {
                System.out.print(" ");
            }

            System.out.print(root.data);
        } else {
            for (int j = 0; j < size[0]; j++) {
                System.out.print(" ");
            }
        }
    }

    public static TreeNode dummyTree() {
        /**
         *             4
         *           /   \
         *          2     7
         *         / \   / \
         *        1  3  5  8
         *              \
         *               6
         */
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);

        node4.left = node2;
        node2.left = node1;
        node2.right = node3;

        node4.right = node7;
        node7.left = node5;
        node5.right = node6;

        node7.right = node8;
        return node4;
    }
}