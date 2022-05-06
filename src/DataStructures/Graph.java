package DataStructures;

import java.util.*;

public class Graph {

    private int numVertices;
    private List<Integer>[] adj;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        this.adj = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++)
            adj[i] = new LinkedList<>();
    }

    void addEdge(int u, int v) {
        adj[u].add(v);
    }

    void dfs(int from) {
        boolean[] visited = new boolean[numVertices];
        dfs(from, visited);
    }

    void dfs(int from, boolean[] visited) {
        visited[from] = true;
        Stack<Integer> toVisit = new Stack<>();
        toVisit.push(from);
        while (!toVisit.empty()) {
            int next = toVisit.pop();
            System.out.println(next);
            for (int neighbor: adj[next]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    toVisit.push(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(10);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.dfs(0);
    }

}
