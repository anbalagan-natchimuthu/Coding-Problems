package interview.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by 212438472 on 4/1/18.
 */
public class BFSandDFS {

    private static HashMap<Integer, Node> nodeLookup = new HashMap<>();

    public static void main(String[] args) {
        BFSandDFS bfSandDFS = new BFSandDFS();

        bfSandDFS.addNode(30);
        bfSandDFS.addNode(10);
        bfSandDFS.addNode(40);
        bfSandDFS.addNode(50);
        bfSandDFS.addNode(20);
        bfSandDFS.addNode(70);
        bfSandDFS.addNode(80);
        bfSandDFS.addNode(90);

        bfSandDFS.addEdge(30, 40);
        bfSandDFS.addEdge(40, 20);
        bfSandDFS.addEdge(10, 40);
        bfSandDFS.addEdge(40, 10);
        bfSandDFS.addEdge(10, 50);
        bfSandDFS.addEdge(10, 50);
        bfSandDFS.addEdge(10, 80);
        bfSandDFS.addEdge(80, 90);
        bfSandDFS.addEdge(50, 70);

        System.out.println(bfSandDFS.hasPathDFS(30, 70));

        System.out.println(bfSandDFS.hasPathBFS(30, 70));
    }

    public static class Node {

        private int id;

        LinkedList<Node> adjacent = new LinkedList();

        private Node(int id) {
            this.id = id;
        }
    }

    private void addNode(int id) {
        Node node = new Node(id);
        nodeLookup.put(id, node);
    }

    private Node getNode(int id) {
        return nodeLookup.get(id);
    }

    public void addEdge(int source, int destination) {
        Node s = getNode(source);
        Node d = getNode(destination);
        s.adjacent.add(d);
    }

    public boolean hasPathDFS(int source, int destination) {
        Node s = getNode(source);
        Node d = getNode(destination);
        HashSet<Integer> visited = new HashSet<>();
        return hasPathDFS(s, d, visited);
    }

    private boolean hasPathDFS(Node source, Node destination, HashSet<Integer> visited) {
        // If already visited source node, then return false.
        if (visited.contains(source.id)) {
            return false;
        }

        visited.add(source.id);

        if (source == destination) {
            return true;
        }

        for (Node child : source.adjacent) {
            if (hasPathDFS(child, destination, visited)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPathBFS(int source, int destination) {
        Node s = getNode(source);
        Node d = getNode(destination);
        return hasPathBFS(s, d);
    }

    private boolean hasPathBFS(Node source, Node destination) {
        LinkedList<Node> nextToVisit = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        nextToVisit.add(source);

        while (!nextToVisit.isEmpty()) {
            Node node = nextToVisit.remove();
            if (node == destination) {
                return true;
            }

            if (visited.contains(node.id)) {
                continue;
            }
            visited.add(node.id);
            for (Node child : node.adjacent) {
                nextToVisit.add(child);
            }
        }
        return false;
    }
}
