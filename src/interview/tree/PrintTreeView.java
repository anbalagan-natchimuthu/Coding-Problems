package interview.tree;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 *  https://www.geeksforgeeks.org/print-right-view-binary-tree-2/
 *
 */
public class PrintTreeView {


    public static void main(String[] args) {
        /* Create following Binary Tree For Left View
             1
           /  \
          2    3
           \    \
            4    6
           /     /
          5     7
               /
              8
                */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.right = new Node(4);
        root.left.right.left = new Node(5);
        root.right.right = new Node(6);
        root.right.right.left = new Node(7);
        root.right.right.left.left = new Node(8);
        PrintTreeView t = new PrintTreeView();

        System.out.println("****** Following are nodes in Left view of Binary Tree *****");
        t.leftView(root, 1);
        System.out.println();

        System.out.println("****** Following are nodes in Top view of Binary Tree *****");
        t.printTopView(root);

         /* Create following Binary Tree For Left View
             1
           /  \
          2    3
           \    \
            4    6
           /
          5
                */

        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.left.right = new Node(4);
        root1.left.right.left = new Node(5);
        root1.right.right = new Node(6);

        System.out.println("****** Following are nodes in Right view of Binary Tree *****");
        t.rightView(root1);

    }

    // Recursive function to print right view of a binary tree.
    void rightViewUtil(Node node, int level, Max_level max_level) {

        // Base Case
        if (node == null) {
            return;
        }

        // If this is the last Node of its level
        if (max_level.max_level < level) {
            System.out.print(node.data + " ");
            max_level.max_level = level;
        }

        // Recur for right subtree first, then left subtree
        rightViewUtil(node.right, level + 1, max_level);
        rightViewUtil(node.left, level + 1, max_level);
    }

    // A wrapper over rightViewUtil()
    void rightView(Node node) {

        Max_level max = new Max_level();
        rightViewUtil(node, 1, max);
        System.out.println();
    }

    // class to access maximum level by reference
    class Max_level {

        int max_level;
    }

    void printTopView(Node root) {
        class QueueObject {
            int horizontalDistance;
            Node node;

            QueueObject(int horizontalDistance,  Node node) {
                this.horizontalDistance = horizontalDistance;
                this.node = node;
            }
        }

        Queue<QueueObject> queue = new LinkedList<>();
        queue.add(new QueueObject(0, root));

        // map to maintain unique position
        Map<Integer, Node> treeMap = new TreeMap<>();

        while(!queue.isEmpty()) {
            QueueObject queueObject = queue.remove();

            if (!treeMap.containsKey(queueObject.horizontalDistance)) {
                treeMap.put(queueObject.horizontalDistance, queueObject.node);
            }

            if (queueObject.node.getLeft() != null) {
                queue.add(new QueueObject(queueObject.horizontalDistance -1, queueObject.node.getLeft()));
            }

            if (queueObject.node.getRight() != null) {
                queue.add(new QueueObject(queueObject.horizontalDistance +1, queueObject.node.getRight()));
            }
        }

        for (Map.Entry<Integer, Node> entry : treeMap.entrySet()) {
            System.out.print(entry.getValue().getData() + " ");
        }

        //treeMap.forEach((k, v) -> System.out.println(v.getData()));

        System.out.println();
    }

    private static int  max_level = 0;
    private void leftView(Node root, int level) {
        // Base Case
        if (root == null) {
            return;
        }
        // If this is the first node of its level
        if (max_level < level)
        {
            System.out.print(" " + root.getData());
            max_level = level;
        }
        leftView(root.getLeft(), level + 1);
        leftView(root.getRight(), level + 1);
    }


}
