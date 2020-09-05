import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class CheckAllConnected {
    private int[] id;
    private int[] sz;
    private int dataSize;
    boolean isAllConnected = false;

    public CheckAllConnected(int N) {
        id = new int[N];
        sz = new int[N];
        dataSize = N;
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int find(int p) {
        while (p != id[p])
            p = id[p];
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) {
            return;
        }
        if (sz[p] < sz[q]) {
            id[i] = j;
            sz[j] += sz[i];
            if (sz[j] == dataSize)
                isAllConnected = true;
        } else {
            id[j] = i;
            sz[i] += j;
            if (sz[i] == dataSize)
                isAllConnected = true;
        }
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        CheckAllConnected cc = new CheckAllConnected(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            String connectedDate = StdIn.readString();
            if (cc.connected(p, q)) {
                continue;
            }
            cc.union(p, q);
            if (cc.isAllConnected) {
                StdOut.println("# All menbers connected at : " + connectedDate);
            }
        }
    }
}
