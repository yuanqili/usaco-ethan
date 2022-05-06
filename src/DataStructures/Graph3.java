package DataStructures;

import java.util.*;

class Coordinate {

    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate[] around4() {
        return new Coordinate[]{
                new Coordinate(x + 1, y),
                new Coordinate(x - 1, y),
                new Coordinate(x, y + 1),
                new Coordinate(x, y - 1),
        };
    }

    public Coordinate[] around8() {
        return new Coordinate[]{
                new Coordinate(x - 1, y - 1),
                new Coordinate(x - 1, y),
                new Coordinate(x - 1, y + 1),
                new Coordinate(x, y - 1),
                new Coordinate(x, y + 1),
                new Coordinate(x + 1, y - 1),
                new Coordinate(x + 1, y),
                new Coordinate(x + 1, y + 1),
        };
    }

}

public class Graph3 {

    private static final Scanner scanner = new Scanner(System.in);

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

    public static int[][] readAdjMatrix3() {
        int nodes = scanner.nextInt();
        int[][] adjMatrix = new int[nodes][nodes];

        int edges = scanner.nextInt();
        for (int i = 0; i < edges; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int weight = scanner.nextInt();
            adjMatrix[from][to] = weight;
            adjMatrix[to][from] = weight;
        }

        return adjMatrix;
    }

    public static ArrayList<Integer>[] readAdjList() {
        var nodes = scanner.nextInt();
        ArrayList<Integer>[] adjList = new ArrayList[nodes];
        Arrays.setAll(adjList, element -> new ArrayList<>());

        for (int i = 0; i < nodes; i++) {
            var count = scanner.nextInt();
            for (int j = 0; j < count; j++) {
                var to = scanner.nextInt();
                adjList[i].add(to);
            }
        }

        return adjList;
    }

    public static void dfs(ArrayList<Integer>[] graph, int from) {
        int nodes = graph.length;
        boolean[] seen = new boolean[nodes];
        dfs(graph, from, seen);
    }

    public static void dfs(ArrayList<Integer>[] graph, int from, boolean[] seen) {
        seen[from] = true;
        // processing the node `from`
        // System.out.println(from);
        for (int neighbor : graph[from]) {
            if (!seen[neighbor]) {
                dfs(graph, neighbor, seen);
            }
        }
    }

    public static void dfsIterative(ArrayList<Integer>[] graph, int from) {
        int nodes = graph.length;
        boolean[] seen = new boolean[nodes];
        dfsIterative(graph, from, seen);
    }

    public static void dfsIterative(ArrayList<Integer>[] graph, int from, boolean[] seen) {
        seen[from] = true;
        Stack<Integer> toVisit = new Stack<>();
        toVisit.push(from);
        while (!toVisit.isEmpty()) {
            int node = toVisit.pop();
            // callback
            // node has been fully discovered
            // System.out.format("DFS is currently visiting node %d\n", node);
            for (int neighbor : graph[node]) {
                if (seen[neighbor])
                    continue;
                seen[neighbor] = true;
                toVisit.push(neighbor);
            }
        }
    }

    // implicit graph
    public static void dfsGrid(char[][] grid, Coordinate from, char mark, char free) {
        int height = grid.length;
        int width = grid[0].length;
        Stack<Coordinate> toVisit = new Stack<>();
        toVisit.push(from);
        grid[from.x][from.y] = mark;
        while (!toVisit.empty()) {
            Coordinate next = toVisit.pop();
            for (Coordinate neighbor : next.around4()) {
                int x = neighbor.x, y = neighbor.y;
                if (x >= 0 && x < height && y >= 0 && y < width && grid[x][y] == free) {
                    grid[x][y] = mark;
                    toVisit.push(neighbor);
                }
            }
        }
    }

    public static int dfsScc(char[][] grid, char interest) {
        int components = 0;
        int height = grid.length;
        int width = grid[0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == interest) {
                    dfsGrid(grid, new Coordinate(i, j), (char) ('1' + components), interest);
                    components++;
                }
            }
        }
        return components;
    }

    public static void main(String[] args) {

        int nodes = 5;

//        ArrayList<Integer>[] weights = new ArrayList[nodes];
//        ArrayList<Integer>[] graph = new ArrayList[nodes];
//        Arrays.setAll(graph, element -> new ArrayList<>());
//        graph[0].add(1);
//        graph[0].add(2);
//        graph[0].add(3);
//        graph[1].add(2);
//        graph[1].add(4);
//        graph[2].add(1);
//        graph[2].add(3);
//        graph[2].add(4);
//        graph[3].add(0);
//        graph[3].add(1);
//        graph[3].add(2);
//        graph[4].add(0);
//        graph[4].add(2);
//        dfsIterative(graph, 0);

        char[][] grid = {
                {'.', '.', 'X', '.', '.', 'X'},
                {'.', 'X', 'X', '.', '.', 'X'},
                {'X', '.', 'X', 'X', '.', 'X'},
                {'X', '.', '.', '.', '.', 'X'},
        };
        for (char[] row: grid) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println();
        dfsScc(grid, 'X');
        for (char[] row: grid) {
            System.out.println(Arrays.toString(row));
        }

    }


}
