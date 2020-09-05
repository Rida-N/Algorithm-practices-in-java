import edu.princeton.cs.algs4.StdOut;

/****************************************************************************
 * Compilation: javac WeightedQuickUnionUF.java Execution: java
 * WeightedQuickUnionUF < input.txt Dependencies: StdIn.java StdOut.java
 *
 * Weighted quick-union (without path compression).
 *
 ****************************************************************************/


public class FindLargestNoTerminal {
    private int[] id; // id[i] = parent of i
    private int[] sz; // sz[i] = number of objects in subtree rooted at i
    private int count; // number of components
    private int[] largest;

    // Create an empty union find data structure with N isolated sets.
    public FindLargestNoTerminal(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        largest = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
            largest[i] = i;
        }
    }

    // Return the number of disjoint sets.
    public int count() {
        return count;
    }

    // Return component identifier for component containing p
    public int find(int p) {
        while (p != id[p]) {
            id[p] = id[id[p]]; // path compression
            p = id[p];
        }
        return p;
    }

    public int findLargest(int i) {
        return largest[find(i)];
    }

    // Are objects p and q in the same set?
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // Replace sets containing p and q with their union.
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j)
            return;

        // make smaller root point to larger one
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
            if (largest[i] > largest[j]) largest[j] = largest[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
            if (largest[i] < largest[j]) largest[i] = largest[j];
        }

        count--;
    }

    public static void main(String[] args) {
        // StdOut.println("please input a number");
        int N = 10;// StdIn.readInt();
        FindLargestNoTerminal uf = new FindLargestNoTerminal(N);
        int[][] test = {{2, 5}, {1, 7}, {7, 6}, {8, 9}, {2, 8}, {3, 0}};
        int targetIndex = test.length;
        // read in a sequence of pairs of integers (each in the range 0 to N-1),
        // calling find() for each pair: If the members of the pair are not already
        // call union() and print the pair.
        while (targetIndex-- > 0) { //!StdIn.isEmpty()
            int[] target = test[test.length - 1 - targetIndex];
            int p = target[0];// StdIn.readInt();
            int q = target[1];// StdIn.readInt();
            if (uf.connected(p, q))
                continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println("# components: " + uf.count());
        for (int i = 0; i < N; i++) {
            StdOut.print("id: " + i + ";   value :" + uf.id[i] + "   ");
            StdOut.print("sz: " + uf.sz[i] + "   ");
            StdOut.println("largest: " + uf.largest[i] + "   ");
        }
    }

}
