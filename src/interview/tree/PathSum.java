package interview.tree;

import java.util.ArrayList;
import java.util.List;

public class PathSum {

  public static void main(String[] args) {
    /**
     *             4
     *           /   \
     *          2     7
     *         / \   / \
     *        1  3  5  8
     *              \
     *               6
     */

    ArrayList<Integer> path = new ArrayList<>();
    TreeNode treeNode = TreeNode.dummyTree();

    System.out.println("******* Calculate path for the given sum *******");
    calcPaths(treeNode, path, 0, 19, 0);

    System.out.println("******* populate path *******");
    List<List<Integer>> resultList = new ArrayList<>();
    populatePath(treeNode, new ArrayList<>(), resultList);
    System.out.println(resultList.toString());

    System.out.println("******* Print all nodes in between ranges *******");
    PathSum pathSum = new PathSum();
    pathSum.printRange(treeNode, 2, 8);
    System.out.println(pathSum.rangeList);
  }

  public static void calcPaths(TreeNode node, ArrayList<Integer> path, int i, int sum, int actualSum) {
    if (node == null) {
      return;
    }
    path.add(i, node.getData());
    i++;
    actualSum = actualSum + node.getData();

    if (node.getLeft() == null && node.getRight() == null) {
      if (sum == actualSum) {
        printPath(path, i);
      }
    } else {
      if (node.getLeft() != null) {
        calcPaths(node.getLeft(), path, i, sum, actualSum);
      }

      if (node.getRight() != null) {
        calcPaths(node.getRight(), path, i, sum, actualSum);
      }
    }
  }

  public static void printPath(ArrayList<Integer> path, int pathLen) {
    for (int i = pathLen - 1; i >= 0; i--) {
      System.out.print(path.get(i) + " ");
    }
    System.out.println();
  }

  public static void populatePath(TreeNode node, ArrayList<Integer> path, List<List<Integer>> resultList) {
    if (node == null) {
      return;
    }

    //path[pathLen] = node.data;
    //pathLen++;
    path.add(node.data);

    if (node.left == null && node.right == null) {
      resultList.add(new ArrayList<>(path));
      //printPath(path, path);
    } else {
      if (node.left != null) {
        populatePath(node.left, path, resultList);
      }
      if (node.right != null) {
        populatePath(node.right, path, resultList);
      }
    }
    path.remove(path.size()-1);
  }

  /**
   * Given a
   * Binary Search Tree
   * and two numbers - a & b, return all the nodes in the tree that lie in the range [a .. b]. Your method should
   * return an ArrayList with the data of the qualifying nodes inserted in ascending order.
   *
   * Example:
   *  4
   * / \
   *2   8
   *   / \
   *  5  10
   *
   * Range (2,8) ==> [2, 4, 5, 8]
   *
   * Range includes 2 & 8
   */

  public ArrayList<Integer> rangeList = new ArrayList<>();

  public void printRange(TreeNode root, int a, int b) {
    if (root == null) {
      return;
    }
    if (root.data >= a) {
      printRange(root.left, a, b);
    }
    if (root.data >= a && root.data <= b) {
      rangeList.add(root.data);
    }
    if (root.data <= b) {
      printRange(root.right, a, b);
    }
  }
}
