package interview.Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * https://www.youtube.com/watch?v=ddTC4Zovtbc&list=PLrmLmBdmIlpu2f2g8ltqaaCZiq6GJvl1j
 * Generate topologically sorted order for directed acyclic graph.
 */
public class TopologicalSort {

  public static int[] sort(int[][] prerequisites, int noOfVertices) {

    Map<Integer, List<Integer>> dependencyTree = new HashMap<>();

    for (int[] pre : prerequisites) {
      List<Integer> values = dependencyTree.getOrDefault(pre[0], new ArrayList<>());
      values.add(pre[1]);
      dependencyTree.put(pre[0], values);
    }

    Set<Integer> visited = new HashSet<>();
    Stack<Integer> stack = new Stack<>();

    //dependencyTree.forEach((k, v) -> {
    for (int value = 0; value < noOfVertices; value++) {

      //for (int value : v) {
      if (!visited.contains(value)) {
        if (sortHelper(visited, stack, dependencyTree, value, new HashSet<>())) {
          return new int[]{};
        }
      }
      //}
    }

    int[] result = new int[noOfVertices];
    int i = 0;
    while (!stack.isEmpty()) {
      result[i++] = stack.pop();
    }
    return result;
  }

  private static boolean sortHelper(Set<Integer> visited, Stack<Integer> stack,
      Map<Integer, List<Integer>> dependencyTree, int currentNode, Set<Integer> recursion) {

    if (recursion.contains(currentNode)) {
      return true;
    }

    if (visited.contains(currentNode)) {
      return false;
    }

    recursion.add(currentNode);
    visited.add(currentNode);

    for (int child : dependencyTree.getOrDefault(currentNode, new ArrayList<>())) {
      if (sortHelper(visited, stack, dependencyTree, child, recursion)) {
        return true;
      }
    }

    stack.push(currentNode);
    recursion.remove(currentNode);
    return false;
  }

  public static void main(String[] args) {
    // 7 Edges, and 7 vertices
    int[][] inputArr = new int[][]{{0, 2}, {1, 2}, {1, 3}, {2, 4}, {3, 5}, {4, 5}, {5, 6}};

    int[] resArr = sort(inputArr, 7);
    System.out.println(Arrays.toString(resArr));
  }
}
