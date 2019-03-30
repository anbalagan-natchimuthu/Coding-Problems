package interview.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a directed graph, check whether the graph contains a cycle or not
 *
 * https://www.geeksforgeeks.org/detect-cycle-in-a-graph/
 */
public class FindCyclesInDirectedGraph {

  class Graph {
    int vertices;
    List<List<Integer>> adj;

    public Graph(int vertices) {
      this.vertices = vertices;
      adj = new ArrayList<>();

      for (int i = 0; i < vertices; i++) {
        adj.add(new LinkedList<>());
      }
    }

    public void addEdge(int source, int dest) {
      adj.get(source).add(dest);
    }
  }

  // Mark all the vertices as not visited and not part of recursion stack
  public boolean isCyclic(Graph graph) {
    boolean[] visited = new boolean[graph.vertices];
    boolean[] recursionStack = new boolean[graph.vertices];

    for (int i = 0; i<graph.vertices; i++) {
      if (isCyclicUtil(i, visited, recursionStack, graph)) {
        return true;
      }
    }
    return false;
  }

  public boolean isCyclicUtil(int i, boolean[] visited, boolean[] recursionStack, Graph graph) {

    if (recursionStack[i]) {
      return true;
    }

    if (visited[i]) {
      return false;
    }

    recursionStack[i] = true;
    visited[i] = true;

    for (int children : graph.adj.get(i)) {
      if (isCyclicUtil(children, visited, recursionStack, graph)) {
        return true;
      }
    }
    recursionStack[i] = false;
    return false;
  }

  public static void main(String[] args)
  {
    FindCyclesInDirectedGraph f = new FindCyclesInDirectedGraph();
    Graph graph = f.new Graph(4);
    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(1, 2);
    graph.addEdge(2, 0);
    graph.addEdge(2, 3);
    graph.addEdge(3, 3);

    if(f.isCyclic(graph))
      System.out.println("Graph contains cycle");
    else
      System.out.println("Graph doesn't "
          + "contain cycle");
  }
}
