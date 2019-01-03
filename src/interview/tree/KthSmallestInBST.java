package interview.tree;

import java.util.Stack;

public class KthSmallestInBST {

    public static void main(String[] args) {
        /**
         *             25
         *           /   \
         *          8     30
         *         / \   /  \
         *        7  9  27  35
         *       /      /
         *      5      26
         */
        Node root = new Node(25);
        Node node10 = new Node(8);
        Node node30 = new Node(3);
        root.setLeft(node10);
        root.setRight(node30);

        Node node7 = new Node(7);
        Node node9 = new Node(9);
        node10.setLeft(node7);
        node10.setRight(node9);

        Node node27 = new Node(27);
        Node node35 = new Node(35);
        node30.setLeft(node27);
        node30.setRight(node35);

        Node node5 = new Node(5);
        node7.setLeft(node5);

        Node node26 = new Node(26);
        node27.setLeft(node26);

        System.out.println("2nd Smallest:" + findKthSmallest(root, 2));
        System.out.println("7th Smallest:" + findKthSmallest(root, 7));
        System.out.println("20th Smallest:" + findKthSmallest(root, 20));
    }

    private static int findKthSmallest(Node root, int k) {

        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (true) {
            if (current != null) {
                stack.push(current);
                current = current.getLeft();
            } else {
                if (stack.isEmpty()) {
                    break;
                }
                current = stack.pop();
                k--;
                if (k == 0) {
                    return current.getData();
                }
                current = current.getRight();
            }
        }
        return -1;
    }
}
