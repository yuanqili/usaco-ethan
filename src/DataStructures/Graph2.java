package DataStructures;

import java.util.*;

class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate[] around4() {
        return new Coordinate[]{
                new Coordinate(x + 1, y),
                new Coordinate(x, y + 1),
                new Coordinate(x - 1, y),
                new Coordinate(x, y - 1),
        };
    }
}

public class Graph2 {

    final static Scanner scanner = new Scanner(System.in);

    /**
     * An adjacency matrix is given input as it is, 1 for adjacent and 0 for
     * non-adjacent.
     * <ul>
     *  <li>The first line contains a single integer n, the number of nodes.</li>
     *  <li>Each of the next n lines contains n space separated integers.</li>
     * </ul>
     * <pre>
     * 3
     * 0 1 0
     * 1 1 0
     * 0 1 1
     * </pre>
     */
    public static boolean[][] readAdjMatrix1() {
        int n = scanner.nextInt();
        boolean[][] adjMatrix = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjMatrix[i][j] = scanner.nextInt() == 1;
            }
        }
        return adjMatrix;
    }

    /**
     * An adjacency matrix is given input with edge weights. ie, Some value for
     * adjacent and 0 for non-adjacent.
     * <ul>
     *  <li>The first line contains a single integer n, the number of nodes.</li>
     *  <li>Each of the next n lines contains n space separated integers.</li>
     * </ul>
     * <pre>
     * 3
     * 0 10 5
     * 2 7 9
     * 3 2 0
     * </pre>
     */
    public static int[][] readAdjMatrix2() {
        int n = scanner.nextInt();
        int[][] adjMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjMatrix[i][j] = scanner.nextInt();
            }
        }
        return adjMatrix;
    }

    /**
     * A weighted Edge List is given input with multiple edges, i.e., single
     * pair of nodes can have multiple edges between them, and we want to keep
     * only min weight edge.
     * <ul>
     *  <li>
     *      The first line of each test case contains two space-separated
     *      integers N (total vertices) and M (total edges).
     *  </li>
     *  <li>
     *      Each of the next M lines contains three space-separated integers
     *      u, v and w denoting that vertices u and v are connected by an edge
     *      having weight w.
     *  </li>
     * </ul>
     * <pre>
     * 3 5
     * 0 3 10
     * 1 5 2
     * 3 7 -2
     * 0 3 2
     * 2 4 6
     * </pre>
     */
    public static int[][] readAdjMatrix3() {
        int n = scanner.nextInt();
        int[][] adjMatrix = new int[n][n];
        for (int[] row : adjMatrix) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        int edges = scanner.nextInt();
        for (int i = 0; i < edges; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int cost = scanner.nextInt();
            adjMatrix[from][to] = Math.min(adjMatrix[from][to], cost);
            adjMatrix[to][from] = adjMatrix[from][to];
        }

        return adjMatrix;
    }

    /**
     * An adjacency list is given input as it is, i.e., line number denotes
     * vertices, and numbers on those lines denote adjacent nodes.
     * <ul>
     *  <li>
     *      The first line contains a single integer N – the number of nodes.
     *  </li>
     *  <li>
     *      Each of the next N lines contains first number J and followed by J
     *      space separated integers. The i-th row denote i-th node, and j-th
     *      integer in the i-th row denotes an adjacent to that node.
     *  </li>
     * </ul>
     * <pre>
     * 3
     * 2 1 2
     * 1 2
     * 2 0 1
     * </pre>
     */
    public static ArrayList<Integer>[] readAdjList1() {
        int n = scanner.nextInt();
        ArrayList<Integer>[] adjList = new ArrayList[n];
        Arrays.setAll(adjList, element -> new ArrayList<>());

        for (int i = 0; i < n; i++) {
            int count = scanner.nextInt();
            for (int j = 0; j < count; j++) {
                int to = scanner.nextInt();
                adjList[i].add(to);
            }
        }

        return adjList;
    }

    public static void dfs(ArrayList<Integer>[] graph, int start, boolean[] seen) {
        seen[start] = true;
        Stack<Integer> toVisit = new Stack<>();
        while (!toVisit.empty()) {
            int node = toVisit.pop();
            for (int neighbor : graph[node]) {
                if (seen[neighbor])
                    continue;
                seen[neighbor] = true;
                toVisit.push(neighbor);
            }
        }
    }

    public static void bfs(ArrayList<Integer>[] graph, int start) {
        Deque<Integer> toVisit = new ArrayDeque<>();
        double[] dist = new double[graph.length];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        int[] pred = new int[graph.length];
        Arrays.fill(pred, -1);
        toVisit.offerLast(start);
        while (!toVisit.isEmpty()) {
            int next = toVisit.removeFirst();
            for (int neighbor : graph[next]) {
                if (dist[neighbor] != Double.POSITIVE_INFINITY)
                    continue;
                dist[neighbor] = dist[next] + 1;
                pred[neighbor] = next;
                toVisit.offerLast(neighbor);
            }
        }
    }

    /**
     * Consider a grid where certain cells can be visited (marked by the
     * character '.') and certain cannot be (marked by '#'), as in a labyrinth.
     * From any given cell, we can attempt to visit the four neighbouring cells,
     * except for those on the boundary, which have fewer neighbours. In the
     * following implementation, we use the grid itself to mark the visited
     * cells with the letter 'X'.
     *
     * @param grid the 2D grid
     * @param mark cell label for visited
     * @param free free cell can be reached
     */
    public static void dfsGrid(char[][] grid, int i, int j, char mark, char free) {
        int height = grid.length;
        int width = grid[0].length;
        Stack<Coordinate> toVisit = new Stack<>();
        toVisit.push(new Coordinate(i, j));
        grid[i][j] = mark;
        while (!toVisit.empty()) {
            Coordinate next = toVisit.pop();
            for (Coordinate neighbor : next.around4()) {
                if (neighbor.x >= 0 && neighbor.x < height
                        && neighbor.y >= 0 && neighbor.y < width
                        && grid[neighbor.x][neighbor.y] == free) {
                    grid[neighbor.x][neighbor.y] = mark;
                    toVisit.push(neighbor);
                }
            }
        }
    }

    public static int dfsScc(char[][] grid, char free) {
        int components = 0;
        int height = grid.length;
        int width = grid[0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == free) {
                    components += 1;
                    dfsGrid(grid, i, j, (char) ('1' + components), free);
                }
            }
        }
        return components;
    }

    public static void dfs(ArrayList<Integer>[] graph, int from, boolean[] seen, ArrayList<Integer> visited) {
        seen[from] = true;
        for (int to: graph[from]) {
            if (!seen[to])
                dfs(graph, to, seen, visited);
        }
        visited.add(from);
    }

    public static ArrayList<Integer> topological(ArrayList<Integer>[] graph) {
        int nodes = graph.length;
        boolean[] seen = new boolean[nodes]; // {false, false, ..., false}
        ArrayList<Integer> ordering = new ArrayList<>();
        for (int at = 0; at < nodes; at++) {
            if (!seen[at]) {
                ArrayList<Integer> visited = new ArrayList<>();
                dfs(graph, at, seen, visited);
                for (int node: visited) {
                    ordering.add(0, node);
                }
            }
        }
        return ordering;
    }

    public static void main(String[] args) {
    }

}
