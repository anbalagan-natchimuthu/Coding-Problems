package interview.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.geeksforgeeks.org/convert-ternary-expression-binary-tree/
 */
public class ConvertTernaryOperator {

  // Function to convert Ternary Expression to a Binary
  // Tree. It return the root of tree
  CharNode convertExpression(String str) {
    return convertExpression(str.toCharArray(), 0, str.length() - 1);
  }

  CharNode convertExpression(char str[], int s, int e) {

    if (s > e) {
      return null;
    }

    if (s == e) {
      return new CharNode(str[s]);
    }

    CharNode root = new CharNode(str[s]);

    root.right = new CharNode(str[e]);

    root.left = convertExpression(str, s + 2, e - 2);

    return root;
  }

  // function print tree
  public void printTree(CharNode root) {

    Queue<CharNode> q = new LinkedList<>();
    q.add(root);

    while (!q.isEmpty()) {
      int size = q.size();
      while (size-- > 0) {
        CharNode temp = q.remove();
        System.out.print(temp.data + " ");

        if (temp.left != null) {
          q.add(temp.left);
        }

        if (temp.right != null) {
          q.add(temp.right);
        }
      }
      System.out.println();
    }
  }

  // Driver program to test above function
  public static void main(String args[]) {
    String exp = "a?b?c:d:e";
    ConvertTernaryOperator tree = new ConvertTernaryOperator();
    CharNode root = tree.convertExpression(exp);
    tree.printTree(root);
  }
}

// Class to represent Tree node
class CharNode {

  char data;

  CharNode left;
  CharNode right;

  public CharNode(char item) {
    data = item;
    left = null;
    right = null;
  }
}
