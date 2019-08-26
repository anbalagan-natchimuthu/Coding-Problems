package interview.tree;

public class LargestSubTreeSum {

  // Structure of a tree node.
  static class Node {

    int key;
    Node left, right;
  }

  // Function to create new tree node.
  static Node newNode(int key) {
    Node temp = new Node();
    temp.key = key;
    temp.left = temp.right = null;
    return temp;
  }

  static int ans = Integer.MIN_VALUE;

  // Helper function to find largest subtree sum recursively.
  static int findLargestSubtreeSumUtil(Node root) {

    // If current node is null then return 0 to parent node.
    if (root == null) {
      return 0;
    }

    // Subtree sum rooted at current node.
    int currSum = root.key + findLargestSubtreeSumUtil(root.left) + findLargestSubtreeSumUtil(root.right);

    // Update answer if current subtree sum is greater than answer so far.
    ans = Math.max(ans, currSum);

    // Return current subtree sum to its parent node.
    return currSum;
  }

  // Function to find largest subtree sum.
  static int findLargestSubtreeSum(Node root) {
    // If tree does not exist, then answer is 0.
    if (root == null) {
      return 0;
    }

    // Call to recursive function to find maximum subtree sum.
    findLargestSubtreeSumUtil(root);

    return ans;
  }

  // Driver Code
  public static void main(String args[]) {
	/*
           1
          / \
         /	 \
       -2	    3
      / \	   / \
    /   \   /   \
   4	  5 -6	   4
	*/

    Node root = newNode(1);
    root.left = newNode(-2);
    root.right = newNode(3);
    root.left.left = newNode(4);
    root.left.right = newNode(5);
    root.right.left = newNode(-6);
    root.right.right = newNode(4);

    System.out.println(findLargestSubtreeSum(root));
  }
}
