/****************************************************************************
 * Compilation: javac WeightedQuickUnionUF.java Execution: java
 * WeightedQuickUnionUF < input.txt Dependencies: StdIn.java StdOut.java
 *
 * Weighted quick-union (without path compression).
 *
 ****************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FindLargest {
    private int[] id; // id[i] = parent of i
    private int[] sz; // sz[i] = number of objects in subtree rooted at i
    private int count; // number of components
    private int[] largest;

    public FindLargest(int N) {
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
    public int findroot(int p) {
        while (p != id[p]) {
            id[p] = id[id[p]]; // path compression
            p = id[p];
        }
        return p;
    }

    public int find(int i) {
        return largest[findroot(i)];
    }

    // Are objects p and q in the same set?
    public boolean connected(int p, int q) {
        return findroot(p) == findroot(q);
    }

    // Replace sets containing p and q with their union.
    public void union(int p, int q) {
        int i = findroot(p);
        int j = findroot(q);
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
        int N = StdIn.readInt();
        FindLargest uf = new FindLargest(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
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
