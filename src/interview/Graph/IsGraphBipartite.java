package interview.Graph;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode.com/problems/is-graph-bipartite/solution/
 *
 * Given an undirected graph, return true if and only if it is bipartite.
 *
 * Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that
 * every edge in the graph has one node in A and another node in B.
 *
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j
 * exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges:
 * graph[i] does not contain i, and it doesn't contain any element twice.
 *
 * Example 1:
 * Input: [[1,3], [0,2], [1,3], [0,2]]
 * Vertex 0 has edges to vertex 1and 3
 * Vertex 1 has edges to vertex 0 and 2
 * Vertex 2 has edges to vertex 1 and 3
 * Vertex 3 has edges to vertex 0 and 2
 * Output: true
 * Explanation:
 * The graph looks like this:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * We can divide the vertices into two groups: {0, 2} and {1, 3}.
 */
public class IsGraphBipartite {

  public static void main(String[] args) {
    System.out.println(isBipartite(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}}));
    System.out.println(isBipartite(new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}}));
  }

  public static boolean isBipartite(int[][] graph) {
    int n = graph.length;
    int[] color = new int[n];
    Arrays.fill(color, -1);

    for (int start = 0; start < n; ++start) {
      if (color[start] == -1) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        color[start] = 0;

        while (!stack.empty()) {
          Integer node = stack.pop();
          for (int nei : graph[node]) {
            if (color[nei] == -1) {
              stack.push(nei);
              color[nei] = color[node] ^ 1;
            } else if (color[nei] == color[node]) {
              return false;
            }
          }
        }
      }
    }

    return true;
  }
}
