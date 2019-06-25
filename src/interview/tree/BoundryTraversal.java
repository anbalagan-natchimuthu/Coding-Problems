package interview.tree;

/**
 * PROBLEM: 1
 * https://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/
 *
 * Given a binary tree, print boundary nodes of the binary tree Anti-Clockwise starting from the root.
 * For example, boundary traversal of the following tree is “20 8 4 10 14 25 22”
 */
class BoundryTraversal {
    Node root;

    // A simple function to print leaf nodes of a binary tree
    void printLeaves(Node node) {
        if (node != null) {
            printLeaves(node.left);

            // Print it if it is a leaf node
            if (node.left == null && node.right == null) {
                System.out.print(node.data + " ");
            }
            printLeaves(node.right);
        }
    }

    // A function to print all left boundry nodes, except a leaf node.
    // Print the nodes in TOP DOWN manner
    void printBoundaryLeft(Node node) {
        if (node != null) {
            if (node.left != null) {

                // to ensure top down order, print the node
                // before calling itself for left subtree
                System.out.print(node.data + " ");
                printBoundaryLeft(node.left);
            } else if (node.right != null) {
                System.out.print(node.data + " ");
                printBoundaryLeft(node.right);
            }

            // do nothing if it is a leaf node, this way we avoid
            // duplicates in output
        }
    }

    // A function to print all right boundry nodes, except a leaf node
    // Print the nodes in BOTTOM UP manner
    void printBoundaryRight(Node node) {
        if (node != null) {
            if (node.right != null) {
                // to ensure bottom up order, first call for right
                // subtree, then print this node
                printBoundaryRight(node.right);
                System.out.print(node.data + " ");
            } else if (node.left != null) {
                printBoundaryRight(node.left);
                System.out.print(node.data + " ");
            }
            // do nothing if it is a leaf node, this way we avoid
            // duplicates in output
        }
    }

    // A function to do boundary traversal of a given binary tree
    void printBoundary(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");

            // Print the left boundary in top-down manner.
            printBoundaryLeft(node.left);

            // Print all leaf nodes
            printLeaves(node.left);
            printLeaves(node.right);

            // Print the right boundary in bottom-up manner
            printBoundaryRight(node.right);
        }
    }

    // Driver program to test above functions
    public static void main(String args[]) {
        //           20
        //          /  \
        //         8   22
        //        / \   \
        //       4   12  25
        //       \   /\
        //        6 10 14
        //       /
        //      5
        //      \
        //       7
        BoundryTraversal tree = new BoundryTraversal();
        tree.root = new Node(20);
        tree.root.left = new Node(8);
        tree.root.left.left = new Node(4);
        tree.root.left.left.right = new Node(6);
        tree.root.left.left.right.left = new Node(5);
        tree.root.left.left.right.left.right = new Node(7);
        tree.root.left.right = new Node(12);
        tree.root.left.right.left = new Node(10);
        tree.root.left.right.right = new Node(14);
        tree.root.right = new Node(22);
        tree.root.right.right = new Node(25);
        tree.printBoundary(tree.root);

        System.out.println("\n\n ***** Print Boundary traversal Clockwise  ************** ");
        tree.printClockwise(tree.root);
    }

    /** PROBLEM: 2
     * Given a binary tree, print boundary nodes of the binary tree Clockwise starting from the root.
     */
    private static void printClockwise(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.getData() + " ");

        printBoundaryRightUpDown(root.getRight());

        printBoundaryLeaves(root.getRight());
        printBoundaryLeaves(root.getLeft());

        printLeftBoundaryDownUp(root.getLeft());
    }

    private static void printBoundaryRightUpDown(Node root) {
        if (root != null) {
            if (root.getRight() != null) {
                System.out.print(root.getData() + " ");
                printBoundaryRightUpDown(root.getRight());
            } else if (root.getLeft() != null) {
                System.out.print(root.getData() + " ");
                printBoundaryRightUpDown(root.getLeft());
            }
        }
    }

    private static void printBoundaryLeaves(Node root) {
        if (root != null) {
            printBoundaryLeaves(root.right);
            if (root.right == null && root.left == null) {
                System.out.print(root.getData() + " ");
            }
            printBoundaryLeaves(root.left);
        }
    }

    private static void printLeftBoundaryDownUp(Node root) {
        if (root != null) {
            if (root.getLeft() != null) {
                printLeftBoundaryDownUp(root.getLeft());
                System.out.print(root.getData() + " ");
            } else if (root.getRight() != null) {
                printLeftBoundaryDownUp(root.getRight());
                System.out.print(root.getData() + " ");
            }
        }
    }
}
