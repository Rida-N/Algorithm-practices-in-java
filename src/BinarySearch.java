import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BinarySearch {
    private int[] id;
    // private int le;
    // private int mid;
    // private int ri;

    public BinarySearch(int[] list) {
        Arrays.sort(list);
        id = list;
        // le = 0;
        // mid = id.length / 2;
        // ri = id.length - 1;
    }

    public int search(int x, int le, int ri) {
        while (le <= ri) {
            int mid = (le + ri) / 2;
            if (id[mid] > x) {
                ri = mid - 1;
            } else if (id[mid] < x) {
                le = mid + 1;
            } else {
                StdOut.println("Found it in index " + mid + " of the sorted list !");
                for (int i : id)
                    StdOut.print(i + " ");
                return mid;
            }
        }
        StdOut.println("Not found");
        return -1;
    }

    public static void main(String[] args) {
        int[] list = {4, 3, 5, 6, 7, 43, 65, 23, 757, 767, 23, 426, 7, 32, 4, 6, 34, 76, 3, 4, 87, 96, 97, 346, 84, 9,
                6789, 6, 57, 545, 96, 3};
        BinarySearch bs = new BinarySearch(list);
        bs.search(57, 0, list.length - 1);
    }
}
