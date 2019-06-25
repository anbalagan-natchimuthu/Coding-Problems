package interview.Graph;

import java.util.HashMap;
import java.util.Map;

public class FindCyclesInUndirectedGraph {

  static class Graph {

    int vertices, edges;
    Edge[] edgeArray;

    Graph(int vertices, int edges) {
      this.vertices = vertices;
      this.edges = edges;
      edgeArray = new Edge[edges];

      for (int i = 0; i < edges; i++) {
        edgeArray[i] = new Edge();
      }
    }

    class Edge {

      int src;
      int dest;
    }
  }

  public static int find(int[] parentArr, int node) {
    if (parentArr[node] == -1) {
      return node;
    }
    return find(parentArr, parentArr[node]);
  }

  public static void union(int[] parent, int x, int y) {
    //int xset = find(parent, x);
    // int yset = find(parent, y);
    // make X parent as Y
    parent[x] = y;
  }

  /**
   * Solution: 1 using Union-Find algorithm
   * https://www.geeksforgeeks.org/union-find/
   */
  public static boolean is_Cycle_Detected_Union_Find(Graph graph) {

    // Allocate memory for creating V subsets
    int[] parent = new int[graph.vertices];

    // Call makeSet() of disJointSet or use simple array to hold parents
    for (int i = 0; i < graph.vertices; i++) {
      parent[i] = -1;
    }

    // Iterate through all edges of graph, find subset of both vertices of every edge, if both subsets are same, then
    // there is cycle in graph.
    for (int i = 0; i < graph.edgeArray.length; i++) {
      int srcParent = find(parent, graph.edgeArray[i].src);
      int destParent = find(parent, graph.edgeArray[i].dest);

      if (srcParent == destParent) {
        return true;
      }
      union(parent, srcParent, destParent);
    }
    return false;
  }

  /**
   * Solution: 2 Using Union By Rank and Path compression algorithm
   * https://www.geeksforgeeks.org/union-find-algorithm-set-2-union-by-rank/
   */
  Map<Integer, SubSet> lookupMap = new HashMap<>();

  public boolean is_Cycle_Detected_Undirected_Graph(Graph graph) {

    // Iterate through all edges of graph, find subset of both vertices of every edge, if both subsets are same, then
    // there is cycle in graph.
    for (int i = 0; i < graph.edgeArray.length; i++) {
      SubSet src = makeSet(graph.edgeArray[i].src);
      SubSet dest = makeSet(graph.edgeArray[i].dest);

      SubSet srcParent = findSubSet(src);
      SubSet destParent = findSubSet(dest);

      if (srcParent == destParent) {
        return true;
      }
      unionSet(srcParent, destParent);
    }
    return false;
  }

  class SubSet {

    int data;
    SubSet parent;
    int rank;

    SubSet(int data) {
      this.data = data;
      parent = this;
      rank = 0;
    }
  }

  public SubSet makeSet(int vertex) {
    if (lookupMap.containsKey(vertex)) {
      return lookupMap.get(vertex);
    } else {
      SubSet subSet = new SubSet(vertex);
      lookupMap.put(vertex, subSet);
      return subSet;
    }
  }

  public SubSet findSubSet(SubSet data) {
    SubSet parent = data.parent;
    if (parent == data) {
      return parent;
    }
    data.parent = findSubSet(parent);
    return data.parent;
  }

  public void unionSet(SubSet subSet1, SubSet subSet2) {
    if (subSet1.rank >= subSet2.rank) {
      subSet1.rank = subSet1.rank == subSet2.rank ? subSet1.rank + 1 : subSet1.rank;
      subSet2.parent = subSet1;
    } else {
      subSet1.parent = subSet2;
    }
  }

  public static void main(String[] args) {
    FindCyclesInUndirectedGraph findCyclesInUndirectedGraph = new FindCyclesInUndirectedGraph();
        /* Let us create following graph
         0
        |  \
        |    \
        1-----2 */
    int V = 3, E = 3;
    Graph graph = new FindCyclesInUndirectedGraph.Graph(V, E);

    // add edge 0-1
    graph.edgeArray[0].src = 0;
    graph.edgeArray[0].dest = 1;

    // add edge 1-2
    graph.edgeArray[1].src = 1;
    graph.edgeArray[1].dest = 2;

    // add edge 0-2
    graph.edgeArray[2].src = 0;
    graph.edgeArray[2].dest = 2;

    findCyclesInUndirectedGraph.lookupMap.clear();
    if (is_Cycle_Detected_Union_Find(graph)) {
      System.out.println("graph contains cycle");
    } else {
      System.out.println("graph doesn't contain cycle");
    }

    if (findCyclesInUndirectedGraph.is_Cycle_Detected_Undirected_Graph(graph)) {
      System.out.println("graph contains cycle");
    } else {
      System.out.println("graph doesn't contain cycle");
    }

    V = 4;
    E = 3;
    Graph graph1 = new FindCyclesInUndirectedGraph.Graph(V, E);

    // add edge 0-1
    graph1.edgeArray[0].src = 0;
    graph1.edgeArray[0].dest = 1;

    // add edge 1-2
    graph1.edgeArray[1].src = 1;
    graph1.edgeArray[1].dest = 2;

    // add edge 0-3
    graph1.edgeArray[2].src = 0;
    graph1.edgeArray[2].dest = 3;

    findCyclesInUndirectedGraph.lookupMap.clear();
    if (findCyclesInUndirectedGraph.is_Cycle_Detected_Undirected_Graph(graph1)) {
      System.out.println("graph contains cycle");
    } else {
      System.out.println("graph doesn't contain cycle");
    }
  }
}
