package interview.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// Stack LIFO
// Queue FIFO
public class LeftToRightLevelPrint {

    /**
     *             1
     *           /   \
     *          2     3
     *         / \   / \
     *        4  5  6  7
     *        \
     *         8
     *        /
     *       9
     */

    public static void main(String[] args) {
        Node root = new Node(1);

        Node node2 = new Node(2);
        Node node3 = new Node(3);
        root.setLeft(node2);
        root.setRight(node3);

        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node2.setLeft(node4);
        node2.setRight(node5);

        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node3.setLeft(node6);
        node3.setRight(node7);

        Node node8 = new Node(8);
        node4.setRight(node8);

        Node node9 = new Node(9);
        node8.setLeft(node9);

        leftToRight(root);
        System.out.println();
        RightToLeftLevelPrint(root);
    }

    public static void RightToLeftLevelPrint(Node root) {

        Queue<Node> queue = new LinkedList<>();
        Stack<Node> stack = new Stack<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            Node temp = queue.remove();

            if (temp.getLeft() != null) {
                queue.add(temp.getLeft());
            }
            if (temp.getRight() != null) {
                queue.add(temp.getRight());
            }

            stack.push(temp);
        }

        System.out.println("******* Right to Left ********");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop().data + "  ");
        }
        System.out.println();
    }

    private static void leftToRight(Node root) {

        if (root == null) {
            return;
        }

        Queue<Node> que = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        que.add(root);

        while (!que.isEmpty()) {
            Node temp = que.remove();

            if (temp.getRight() != null) {
                que.add(temp.getRight());
            }
            if (temp.getLeft() != null) {
                que.add(temp.getLeft());
            }
            stack.push(temp);
        }
        System.out.println("******* Left to right ********");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop().getData() + "  ");
        }
        System.out.println();
    }
}
//1234567