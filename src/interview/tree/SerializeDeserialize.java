package interview.tree;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserialize {

    public static void main(String[] args) {
          /* Create following Binary Tree
             1
           /  \
          2    3
         /  \
        7    4
             \
              5
               \
                6*/
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(4);
        root.left.right.right = new TreeNode(5);
        root.left.right.right.right = new TreeNode(6);

        SerializeDeserialize sd = new SerializeDeserialize();
        String searializedStr = sd.serializeTree(root);
        TreeNode desearializedNode = sd.deserializeTree(searializedStr);
        TreeNode.prettyPrintTree(desearializedNode);
    }

    /**
     * http://buttercola.blogspot.com/2015/10/leetcode-serialize-and-deserialize.html?_sm_au_=iWHrVQJLLZMKn3nM
     * @param root
     * @return
     */
    public String serializeTree(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode curr = queue.remove();
            if (curr != null) {
                builder.append(curr.data + ",");
                queue.offer(curr.left);
                queue.offer(curr.right);
            } else {
                builder.append("#,");
            }
        }

        // Remove the trailing #
        /*int j = builder.length() - 1;

        while (j > 0 && builder.charAt(j) == ',' && builder.charAt(j-1) == '#') {
            j -= 2;
        }

        String returnStr = builder.substring(0, j);*/
        String returnStr = builder.toString();
        System.out.println(returnStr);
        return returnStr;
    }

    public TreeNode deserializeTree(String inputString) {
        if (inputString == null || inputString.length() == 0) {
            return null;
        }

        String[] nodeArray = inputString.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(nodeArray[0]));

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        int i =1;

        while(!nodeQueue.isEmpty() && i < nodeArray.length) {
            TreeNode curr = nodeQueue.poll();

            // populate Left Node
            if (nodeArray[i].equals("#")) {
                curr.left = null;
            } else {
                TreeNode leftNode = new TreeNode(Integer.parseInt(nodeArray[i]));
                curr.left = leftNode;
                nodeQueue.offer(leftNode);
            }

            i++;

            if (i >=nodeArray.length) {
                break;
            }

            // populate Right node
            if (nodeArray[i].equals("#")) {
                curr.right = null;
            } else {
                TreeNode rightNode = new TreeNode(Integer.parseInt(nodeArray[i]));
                curr.right = rightNode;
                nodeQueue.offer(rightNode);
            }
            i++;
        }

        return root;
    }
}
