package DataStructures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class Edge {
    int from;
    int to;
    int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", weight=" + weight +
                '}';
    }
}

public class MinimumSpanningTree {

    public static ArrayList<Edge> primsMst(ArrayList<Integer>[] adjList, ArrayList<Integer>[] adjWeight, int start) {

        ArrayList<Edge> mst = new ArrayList<>();

        boolean[] added = new boolean[adjList.length];
        added[start] = true;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.weight));
        for (int i = 0; i < adjList[start].size(); i++) {
            pq.add(new Edge(start, adjList[start].get(i), adjWeight[start].get(i)));
        }

        while (mst.size() != adjList.length - 1 && !pq.isEmpty()) {
            Edge edge = pq.remove();
            if (added[edge.to])
                continue;
            // here we see the edge that we want, process as needed
            System.out.println(edge);
            mst.add(edge);
            // modifies seen and pq for next search
            added[edge.to] = true;
            for (int i = 0; i < adjList[edge.to].size(); i++) {
                pq.add(new Edge(start, adjList[edge.to].get(i), adjWeight[edge.to].get(i)));
            }
        }

        return mst;
    }

    public static ArrayList<Edge> kruskalMst(ArrayList<Edge> allEdges, int start, int numNodes) {
        ArrayList<Edge> mst = new ArrayList<>();

        UnionFind uf = new UnionFind(numNodes);
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.weight));
        pq.addAll(allEdges);

        while (mst.size() != numNodes - 1) {
            Edge edge = pq.remove();
            if (uf.connected(edge.from, edge.to))
                continue;
            // now this is the edge connecting two new subtrees
            System.out.println(edge);
            mst.add(edge);
        }

        return mst;
    }

    public static void main(String[] args) {

        int length = 7;
        ArrayList<Integer>[] adjList = new ArrayList[7];
        ArrayList<Integer>[] adjWeight = new ArrayList[7];
        ArrayList<Edge> mst1 = primsMst(adjList, adjWeight, 0);

        ArrayList<Edge> edges = new ArrayList<>();
        ArrayList<Edge> mst2 = kruskalMst(edges, 0, length);

    }

}
