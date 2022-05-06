package DataStructures;

public class SegmentTreeNode {

    // the node represents sum from start to end, i.e., [start, end]
    private int value;
    private int start;
    private int end;

    // the left and right children of the node
    private SegmentTreeNode left;
    private SegmentTreeNode right;

    public SegmentTreeNode(int sum, int start, int end,
                           SegmentTreeNode left, SegmentTreeNode right) {
        this.value = sum;
        this.start = start;
        this.end = end;
        this.left = left;
        this.right = right;
    }

    /**
     * Build a segment tree for vals from start to end, runs in O(n).
     *
     * @param vals  the numbers from which the tree will be constructed
     * @param start the start element
     * @param end   the end element
     * @return the root node of the segment tree
     */
    public static SegmentTreeNode build(int[] vals, int start, int end) {
        if (start == end)
            return new SegmentTreeNode(vals[start], start, end, null, null);
        int mid = (start + end) / 2;
        SegmentTreeNode left = build(vals, start, mid);
        SegmentTreeNode right = build(vals, mid + 1, end);
        return new SegmentTreeNode(left.value + right.value, start, end, left, right);
    }

    /**
     * Updates the segment tree at root for the value in index to the new value
     * val. Runs in O(lgn).
     *
     * @param root  the root of the segment tree
     * @param index the index of the item to be updated
     * @param val   the new value for the item at index
     */
    public static void update(SegmentTreeNode root, int index, int val) {
        if (root.start == root.end && root.end == index) {
            root.value = val;
            return;
        }
        int mid = (root.start + root.end) / 2;
        if (index <= mid) {
            update(root.left, index, val);
        } else {
            update(root.right, index, val);
        }
        root.value = root.left.value + root.right.value;
    }

    /**
     * Calculates the sum from start to end in a segment tree at root.
     * Runs in O(lgn).
     *
     * @param root  the root of the segment tree
     * @param start the index of the starting item
     * @param end   the index of the end item
     * @return the range sum [start, end]
     */
    public static int query(SegmentTreeNode root, int start, int end) {
        if (start > end) {
            return 0;
        }
        if (root.start == start && root.end == end) {
            return root.value;
        }
        int mid = (root.start + root.end) / 2;
        if (end <= mid) {
            return query(root.left, start, end);
        } else if (start > mid) {
            return query(root.right, start, end);
        } else {
            return query(root.left, start, mid) + query(root.right, mid + 1, end);
        }
    }

    public static void main(String[] args) {
        int[] numbers = {2, 1, 5, 3, 4};
        SegmentTreeNode root = build(numbers, 0, numbers.length - 1);
        update(root, 2, 8);
        System.out.printf("query(0, 1): %d\n", query(root, 0, 1));
        System.out.printf("query(0, 2): %d\n", query(root, 0, 2));
        System.out.printf("query(0, 3): %d\n", query(root, 0, 3));
        System.out.printf("query(1, 3): %d\n", query(root, 1, 3));
    }
}
