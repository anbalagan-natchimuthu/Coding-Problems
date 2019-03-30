package interview.Algorithm;

import java.util.Arrays;

/**
 * find SHORTEST / LONGEST path and NEGATIVE WEIGHT CYCLE in DIRECTED GRAPH
 * https://www.youtube.com/watch?v=-mOEd_3gTK0
 * Given a graph and a source vertex src in graph, find shortest paths from src to all vertices in the given graph.
 * The graph may contain negative weight edges
 * https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/
 */
public class BellmanFord {
    static class Graph {
        // A class to represent a weighted edge in graph
        class Edge {
            int src, dest, weight;

            Edge() {
                src = dest = weight = 0;
            }
        }

        int V, E;
        Edge[] edge;

        // Creates a graph with V vertices and E edges
        Graph(int v, int e) {
            V = v;
            E = e;
            edge = new Edge[e];
            for (int i = 0; i < e; ++i)
                edge[i] = new Edge();
        }

        // The main function that finds shortest distances from src
        // to all other vertices using Bellman-Ford algorithm.  The
        // function also detects negative weight cycle
        void BellmanFord(Graph graph, int src) {
            int V = graph.V, E = graph.E;
            int dist[] = new int[V];

            // Step 1: Initialize distances from src to all other
            // vertices as INFINITE
            for (int i = 0; i < V; ++i)
                dist[i] = Integer.MAX_VALUE;
            dist[src] = 0;

            // Step 2: Relax all edges |V| - 1 times. A simple
            // shortest path from src to any other vertex can
            // have at-most |V| - 1 edges
            for (int i = 1; i < V; ++i) {
                for (int j = 0; j < E; ++j) {
                    int u = graph.edge[j].src;
                    int v = graph.edge[j].dest;
                    int weight = graph.edge[j].weight;
                    if (dist[u] != Integer.MAX_VALUE &&
                        dist[u] + weight < dist[v])
                        dist[v] = dist[u] + weight;
                }
            }

            // Step 3: check for negative-weight cycles.  The above
            // step guarantees shortest distances if graph doesn't
            // contain negative weight cycle. If we get a shorter
            //  path, then there is a cycle.
            for (int j = 0; j < E; ++j) {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int weight = graph.edge[j].weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    System.out.println("Graph contains negative weight cycle");
                }
            }
            printArr(dist, V);
        }
    }

    // A utility function used to print the solution
    static void printArr(int dist[], int V) {
        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < V; ++i)
            System.out.println(i + "\t\t" + dist[i]);
    }

    // Driver method to test above function
    public static void main(String[] args) {
        int V = 5;  // Number of vertices in graph
        int E = 8;  // Number of edges in graph

        Graph graph = new Graph(V, E);

        // add edge 0-1 (or A-B in above figure)
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = -1;

        // add edge 0-2 (or A-C in above figure)
        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 4;

        // add edge 1-2 (or B-C in above figure)
        graph.edge[2].src = 1;
        graph.edge[2].dest = 2;
        graph.edge[2].weight = 3;

        // add edge 1-3 (or B-D in above figure)
        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 2;

        // add edge 1-4 (or A-E in above figure)
        graph.edge[4].src = 1;
        graph.edge[4].dest = 4;
        graph.edge[4].weight = 2;

        // add edge 3-2 (or D-C in above figure)
        graph.edge[5].src = 3;
        graph.edge[5].dest = 2;
        graph.edge[5].weight = 5;

        // add edge 3-1 (or D-B in above figure)
        graph.edge[6].src = 3;
        graph.edge[6].dest = 1;
        graph.edge[6].weight = 1;

        // add edge 4-3 (or E-D in above figure)
        graph.edge[7].src = 4;
        graph.edge[7].dest = 3;
        graph.edge[7].weight = -3;

        graph.BellmanFord(graph, 0);


        int inputGraph[][] = new int[][] {
           //0   1  2  3  4  5  6  7  8
            { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
            { 0, 0, 8, 0, 0, 0, 0, 11, 0 },
            { 0, 0, 0, 7, 0, 4, 0, 0, 2 },
            { 0, 0, 0, 0, 9, 14, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 10, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 2, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 6 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 7 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 }
        };
        findLongestPath(inputGraph, 0);
    }

    public static void findLongestPath(int[][] graph, int src) {
        int[] dist = new int[graph.length];
        Arrays.fill(dist, Integer.MIN_VALUE);

        dist[src] = 0;

        for (int i = 0; i <graph.length-1; i++) {
            for (int u = 0; u < graph.length; u++) {
                for (int v = 0; v < graph.length; v++) {
                    if (dist[u] != Integer.MIN_VALUE &&
                        graph[u][v] != 0 && dist[u] + graph[u][v] > dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }
        }

        for (int u = 0; u < graph.length; u++) {
            for (int v = 0; v < graph.length; v++) {
                if (graph[u][v] != 0 && dist[u] + graph[u][v] > dist[v]) {
                    System.out.println("Graph contains negative weight cycle");
                    return;
                }
            }
        }
        printArr(dist, graph.length);
    }
}
