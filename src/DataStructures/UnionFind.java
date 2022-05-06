package DataStructures;

import java.util.Arrays;

public class UnionFind {

    private int[] id;
    private int[] size;
    private int count;

    /**
     * Initializes for n nodes
     *
     * @param n
     */
    public UnionFind(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
        size = new int[n];
        for (int i = 0; i < n; i++) {
            size[i] = 1;
        }
    }

    /**
     * Unions the component that p and q belong to
     *
     * @param p
     * @param q
     */
    void union(int p, int q) {
        if (p == q)
            return;
        int pRoot = find(p), qRoot = find(q);
        if (pRoot == qRoot)
            return;
        // balancing
        if (size[pRoot] < size[qRoot]) {
            id[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        } else {
            id[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        }
        count--;
    }

    /**
     * Finds the component to which p belong
     *
     * @param p
     * @return
     */
    int find(int p) {
        if (p != id[p])
            id[p] = find(id[p]);
        return id[p];
    }

    /**
     * Check if p and q belong to the same component
     *
     * @param p
     * @param q
     * @return
     */
    boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Returns the number of components
     *
     * @return
     */
    int getCount() {
        return count;
    }

    void describe() {
        System.out.format("count: %d\n", count);
        System.out.format("   id: %s\n", Arrays.toString(id));
        System.out.format(" size: %s\n", Arrays.toString(size));
        System.out.println();
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);
        uf.describe();

        uf.union(4, 3);
        uf.union(3, 8);
        uf.union(6, 5);
        uf.union(9, 4);
        uf.union(2, 1);
        uf.describe();

        System.out.format("8 and 9: %d, %d\n", uf.find(8), uf.find(9));
    }

}
