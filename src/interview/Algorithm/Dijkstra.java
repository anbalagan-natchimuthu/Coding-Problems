package interview.Algorithm;

/**
 * find SHORTEST / LONGEST path in UNDIRECTED GRAPH
 * https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
 * Given a graph and a source vertex in the graph, find shortest paths from source to all vertices in the given graph.
 */
public class Dijkstra {

    // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree
    //static final int V = 9;

    int minDistance(int dist[], Boolean sptSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < dist.length; v++) {
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }

        return min_index;
    }

    // A utility function to print the constructed distance array
    void printSolution(int dist[]) {
        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < dist.length; i++) {
            System.out.println(i + " tt " + dist[i]);
        }
    }

    // Funtion that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    void dijkstra(int graph[][], int src) {

        // The output array dist[i] will hold the shortest distance from src to i
        int dist[] = new int[graph.length];

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[graph.length];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < graph.length; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < graph.length; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < graph.length; v++)

            // Update dist[v] only if is not in sptSet, there is an
            // edge from u to v, and total weight of path from src to
            // v through u is smaller than current value of dist[v]
            {
                if (sptSet[v] == false && graph[u][v] != 0 &&
                    dist[u] != Integer.MAX_VALUE &&
                    dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        // print the constructed distance array
        printSolution(dist);
    }

    // Driver method
    public static void main(String[] args) {
        /* Let us create the example graph discussed above */
        int graph[][] = new int[][] {
            { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
            { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
            { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
            { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
            { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
            { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
            { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
            { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
            { 0, 0, 2, 0, 0, 0, 6, 7, 0 }
        };
        Dijkstra t = new Dijkstra();
        t.dijkstra(graph, 0);

        t.findLongestPath(graph, 0);
    }


    public void findLongestPath(int[][] graph, int src) {
        int[] longDist = new int[graph.length];
        boolean[] visited = new boolean[graph.length];

        for(int i = 0; i < longDist.length; i++) {
            longDist[i] = Integer.MIN_VALUE;
        }

        longDist[src] = 0;
        visited[src] = true;

        for (int i = 0; i < graph.length; i++) {
            int u = maxDistance(visited, longDist);

            visited[u] = true;

            for (int v = 0; v < graph.length; v++) {
                if (visited[v] == false && graph[u][v] != 0 && longDist[u] != Integer.MIN_VALUE &&
                longDist[v] < longDist[u] + graph[u][v]) {
                    longDist[v] = longDist[u] + graph[u][v];
                }
            }
        }

        // print the constructed distance array
        printSolution(longDist);
    }

    private int maxDistance(boolean[] visited, int[] dist) {
        int maxDist = Integer.MIN_VALUE, maxIndex = -1;

        for (int i = 0 ; i < dist.length; i++) {
            if (dist[i] > maxDist) {
                maxDist = dist[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}

