import edu.princeton.cs.algs4.StdOut;

public class ThreeSumQuick {
    private int[] id;
    private int count;

    public ThreeSumQuick(int[] id) {
        this.id = id;
        this.count = 0;
    }

    public void sum() {
        int n = id.length;
        BinarySearch bs = new BinarySearch(id);
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (bs.search(-id[i] - id[j], i + 1, n - 1) != -1) count++;
            }
        }
    }

    public static void main(String[] args) {
        int[] list = {4, 3, 5, 6, 7, 43, 65, 23, 757, 767, 23, 426, 7, 32, 4, 6, 34, 76, 3, 4, 87, 96, 97, 346, 84, 9,
                6789, 6, 57, 545, 96, 3, -45, -35, -10, -7, -91, -8, -50, -100, -598, -63};
        ThreeSumQuick ts = new ThreeSumQuick(list);
        ts.sum();
        StdOut.println("Total sum equal to 0 group number : " + ts.count);
    }
}
