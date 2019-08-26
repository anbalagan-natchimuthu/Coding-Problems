package interview.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

public class DeepestNode {

  /**
   * Given a binary tree, write a method to find and return its deepest node. Return null for an empty tree.
   * Example:
   *        1
   *       / \
   *      2   3     ==> deepest = 9
   *     / \ / \
   *    4  5 6  7
   *   / \
   *  8   9
   */
  public TreeNode findDeepest(TreeNode root) {
    if(root == null) return null;
    TreeNode curr = null;
    Queue<TreeNode> q =new LinkedList<TreeNode>();
    q.add(root);
    while(!q.isEmpty()) {
      curr = q.remove();
      if(curr.left != null) {
        q.add(curr.left);
      }
      if(curr.right != null) {
        q.add(curr.right);
      }
    }
    return curr;
  }

  public TreeNode findDeepestBigNode(TreeNode root) {

    if (root == null) {
      return null;
    }

    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);

    TreeMap<Integer, List<TreeNode>> map = new TreeMap<>(Collections.reverseOrder());
    int level = 1;

    while (!q.isEmpty()) {
      int size = q.size();

      while (size-->0) {
        TreeNode temp = q.remove();

        List<TreeNode> value = map.getOrDefault(level, new ArrayList<>());
        value.add(temp);
        map.put(level, value);

        if (temp.left !=null) {
          q.add(temp.left);
        }

        if (temp.right !=null) {
          q.add(temp.right);
        }
      }
      level++;
    }

    List<TreeNode> values = map.firstEntry().getValue();

    values.sort((v1, v2) -> (v2.data - v1.data));
    return values.get(0);

  }
}
